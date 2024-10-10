import java.util.*;
import java.io.*;

/**
 * 상어가 0,0 으로 들어감, 방향은 기존 물고기의 방향과 동일
 *
 * 물고기의 이동
 * 물고기는 번호가 작은 물고기부터 순서대로 이동
 * 물고기는 한칸을 이동할 수 있음
 * - 이동할 수 있는 칸 : 빈칸과 다른 물고기가 있는 칸
 * - 이동할 수 없는 칸 : 상어가 있거나, 공간의 경계를 넘는 칸
 * 이동할 수 없는 경우 : 이동할 수 있는 칸을 향할 때까지 45도 회전, 이동할 수 있는 칸이 없으면 이동할지 않음
 * 물고기가 다른 물고기가 있는 칸으로 이동할 때는 서로의 위치를 바꾸는 방식으로 이동
 *
 * 상어의 이동
 * 물고기의 이동이 끝나고 한 후 상어가 이동
 * 상어는 방향에 있는 칸으로 이동할 수 있음
 * 물고기가 있는 칸으로 이동했다면 : 그 칸에 있는 물고기를 먹고, 그 물고기의 방향을 가짐
 * 이동하는 중에 지나는 칸에 있는 물고기는 먹지 않음
 * 물고기가 없는 칸으로는 이동할 수 없음
 * 상어가 이동할 수 있는 칸이 없으면 공간에서 벗어나 집으로 감
 */
public class Main {
    static final int dx[] = {0,-1,-1,0,1,1,1,0,-1};
    static final int dy[] = {0,0,-1,-1,-1,0,1,1,1};
    static class Fish {
        public int x, y;
        public int num;
        public int direction; // 1부터 시작 ↑, ↖, ←, ↙, ↓, ↘, →, ↗
        Fish(int x, int y, int n, int d) {
            this.x = x;
            this.y = y;
            num = n;
            direction = d;
        }
        public void turn() {
            direction = (direction == 8 ? 1 : direction+1);
        }
        public Fish(Fish oldFish) {
            this.x = oldFish.x;
            this.y = oldFish.y;
            this.num = oldFish.num;
            this.direction = oldFish.direction;
        }
    }
    static class Game {
        public Fish shark;
        public Fish fishes[][] = new Fish[4][4];
        public Fish fishList[] = new Fish[17];
        Game() {}
        Game(Game oldGame) {
            for(int i=0; i<4; i++) {
                for(int j=0; j<4; j++) {
                    if(oldGame.fishes[i][j] == null) continue;
                    fishes[i][j] = new Fish(oldGame.fishes[i][j]);
                    fishList[fishes[i][j].num] = fishes[i][j];
                }
            }
        }
        public void addFish(int x, int y, int num, int dir) {
            fishes[x][y] = new Fish(x,y,num,dir);
            fishList[num] = fishes[x][y];
        }
        public void start(int x, int y) {
            // 상어 등장
            shark = fishes[x][y];
            fishList[shark.num] = null; // 잡아먹힘

            // 물고기 이동
            for (int i = 1; i <= 16; i++) {
                if (fishList[i] == null) continue; // 물고기가 이미 먹힌 경우
                Fish curFish = fishList[i];
                for (int d = 0; d < 8; d++) {
                    int nextX = curFish.x + dx[curFish.direction];
                    int nextY = curFish.y + dy[curFish.direction];
                    if (nextX < 0 || nextY < 0 || nextX >= 4 || nextY >= 4 || fishes[nextX][nextY] == shark) {
                        curFish.turn();
                        continue;
                    }
                    if (fishes[nextX][nextY] != null) { // 물고기 있을 경우 위치 바꾸기
                        Fish tmpFish = fishes[nextX][nextY];
                        fishes[curFish.x][curFish.y] = tmpFish;
                        fishes[nextX][nextY] = curFish;
                        tmpFish.x = curFish.x;
                        tmpFish.y = curFish.y;
                        curFish.x = nextX;
                        curFish.y = nextY;
                    } else { // 물고기 이동
                        fishes[nextX][nextY] = curFish;
                        fishes[curFish.x][curFish.y] = null;
                        curFish.x = nextX;
                        curFish.y = nextY;
                    }
                    break;
                }
            }

            int maxShark = 0;
            // 상어 이동
            for(int i=1; i<4; i++) {
                int nextX = shark.x + dx[shark.direction] * i;
                int nextY = shark.y + dy[shark.direction] * i;
                if (nextX < 0 || nextY < 0 || nextX >= 4 || nextY >= 4) {
                    break;
                }
                if(fishes[nextX][nextY] != null) {
                    fishes[x][y] = null;
                    Game newGame = new Game(this);
                    newGame.start(nextX, nextY);
                    maxShark = Math.max(newGame.shark.num, maxShark);
                    fishes[x][y] = shark;
                }
            }
            shark.num += maxShark;
        }
    }
    static Game game = new Game();
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i=0; i<4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<4; j++) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                game.addFish(i,j,a,b);
            }
        }
        game.start(0, 0);
        System.out.println(game.shark.num);
    }
}

import java.io.*;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/18809
 * 배양액을 뿌릴 수 있는 땅은 미리 정해져 있음
 * 배양액을 뿌릴 수 없는땅 1, 배양액을 뿌릴 수 있는 땅 2, 호수 0
 * 초록 배양액과 빨간 배양액이 동일한 시간에 도달한 땅에서 꽃이 피고 배양액의 확산을 더 하지 않음
 * 주어진 모든 배양액을 사용해야함
 */
public class Main {
    static final int LAKE = Integer.MIN_VALUE, FLOWER = Integer.MIN_VALUE + 1;
    static int N, M, G, R;
    static int maxBloom = 0;
    static int garden[][];
    static List<Position> availablePos;
    static List<Position> greenPos = new ArrayList<>(), redPos = new ArrayList<>();
    static class Position {
        int x,y;
        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static void combination(int idx, int gCnt, int rCnt) {
        if(gCnt == G && rCnt == R) {
            maxBloom = Math.max(maxBloom, bfs());
            return;
        }
        if(availablePos.size() - idx < (G-gCnt) + (R-rCnt)) return;
        for(int i=idx; i<availablePos.size(); i++) {
            if(gCnt < G) {
                greenPos.add(availablePos.get(i));
                combination(i+1, gCnt+1, rCnt);
                greenPos.remove(greenPos.size()-1);
            }
            if(rCnt < R) {
                redPos.add(availablePos.get(i));
                combination(i+1, gCnt, rCnt+1);
                redPos.remove(redPos.size()-1);
            }
        }
    }
    static final int dx[] = {1,-1,0,0}, dy[] = {0,0,1,-1};
    static int bfs() {
        int flowerCnt = 0;
        int curGarden[][] = new int[N][M];
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                curGarden[i][j] = garden[i][j];
            }
        }
        for(Position p : greenPos) {
            curGarden[p.x][p.y] = 1;
        }
        for(Position p : redPos) {
            curGarden[p.x][p.y] = -1;
        }
        Queue<Position> seedQ = new LinkedList<>(greenPos);
        seedQ.addAll(redPos);
        Position curPos = null;
        while((curPos = seedQ.poll()) != null) {
            if(curGarden[curPos.x][curPos.y] == FLOWER) continue;
            // 확산 시도
            for(int d=0; d<4; d++) {
                int nx = curPos.x + dx[d], ny = curPos.y + dy[d];
                if(nx < 0 || N <= nx || ny < 0 || M <= ny) continue;
                if(curGarden[nx][ny] == LAKE) continue;
                if(curGarden[nx][ny] == FLOWER) continue;
                if(curGarden[nx][ny] == 0) {
                    curGarden[nx][ny] = curGarden[curPos.x][curPos.y] + (curGarden[curPos.x][curPos.y] > 0 ? 1 : -1);
                    seedQ.add(new Position(nx, ny));
                }
                else if(curGarden[nx][ny] + curGarden[curPos.x][curPos.y] - 1 == 0) { // 씨가 생기는 경우
                    curGarden[nx][ny] = FLOWER;
                    flowerCnt++;
                }
            }
        }
        return flowerCnt;
    }
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        garden = new int[N][M];
        availablePos = new ArrayList<>(10);
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                switch (Integer.parseInt(st.nextToken())) {
                    case 0 : {
                        garden[i][j] = LAKE;
                        break;
                    }
                    case 1 : {
                        garden[i][j] = 0;
                        break;
                    }
                    case 2 : {
                        garden[i][j] = 0;
                        availablePos.add(new Position(i,j));
                        break;
                    }
                }
            }
        }

        combination(0, 0,0);
        System.out.println(maxBloom);
    }
}

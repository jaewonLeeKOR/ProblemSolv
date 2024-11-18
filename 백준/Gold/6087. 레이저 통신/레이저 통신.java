import java.io.*;
import java.util.*;

public class Main {
    static final boolean WALL = true;
    static final int dx[] = {1,0,-1,0}, dy[] = {0,1,0,-1};
    static int W, H;
    static int minMirror = Integer.MAX_VALUE;
    static int startX=-1, startY=-1, endX=-1, endY=-1;
    static boolean walls[][];
    static int visited[][][];
    static class Status {
        int x, y, dir;
        Status(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }
    static void bfs() {
        Queue<Status> q = new LinkedList<>();
        q.add(new Status(startX, startY, 0));
        q.add(new Status(startX, startY, 1));
        q.add(new Status(startX, startY, 2));
        q.add(new Status(startX, startY, 3));
        Status status = null;
        while((status = q.poll()) != null) {
            if(status.x == endX && status.y == endY) {
                minMirror = Math.min(minMirror, visited[status.x][status.y][status.dir]);
                continue;
            }
            int frontX = status.x + dx[status.dir], frontY = status.y + dy[status.dir];
            if(!(frontX < 0 || W <= frontX || frontY < 0 || H <= frontY) && !walls[frontX][frontY]) {
                if (visited[frontX][frontY][status.dir] > visited[status.x][status.y][status.dir]) {
                    visited[frontX][frontY][status.dir] = visited[status.x][status.y][status.dir];
                    q.add(new Status(frontX,frontY,status.dir));
                }
            }

            int leftDir = (status.dir + 3) % 4;
            int leftX = status.x + dx[leftDir], leftY = status.y + dy[leftDir];
            if(!(leftX < 0 || W <= leftX || leftY < 0 || H <= leftY) && !walls[leftX][leftY]) {
                if (visited[leftX][leftY][leftDir] > visited[status.x][status.y][status.dir]) {
                    visited[leftX][leftY][leftDir] = visited[status.x][status.y][status.dir] + 1;
                    q.add(new Status(leftX,leftY,leftDir));
                }
            }

            int rightDir = (status.dir + 1) % 4;
            int rightX = status.x + dx[rightDir], rightY = status.y + dy[rightDir];
            if(!(rightX < 0 || W <= rightX || rightY < 0 || H <= rightY) && !walls[rightX][rightY]) {
                if (visited[rightX][rightY][rightDir] > visited[status.x][status.y][status.dir]) {
                    visited[rightX][rightY][rightDir] = visited[status.x][status.y][status.dir] + 1;
                    q.add(new Status(rightX,rightY,rightDir));
                }
            }
        }
    }
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        walls = new boolean[W][H];
        visited = new int[W][H][4];
        for(int i=0; i<W; i++) {
            for(int j=0; j<H; j++) {
                Arrays.fill(visited[i][j], Integer.MAX_VALUE);
            }
        }
        for(int i=0; i<H; i++) {
            String tmpS = br.readLine();
            for(int j=0; j<W; j++) {
                switch (tmpS.charAt(j)) {
                    case('*') : {
                        walls[j][i] = true;
                        break;
                    }
                    case('C') : {
                        if(startX == -1) {
                            startX = j;
                            startY = i;
                        }
                        else {
                            endX = j;
                            endY = i;
                        }
                        walls[j][i] = false;
                        break;
                    }
                    default:
                        walls[j][i] = false;
                }
            }
        }
        visited[startX][startY][0] = 0;
        visited[startX][startY][1] = 0;
        visited[startX][startY][2] = 0;
        visited[startX][startY][3] = 0;
        bfs();
        System.out.println(minMirror);
    }
}

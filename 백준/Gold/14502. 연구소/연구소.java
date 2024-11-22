import java.io.*;
import java.util.*;

/**
 * 바이러스는 상하좌우 인접한 빈칸으로 모두 퍼져나갈 수 있음
 * 새로 세울 수 있는 벽 3개
 * 얻을 수 있는 안전 영역 크기의 최댓값을 구하자
 */
public class Main {
    static int dx[] = {1,0,-1,0}, dy[] = {0,1,0,-1};
    static int N, M;
    static boolean walls[][];
    static boolean viruses[][];
    static int safeZoneCnt;
    static int maxSafeZone;
    static class Position {
        int x, y;
        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int propagation() {
        int newVirusCnt = 0;
        boolean newViruses[][] = new boolean[N][M];

        Queue<Position> q = new LinkedList<>();
        Position p = null;
        for(int x=0; x<N; x++) {
            for(int y=0; y<M; y++) {
                if(viruses[x][y]) {
                    p = new Position(x, y);
                    q.add(p);
                }
            }
        }
        while((p = q.poll()) != null) {
            for(int i=0; i<4; i++) {
                int nx = p.x + dx[i], ny = p.y + dy[i];
                if(nx < 0 || N <= nx || ny < 0 || M <= ny) continue;
                if(walls[nx][ny] || viruses[nx][ny] || newViruses[nx][ny]) continue;
                newVirusCnt++;
                newViruses[nx][ny] = true;
                q.add(new Position(nx, ny));
            }
        }
        return newVirusCnt;
    }
    static void func(int lastX, int lastY, int newWallCnt) {
        if(newWallCnt == 3) {
            maxSafeZone = Math.max(maxSafeZone, safeZoneCnt - 3 - propagation());
            return;
        }
        for(int x=lastX; x<N; x++) {
            for(int y=(x==lastX ? lastY : 0); y<M; y++) {
                if(!walls[x][y] && !viruses[x][y]) {
                    walls[x][y] = true;
                    func(x,y,newWallCnt+1);
                    walls[x][y] = false;
                }
            }
        }
    }
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        walls = new boolean[N][M];
        viruses = new boolean[N][M];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                switch(st.nextToken()) {
                    case "1" : {
                        walls[i][j] = true;
                        break;
                    }
                    case "2" : {
                        viruses[i][j] = true;
                        break;
                    }
                    default : {
                        safeZoneCnt++;
                        break;
                    }
                }
            }
        }
        func(0,0,0);
        System.out.println(maxSafeZone);
    }
}

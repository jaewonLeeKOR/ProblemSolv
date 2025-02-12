import java.io.*;
import java.util.*;

public class Main {
    static final int dx[] = {1,-1,0,0}, dy[] = {0,0,1,-1};
    static int N, M, cheeseCnt = 0;
    static int cheeses[][];
    static boolean visited[][];
    static int exposed[][];
    static class Position {
        int x, y;
        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        cheeses = new int[N+2][M+2];
        visited = new boolean[N+2][M+2];
        exposed = new int[N+2][M+2];
        for(int i=1; i<N+1; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<M+1; j++) {
                if(st.nextToken().equals("1")) {
                    cheeses[i][j] = 1;
                    cheeseCnt++;
                }
            }
        }
        int time = 0;
        Queue<Position> q = new LinkedList<>();
        while(cheeseCnt > 0) {
            time++;
            for(boolean[] v : visited) Arrays.fill(v, false);
            for(int[] e : exposed) Arrays.fill(e,0);
            q.clear();
            q.add(new Position(0,0));
            visited[0][0] = true;
            Position curPos = null;
            while((curPos = q.poll()) != null) {
                int x = curPos.x, y = curPos.y;
                for(int d=0; d<4; d++) {
                    int nx = x + dx[d], ny = y + dy[d];
                    if(nx < 0 || N+2 <= nx || ny < 0 || M+2 <= ny) continue;
                    if(visited[nx][ny]) continue;
                    if(cheeses[nx][ny] == 1 || cheeses[nx][ny] == -1 * time) {
                        // 산소 노출
                        exposed[nx][ny]++;
                        if (exposed[nx][ny] == 2) {
                            cheeseCnt--;
                            cheeses[nx][ny] = -1 * time;
                        }
                    }
                    else {
                        visited[nx][ny] = true;
                        q.add(new Position(nx, ny));
                    }
                }
            }
        }
        System.out.println(time);
    }
}

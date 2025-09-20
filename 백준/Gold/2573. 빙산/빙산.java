import java.io.*;
import java.util.*;

/**
 * 빙산이 분리되는 최초의 시간, 빙산이 다 녹을떄까지 분리되지 않으면 0
 */
public class Main {
    private static short N, M;
    private static byte[][] ices;
    private static boolean[][] visited;
    private static final int[] dx = {1,0,-1,0};
    private static final int[] dy = {0,1,0,-1};

    static class Tuple {
        short x, y;
        public Tuple(short x, short y) {
            this.x = x;
            this.y = y;
        }
    }

    private static int aging() {
        int groupCnt = 0;
        visited = new boolean[N][M];
        byte[][] meltingIces = new byte[N][M];
        for (short i = 0; i < N; i++) {
            for (short j = 0; j < M; j++) {
                if (visited[i][j]) continue;
                visited[i][j] = true;
                if(ices[i][j] == 0) continue;
                groupCnt++;
                LinkedList<Tuple> queue = new LinkedList<>();
                queue.addLast(new Tuple(i,j));
                while(!queue.isEmpty()) {
                    Tuple cur = queue.removeFirst();
                    short x = cur.x, y = cur.y;
                    byte cnt = 0;
                    for (short d = 0; d < 4; d++) {
                        if(x + dx[d] < 0 || x + dx[d] >= N || y + dy[d] < 0 || y + dy[d] >= M) continue;
                        if(ices[x + dx[d]][y + dy[d]] == 0) {
                            cnt++;
                        }
                    }
                    meltingIces[x][y] = cnt;
                    for (short d = 0; d < 4; d++) {
                        if(x + dx[d] < 0 || x + dx[d] >= N || y + dy[d] < 0 || y + dy[d] >= M) continue;
                        if(ices[x + dx[d]][y + dy[d]] == 0) continue;
                        if(visited[x + dx[d]][y + dy[d]]) continue;
                        visited[x + dx[d]][y + dy[d]] = true;
                        queue.addLast(new Tuple((short) (x + dx[d]), (short) (y + dy[d])));
                    }
                }
            }
        }
        for (short i = 0; i < N; i++) {
            for (short j = 0; j < M; j++) {
                ices[i][j] = (byte) Math.max(ices[i][j] - meltingIces[i][j], 0);
            }
        }
        return groupCnt;
    }

    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Short.parseShort(st.nextToken());
        M = Short.parseShort(st.nextToken());
        ices = new byte[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                ices[i][j] = Byte.parseByte(st.nextToken());
            }
        }
        int time = 0, groupCnt = 0;
        while (true) {
            groupCnt = aging();

            if (groupCnt >= 2) {
                System.out.println(time);
                break;
            }
            if (groupCnt == 0) {
                System.out.println(0);
                break;
            }
            time++;
        }
    }
}

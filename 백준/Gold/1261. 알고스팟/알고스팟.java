import java.io.*;
import java.util.*;

/**
 * x, y에 있을때 -> 상하좌우로
 * 알고 스팟 무기를 이용하면 벽을 부술 수 있음 -> 벽을 부수면 빈방과 동일한 방으로 변함
 * <p>
 * dp -> 현재 x, y, 벽뿌수고 온 개수
 */
public class Main {
    static int n, m;
    static final int dx[] = {1, -1, 0, 0};
    static final int dy[] = {0, 0, 1, -1};
    static final int UNVISITED = Integer.MAX_VALUE;
    static int dp[][] = new int[100][100];
    static boolean walls[][] = new boolean[100][100];

    static void dfs(int x, int y, int prev) {
        if (x<0 || n<=x || y<0 || m<=y) {
            return;
        }
        int cur = prev + (walls[x][y] ? 1 : 0);
        if(dp[x][y] <= cur) {
            return;
        }
        dp[x][y] = cur;
        for(int i=0; i<4; i++) {
            dfs(x+dx[i], y+dy[i], cur);
        }
    }

    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        for (int j = 0; j < m; j++) {
            String tmp = br.readLine();
            for (int i = 0; i < n; i++) {
                walls[i][j] = tmp.charAt(i) == '1';
            }
        }
        for (int[] tmp : dp) {
            for (int i = 0; i < 100; i++) {
                tmp[i] = UNVISITED;
            }
        }

        dfs(0,0,0);

        System.out.println(dp[n-1][m-1]);
    }
}
import java.io.*;
import java.util.*;

public class Main {
    static int N, roads[][];
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        roads = new int[N][N];
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                roads[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i=0; i<N; i++) { // 기준
            for(int k=0; k<N; k++) { // y
                if(i == k) continue;
                for(int j=k+1; j<N; j++) { // x
                    if(i == j) continue;
                    if(Math.abs(roads[j][k]) == Math.abs(roads[j][i]) + Math.abs(roads[i][k])) {
                        roads[k][j] = roads[j][k] = (roads[j][k] > 0 ? -1 * roads[j][k] : roads[j][k]);
                    }
                    else if(Math.abs(roads[j][k]) > Math.abs(roads[j][i]) + Math.abs(roads[i][k])) {
                        System.out.println(-1);
                        return;
                    }
                }
            }
        }
        int sum = 0;
        for(int[] road : roads)
            for(int r : road)
                sum += Math.max(r, 0);
        System.out.println(sum >> 1);
    }
}
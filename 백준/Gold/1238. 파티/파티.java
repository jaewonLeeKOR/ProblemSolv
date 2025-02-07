import java.io.*;
import java.util.*;

/**
 * N명의 학생이 X번 마을에 모여서 파티를 벌인다
 * 마을 사이에 총 M개의 단방향 도로들, i번째 길을 지나는데 Ti의 시간 소비
 * 단방향의 도로들, N명의 학생 중 오고 가는데 가장 많은 시간을 소비하는 학생의 소요시간 반환
 */
public class Main {
    static final int INF = Integer.MAX_VALUE / 2;
    static int N, M, X;
    static int roads[][];
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        roads = new int[N+1][N+1];
        for(int[] road : roads) {
            Arrays.fill(road, INF);
        }

        for(int i=0; i<M; i++) {
            int s, e, d;
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            roads[s][e] = d;
        }

        for(int i=0; i<N+1; i++) {
            roads[i][i] = 0;
        }

        for(int j=1; j<N+1; j++) {
            for(int i=1; i<N+1; i++) {
                for(int k=1; k<N+1; k++) {
                    if(roads[i][j] + roads[j][k] >= roads[i][k]) continue;
                    roads[i][k] = roads[i][j] + roads[j][k];
                }
            }
        }

        int maxTime = Integer.MIN_VALUE;
        for(int i=1; i<N+1; i++) {
            maxTime = Math.max(maxTime, roads[i][X] + roads[X][i]);
        }
        System.out.println(maxTime);
    }
}

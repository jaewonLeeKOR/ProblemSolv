import java.io.*;
import java.util.*;

/**
 * 고속도로의 휴게소 N개, 고속도로의 시작으로부터 얼만큼 떨어져있는지로 주어짐
 * M개를 더 세우려 함
 * 휴게소는 이미 있는곳, 끝에는 세울 수 없음
 *
 * 휴게소가 없는 구간의 길이의 최댓값을 최대로 해야함
 */
public class Main {
    static int N, M, L;
    static int[] station;
    static int[] stationInterval;
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        station = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            station[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(station);

        stationInterval = new int[N+1];
        int prevStation = 0;
        for(int i=0; i<N; i++) {
            stationInterval[i] = station[i] - prevStation;
            prevStation = station[i];
        }
        stationInterval[N] = L - prevStation;
        Arrays.sort(stationInterval);

        int low=1, high=stationInterval[N], mid;
        while(low < high) {
            mid = (low + high) / 2;
            int count = 0;
            for(int i=0; i<=N; i++) {
                int newStation = stationInterval[i] / mid; // mid 간격으로 몇개의 추가 휴게소가 들어갈 수 있는지
                if((stationInterval[i]) % mid == 0)
                    newStation--; // 휴게소간 간격이 mid로 나누어 떨어지면 한개의 추가 휴게소는 기존 휴게소와 겹치므로 뺴줌
                count += newStation;
            }
            if(count <= M) {
                high = mid;
            }
            else {
                low = mid + 1;
            }
        }
        System.out.println(low);
    }
}

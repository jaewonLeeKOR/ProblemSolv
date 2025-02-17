import java.io.*;
import java.util.*;

/**
 * M종류의 1인승 놀이기구를 N명의 아이가 모두 탈때 마지막 아이가 타는 놀이기구 번호
 */
public class Main {
    static int N, M;
    static long[] times;
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        times = new long[M];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++) {
            times[i] = Long.parseLong(st.nextToken());
        }
        if(N <= M) {
            System.out.println(N);
            return;
        }
        long low = 1, high = N * 30L, mid; // N명이 놀이기구를 타는데 걸리는 시간
        while(low < high) {
            mid = (low + high) / 2;
            if(N <= capacityForTime(mid)) high = mid;
            else low = mid+1;
        }
        long timeToEnd = low;
        long alreadyPlayedPerson = capacityForTime(timeToEnd - 1), rideNum=-1;
        for(int i=0; i<M; i++) {
            if(timeToEnd % times[i] == 0) alreadyPlayedPerson++; // timeToEnd 시각에 마지막으로 탑승하는 아이와 함께 탑승하는 경우
            if(alreadyPlayedPerson == N) {
                rideNum = i+1;
                break;
            }
        }
        System.out.println(rideNum);
    }
    static long capacityForTime(long time) {
        long res = M;
        for(int i=0; i<M; i++) {
            res += time / times[i];
        }
        return res;
    }
}
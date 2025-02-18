import java.io.*;
import java.util.*;

public class Main {
    static int K, N;
    static int[] lanCables;
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        lanCables = new int[K];
        for(int i=0; i<K; i++) {
            lanCables[i] = Integer.parseInt(br.readLine());
        }
        long low = 1, high = Integer.MAX_VALUE + 1L, mid;
        while(low + 1 < high) {
            mid = (low + high) >> 1;
            long cnt = 0;
            for(int lanCable : lanCables) {
                cnt += lanCable / mid;
            }
            if(cnt >= N) low = mid;
            else high = mid;
        }
        System.out.println(low);
    }
}

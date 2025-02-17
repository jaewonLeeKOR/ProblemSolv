import java.io.*;
import java.util.*;

public class Main {
    static long N, K;
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        // 10_000_000_000
        long left=1, right=N*N, mid; // B[k]를 매개변수 탐색
        while(left < right) {
            mid = (left + right) / 2;
            long count = 0;
            for(int i=1; i<=N; i++) {
                count += Math.min(mid/i, N);
            }
            if(K <= count) {
                right = mid;
            }
            else {
                left = mid + 1;
            }
        }
        System.out.println(left);
    }
}

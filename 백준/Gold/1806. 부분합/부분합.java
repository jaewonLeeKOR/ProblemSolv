import java.io.*;
import java.util.*;

public class Main {
    static int N, S, min = Integer.MAX_VALUE;
    static List<Long> nums = new ArrayList<>(100_000);
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums.add(Long.parseLong(st.nextToken()));
        }
        int i = 0, j = 0;
        long sum = 0;
        while (j <= N) {
            if (sum < S) {
                if(j >= N) break;
                sum += nums.get(j++);
            } else {
                min = Math.min(min, j-i);
                sum -= nums.get(i++);
            }
        }
        System.out.println(min == Integer.MAX_VALUE ? 0 : min);
    }
}

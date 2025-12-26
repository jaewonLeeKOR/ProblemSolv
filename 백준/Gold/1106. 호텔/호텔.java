import java.io.*;
import java.util.*;

/**
 * 호텔의 고객을 적어도 C명 늘이기 위한 최소한의 투자금
 */
public class Main {
    static int C, N, minCost = Integer.MAX_VALUE;
    static Ad ads[];
    static Map<Integer, Integer> memo;
    static class Ad {
        int cost, count;
        Ad(int cost, int count) {
            this.cost = cost;
            this.count = count;
        }
    }
    static int func(int remain) {
        if(remain <= 0) return 0;
        if(memo.get(remain) != null) return memo.get(remain);
        int minC = Integer.MAX_VALUE;
        for (Ad ad : ads) {
            minC = Math.min(minC, ad.cost + func(remain - ad.count));
        }
        memo.put(remain, minC);
        return minC;
    }
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        ads = new Ad[N];
        memo = new HashMap<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            ads[i] = new Ad(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        System.out.println(func(C));
    }
}

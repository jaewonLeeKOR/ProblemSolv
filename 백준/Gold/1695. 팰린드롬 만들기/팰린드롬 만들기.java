import java.io.*;
import java.util.*;

/**
 * 주어진 수열에 최소의 수를 끼워 펠린드롬 만들기
 */
public class Main {
    static int N;
    static int nums[];
    static int dp[][];
    static int func(int l, int r) {
        int res;
        if(dp[l][r] != Integer.MAX_VALUE) { // 메모이제이션
            return dp[l][r];
        }
        if(l >= r) { // 펠린드롬이 완성된 경우
            res = 0;
        }
        else if(nums[l] == nums[r]) { // 같은 경우
            res = func(l+1, r-1);
        }
        else {
            int resL = func(l, r-1) + 1; // 오른쪽의 수를 왼쪽의 수의 왼쪽에 넣는 경우
            int resR = func(l+1, r) + 1; // 왼쪽의 수를 오른쪽의 수의 오른쪽에 넣는 경우
            res = Math.min(resL, resR);
        }
        dp[l][r] = res;
        return res;
    }
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nums = new int[N];
        dp = new int[N][N];
        for(int i=0; i<N; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        int res = func(0, N-1);
        System.out.println(res);
    }
}

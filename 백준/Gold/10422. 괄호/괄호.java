import java.io.*;
import java.util.*;

/**
 * 길이가 L인 괄호 올바른 문자열의 개수
 * 길이 L이 주어졌을때 길이가 L인 서로 다른 올바른 괄호 문자열의 개수를 출력하는 프로그램
 * t(n) = t(n-2) + ∑(t((n-4)-2*k)) * t(2*k)) ~2*k=n-4
 */
public class Main {
    static int N;
    static int tcs[];
    static long tdp[];
    static long t(int n, int d) { // 남은 괄호의 개수 n, depth d
        if(n%2==1) return 0;
        if(n==0) return 1;
        if(n==2) return 1;
        if(tdp[n] != 0) return tdp[n];
        long caseSum = 0;
        for(int k=2; k<=n; k+=2) { // d에서 사용할 괄호의 개수 k
            caseSum = divide(caseSum + t(k-2, 0) * t(n-k, d+1));
        }
        tdp[n] = caseSum;
        return caseSum;
    }
    static long divide(long res) {
        return res % 1_000_000_007L;
    }
    public static void main(String args[]) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        tcs = new int[N];
        for(int i=0; i<N; i++) tcs[i] = Integer.parseInt(br.readLine());
        int tdpLength = 0;
        for(int tc : tcs) tdpLength = Math.max(tdpLength, tc);
        tdp = new long[tdpLength + 1];
        StringBuffer sb = new StringBuffer();
        for(int tc : tcs) {
            sb.append(t(tc, 0) + "\n");
        }
        System.out.println(sb);
    }
}

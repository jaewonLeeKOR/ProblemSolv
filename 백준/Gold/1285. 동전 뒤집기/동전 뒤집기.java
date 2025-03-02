import java.io.*;
import java.util.*;

public class Main {
    static int N, headBits[];
    static int func(int rowConvertBit) {
        int res = 0;
        for(int j=0; j<N; j++) {
            int cnt = 0;
            for(int i=0; i<N; i++) {
                boolean isHead = (headBits[i] & (1 << j)) == (1 << j);
                if((rowConvertBit & (1 << i)) == (1 << i)) isHead = !isHead;
                if(isHead) cnt++;
            }
            res += Math.min(cnt, N-cnt);
        }
        return res;
    }
    public static void main(String[] args) throws Exception{
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        headBits = new int[N];
        for(int i=0; i<N; i++) {
            String s = br.readLine();
            for(int j=0; j<N; j++) {
                if(s.charAt(j) == 'H')
                    headBits[i] += (1 << j);
            }
        }
        int minCnt = Integer.MAX_VALUE;
        for(int rowConvertBit=0; rowConvertBit<(1<<N); rowConvertBit++) {
            minCnt = Math.min(minCnt, func(rowConvertBit));
        }
        System.out.println(minCnt);
    }
}

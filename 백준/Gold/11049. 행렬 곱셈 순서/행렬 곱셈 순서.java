import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static Matrix dp[][] = new Matrix[500][500];
    static class Matrix {
        int r,c,multiplyCnt=0;
        Matrix(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.multiplyCnt = cnt;
        }
        public Matrix multiply(Matrix other) {
            int cnt = this.multiplyCnt + other.multiplyCnt + r * c * other.c;
            return new Matrix(r, other.c, cnt);
        }
    }
    static Matrix initialMatrix = new Matrix(-1,-1,Integer.MAX_VALUE);
    static Matrix func(int srt, int end) {
        if(dp[srt][end].multiplyCnt != Integer.MAX_VALUE) return dp[srt][end];
        Matrix minMatrix = initialMatrix;
        for(int i=srt; i<end; i++) {
            Matrix m1 = func(srt,i);
            Matrix m2 = func(i+1, end);
            Matrix res = m1.multiply(m2);
            if(minMatrix.multiplyCnt >= res.multiplyCnt) {
                minMatrix = res;
            }
        }
        dp[srt][end] = minMatrix;
        return minMatrix;
    }
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < 500; i++) {
            for (int j = 0; j < 500; j++) {
                dp[i][j] = initialMatrix;
            }
        }
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()), c = Integer.parseInt(st.nextToken());
            dp[i][i] = new Matrix(r,c,0);
        }
        Matrix res = func(0, N - 1);
        System.out.println(res.multiplyCnt);
    }
}

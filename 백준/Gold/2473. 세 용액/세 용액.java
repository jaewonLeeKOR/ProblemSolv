import java.io.*;
import java.util.*;

public class Main {
    static int N, w[];
    static Res minRes = new Res(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
    static class Res {
        int a,b,c;
        long sum;
        Res(int a, int b, int c) {
            int l[] = {a,b,c};
            Arrays.sort(l);
            this.a = l[0];
            this.b = l[1];
            this.c = l[2];
            this.sum = Math.abs(((long) a) + b + c);
        }
    }
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        w = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            w[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(w);
        whole:
        for (int i = 0; i < N; i++) {
            for (int j = i+2; j < N; j++) {
                int a=w[i], c=w[j];
                int targetB = -1 * (a+c);
                int insertionPoint = Arrays.binarySearch(w, i+1, j, targetB);
                if(insertionPoint >= 0) {
                    int b = w[insertionPoint];
                    minRes = new Res(a,b,c);
                    break whole;
                }
                int upperBound = -1 * (insertionPoint + 1);
                if(i < upperBound && upperBound < j) {
                    int b = w[upperBound];
                    Res curRes = new Res(a,b,c);
                    if(minRes.sum > curRes.sum) {
                        minRes = curRes;
                    }
                }
                int lowerBound = upperBound - 1;
                if(i < lowerBound && lowerBound < j) {
                    int b = w[lowerBound];
                    Res curRes = new Res(a,b,c);
                    if(minRes.sum > curRes.sum) {
                        minRes = curRes;
                    }
                }
            }
        }
        System.out.printf("%d %d %d", minRes.a, minRes.b, minRes.c);
    }
}

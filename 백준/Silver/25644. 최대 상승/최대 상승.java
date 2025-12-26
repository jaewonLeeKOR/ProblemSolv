import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int a[];
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        int minValue = a[0], res = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            minValue = Math.min(minValue, a[i]);
            res = Math.max(res, a[i] - minValue);
        }
        System.out.println(res);
    }
}

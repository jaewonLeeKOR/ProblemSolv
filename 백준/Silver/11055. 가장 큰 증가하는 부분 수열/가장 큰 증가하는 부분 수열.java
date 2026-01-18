import java.io.*;
import java.util.*;

public class Main {
    static int n, s[], sum[];
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        s = new int[n];
        sum = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            s[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n; i++) {
            sum[i] = s[i];
            for (int j = 0; j < i; j++) {
                if(s[j] < s[i]) {
                    sum[i] = Math.max(sum[i], sum[j] + s[i]);
                }
            }
        }

        int m = 0;
        for (int i = 0; i < n; i++) {
            m = Math.max(m, sum[i]);
        }
        System.out.println(m);
    }
}

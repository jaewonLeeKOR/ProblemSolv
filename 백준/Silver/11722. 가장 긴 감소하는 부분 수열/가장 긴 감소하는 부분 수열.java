import java.io.*;
import java.util.*;

public class Main {
    static int n, s[], decreaseDp[];
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        s = new int[n];
        decreaseDp = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            s[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = n-1; i >= 0; i--) {
            decreaseDp[i] = 1;
            for (int j = n-1; j > i; j--) {
                if(s[j] < s[i]) {
                    decreaseDp[i] = Math.max(decreaseDp[i], decreaseDp[j] + 1);
                }
            }
        }

        int m = 0;
        for (int i = 0; i < n; i++) {
            m = Math.max(m, decreaseDp[i]);
        }
        System.out.println(m);
    }
}

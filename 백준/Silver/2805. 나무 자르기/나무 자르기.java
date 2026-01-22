import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int t[];
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        t = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            t[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(t);
        int left = 0, right = t[t.length-1];
        for (int mid = (left+right)/2; left < right; mid = (left+right)/2) {
            long sum = 0;
            for (int i = t.length-1; i >= 0; i--) {
                if (sum > M) break;
                if (t[i] <= mid) break;
                sum += (t[i] - mid);
            }
            if(sum >= M) {
                left = mid + 1;
            }
            else {
                right = mid;
            }
        }
        System.out.println(left-1);
    }
}

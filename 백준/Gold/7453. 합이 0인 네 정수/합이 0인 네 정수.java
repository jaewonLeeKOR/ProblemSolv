import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] a = new int[4000], b = new int[4000], c = new int[4000], d = new int[4000], ab = new int[4000*4000];
    static int getUpperBound(int target) {
        int left=0, right=N*N;
        while (left<right) {
            int mid = (left+right)/2;
            if(ab[mid] > target) right = mid;
            else left = mid + 1;
        }
        return left;
    }
    static int getLowerBound(int target) {
        int left=0, right=N*N;
        while (left<right) {
            int mid = (left+right)/2;
            if(ab[mid] >= target) right = mid;
            else left = mid + 1;
        }
        return left;
    }
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            a[i] = Integer.parseInt(st.nextToken());
            b[i] = Integer.parseInt(st.nextToken());
            c[i] = Integer.parseInt(st.nextToken());
            d[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ab[i*N + j] = a[i] + b[j];
            }
        }
        Arrays.sort(ab, 0,N*N);
        long cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int cdSum = -1 * (c[i] + d[j]);
                int upperBound = getUpperBound(cdSum), lowerBound = getLowerBound(cdSum);
                if(upperBound == lowerBound) {
                    continue;
                }
                int curCnt = upperBound-lowerBound;
                cnt += curCnt;
            }
        }
        System.out.println(cnt);
    }
}

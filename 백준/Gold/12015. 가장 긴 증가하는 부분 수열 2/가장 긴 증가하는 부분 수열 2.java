import java.io.*;
import java.util.*;

/**
 * 가장 긴 부분 수열을 앞에서부터 누적하면서 새롭게 추가될 숫자보다 큰 숫자 바로 전에 넣는 방식
 */
public class Main {
    static int N;
    static int origin[];
    static List<Integer> LIA;
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        origin = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            origin[i] = Integer.parseInt(st.nextToken());
        }

        LIA = new ArrayList<>(1_000_000);
        LIA.add(origin[0]);
        for(int i=1; i<N; i++) {
            if(origin[i] > LIA.get(LIA.size()-1)) LIA.add(origin[i]);
            else binarySearchAndReplace(origin[i]);
        }
        System.out.println(LIA.size());
    }
    static void binarySearchAndReplace(int target) {
        int left = 0, right = LIA.size()-1, mid;
        while(left < right) {
            mid = (left + right) / 2;
            if(LIA.get(mid) >= target) right = mid;
            else left = mid + 1;
        }
        LIA.set(right, target);
    }
}

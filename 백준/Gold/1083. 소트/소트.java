import java.io.*;
import java.util.*;

/**
 * 1083
 * 모든 원소가 다른 크기가 N인 배열
 * - 배열을 소트할 때, 연속된 두개의 원소만 교환 가능
 * - 교환은 많아봐야 S번 가능
 * 소트한 결과가 사전순으로 가장 뒷서는것을 출력
 *
 * 가장 앞에서 S개중 가장 큰 숫자를 가장 앞으로 보내는 방식
 */
public class Main {
    static int N, S;
    static int[] nums;
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nums = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        S = Integer.parseInt(br.readLine());
        func();
        for(int num : nums)
            System.out.print(num + " ");
    }

    static void func() {
        for(int startIdx = 0; startIdx<N && S>0; startIdx++) {
            int maxIdx = startIdx;
            for(int i=startIdx+1; i<N && i<=startIdx+S; i++) {
                if(nums[i] > nums[maxIdx])
                    maxIdx = i;
            }
            bubbleUp(maxIdx, startIdx);
        }
    }

    static void bubbleUp(int from, int to) {
        for(int i= from; i>to; i--) {
            int tmp = nums[i];
            nums[i] = nums[i-1];
            nums[i-1] = tmp;
            S--;
        }
    }
}
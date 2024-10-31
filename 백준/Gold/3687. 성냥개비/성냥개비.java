import java.io.*;
import java.util.*;

/**
 * 0 -> 6개
 * 1 -> 2개
 * 2 -> 5개
 * 3 -> 5개
 * 4 -> 4개
 * 5 -> 5개
 * 6 -> 6개
 * 7 -> 3개
 * 8 -> 7개
 * 9 -> 6개
 *
 * 2개 -> 1
 * 3개 -> 7
 * 4개 -> 4
 * 5개 -> 2(,3,5)
 * 6개 -> 0,6(,9)
 * 7개 -> 8
 *
 * 가장 작은 값
 * 작은 수에서 최대한 성냥을 소모
 *
 * 가장 큰 값
 * 자릿수를 클수록 큰 값
 * 1로 자리수 늘리고, 홀수개가 주어질 경우 1 -> 7 로 첫번째 자리수를 변형
 */

public class Main {
    static long minMap[] = new long[101];
    static Map<Integer, String> maxMap = new HashMap<>(101);
    static String maxValue(int n) {
        if(maxMap.get(n) != null) return maxMap.get(n);
        String res = maxValue(n-2) + "1";
        maxMap.put(n, res);
        return res;
    }
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int realMin[] = {0,0,1,7,4,2,0,8};

        Arrays.fill(minMap, Long.MAX_VALUE);
        minMap[2] = 1;
        minMap[3] = 7;
        minMap[4] = 4;
        minMap[5] = 2;
        minMap[6] = 6;
        minMap[7] = 8;
        minMap[8] = 10;
        for(int i=9; i<=100; i++) {
            for(int j=2; j<8; j++) {
                String tmp = minMap[i-j] + Integer.toString(realMin[j]);
                minMap[i] = Math.min(minMap[i], Long.parseLong(tmp));
            }
        }

        maxMap.put(2, "1");
        maxMap.put(3, "7");

        for(int i=0; i<n; i++) {
            int k = Integer.parseInt(br.readLine());
            System.out.println(minMap[k] + " " + maxValue(k));
        }
    }
}

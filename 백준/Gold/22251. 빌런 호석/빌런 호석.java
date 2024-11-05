import java.io.*;
import java.util.*;

/**
 * 1층부터 N층까지 이용이 가능한 엘리베이터
 * N: 최대 층수
 * K: 엘리베이터 층수를 보여주는 자리 수
 * P: 빌런 호석이 반전시킬 LED 최대 개수 1~P
 * X: 실제 층 수
 *
 * 엘리베이터의 층수를 보여주는 디스플레이 K자리 수가 보임
 */
public class Main {
    static int N, K, P, X;
    static int nums[];
    static int chart[][] = {
        {0,4,3,3,4,3,2,3,1,2},
        {4,0,5,3,2,5,6,1,5,4},
        {3,5,0,2,5,4,3,4,2,3},
        {3,3,2,0,3,2,3,2,2,1},
        {4,2,5,3,0,3,4,3,3,2},
        {3,5,4,2,3,0,1,4,2,1},
        {2,6,3,3,4,1,0,5,1,2},
        {3,1,4,2,3,4,5,0,4,3},
        {1,5,2,2,3,2,1,4,0,1},
        {2,4,3,1,2,1,2,3,1,0}
    };
    static int cnt = 0;
    static void func(int curNum, int depth, int curReveredCnt) {
        if(curNum > N) { // 범위를 넘어간 경우
            return;
        }
        if(curReveredCnt > P) {
            return;
        }
        if(depth == K) {
            if(curNum == X || curNum == 0) return;
            cnt++;
            return;
        }
        for(int i=0; i<10; i++) {
            int nextNum = curNum*10 + i;
            func(nextNum, depth+1, curReveredCnt + chart[nums[depth]][i]);
        }
    }
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        nums = new int[K];
        for(int i=0; i<K; i++) {
            nums[(K-1)-i] = (X % ((int)Math.pow(10,i+1))) / ((int)Math.pow(10,i));
        }
        func(0, 0, 0);
        System.out.println(cnt);
    }
}

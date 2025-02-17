import java.io.*;
import java.util.*;

/**
 * 세계 정복을 위해 N개의 국가를 정복함
 *
 * 전세계 사람들을 그룹으로 나누려고 함
 * 그룹을 나누는 조건
 * - 그룹에 들어있는 사람의 수는 정확히 K명이어야 함
 * - 각 그룹에 있는 사람들은 모두 다른 나라 소속이어야 함
 * 최대 몇개의 그룹으로 나눌 수 있는지
 *
 * 각 나라 사람이 K명 보다 많더라도 최대 K명을 사용하면 중복되는 국가의 인원이 한 그룹에 들어갈 일이 없음
 * 모든 나라의 사람을 합해주고(최대 나라당 K명) 목표 그룹 인원수인 K로 나눠주면 그룹수 도출 가능
 */
public class Main {
    static int N, K;
    static long[] countries;
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        countries = new long[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            countries[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(countries);
        long low = 0, high = 1_000_000_001L * 50, mid; // 그룹 개수
        while(low + 1 < high) {
            mid = (low + high) / 2;
            long people = 0, peoplePerGroup = 0;
            for(int i=0; i<N; i++) {
                people += Math.min(countries[i], mid);
                if(people >= mid) {
                    peoplePerGroup += people / mid;
                    people %= mid;
                }
            }
            if(K <= peoplePerGroup) low = mid;
            else high = mid;
        }
        System.out.println(low);
    }
}
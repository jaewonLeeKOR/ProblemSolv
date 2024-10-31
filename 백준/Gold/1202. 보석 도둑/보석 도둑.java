import java.io.*;
import java.util.*;

/**
 * 보석털이
 * 보석 N개
 * 각 보석 : 무게 M, 가격 V
 * 상덕이는 가방 K개를 가짐
 * 각 가방에 담을 수 있는 최대 무게 C, 각 가방에는 최대 한개의 보석만 넣을 수 있음
 */

public class Main {
    static int N, K;
    static class Jewel {
        int mess, value;
        Jewel(int m, int v) {
            mess = m;
            value = v;
        }
    }
    static List<Jewel> jewels;
    static List<Integer> packs;
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 보석 개수
        K = Integer.parseInt(st.nextToken()); // 가방 개수
        jewels = new ArrayList<>(300_001); // 특정 index의 조회가 잦아 ArrayList 사용
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int m, v;
            m = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            jewels.add(new Jewel(m, v));
        }
        Collections.sort(jewels, (Jewel j1, Jewel j2) -> {
            // 무게가 가벼운것부터
            if(j1.mess != j2.mess) return Integer.compare(j1.mess, j2.mess);
            // 무게가 같다면 비싼것부터
            return -Integer.compare(j1.value, j2.value);
        });

        packs = new ArrayList<>(300_001); // 특정 index의 조회가 잦아 ArrayList 사용
        for(int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            packs.add(p);
        }
        Collections.sort(packs);

        long messSum = 0;
        Queue<Integer> values = new PriorityQueue<>(Comparator.reverseOrder()); // 가격이 비싼것부터 정렬
        for(int j=0, p=0; p<K; p++) {
            while(j < jewels.size() && jewels.get(j).mess <= packs.get(p)) {
                values.add(jewels.get(j++).value);
            }
            if(!values.isEmpty()) {
                messSum += values.poll();
            }
        }
        System.out.println(messSum);
    }
}

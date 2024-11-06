import java.io.*;
import java.util.*;

/**
 * 일직선으로 다양한 높이의 건물들 N개 존재
 * 가희 - 건물 - 단비
 * 가려진 건물은 못봄
 *
 * N : 건물 개수
 * a : 가희가 볼 수 있는 건물 수
 * b : 단비가 볼 수 있는 건물 수
 * 사전순으로 가장 앞서는 N개의 건물 높이 정보 반환 -> 가장 건물이 낮은 케이스
 */
public class Main {
    static int N, a, b;
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        if(N < a + (b-1)) { // 조건을 만족할 최소의 크기 a + (b-1)
            System.out.println(-1);
            return;
        }
        StringBuilder sb = new StringBuilder();
        if(a > b) {
            if(N > a + (b-1)) for(int n=0; n<N-(a+(b-1)); n++)sb.append("1 ");
            for (int i = 1; i <= a; i++) sb.append(i + " ");
            for (int i = b - 1; i >= 1; i--) sb.append(i + " ");
        }
        else {
            if(a != 1 && N > a + (b-1)) for(int n=0; n<N-(a+(b-1)); n++)sb.append("1 ");
            for (int i = 1; i <= a - 1; i++) sb.append(i + " ");
            for (int i = b; i >= 1; i--) {
                sb.append(i + " ");
                if(i== b && a == 1 && N > a + (b-1)) for(int n=0; n<N-(a+(b-1)); n++)sb.append("1 ");
            }
        }
        System.out.println(sb);
    }
}

import java.io.*;

/**
 * 4개의 동작
 * 1 화면에 A 출력
 * 2 화면을 전체 선택 -> 3,4번 동작이 따라야 최대가 될 수 있음 => 2,3,4를 묶자
 * 3 전체 선택한 내용을 버퍼에 복사
 * 4 버퍼가 비어있지 않은 경우에 화면에 출력된 문자열의 바로 뒤에 버퍼의 내용을 붙여넣음
 *
 * 버튼을 N번 눌러서 화면에 출력된 A개수를 최대로
 *
 * 2^100 보다 100*100이 작음
 *
 * Memoization
 */
public class Main {
    static int N;
    static long aCnt[];
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        aCnt = new long[N+1];

        for(int i=1; i<=N; i++) {
            aCnt[i] = aCnt[i-1] + 1;
            for(int j=3; j<i; j++) {
                aCnt[i] = Math.max(aCnt[i], aCnt[i-j] * (j-1));
            }
        }

        System.out.println(aCnt[N]);
    }
}

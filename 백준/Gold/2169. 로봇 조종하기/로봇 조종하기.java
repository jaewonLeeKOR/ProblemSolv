import java.io.*;
import java.util.*;

/**
 * 화성의 지형을 NxM 배열로 단순화 하여 생각
 *
 * 로봇은 왼쪽, 오른쪽, 아래쪽으로만 이동할 수 있음
 * 한번 탐사한 지역은 탐사하지 않음
 * 로봇을 (1,1) -> (N,M) 으로 이동할때 탐사지역의 가치 합이 최대값 반환
 */
public class Main {
    static int value[][];
    static int maxValue[][];
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N,M;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        value = new int[N][M];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                value[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        maxValue = new int[N][M];
        for(int i=0; i<N; i++) {
            Arrays.fill(maxValue[i], Integer.MIN_VALUE);
        }

        // 0행 세팅
        maxValue[0][0] = value[0][0];
        for(int j=1; j<M; j++) {
            maxValue[0][j] = maxValue[0][j-1] + value[0][j];
        }
        // 1~N-1행 행별로 계산 후 dp
        for(int i=1; i<N; i++) {
            for(int j=0; j<M; j++) {
                int curValue = maxValue[i-1][j] + value[i][j];
                if(curValue > maxValue[i][j]) // 더 큰값인 경우
                    maxValue[i][j] = curValue;
                // 좌로 전파
                int newValue = curValue;
                for(int k=j-1; k>=0; k--) {
                    newValue = newValue + value[i][k];
                    if(newValue > maxValue[i][k]) // 더 큰값인 경우
                        maxValue[i][k] = newValue;
                }
                // 우로 전파
                newValue = curValue;
                for(int k=j+1; k<M; k++) {
                    newValue = newValue + value[i][k];
                    if(newValue > maxValue[i][k]) // 더 큰값인 경우
                        maxValue[i][k] = newValue;
            }
            }
        }
        System.out.println(maxValue[N-1][M-1]);
    }
}

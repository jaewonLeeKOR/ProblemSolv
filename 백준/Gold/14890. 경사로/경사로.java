import java.io.*;
import java.util.*;

/**
 * 지나갈 수 있는 길의 개수
 * 길을 지나갈수 있으려면 모든칸의 높이가 같거나 경사로를 놓아서 지나갈 수 있는 길을 만들어야함
 * 경사로 : L칸에 걸쳐서 1칸 높이 높아짐
 */
public class Main {
    static int N, L;
    static final int FLAT = 1, DOWN_HILL = 2, FAIL = 3;
    static int heights[][];
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        heights = new int [N][N];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                heights[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int cnt = 0;
        for(int i=0; i<N; i++) {
            int flatCnt = 1, prevHeight = heights[i][0], state = FLAT;
            for(int j=1; j<N; j++) {
                if(state == DOWN_HILL) {
                    if(heights[i][j] != prevHeight) {
                        state = FAIL;
                        break;
                    }
                    flatCnt++;
                }
                else {
                    if(heights[i][j] == prevHeight) {
                        flatCnt++;
                    }
                    else if(heights[i][j] == prevHeight - 1) {
                        state = DOWN_HILL;
                        flatCnt = 1;
                        prevHeight = heights[i][j];
                    }
                    else if(heights[i][j] == prevHeight + 1) {
                        if(flatCnt < L) {
                            state = FAIL;
                            break;
                        }
                        flatCnt = 1;
                        prevHeight = heights[i][j];
                    }
                    else {
                        state = FAIL;
                        break;
                    }
                }
                if(state == DOWN_HILL && flatCnt == L) {
                    state = FLAT;
                    flatCnt = 0;
                }
            }
            if(state == FLAT) {
                cnt++;
            }
        }

        for(int j=0; j<N; j++) {
            int flatCnt = 1, prevHeight = heights[0][j], state = FLAT;
            for(int i=1; i<N; i++) {
                if(state == DOWN_HILL) {
                    if(heights[i][j] != prevHeight) {
                        state = FAIL;
                        break;
                    }
                    flatCnt++;
                }
                else {
                    if(heights[i][j] == prevHeight) {
                        flatCnt++;
                    }
                    else if(heights[i][j] == prevHeight - 1) {
                        state = DOWN_HILL;
                        flatCnt = 1;
                        prevHeight = heights[i][j];
                    }
                    else if(heights[i][j] == prevHeight + 1) {
                        if(flatCnt < L) {
                            state = FAIL;
                            break;
                        }
                        flatCnt = 1;
                        prevHeight = heights[i][j];
                    }
                    else {
                        state = FAIL;
                        break;
                    }
                }
                if(state == DOWN_HILL && flatCnt == L) {
                    state = FLAT;
                    flatCnt = 0;
                }
            }
            if(state == FLAT) {
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}

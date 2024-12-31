import java.io.*;
import java.util.*;

public class Main {
    static final int NONE = 0, RED = 0, GREEN = 1, BLUE = 2;
    static int N;
    static HouseState houseStates[];
    static class HouseState {
        int costs[] = new int[3];
        int costSum[][] = new int[3][3]; // 시작 색상 - 현재 색상
        HouseState(int r, int g, int b) {
            costs[RED] = r;
            costs[GREEN] = g;
            costs[BLUE] = b;
        }
    }
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        houseStates = new HouseState[N];
        StringTokenizer st;
        int r,g,b;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            houseStates[i] = new HouseState(r,g,b);
        }

        // 1번집 세팅
        houseStates[0].costSum[RED][RED] = houseStates[0].costs[RED];
        houseStates[0].costSum[GREEN][GREEN] = houseStates[0].costs[GREEN];
        houseStates[0].costSum[BLUE][BLUE] = houseStates[0].costs[BLUE];

        // 2 ~ N번집 계산
        for(int i=1; i<N; i++) {
            for(int prevColor=0; prevColor<3; prevColor++) {
                for(int curColor=0; curColor<3; curColor++) {
                    if(prevColor == curColor) continue;
                    for(int firstColor=0; firstColor<3; firstColor++) {
                        if(houseStates[i-1].costSum[firstColor][prevColor] == NONE) continue;
                        if (houseStates[i].costSum[firstColor][curColor] == NONE) {
                            houseStates[i].costSum[firstColor][curColor] = houseStates[i-1].costSum[firstColor][prevColor] + houseStates[i].costs[curColor];
                        } else {
                            houseStates[i].costSum[firstColor][curColor] = Math.min(houseStates[i].costSum[firstColor][curColor], houseStates[i-1].costSum[firstColor][prevColor] + houseStates[i].costs[curColor]);
                        }
                    }
                }
            }
        }

        // N번집 1번집 색상 중복 제외 최소 비용 계산
        int minCost = Integer.MAX_VALUE;
        for(int lastColor=0; lastColor<3; lastColor++) {
            for(int firstColor=0; firstColor<3; firstColor++) {
                if(firstColor == lastColor) continue;
                minCost = Math.min(minCost, houseStates[N-1].costSum[firstColor][lastColor]);
            }
        }
        System.out.println(minCost);
    }
}

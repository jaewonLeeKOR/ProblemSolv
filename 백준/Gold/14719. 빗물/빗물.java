import java.util.*;
import java.io.*;

/**
 * 빗물
 *
 * 높이별 직전까지의 상황을 기록하고 현재 상황애 대한 추가량 합산
 */
public class Main {
    static final int NONE = -1; // 벽이 한번도 없었음
    static int H, W;
    static int heights[]; // 벽 높이 컬렉션
    static int preState[]; // 높이별 마지막 벽 위치 컬렉션
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        heights = new int[W];
        for(int i=0; i<W; i++) {
            Integer h = Integer.parseInt(st.nextToken());
            heights[i] = h;
        }

        preState= new int[H+1];
        for(int i=0; i<H+1; i++) {
            preState[i] = NONE;
        }

        int waterSum = 0;
        for(int currentWidth=0; currentWidth<W; currentWidth++) {
            int currentHeight = heights[currentWidth];
            for(int h=currentHeight; h>=0; h--) {
                if(preState[h]!=NONE && preState[h] != currentWidth-1) { // 물이 찰 공간이 있었을 경우
                    waterSum += (currentWidth-1) - preState[h];
                }
                preState[h] = currentWidth;
            }
        }
        System.out.println(waterSum);
    }
}

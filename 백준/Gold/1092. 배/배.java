import java.io.*;
import java.util.*;

/**
 * 화물을 배에 실어야 함
 * 크레인 N대, 1분에 박스 하나씩 실을 수 있음
 * 각 크레인은 무게 제한이 있음
 */
public class Main {
    static int N, M;
    static int[] cranes, boxes, cranePositions;
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        cranes = new int[N];
        for(int i=0; i<N; i++) {
            cranes[i] = Integer.parseInt(st.nextToken());
        }
        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        boxes = new int[M];
        for (int i = 0; i < M; i++) {
            boxes[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(cranes);
        Arrays.sort(boxes);

        cranePositions = new int[N];
        for (int i = 0; i < N; i++) {
            int crane = cranes[i];
            int insertionPoint = Arrays.binarySearch(boxes, crane);
            if(insertionPoint < 0) {
                insertionPoint = -1 * (insertionPoint + 1);
                insertionPoint--;
            }
            if(insertionPoint >= 0 ) {
                for (int j = insertionPoint + 1; j < M; j++) {
                    if (boxes[j] == boxes[insertionPoint]) {
                        insertionPoint = j;
                        continue;
                    }
                    break;
                }
            }
            cranePositions[i] = insertionPoint;
        }

        if(cranePositions[cranePositions.length -1] != boxes.length - 1) {
            System.out.println(-1);
            return;
        }

        int movedBoxCnt = 0, turnCnt = 0;
        while(movedBoxCnt < M) {
            turnCnt++;
            for(int i = 0; i< cranePositions.length; i++) {
                int craneIdx = cranePositions[i];
                for(int j=craneIdx; j>=0; j--) {
                    if(boxes[j] > 0) {
                        boxes[j] *= -1;
                        cranePositions[i] = j - 1;
                        movedBoxCnt++;
                        break;
                    }
                    if(j == 0) {
                        cranePositions[i] = -1;
                    }
                }
                if(movedBoxCnt >= M) break;
            }
        }
        System.out.println(turnCnt);
    }
}

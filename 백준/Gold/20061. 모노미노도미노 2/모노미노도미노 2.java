import java.util.*;
import java.io.*;

/**
 * 빨간색보드, 파란색 보드, 초록색 보드
 *
 * 한 행/열이 꽉 찬경우 없애고 다음의 행/열을 모두 한칸씩 이동, 점수 +1
 */
public class Main {
    // t : 1 -> 1x1, 2 -> 1x2 (가로), 3 -> 2x1 (세로)
    static int N; // 블록을놓은 횟수
    static int score;
    static boolean greenBoard[][] = new boolean[6][4];
    static boolean blueBoard[][] = new boolean[6][4];

    static void addBlock2Green(int type, int x, int y) { // y유지
        // 블록 이동
        if(type == 1){
            int i=2;
            while(i<6 && !greenBoard[i][y]) {
                i++;
            }
            greenBoard[i-1][y] = true;
        }
        else if(type == 2) {
            int i=2;
            while(i<6 && !greenBoard[i][y] && !greenBoard[i][y+1]) {
                i++;
            }
            greenBoard[i-1][y] = true;
            greenBoard[i-1][y+1] = true;
        }
        else if(type == 3) {
            int i=2;
            while(i<6 && !greenBoard[i][y]) {
                i++;
            }
            greenBoard[i-1][y] = true;
            greenBoard[i-2][y] = true;
        }
        // 점수 확인, 점수 추가된 경우 블록 밀기
        int from=-1, to=-1;
        for(int i=2; i<6; i++) {
            if(greenBoard[i][0] && greenBoard[i][1] && greenBoard[i][2] && greenBoard[i][3]) {
                to = i;
                if(from == -1) {
                    from = i-1;
                }
            }
        }
        int removedRow = to - from;
        score += removedRow;
        for(int i=from; i>=0; i--) {
            greenBoard[i + removedRow] = greenBoard[i];
        }
        for(int i=0; i<removedRow; i++) {
            boolean newRow[] = new boolean[4];
            greenBoard[i] = newRow;
        }

        // 0,1 영역까지 나온경우 밀기
        int needMoveRow = 0;
        for(int i=1; i>=0; i--) {
            if(greenBoard[i][0] || greenBoard[i][1] || greenBoard[i][2] || greenBoard[i][3])
                needMoveRow++;
            else break;
        }
        for(int i=5-needMoveRow; i>=0; i--) {
            greenBoard[i+needMoveRow] = greenBoard[i];
        }
        for(int i=0; i<needMoveRow; i++) {
            boolean newRow[] = new boolean[4];
            greenBoard[i] = newRow;
        }
    }

    static void addBlock2Blue(int type, int x, int y) { // x=y 대칭 -> 2와 3을 반대로 사용하면 됨
        // 블록 이동
        if(type == 1){
            int i=2;
            while(i<6 && !blueBoard[i][y]) {
                i++;
            }
            blueBoard[i-1][y] = true;
        }
        else if(type == 3) {
            int i=2;
            while(i<6 && !blueBoard[i][y] && !blueBoard[i][y+1]) {
                i++;
            }
            blueBoard[i-1][y] = true;
            blueBoard[i-1][y+1] = true;
        }
        else if(type == 2) {
            int i=2;
            while(i<6 && !blueBoard[i][y]) {
                i++;
            }
            blueBoard[i-1][y] = true;
            blueBoard[i-2][y] = true;
        }
        // 점수 확인, 점수 추가된 경우 블록 밀기
        int from=-1, to=-1;
        for(int i=2; i<6; i++) {
            if(blueBoard[i][0] && blueBoard[i][1] && blueBoard[i][2] && blueBoard[i][3]) {
                to = i;
                if(from == -1) {
                    from = i-1;
                }
            }
        }
        int removedRow = to - from;
        score += removedRow;
        for(int i=from; i>=0; i--) {
            blueBoard[i + removedRow] = blueBoard[i];
        }
        for(int i=0; i<removedRow; i++) {
            boolean newRow[] = new boolean[4];
            blueBoard[i] = newRow;
        }

        // 0,1 영역까지 나온경우 밀기
        int needMoveRow = 0;
        for(int i=1; i>=0; i--) {
            if(blueBoard[i][0] || blueBoard[i][1] || blueBoard[i][2] || blueBoard[i][3])
                needMoveRow++;
            else break;
        }
        for(int i=5-needMoveRow; i>=0; i--) {
            blueBoard[i+needMoveRow] = blueBoard[i];
        }
        for(int i=0; i<needMoveRow; i++) {
            boolean newRow[] = new boolean[4];
            blueBoard[i] = newRow;
        }
    }

    static int totalRemainBlocks() {
        int blockCnt = 0;
        for(int i=0; i<4; i++) {
            for(int j=0; j<4; j++) {
                if(greenBoard[2+i][j]) blockCnt++;
                if(blueBoard[2+i][j]) blockCnt++;
            }
        }
        return blockCnt;
    }

    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            addBlock2Green(t,x,y);
            addBlock2Blue(t,y,x);
        }
        System.out.println(score);
        System.out.println(totalRemainBlocks());
    }
}

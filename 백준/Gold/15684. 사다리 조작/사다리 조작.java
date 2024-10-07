import java.io.*;
import java.util.*;

/**
 * 입력
 * 세로선 개수 N, 주어지는 가로선 개수 M, 세로선마다 가로선을 놓을 수 있는 위치의 개수 H
 * a b (M줄; b와 b+1번 세로선을 a번 점선 위치에서 연결)
 *
 * 출력
 * i번 세로선의 결과가 i가 나오도록 조작 하려면 추가해야 하는 가로선의 개수의 최소값
 * 정답이 3보다 크거나 불가능한 경우 -1 출력
 */

public class Main {
    static final int NONE = 0, LEFT = 1, RIGHT = 2;
    static int N, M, H;
    static int ladder[][];
    static int minCase = 4;
    static boolean straight() {
        for(int r=1; r<N; r++) {
            int curRow = r;
            for(int h=1; h<=H; h++) {
                if(ladder[h][curRow] == LEFT)
                    curRow--;
                else if(ladder[h][curRow] == RIGHT)
                    curRow++;
            }
            if(curRow != r)
                return false;
        }
        return true;
    }
    static class Position {
        public int row, column; // row : 가로선 idx, column : 세로선 idx
        Position(int r, int c) {
            row = r;
            column = c;
        }
        public boolean nextPosition() {
            if(++column == N) {
                column = 1;
                row++;
            }
            if(column == 1 && row == H+1)
                return false;
            return true;
        }
        public Position copy() {
            return new Position(row, column);
        }
    }
    static void comb(Position curPos, int createdLadderCount) {
        if(createdLadderCount > 3) return;
        if(straight())
            minCase = Math.min(minCase, createdLadderCount);

        while(curPos.nextPosition()) {
            if(ladder[curPos.row][curPos.column] != NONE) continue;
            if(ladder[curPos.row][curPos.column+1] != NONE) continue;
            ladder[curPos.row][curPos.column] = RIGHT;
            ladder[curPos.row][curPos.column+1] = LEFT;
            comb(curPos.copy(), createdLadderCount+1);
            ladder[curPos.row][curPos.column] = ladder[curPos.row][curPos.column+1] = NONE;
        }
    }
    public static void main(String[] args) throws Exception{
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        ladder = new int[H+1][N+1];
        for(int i=0; i<M; i++) {
            // a b (b와 b+1번 세로선을 a번 점선 위치에서 연결)
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            ladder[a][b] = RIGHT;
            ladder[a][b+1] = LEFT;
        }

        comb(new Position(1, 0),0);
        System.out.println(minCase == 4 ? -1 : minCase);
    }
}
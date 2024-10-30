import java.util.*;
import java.io.*;

/**
 * 틱택토 게임에서 발생할 수 있는 최종 상태인지 판별
 *
 * X가 먼저 시작
 * 한사람이 3칸을 잇는데 성공하면 즉시 끝남
 * 게임판이 가득차도 끝남
 *
 * 불가능한 경우
 * O가 X 보다 많은경우
 * X가 O 보다 2개 이상 많은 경우
 * O나 X가 이어지지 않은 두개의 성공을 가지는 경우 -> 6개 3개로 3개 차이가 나는경우라 신경 안써도 될듯
 * 아무도 이기지 못한 경우
 */

public class Main {
    static void printInvalid() {
        System.out.println("invalid");
    }
    static void printValid() {
        System.out.println("valid");
    }
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while(!(s = br.readLine()).equals("end")) {
            char ttt[] = new char[9];
            int xCount = 0, oCount = 0, dCount = 0;
            for(int i=0; i<9; i++) {
                ttt[i] = s.charAt(i);
                if(s.charAt(i) == 'X') xCount++;
                if(s.charAt(i) == 'O') oCount++;
                if(s.charAt(i) == '.') dCount++;
            }

            if(xCount < oCount) { // O가 X 보다 많은경우
                printInvalid();
                continue;
            }
            if(xCount > oCount+1) { // X가 O 보다 2개 이상 많은 경우
                printInvalid();
                continue;
            }

            boolean xWin = false, oWin = false;
            // 가로
            if(ttt[0] == ttt[1] && ttt[0] == ttt[2]) {
                if(ttt[0] == 'X') xWin = true;
                else if(ttt[0] == 'O') oWin = true;
            }
            if(ttt[3] == ttt[4] && ttt[3] == ttt[5]) {
                if(ttt[3] == 'X') xWin = true;
                else if(ttt[3] == 'O') oWin = true;
            }
            if(ttt[6] == ttt[7] && ttt[6] == ttt[8]) {
                if(ttt[6] == 'X') xWin = true;
                else if(ttt[6] == 'O') oWin = true;
            }

            // 세로
            if(ttt[0] == ttt[3] && ttt[0] == ttt[6]) {
                if(ttt[0] == 'X') xWin = true;
                else if(ttt[0] == 'O') oWin = true;
            }
            if(ttt[1] == ttt[4] && ttt[1] == ttt[7]) {
                if(ttt[1] == 'X') xWin = true;
                else if(ttt[1] == 'O') oWin = true;
            }
            if(ttt[2] == ttt[5] && ttt[2] == ttt[8]) {
                if(ttt[2] == 'X') xWin = true;
                else if(ttt[2] == 'O') oWin = true;
            }

            // 대각
            if(ttt[0] == ttt[4] && ttt[0] == ttt[8]) {
                if(ttt[0] == 'X') xWin = true;
                else if(ttt[0] == 'O') oWin = true;
            }
            if(ttt[2] == ttt[4] && ttt[2] == ttt[6]) {
                if(ttt[2] == 'X') xWin = true;
                else if(ttt[2] == 'O') oWin = true;
            }

            if(xWin && oWin) { // 둘 다 성공한 경우
                printInvalid();
                continue;
            }
            if(xWin && xCount == oCount) { // x가 이겼지만 o가 이후에도 더 둔 경우
                printInvalid();
                continue;
            }
            if(oWin && xCount > oCount) { // o가 이겼지만 x가 이후에도 더 둔 경우
                printInvalid();
                continue;
            }
            if(!xWin && !oWin && dCount!=0) { // 아무도 못이겼지만 전체가 채워지지 않은 경우
                printInvalid();
                continue;
            }

            printValid();
        }
    }
}

import java.util.*;
import java.io.*;

public class Main {
    static final int TO_DOWN = 0, TO_RIGHT = 1, TO_UP = 2, TO_LEFT = 3;
    static int N;
    static int maxBlock = 0;
    static void getMax(int[][] board) {
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                maxBlock = Math.max(maxBlock, board[i][j]);
            }
        }
    }

    static int[][] copyArray(int[][] origin) {
        int[][] newArray = new int[N][N];
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++)
                newArray[i][j] = origin[i][j];
        }
        return newArray;
    }

    static void func(int[][] board, int depth) {
        if(depth == 5) {
            getMax(board);
            return;
        }
        for(int d=0; d<4; d++) {
            Deque<Integer> dq = new LinkedList<>();
            int newBoard[][] = copyArray(board);
            switch (d) {
                case TO_DOWN:
                    for(int j=0; j<N; j++) {
                        for(int i=N-1; i>=0; i--) {
                            func1(dq, newBoard, i, j);
                        }

                        for(int i=N-1; i>=0; i--) {
                            func2(dq, newBoard, i, j);
                        }
                    }
                    func(newBoard, depth + 1);
                    break;
                case TO_UP:
                    for(int j=0; j<N; j++) {
                        for(int i=0; i<N; i++) {
                            func1(dq, newBoard, i, j);
                        }

                        for(int i=0; i<N; i++) {
                            func2(dq, newBoard, i, j);
                        }
                    }
                    func(newBoard, depth + 1);
                    break;
                case TO_RIGHT:
                    for(int i=0; i<N; i++) {
                        for(int j=N-1; j>=0; j--) {
                            func1(dq, newBoard, i, j);
                        }

                        for(int j=N-1; j>=0; j--) {
                            func2(dq, newBoard, i, j);
                        }
                    }
                    func(newBoard, depth + 1);
                    break;
                case TO_LEFT:
                    for(int i=0; i<N; i++) {
                        for(int j=0; j<N; j++) {
                            func1(dq, newBoard, i, j);
                        }

                        for(int j=0; j<N; j++) {
                            func2(dq, newBoard, i, j);
                        }
                    }
                    func(newBoard, depth + 1);
                    break;
            }
        }
    }

    static void func1(Deque<Integer> dq, int[][] newBoard, int i, int j) {
        if(newBoard[i][j] == 0)
            return;
        else if(dq.isEmpty()) {
            dq.addLast(newBoard[i][j]);
        }
        else if(dq.getLast() == newBoard[i][j]) {
            dq.removeLast();
            dq.addLast(newBoard[i][j] * -2);
        }
        else {
            dq.addLast(newBoard[i][j]);
        }
    }

    static void func2(Deque<Integer> dq, int[][] newBoard, int i, int j) {
        if(dq.isEmpty()) {
            newBoard[i][j] = 0;
        }
        else {
            newBoard[i][j] = Math.abs(dq.getFirst());
            dq.removeFirst();
        }
    }

    public static void main(String args[]) throws Exception{
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int board[][] = new int[N][N];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        func(board, 0);
        System.out.println(maxBlock);
    }
}

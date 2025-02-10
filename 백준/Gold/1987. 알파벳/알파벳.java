import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int maxCnt = 0;
    static char[][] board;
    static int dx[] = {1,-1,0,0}, dy[] = {0,0,1,-1};
    static class Position {
        int x,y;
        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
        @Override
        public boolean equals(Object o) {
            Position p = (Position) o;
            return this.x == p.x && this.y == p.y;
        }
        @Override
        public int hashCode() {
            return Objects.hash(x,y);
        }
    }
    static boolean[][] visited_;
    static boolean[] dictionary_ = new boolean[26];
    static void dfs(Position pos, int depth) {
        maxCnt = Math.max(maxCnt, depth);
        for(int d=0; d<4; d++) {
            int nx = pos.x + dx[d], ny = pos.y + dy[d];
            if(nx < 0 || N <= nx || ny < 0 || M <= ny) continue;
            Position newPos = new Position(nx,ny);
            if(visited_[nx][ny]) continue;
            if(dictionary_[board[nx][ny]- 'A']) continue;
            visited_[nx][ny] = true;
            dictionary_[board[nx][ny]- 'A'] = true;
            dfs(newPos, depth+1);
            visited_[nx][ny] = false;
            dictionary_[board[nx][ny]- 'A'] = false;
        }
    }
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        visited_ = new boolean[N][M];

        for(int i=0; i<N; i++) {
            String s = br.readLine();
            for(int j=0; j<s.length(); j++) {
                board[i][j] = s.charAt(j);
            }
        }

        visited_[0][0] = true;
        dictionary_[board[0][0] - 'A'] = true;
        dfs(new Position(0,0),1);
        System.out.println(maxCnt);
    }
}

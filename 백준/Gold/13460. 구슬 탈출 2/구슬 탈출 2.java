import java.io.*;
import java.util.*;

public class Main {
    static final int[] dx = {1,-1,0,0}, dy = {0,0,1,-1};
    static int N, M;
    static char board[][];
    static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static class State {
        Point blue, red;
        int depth;
        State(Point blue, Point red, int depth) {
            this.blue = new Point(blue.x, blue.y);
            this.red = new Point(red.x, red.y);
            this.depth = depth;
        }
        public boolean equals(Object obj) {
            State state = (State) obj;
            return blue.x == state.blue.x && blue.y == state.blue.y && red.x == state.red.x && red.y == state.red.y;
        }
        public int hashCode() {
            return Objects.hash(blue.x, blue.y, red.x, red.y);
        }
    }
    static Set<State> visited = new HashSet<>();
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        State state = new State(new Point(0,0), new Point(0,0), 0);
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = input.charAt(j);
                board[i][j] = c;
                if(c == 'B') {
                    state.blue = new Point(i, j);
                    board[i][j] = '.';
                }
                if(c == 'R') {
                    state.red = new Point(i, j);
                    board[i][j] = '.';
                }
            }
        }
        int depth = -1;
        Queue<State> q = new LinkedList<>();
        q.add(state);
        visited.add(state);
        total:
        while ((state = q.poll()) != null && state.depth < 10) {
            changeDirection:
            for (int i = 0; i < 4; i++) {
                State newState = new State(state.blue, state.red, state.depth+1);
                boolean onMore = false, isEnd = false;
                char next;
                redMove:
                while((next = board[newState.red.x + dx[i]][newState.red.y + dy[i]]) != '#') {
                    if(newState.blue.x == newState.red.x + dx[i] && newState.blue.y == newState.red.y + dy[i]) {
                        onMore = true;
                        break;
                    }
                    newState.red.x += dx[i];
                    newState.red.y += dy[i];
                    if(next == 'O') {
                        isEnd = true;
                        break;
                    }
                }
                blueMove:
                while((next = board[newState.blue.x + dx[i]][newState.blue.y + dy[i]]) != '#') {
                    if(next == 'O') {
                        continue changeDirection;
                    }
                    if(newState.red.x == newState.blue.x + dx[i] && newState.red.y == newState.blue.y + dy[i]) {
                        break;
                    }
                    newState.blue.x += dx[i];
                    newState.blue.y += dy[i];
                }
                if(onMore) {
                    while((next = board[newState.red.x + dx[i]][newState.red.y + dy[i]]) != '#') {
                        if(next == 'O') {
                            isEnd = true;
                            break;
                        }
                        if(newState.blue.x == newState.red.x + dx[i] && newState.blue.y == newState.red.y + dy[i]) {
                            break;
                        }
                        newState.red.x += dx[i];
                        newState.red.y += dy[i];
                    }
                }

                if(isEnd) {
                    depth = newState.depth;
                    break total;
                }
                else {
                    if(visited.contains(newState)) continue;
                    q.add(newState);
                }
            }
        }
        System.out.println(depth);
    }
}

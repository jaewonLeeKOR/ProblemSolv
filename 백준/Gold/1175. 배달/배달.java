import java.io.*;
import java.util.*;

public class Main {
    static final int dx[] = {1,-1,0,0}, dy[] = {0,0,1,-1};
    static int N, M;
    static char classroom[][];
    static Position deliverPositions[];
    static boolean visited[][][][];
    static class Position {
        int x, y;
        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static class Status {
        Position minsik;
        int state;
        int time;
        int direction;
        Status(Position startPosition, int state, int time, int direction) {
            minsik = startPosition;
            this.state = state;
            this.time = time;
            this.direction = direction;
        }
    }
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        classroom = new char[N][M];
        deliverPositions = new Position[2];
        Position minsik = null;
        for(int i=0, dpIdx=0; i<N; i++) {
            String tmp = br.readLine();
            for(int j=0; j<M; j++) {
                classroom[i][j] = tmp.charAt(j);
                if(classroom[i][j] == 'S') {
                    minsik = new Position(i, j);
                }
                else if(classroom[i][j] == 'C') {
                    deliverPositions[dpIdx++] = new Position(i,j);
                }
            }
        }

        int res = -1;
        visited = new boolean[N][M][4][3]; // x, y, direction, state
        Status curStatus = null;
        Queue<Status> q = new LinkedList<>();
        q.add(new Status(minsik, 0, 0, -1));
        bfsWhile:
        while((curStatus = q.poll()) != null) {
            minsik = curStatus.minsik;
            int state = curStatus.state;
            int time = curStatus.time;
            int prevDir = curStatus.direction;
            for(int d=0; d<4; d++) {
                if(prevDir == d) continue; // 방향이 이전과 동일한 경우
                int nx = minsik.x + dx[d], ny = minsik.y + dy[d];
                if(nx < 0 || N <= nx || ny < 0 || M <= ny) continue; // 범위 밖의 이동한 경우
                if(classroom[nx][ny] == '#') continue; // 갈수 없는 경우
                if(visited[nx][ny][d][state]) continue; // 같은 상황에서 이미 방문한 경우
                visited[nx][ny][d][state] = true;
                int newState = state;
                if(classroom[nx][ny] == 'C') { // 배달 위치인 경우
                    if(deliverPositions[0].x == nx && deliverPositions[0].y == ny && state != 1) {
                        newState += 1;
                    }
                    if(deliverPositions[1].x == nx && deliverPositions[1].y == ny && state != 2) {
                        newState += 2;
                    }
                    if(newState == 3) { // 두 배달 위치를 모두 배달한 경우
                        res = time + 1;
                        break bfsWhile;
                    }
                }
                q.add(new Status(new Position(nx, ny), newState, time+1, d));
            }
        }

        System.out.println(res);
    }
}

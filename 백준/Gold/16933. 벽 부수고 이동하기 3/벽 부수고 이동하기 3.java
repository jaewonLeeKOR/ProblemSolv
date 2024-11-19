import java.io.*;
import java.util.*;

/**
 * 최단 경로 찾기 (시작 끝 칸 포함)
 * 이동하지 않고 같은 칸에 머물러 있는것 가능
 * 처음 이동시 낮, 이동할때마다 낮과 밤이 바뀜
 * 낮에는 벽을 K개까지 부술수 있음
 *
 * 이동 후 새로운 정보가 기존보다 (해당 위치가 지나온 칸의 개수)가 많으면서 (남은 드릴 수)가 적으면 업데이트
 */
public class Main {
    static int N, M, K;
    static int dx[] = {1,0,-1,0}, dy[] = {0,1,0,-1};
    static boolean walls[][];
    static boolean visited[][][]; // [x][y][k] 해당 위치의 뚫을 수 있는 개수 별 방문 여부
    static class State {
        int x,y; // 현재 위치
        int cnt; // 지나온 칸의 개수
        int k; // 남은 뚫을 수 있는 개수
        State(int x, int y, int cnt, int k, boolean isDay) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.k = k;
        }
    }
    static int func() {
        Queue<State> q = new LinkedList<>();
        q.add(new State(0,0,1,K,true));
        visited[0][0][K] = true;
        State s = null;
        while ((s = q.poll()) != null) {
            if(s.x == N-1 && s.y ==  M-1) {
                return s.cnt;
            }
            int preX = s.x, preY = s.y, preCnt = s.cnt, preK = s.k;
            boolean preIsDay = (s.cnt % 2 == 1);
            for(int i=0; i<4; i++) {
                int newX = preX + dx[i], newY = preY + dy[i];
                if(newX < 0 || N <= newX || newY < 0 || M <= newY) continue;
                if(walls[newX][newY]) { // 새로 갈곳이 벽인 경우
                    if(preK == 0) continue; // 뚫을 남은 횟수가 없는 경우
                    if(!goodVisited(newX, newY, preK)) { // 더 좋은 조건으로 이전에 벽을 뚫고 해당 경로에서 오지 않은 경우
                        if(!preIsDay) { // 밤인 경우 낮이 될때까지 기다림
                            q.add(new State(preX, preY, preCnt+1, preK, !preIsDay));
                            continue;
                        }
                        visited[newX][newY][preK-1] = true;
                        q.add(new State(newX, newY, preCnt+1, preK-1, !preIsDay));
                    }
                }
                else { // 새로 갈곳이 벽이 아닌 경우
                    if(!goodVisited(newX, newY, preK)) { // 더 좋은 조건으로 해당 위치를 이미 방문한 경우
                        visited[newX][newY][preK] = true;
                        q.add(new State(newX, newY, preCnt+1, preK, !preIsDay));
                    }
                }
            }
        }
        return -1;
    }
    static boolean goodVisited(int x, int y, int k) { // 더 적은 벽 둟기 횟수로 방문 했는지 확인
        for(int i=k; i<=K; i++) {
            if(visited[x][y][i]) return true;
        }
        return false;
    }
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        walls = new boolean[N][M];
        visited = new boolean[N][M][K+1];
        for(int i=0; i<N; i++) {
            String line = br.readLine();
            for(int j=0; j<line.length(); j++) {
                walls[i][j] = line.charAt(j) == '1';
            }
        }
        System.out.println(func());
    }
}

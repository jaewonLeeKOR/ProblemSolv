import java.io.*;
import java.util.*;

/**
 * 입력
 * N (보드의 크기)
 * K (사과의 개수)
 * 행 열 (사과의 위치; K줄)
 * L (뱀의 방향 변환 횟수)
 * X C (게임 시작 후 X초가 지난 뒤 왼쪽(L) 또는 오른쪽(D)로 90도 방향으로 회전; L줄)
 *
 * 사과를 먹으면 길이가 늘어남, 벽 또는 자기 자신의 몸과 부딪히면 게임이 끝
 * 뱀위 첫 위치는 맨위 맨 좌측, 오른쪽을 향함
 *
 * 몸길이를 늘려 머리를 다음칸에 위치 -> Queue가 필요 -> LinkedList?
 * 벽이나 자기 자신의 몸과 부딪히면 게임이 끝남 -> Set이 필요 -> HashSet?
 * 이동한 칸에 사과가 있다면, 칸에 있던 사과가 없어지고 꼬리는 움직이지 않음
 * 없다면, 몸길이를 줄여서 꼬리가 위치한 칸을 비워줌
 *
 * 게임이 몇초에 끝나는지?
 */

public class Main {
    static int dx[] = {0,1,0,-1}; // idx 작아지면 왼쪽 회전
    static int dy[] = {1,0,-1,0}; // idx 커지면 오른쪽 회전
    static final int LEFT = 1, RIGHT = 2;
    static int N, K, L;
    static boolean apples[][] = {};
    static int moves[] = {};
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(bufferedReader.readLine());
        K = Integer.parseInt(st.nextToken());;
        apples = new boolean[N+1][N+1];
        for(int i=0; i<K; i++) {
            st = new StringTokenizer(bufferedReader.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            apples[x][y] = true;
        }
        st = new StringTokenizer(bufferedReader.readLine());
        L = Integer.parseInt(st.nextToken());
        moves = new int[10001];
        for(int i=0; i<L; i++) {
            st = new StringTokenizer(bufferedReader.readLine());
            int idx = Integer.parseInt(st.nextToken());
            if(st.nextToken().equals("L")) moves[idx] = LEFT;
            else moves[idx] = RIGHT;
        }

        int time = 0;
        int dir = 0; // 방향
        boolean bodyMap[][] = new boolean[N+1][N+1];
        bodyMap[1][1] = true;
        Deque<int[]> bodyQ = new LinkedList<>();
        bodyQ.addFirst(new int[]{1,1});
        while(true) {
            int curPos[] = Arrays.copyOf(bodyQ.getFirst(),2);
            time++;

            // 움직이기
            // 머리 움직이기
            curPos[0] = curPos[0] + dx[dir];
            curPos[1] = curPos[1] + dy[dir];

            // 종료 체크
            // 벽 체크
            if(curPos[0] == 0 || curPos[0] == N+1 || curPos[1] == 0 || curPos[1] == N+1)
                break;
            // 몸 체크
            if(bodyMap[curPos[0]][curPos[1]])
                break;

            bodyMap[curPos[0]][curPos[1]] = true;
            bodyQ.addFirst(new int[]{curPos[0], curPos[1]});

            // 움직인 자리에 사과가 없으면 꼬리 자르기
            if(!apples[curPos[0]][curPos[1]]) {
                int last[] = bodyQ.getLast();
                bodyMap[last[0]][last[1]] = false;
                bodyQ.removeLast();
            }
            else {
                apples[curPos[0]][curPos[1]] = false;
            }
            // 방향 회전 체크
            if(moves[time] != 0) {
                if(moves[time] == LEFT) {
                    dir = (dir + 3) % 4;
                }
                else {
                    dir = (dir + 1) % 4;
                }
            }
        }
        System.out.println(time);
    }
}

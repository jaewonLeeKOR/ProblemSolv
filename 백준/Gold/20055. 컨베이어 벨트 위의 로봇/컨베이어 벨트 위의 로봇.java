import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 길이가 N인 컨베이어벨트
 * 1번칸 -> 올리는 위치
 * N번칸 -> 내리는 위치
 *
 * 로봇을 올리는 위치에 올리거나 로봇이 어떤 칸으로 이동하면 그 칸의 내구도는 1만큼 감소함
 *
 * 1. 벨트가 각 칸 위에 있는 로볼과 함께 한칸 회전
 * 2. (이동하려는 칸에 로봇이 없고, 그 칸의 내구도가 1 이상 남아 있다면) 가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한칸 이동, 못하면 가만히 있음
 * 3. 올리는 위치의 내구도가 0이 아니면 올리는 위치에 로봇을 올림
 * 4. 내구도가 0인 칸의 개수가 K개 이상이면 과정 종료
 */
public class Main {
    static int N, K;
    static final int BROKEN = 0;
    static class Cell{
        public int power;
        public boolean includeRobot = false;
        Cell(int p) {
            power = p;
        }
    }
    static class Belt{
        List<Cell> belt;
        Integer level = 0;
        Integer brokenBeltCount = 0;
        Belt(List<Integer> powers) {
            belt = powers.stream().map(Cell::new).collect(Collectors.toCollection(LinkedList::new));
        }
        int doJob() {
            while(true) {
                if (brokenBeltCount >= K) {
                    return level;
                }
                level++;
                moveBelt();
                moveRobot();
                uploadRobot();
            }
        }
        void moveBelt() { // 벨트 회전
            belt.add(0, belt.remove(belt.size()-1));
            downloadRobot();
        }
        void moveRobot() { // 로봇 움직이기
            for(int i=N-1; i>=0; i--) {
                if(belt.get(i).includeRobot && !belt.get(i+1).includeRobot && belt.get(i+1).power != BROKEN) {
                    belt.get(i).includeRobot = false;
                    belt.get(i+1).includeRobot = true;
                    belt.get(i+1).power--;
                    if(belt.get(i+1).power == BROKEN)
                        brokenBeltCount++;
                }
            }
            downloadRobot();
        }
        void uploadRobot() { // 새로봇 올리기
            if(belt.get(0).power != BROKEN) {
                belt.get(0).includeRobot = true;
                belt.get(0).power--;
                if(belt.get(0).power == BROKEN)
                    brokenBeltCount++;
            }
        }
        void downloadRobot() {
            if(belt.get(N-1).includeRobot) {
                belt.get(N-1).includeRobot = false;
            }
        }
    }
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        List<Integer> powers = new LinkedList<>();
        for(int i=0; i<2*N; i++) {
            powers.add(Integer.parseInt(st.nextToken()));
        }
        Belt belt = new Belt(powers);
        System.out.println(belt.doJob());
    }
}

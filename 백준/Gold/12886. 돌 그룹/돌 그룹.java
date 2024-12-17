import java.io.*;
import java.util.*;

/**
 * 돌 그룹
 * 모든 그룹에 있는 돌의 개수를 같게!
 *
 * 단계
 * 크기가 같지 않은 두 그룹을 고름
 * 돌의 개수가 작은 쪽을 X, 큰쪽을 Y
 * X에 있는 돌의 개수를 X+X개로 Y에 있는 돌의 개수를 Y-X개로 만듦
 */
public class Main {
    static int A, B, C;
    static boolean available = false;
    static byte[][] indexPairs = {{0,1,2}, {0,2,1}, {1,2,0}}; // X, Y, 나머지
    static Set<Status> visited = new HashSet<>();
    static class Status {
        int[] rocks;
        Status(int ... oldStatus) {
            rocks = new int[]{oldStatus[0], oldStatus[1], oldStatus[2]};
        }
        void sort() {
            Arrays.sort(rocks);
        }
        public boolean allEquals() {
            return rocks[0] == rocks[1] && rocks[1] == rocks[2];
        }
        @Override
        public boolean equals(Object o) {
            Status s = (Status) o;
            return rocks[0] == s.rocks[0] && rocks[1] == s.rocks[1] && rocks[2] == s.rocks[2];
        }
        @Override
        public int hashCode() {
            return Objects.hash(rocks[0], rocks[1], rocks[2]);
        }
    }
    static void func(Status status) {
        if(status.allEquals()) {
            available = true;
            return;
        }
        Queue<Status> q = new LinkedList<>();
        q.add(status);
        while((status = q.poll()) != null) {
            for(byte[] indexPair : indexPairs) {
                int X = status.rocks[indexPair[0]];
                int Y = status.rocks[indexPair[1]];
                int L = status.rocks[indexPair[2]];
                Status newStatus = new Status(X+X, Y-X, L);
                newStatus.sort();
                if(visited.contains(newStatus)) continue;
                if(newStatus.allEquals()) {
                    available = true;
                    return;
                }
                visited.add(newStatus);
                q.add(newStatus);
            }
        }
    }
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        Status firstStatus = new Status(A, B, C);
        firstStatus.sort();
        func(firstStatus);
        System.out.println(available ? "1" : "0");
    }
}

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static Set<Integer> smallRock = new HashSet<>();
    static class Status {
        int prevJump;
        int jumpCount;
        Status(int prevJump, int jumpCount) {
            this.prevJump = prevJump;
            this.jumpCount = jumpCount;
        }
    }
    static List<Status> rockCases[];
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for(int i=0; i<M; i++)
            smallRock.add(Integer.parseInt(br.readLine()));
        rockCases = new LinkedList[N+1];
        for(int i=0; i<=N; i++) rockCases[i] = new LinkedList<>();
        rockCases[1].add(new Status(0, 0));
        for(int i=1; i<=N; i++) {
            if(smallRock.contains(i)) continue;
            List<Status> tmpCases = new ArrayList<>(rockCases[i]);
            if(i == N) {
                int minimumJump = Integer.MAX_VALUE;
                for(Status s : rockCases[i]) {
                    minimumJump = Math.min(minimumJump, s.jumpCount);
                }
                System.out.println(minimumJump == Integer.MAX_VALUE ? -1 : minimumJump);
                break;
            }
            if(tmpCases.isEmpty()) continue;
            Collections.sort(tmpCases, (Status s1, Status s2) -> {
                if(s1.prevJump != s2.prevJump) return Integer.compare(s1.prevJump, s2.prevJump);
                return Integer.compare(s1.jumpCount, s2.jumpCount);
            });
            Status prevS = null;
            for(int j=0; j<tmpCases.size(); j++) {
                Status s = tmpCases.get(j);
                if(prevS != null && s.prevJump == prevS.prevJump) continue;
                if ((s.prevJump - 1) > 0 && i + (s.prevJump - 1) <= N)
                    rockCases[i + (s.prevJump - 1)].add(new Status(s.prevJump - 1, s.jumpCount + 1));
                if (i + (s.prevJump) <= N)
                    rockCases[i + (s.prevJump)].add(new Status(s.prevJump, s.jumpCount + 1));
                if (i + (s.prevJump + 1) <= N)
                    rockCases[i + (s.prevJump + 1)].add(new Status(s.prevJump + 1, s.jumpCount + 1));
                prevS = s;
            }
        }
    }
}

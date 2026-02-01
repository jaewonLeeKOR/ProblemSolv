import java.io.*;
import java.util.*;

/**
 * 3명의 플레이어
 * N개의 카드
 *
 * 카드를 한번 섞으면 i번째 위치에 있던 카드가 S[i]로 이동
 * 카드는 맨 처음에 0~N-1 순서?
 */
public class Main {
    // pos 숫자가 어느 인덱스에 있는지
    // num 어느 인덱스에 어떤 숫자가 있는지
    static int n, pos[], ogPos[], num[], p[], s[];
    static boolean visited[];
    static boolean judge() {
        for (int i = 0; i < n; i++) {
            if(pos[i] % 3 != p[i]) return false;
        }
        return true;
    }
    static void shuffle() {
        Arrays.fill(visited, false);
        for (int i = 0; i < n; i++) {
            if(visited[i]) continue;
            int curIdx = i, nextIdx, curNum = num[curIdx], tmp;
            do {
                visited[curIdx] = true;
                nextIdx = s[curIdx];
                tmp = num[nextIdx];
                num[nextIdx] = curNum;
                curNum = tmp;
                curIdx = nextIdx;
                pos[num[curIdx]] = curIdx;
            } while (curIdx != i);
        }
    }
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        pos = new int[n];
        ogPos = new int[n];
        num = new int[n];
        p = new int[n];
        s = new int[n];
        visited = new boolean[n];
        for(int i=0; i<n; i++) {
            pos[i] = i;
            ogPos[i] = i;
            num[i] = i;
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            p[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            s[i] = Integer.parseInt(st.nextToken());
        }
        int cnt = 0;
        whileJudge:
        while(!judge()) {
            cnt++;
            shuffle();
            for (int i = 0; i < n; i++) {
                if(pos[i] != ogPos[i]) continue whileJudge;
            }
            System.out.println(-1);
            return;
        }
        System.out.println(cnt);
    }
}

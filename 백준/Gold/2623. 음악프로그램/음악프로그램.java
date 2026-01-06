import java.io.*;
import java.util.*;

// 나가는 간선이 0개인 가수가 우선 처리
public class Main {
    static int N, M;
    static Node singers[];
    static Stack<Integer> res = new Stack<>();
    static boolean isCycle = false;
    static class Node {
        public Set<Node> inCome = new HashSet<>();
        public Set<Node> outGo = new HashSet<>();
        public int num;
        public boolean visited = false;
        Node(int num) {
            this.num = num;
        }
        public boolean equals(Object obj) {
            Node node = (Node) obj;
            return num == node.num;
        }
        public int hashCode() {
            return Objects.hash(num);
        }
    }
    static Node findEnd(Node cur, int depth) {
        if(depth >= N) {
            isCycle = true;
            return null;
        }
        for (Node next : cur.outGo) {
            if(next.visited) continue;
            return findEnd(next, depth+1);
        }
        return cur;
    }
    static void func(Node cur) {
        if(cur.visited) return;
        Node end = findEnd(cur, 0);
        if(isCycle) return;
        end.visited = true;
        res.push(end.num);
        for (Node newEnd : end.inCome) {
            newEnd.outGo.remove(end);
        }
        for (Node newEnd : end.inCome) {
            func(newEnd);
        }
    }
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        singers = new Node[N];
        for (int i = 0; i < N; i++) {
            singers[i] = new Node(i);
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            int prev = -1, cur;
            for (int j = 0; j < cnt; j++) {
                cur = Integer.parseInt(st.nextToken()) - 1;
                if(prev != -1) {
                    singers[prev].outGo.add(singers[cur]);
                    singers[cur].inCome.add(singers[prev]);
                }
                prev = cur;
            }
        }

        for (int i = 0; i < N; i++) {
            func(singers[i]);
            if(isCycle) break;
        }

        if(isCycle) {
            System.out.println(0);
            return;
        }
        StringBuffer sb = new StringBuffer();
        while (!res.isEmpty()) {
            sb.append(res.pop()+1).append('\n');
        }
        System.out.println(sb);
    }
}

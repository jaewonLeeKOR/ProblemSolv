import java.io.*;
import java.util.*;

public class Main {
    static int T;
    static class Node {
        Set<Node> income = new HashSet<>(), outgo = new HashSet<>();
        int num, cost, completeTime = 0;
        boolean shouldVisit = false, visited = false;
        Node(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }
        public boolean equals(Object obj) {
            Node node = (Node) obj;
            return this.num == node.num;
        }
        public int hashCode() {
            return this.num;
        }
    }
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        int N, K;
        StringBuffer sb = new StringBuffer();
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            Node nodes[] = new Node[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                int d = Integer.parseInt(st.nextToken());
                nodes[i] = new Node(i, d);
            }
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken()) - 1;
                int y = Integer.parseInt(st.nextToken()) - 1;
                nodes[x].outgo.add(nodes[y]);
                nodes[y].income.add(nodes[x]);
            }
            int W = Integer.parseInt(br.readLine()) - 1;
            Node curNode = nodes[W];
            Queue<Node> q = new LinkedList<>();
            q.add(curNode);
            while((curNode = q.poll()) != null) {
                curNode.shouldVisit = true;
                for (Node prev : curNode.income) {
                    if(prev.shouldVisit) continue;
                    q.add(prev);
                }
            }
            int maxTime = 0;
            nodes[W].completeTime = nodes[W].cost;
            q.add(nodes[W]);
            while((curNode = q.poll()) != null) {
                maxTime = Math.max(maxTime, curNode.completeTime);
                curNode.visited = true;
                prevLoop:
                for (Node prev : curNode.income) {
                    prev.completeTime = Math.max(prev.completeTime, curNode.completeTime + prev.cost);
                    for (Node prevNext : prev.outgo) {
                        if(prevNext.shouldVisit && !prevNext.visited) {
                            prev.outgo.remove(curNode);
                            continue prevLoop;
                        }
                    }
                    q.add(prev);
                }
            }
            sb.append(maxTime).append('\n');
        }
        System.out.println(sb);
    }
}

import java.io.*;
import java.util.*;

public class Main {
    static int T;
    static Node students[];
    static class Node {
        int curNode, nextNode;
        boolean inTeam = false;
        boolean visited = false;
        Node(int curNode, int nextNode) {
            this.curNode = curNode;
            this.nextNode = nextNode;
        }

        @Override
        public int hashCode() {
            return Objects.hash(curNode, nextNode);
        }

        @Override
        public boolean equals(Object obj) {
            Node node = (Node) obj;
            return curNode == node.curNode && nextNode == node.nextNode;
        }
    }
    static void checkTeam(int node) {
        Node curNode = students[node];
        Set<Node> set = new HashSet<>();
        LinkedList<Node> queue = new LinkedList<>();
        queue.addLast(curNode);
        while(!set.contains(curNode)) {
            if(curNode.inTeam || curNode.visited) {
                for (Node n : queue) {
                    n.visited = true;
                }
                return;
            }
            set.add(curNode);
            queue.addLast(curNode);
            curNode = students[curNode.nextNode];
        }
        for(Node n : queue) {
            n.visited = true;
        }
        Node lastNode = curNode;
        lastNode.inTeam = true;
        while(!queue.isEmpty() && lastNode.curNode != (curNode = queue.pollLast()).curNode) {
            curNode.inTeam = true;
        }
    }
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuffer sb = new StringBuffer();
        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());
            students = new Node[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                students[i] = new Node(i, Integer.parseInt(st.nextToken())-1);
            }
            for (int i = 0; i < n; i++) {
                if(students[i].inTeam) continue;
                checkTeam(i);
            }
            sb.append(Arrays.stream(students).filter(s -> !s.inTeam).count()).append('\n');
        }
        System.out.print(sb);
    }
}

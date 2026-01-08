import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static Node students[];
    static Stack<Integer> stack = new Stack();
    static class Node {
        Set<Node> income = new HashSet<>(), outgo = new HashSet<>();
        int num;
        boolean visited = false;
        Node(int num) {
            this.num = num;
        }
    }
    static void func(Node n) {
        if(n.visited) return;
        for (Node next : new ArrayList<Node>(n.outgo)) {
            if(next.visited) continue;
            func(next);
        }
        if(n.outgo.isEmpty()) {
            n.visited = true;
            stack.add(n.num);
            for (Node incomeNode : n.income) {
                incomeNode.outgo.remove(n);
            }
        }
    }
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        students = new Node[N];
        for (int i = 0; i < N; i++) {
            students[i] = new Node(i);
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            students[a].outgo.add(students[b]);
            students[b].income.add(students[a]);
        }
        for (int i = 0; i < N; i++) {
            if(students[i].visited) continue;
            func(students[i]);
        }
        StringBuffer sb = new StringBuffer();
        while (!stack.isEmpty()) {
            sb.append(stack.pop() + 1).append(' ');
        }
        System.out.println(sb);
    }
}

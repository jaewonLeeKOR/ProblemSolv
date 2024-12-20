import java.io.*;
import java.util.*;

/**
 * 각 정점의 순환선까지의 거리 구하기
 */
public class Main {
    static int N;
    static class Node {
        List<Node> adjacentNode;
        int num;
        int distance;
        boolean onCircleLine;
        Node(int num) {
            adjacentNode = new LinkedList<>();
            this.num = num;
            distance = -1;
            onCircleLine = false;
        }
    }
    static Node[] nodes;
    static boolean catchCircle(int num, Set<Integer> visited, int prevNum) {
        if(visited == null) visited = new HashSet<>(N);
        if(visited.contains(num)) {
            nodes[num].onCircleLine = true;
            nodes[num].distance = 0;
            return true;
        }
        visited.add(num);
        for(Node next : nodes[num].adjacentNode) {
            if(next.num == prevNum) continue;
            if(catchCircle(next.num, visited, num)) {
                if(nodes[num].onCircleLine) { // 이미 한바퀴 돌음
                    return false;
                }
                nodes[num].onCircleLine = true;
                nodes[num].distance = 0;
                return true;
            }
        }
        return false;
    }
    static int distanceToCircle(int num, Set<Integer> visited) {
        if(nodes[num].distance != -1)
            return nodes[num].distance;
        if(visited == null) visited = new HashSet<>();
        visited.add(num);
        int minDistance = 3001;
        for(Node next : nodes[num].adjacentNode) {
            if(visited.contains(next.num)) continue;
            int distance = distanceToCircle(next.num, visited);
            if(distance != -1)
                minDistance = Math.min(minDistance, distance);
        }
        if(minDistance == 3001)
            return -1;
        minDistance++;
        nodes[num].distance = minDistance;
        return minDistance;
    }
    static void calculateDistance() {
        for(int i=1; i<=N; i++) {
            if(nodes[i].distance == -1) {
                distanceToCircle(i, null);
            }
        }
    }
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nodes = new Node[N+1];
        for(int i=1; i<=N; i++) {
            nodes[i] = new Node(i);
        }
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x, y;
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            nodes[x].adjacentNode.add(nodes[y]);
            nodes[y].adjacentNode.add(nodes[x]);
        }
        // 순환선 정의
        catchCircle(1, null, -1);
        // 거리 구하기
        calculateDistance();
        // 출력
        StringBuffer sb = new StringBuffer();
        for(int i=1; i<=N; i++)
            sb.append(nodes[i].distance + " ");
        System.out.println(sb);
    }
}

import java.util.*;
import java.io.*;

/**
 * N개의 지역, 그 지역 사이를 잇는 몇개의 횡단보도
 *
 * 횡단보도의 주기 : M분
 * 횡단보도 건너는데 1분 걸림
 *
 * 필요한것
 * -> 켜지는 주기 시각 저장 객체
 * -> 이미 방문한 노드 저장 객체
 * -> 인접한 노드 저장 객체
 *
 * 흐름
 * - 초기 세팅 -> 1 을 근접한 노드로 저장
 *  - 근접한 노드 중 가장 가까운 노드 추출, 최소 방문 가능 시각으로 기록
 *  - 추출한 노드에서 인접한 노드를 인접한 노드로 저장
 *  - N번 노드까지 반복
 */
public class Main {
    static int N, M;
    static List<List<Edge>> edges;
    static long distances[];
    static class Vertex{
        Integer num;
        Long distance;
        Vertex(int num, long distance) {
            this.num = num;
            this.distance = distance;
        }
    }
    static class Edge{
        int A, B, cycle;
        Edge(int A, int B, int cycle) {
            this.A = A;
            this.B = B;
            this.cycle = cycle;
        }
    }
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        edges = new ArrayList<>();
        for(int i=0; i<N+1; i++)
            edges.add(new LinkedList<>());
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int A, B;
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            Edge edge = new Edge(A,B,i);
            edges.get(A).add(edge);
            edges.get(B).add(edge);
        }

        distances = new long[N+1];
        for(int i=0; i<N+1; i++) {
            distances[i] = Long.MAX_VALUE;
        }
        distances[1] = 0;
        Queue<Vertex> pq = new PriorityQueue<>(M, (Vertex v1, Vertex v2) -> {
            return v1.distance.compareTo(v2.distance);
        });
        pq.add(new Vertex(1,0));
        while(!pq.isEmpty()) {
            Vertex curVertex = pq.poll();
            int current = curVertex.num;
            long curDistance = curVertex.distance;
            if(distances[current] < curDistance) continue; // 이미 방문한 경우 제외
            // 현재의 주기 위치
            long curCycle = curDistance % M;
            for(Edge nextEdge : edges.get(current)) {
                int next = nextEdge.A == current ? nextEdge.B : nextEdge.A; // 다음 노드
                int nextCycle = nextEdge.cycle;
                // 이동 시작 시간 계산
                long signalDistance;
                if(curCycle <= nextCycle)
                    signalDistance = nextCycle - curCycle;
                else
                    signalDistance = M + nextCycle - curCycle;
                // 이동한 후 시간 계산
                long nextDistance = curDistance + signalDistance + 1;
                if(nextDistance < distances[next]) { // 기존 걸리는 시간보다 짧은 경우 큐에 넣어줌
                    distances[next] = nextDistance;
                    pq.add(new Vertex(next, distances[next]));
                }
            }
        }
        System.out.println(distances[N]);
    }
}

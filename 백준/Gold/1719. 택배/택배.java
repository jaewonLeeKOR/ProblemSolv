import java.io.*;
import java.util.*;

public class Main {
    static int N, M, roads[][], firstVertex[][];
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        roads = new int[N][N];
        for(int[] road : roads)
            Arrays.fill(road, Integer.MAX_VALUE >> 2);
        M = Integer.parseInt(st.nextToken());
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()),
                end = Integer.parseInt(st.nextToken()),
                dst = Integer.parseInt(st.nextToken());
            roads[start-1][end-1] = dst;
            roads[end-1][start-1] = dst;
            roads[start-1][start-1] = 0;
            roads[end-1][end-1] = 0;
        }

        firstVertex = new int[N][N];
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(i == j)
                    firstVertex[i][i] = -1;
                else
                    firstVertex[i][j] = j;
            }
        }

        Set<Integer> endedVertexes = new HashSet<>();
        Queue<Candidate> candidateVertexes = new PriorityQueue<>((o1, o2) -> {
            if(o1.distance == o2.distance) return Integer.compare(o1.vertex,o2.vertex);
            return Integer.compare(o1.distance, o2.distance);
        });
        for(int startedVertex=0; startedVertex<N; startedVertex++) {
            endedVertexes.clear();
            candidateVertexes.clear();

            endedVertexes.add(startedVertex);
            for(int i=0; i<N; i++) {
                if(startedVertex==i) continue;
                candidateVertexes.add(new Candidate(roads[startedVertex][i], i, i));
            }

            while(endedVertexes.size() < N) {
                Candidate curCandidate = candidateVertexes.poll();
                int curDistance = curCandidate.distance, curVertex = curCandidate.vertex, curFirstVertex = curCandidate.firstVertex;
                if(endedVertexes.contains(curVertex)) continue;
                endedVertexes.add(curVertex);
                roads[startedVertex][curVertex] = curDistance;
                firstVertex[startedVertex][curVertex] = curFirstVertex;
                for(int i=0; i<N; i++) {
                    if(endedVertexes.contains(i)) continue;
                    candidateVertexes.add(new Candidate(curDistance + roads[curVertex][i], i, curFirstVertex));
                }
            }
        }

        StringBuffer sb = new StringBuffer();
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                sb.append((firstVertex[i][j] == -1 ? "-" : (firstVertex[i][j] + 1))).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static class Candidate {
        int distance;
        int vertex;
        int firstVertex;
        Candidate(int distance, int vertex, int firstVertex) {
            this.distance = distance;
            this.vertex = vertex;
            this.firstVertex = firstVertex;
        }
    }
}

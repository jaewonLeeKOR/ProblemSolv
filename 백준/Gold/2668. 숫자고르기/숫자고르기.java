import java.io.*;
import java.util.*;

/**
 * 세로 두줄, 가로 N개의 칸으로 이루어진 표
 * 첫째줄 - 각 칸에 1~N의 수 (시작 노드)
 * 둘째줄 - 1이상 N이하인 정수 (도착 노드)
 *
 * 순환 그래프 들의 크기 합
 */
public class Main {
    static int N;
    static boolean visited[];
    static int edges[];
    static List<List<Integer>> cycles = new ArrayList<>();
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        edges = new int[N+1];
        for(int i=1; i<N+1; i++) {
            int t = Integer.parseInt(br.readLine());
            edges[i] = t;
        }
        visited = new boolean[N+1];
        bigLoop: // label 사용하여 바로 continue
        for(int cur=1; cur<N+1; cur++) {
            if(visited[cur]) continue;
            // 사이클이 생길떄까지 집합 키우기
            Set<Integer> tmpSet = new HashSet<>();
            int next = cur;
            while(!tmpSet.contains(next)) { // 방문 지점의 중복이 생기면 사이클 형성
                if(visited[next]) continue bigLoop;
                visited[next] = true;
                tmpSet.add(next);
                next = edges[next];
            }
            // 집합 초기화
            tmpSet.clear();
            while(!tmpSet.contains(next)) { // 사이클 부분만 집합에 등록
                tmpSet.add(next);
                next = edges[next];
            }
            cycles.add(new ArrayList<>(tmpSet));
        }
        // 순환 그래프 정점 리스트 합치기
        List<Integer> vertexes = new ArrayList<>(100);
        for(List<Integer> cycle : cycles) {
            for(Integer vertex : cycle) {
                vertexes.add(vertex);
            }
        }
        Collections.sort(vertexes);
        StringBuilder sb = new StringBuilder();
        sb.append(vertexes.size() + "\n");
        for(Integer vertex : vertexes)
            sb.append(vertex + "\n");
        System.out.println(sb);
    }
}

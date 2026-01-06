import java.io.*;
import java.util.*;

public class Main {
    static int V, E;
    static LinkedList<Edge> edges;
    static int[] parent;
    static long spanningMess = 0, edgeCount = 0;
    static class Edge {
        int v1, v2;
        long m;
        Edge(int v1, int v2, int m) {
            if(v1 < v2) {
                this.v1 = v1;
                this.v2 = v2;
            }
            else {
                this.v1 = v2;
                this.v2 = v1;
            }
            this.m = m;
        }
    }
    static int findRoot(int v) {
        if(parent[v] != v)
            parent[v] = findRoot(parent[v]);
        return parent[v];
    }
    static boolean union(int x, int y) {
        int rootX = findRoot(x);
        int rootY = findRoot(y);
        if (rootX == rootY) return false;
        parent[rootY] = rootX;
        return true;
    }
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        edges = new LinkedList<>();
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            if(v1 == v2) continue;
            edges.add(new Edge(v1-1,v2-1,m));
        }
        Collections.sort(edges, (e1, e2) -> Long.compare(e1.m, e2.m));
        parent = new int[V];
        for (int i = 0; i < V; i++) {
            parent[i] = i;
        }
        Edge curEdge = edges.pollFirst();
        if(curEdge == null) {
            System.out.println(0);
            return;
        }
        union(curEdge.v1, curEdge.v2);
        spanningMess += curEdge.m;
        while ((curEdge = edges.pollFirst()) != null) {
            if(union(curEdge.v1, curEdge.v2)) {
                spanningMess += curEdge.m;
                if (++edgeCount == V-1) break;
            }
        }
        System.out.println(spanningMess);
    }
}

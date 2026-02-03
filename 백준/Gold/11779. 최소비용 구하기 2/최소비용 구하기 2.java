import java.io.*;
import java.util.*;

public class Main {
    static int n, m, s, d;
    static City cities[];
    static Set<City> visited = new HashSet<>(); // city#
    static PriorityQueue<CityCost> candidate = new PriorityQueue<>(100_000, (s1, s2) -> {
        return Long.compare(s1.cost, s2.cost);
    }); // cost -> city#
    static class CityCost {
        long cost;
        City city;
        CityCost(City city, long cost) {
            this.city = city;
            this.cost = cost;
        }
    }
    static class City {
        int num;
        long minCost = Long.MAX_VALUE;
        List<CityCost> outgoing = new ArrayList<>(); // city#, cost
        City parent = null;
        City(int num) {
            this.num = num;
        }
        public boolean equals(Object obj) {
            City c = (City) obj;
            return num == c.num;
        }
        public int hashCode() {
            return num;
        }
    }
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        cities = new City[n];
        for (int i = 0; i < n; i++) {
            cities[i] = new City(i);
        }
        StringTokenizer st;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int srt = Integer.parseInt(st.nextToken())-1, dst = Integer.parseInt(st.nextToken())-1, cost = Integer.parseInt(st.nextToken());
            cities[srt].outgoing.add(new CityCost(cities[dst], cost));
        }
        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken())-1;
        d = Integer.parseInt(st.nextToken())-1;
        candidate.add(new CityCost(cities[s], 0));
        while(!candidate.isEmpty()) {
            CityCost cityCost = candidate.poll();
            if(visited.contains(cityCost.city)) continue;
            City cur = cityCost.city;
            visited.add(cur);
            cur.minCost = cityCost.cost;

            for (CityCost cc : cur.outgoing) {
                City next = cc.city;
                if(visited.contains(next)) continue;
                long nextCost = cur.minCost + cc.cost;
                if(nextCost < next.minCost) {
                    next.minCost = nextCost;
                    next.parent = cur;
                    candidate.add(new CityCost(next, nextCost));
                }
            }
        }

        Stack<City> cityStack = new Stack<>();
        City cur = cities[d];
        while(cur != null) {
            cityStack.push(cur);
            cur = cur.parent;
        }

        StringBuffer sb = new StringBuffer();
        sb.append(cities[d].minCost).append('\n');
        sb.append(cityStack.size()).append('\n');
        while (!cityStack.isEmpty()) {
            sb.append(cityStack.pop().num+1).append(' ');
        }
        System.out.println(sb);
    }
}

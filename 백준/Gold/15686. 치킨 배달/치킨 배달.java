import java.io.*;
import java.util.*;

/**
 * N*N 도시 -> 빈칸(0), 치킨집(2), 집(1) 중 하나
 * 도시의 치킨거리 : 모든 집의 치킨 거리의 합
 * 도시의 치킨거리가 최소가 될 M개의 치킨집이 남았을때의 치킨거리
 *
 * 치킨집별
 * BFS 필요없을듯? 거리를 산수로
 */

public class Main {
    static final int NONE = 0, HOUSE = 1, BBQ = 2;
    static int N, M;
    static int chickenMap[][];
    static List<Point> houses = new ArrayList<>();
    static List<Point> chickens = new ArrayList<>();
    static int chickenToHouse[][];
    static int maxChicken = Integer.MAX_VALUE;
    static class Point {
        public int r, c;
        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
        public int getDistance(Point p) {
            return Math.abs(r - p.r) + Math.abs(c - p.c);
        }
    }
    static public void comb(boolean status[], int curIdx, int r, int cnt) {
        if(r == cnt) {
            List<Integer> chickenIndexes = new ArrayList<>();
            for(int i=0; i<status.length; i++) {
                if(status[i]) chickenIndexes.add(i);
            }
            int currentChickenScore = 0;
            for(int j=0; j< houses.size(); j++) {
                int maxChickenPerHouse = Integer.MAX_VALUE;
                for(Integer chickenIndex : chickenIndexes) {
                    maxChickenPerHouse = Math.min(maxChickenPerHouse, chickenToHouse[chickenIndex][j]);
                }
                currentChickenScore += maxChickenPerHouse;
            }
            maxChicken = Math.min(maxChicken, currentChickenScore);
            return;
        }
        for(int i=curIdx; i<chickens.size(); i++) {
            status[i] = true;
            comb(status, i+1, r, cnt+1);
            status[i] = false;
        }
    }
    public static void main(String[] args) throws Exception{
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        chickenMap = new int[N][N];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                chickenMap[i][j] = Integer.parseInt(st.nextToken());
                if(chickenMap[i][j] == BBQ)
                    chickens.add(new Point(i, j));
                if(chickenMap[i][j] == HOUSE)
                    houses.add(new Point(i, j));
            }
        }
        chickenToHouse = new int[chickens.size()][houses.size()];
        for(int i=0; i<chickens.size(); i++) {
            for(int j=0; j<houses.size(); j++) {
                chickenToHouse[i][j] = chickens.get(i).getDistance(houses.get(j));
            }
        }

        comb(new boolean[chickens.size()], 0, M, 0);
        System.out.println(maxChicken);
    }
}

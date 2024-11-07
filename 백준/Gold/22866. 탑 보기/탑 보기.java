import java.io.*;
import java.util.*;

/**
 * 다양한 높이의 건물 총 N개
 * 각 건물 옥상에서 양 옆 건물의 옆을 몇개 볼수 있는지
 * 현재 건물의 높이 L보다 큰곳의 건물만 볼 수 있음
 *
 * 좌,우에서 빌딩 높이를 차례대로 저장하면서 현재 빌딩보다 높은 빌딩 개수를 계산
 *
 *
 */
public class Main {
    static int N;
    static class Building implements Comparable<Building> {
        int cnt;
        int nearestBuilding;
        int num;
        int height;
        Building(int num, int height) {
            cnt = 0;
            nearestBuilding = Integer.MAX_VALUE;
            this.num = num;
            this.height = height;
        }
        @Override
        public int compareTo(Building other) {
            if(this.height == other.height) return Integer.compare(this.num, other.num);
            return Integer.compare(this.height, other.height);
        }
        @Override
        public String toString() {
            return cnt + " " + nearestBuilding + " " + num + " " + height;
        }
    }
    static Building buildings[];
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        buildings = new Building[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            int h = Integer.parseInt(st.nextToken());
            buildings[i] = new Building(i, h);
        }

        List<Building> leftBuildings = new ArrayList<>(N);
        for(int i=0; i<N; i++) {
            func(buildings[i], leftBuildings);
        }
        List<Building> rightBuildings = new ArrayList<>(N);
        for(int i=N-1; i>=0; i--) {
            func(buildings[i], rightBuildings);
        }

        for(Building b:buildings) {
            if(b.cnt == 0)
                System.out.println(0);
            else
                System.out.println(b.cnt + " " + (b.nearestBuilding+1));
        }
    }

    static void func(Building curBuilding, List<Building> sideBuildings) {
        int idx = Collections.binarySearch(sideBuildings, curBuilding);
        if(idx < 0) { // 현재 건물의 높이와 같은 건물이 없는 경우
            idx = -1 * (idx+1);
        }
        if(idx != sideBuildings.size()) { // 현재 건물의 높이와 같거나 큰 건물이 있는 경우
            int biggerBuildingIdx = idx;
            while(true) { // biggerBuildingIdx 찾기
                if(biggerBuildingIdx == sideBuildings.size()) break;
                if(sideBuildings.get(biggerBuildingIdx).height > curBuilding.height) break;
                biggerBuildingIdx++;
            }
            if(biggerBuildingIdx == sideBuildings.size()) { // 현재 건물의 높이보다 큰 건물이 존재하지 않는 경우
                sideBuildings.clear();
                sideBuildings.add(curBuilding);
                return;
            }
            while(sideBuildings.get(0).height <= curBuilding.height) // 주소값은 유지하면서 현재 빌딩보다 작거나 같은 빌딩 없애주기
                sideBuildings.remove(0);
//            sideBuildings = sideBuildings.subList(biggerBuildingIdx, sideBuildings.size()); // 새로운 주소가 생겨버려서 Reference 자체가 바뀌어 메서드 호출을 한 쪽의 데이터는 바뀌지 않음
            if(Math.abs(sideBuildings.get(0).num - curBuilding.num) < Math.abs(curBuilding.nearestBuilding - curBuilding.num)) { // 더 가까운 빌딩인 경우
                curBuilding.nearestBuilding = sideBuildings.get(0).num;
            }
            curBuilding.cnt += sideBuildings.size();
            sideBuildings.add(0, curBuilding);
            return;
        }
        sideBuildings.clear();
        sideBuildings.add(curBuilding);
    }
}

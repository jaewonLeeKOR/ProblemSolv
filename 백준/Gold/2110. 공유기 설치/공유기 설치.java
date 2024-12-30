import java.io.*;
import java.util.*;

// 가장 인접한 공유기 사이의 거리를 가능한 크게
// 가장 인접한 공유기 사이의 거리를 기준으로 매개변수 탐색
public class Main {
    static int N, C;
    static int[] houses;
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        houses = new int[N];
        for(int i=0; i<N; i++) {
            houses[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(houses);

        int startDistance = 0, endDistance = houses[houses.length-1] - houses[0], maxDistance = 0;
        while(startDistance <= endDistance) {
            int midDistance = (startDistance + endDistance) / 2, prevInstalledHome = houses[0], installedCnt = 1;
            for(int i=1; i<N; i++) {
                if(houses[i] - prevInstalledHome >= midDistance) {
                    prevInstalledHome = houses[i];
                    installedCnt++;
                }
            }
            if(installedCnt >= C) { // 더 많이 설치해도 됨
                maxDistance = midDistance;
                startDistance = midDistance + 1;
            }
            else {
                endDistance = midDistance - 1;
            }
        }
        System.out.println(maxDistance);
    }
}

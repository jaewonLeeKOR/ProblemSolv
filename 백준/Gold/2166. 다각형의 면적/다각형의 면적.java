import java.io.*;
import java.util.*;

/**
 * 신발끈 공식
 */
public class Main {
    static int N;
    static Point points[];
    static class Point {
        long x,y;
        Point (long x, long y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        points = new Point[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            points[i] = new Point(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
        }
        double sum1 = 0, sum2 = 0;
        for (int i = 0; i < N; i++) {
            int ni = (i+1) % N;
            sum1 += points[i].x * points[ni].y;
            sum2 += points[i].y * points[ni].x;
        }
        System.out.println(String.format("%.1f", ((Math.abs(sum1 - sum2)) / 2)));
    }
}

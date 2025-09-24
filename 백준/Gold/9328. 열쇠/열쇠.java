import java.io.*;
import java.util.*;

/**
 * 훔칠 수 있는 문서 최대 개수
 * 대문자 : 문, 소문자 : 열쇠
 * <p>
 * 시작지점 후보 찾기
 * 탐색
 * -> 탐색 중 새로운 키를 찾으면 탐색 기록 초기화 & 시작지점 재탐색
 */

public class Main {
    private static char[][] slice;
    private static boolean[] keys;
    private static boolean[][] visited;
    private static LinkedList<Point> candidates;
    private static int h, w, docCnt;
    private static final int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};

    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static void addStartPoints() {
        for (int i = 0; i < h; i++) {
            if (slice[i][0] != '*') {
                candidates.add(new Point(i, 0));
            }
            if (slice[i][w - 1] != '*') {
                candidates.add(new Point(i, w - 1));
            }
        }
        for (int i = 0; i < w; i++) {
            if (slice[0][i] != '*') {
                candidates.add(new Point(0, i));
            }
            if (slice[h - 1][i] != '*') {
                candidates.add(new Point(h - 1, i));
            }
        }
    }

    private static boolean visit(int x, int y) {
        if (x < 0 || h <= x || y < 0 || w <= y) {
            addStartPoints();
            return false;
        }
        if (visited[x][y]) return false;
        if (slice[x][y] == '*') return false;
        if ('A' <= slice[x][y] && slice[x][y] <= 'Z') {
            if (keys[slice[x][y] - 'A']) {
                visited[x][y] = true;
                slice[x][y] = '.';
                return true;
            }
            return false;
        }
        if ('a' <= slice[x][y] && slice[x][y] <= 'z') {
            if (keys[slice[x][y] - 'a']) {
                visited[x][y] = true;
                slice[x][y] = '.';
                return true;
            }
            visited = new boolean[h][w];
            visited[x][y] = true;
            keys[slice[x][y] - 'a'] = true;
            slice[x][y] = '.';
            candidates.clear();
            return true;
        }
        if (slice[x][y] == '$') {
            slice[x][y] = '.';
            docCnt++;
        }
        visited[x][y] = true;
        return true;
    }

    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        for (int t = 0; t < tc; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            slice = new char[h][w];
            keys = new boolean[26];
            visited = new boolean[h][w];
            candidates = new LinkedList<>();

            for (int i = 0; i < h; i++) {
                String s = br.readLine();
                slice[i] = s.toCharArray();
            }

            String keyString = br.readLine();
            if (keyString.charAt(0) != '0') {
                for (char key : keyString.toCharArray()) {
                    keys[key - 'a'] = true;
                }
            }

            docCnt = 0;
            addStartPoints();
            while (candidates.size() >= 0) {
                Point cur = candidates.pollFirst();
                if (cur == null) break;
                if (visit(cur.x, cur.y)) {
                    for (int d = 0; d < 4; d++) {
                        int nx = cur.x + dx[d], ny = cur.y + dy[d];
                        candidates.add(new Point(nx, ny));
                    }
                }
            }
            System.out.println(docCnt);
        }
    }
}

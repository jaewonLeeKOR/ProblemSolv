import java.io.*;
import java.util.*;

/** 백준 4179
 */

public class Main {
    static final char WALL = '#', SPACE = '.', JH = 'J', FIRE = 'F';
    static final int dx[] = {1,-1,0,0}, dy[] = {0,0,1,-1};
    static class Pos {
        int x, y;
        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
        @Override
        public boolean equals(Object obj) {
            if(obj instanceof Pos){
                if(((Pos) obj).x == x && ((Pos) obj).y == y){
                    return true;
                }
            }
            return false;
        }
    }
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Integer n = Integer.parseInt(st.nextToken());
        Integer m = Integer.parseInt(st.nextToken());

        Set<Pos> jh = new HashSet<>();
        Set<Pos> fire = new HashSet<>();
        char map[][] = new char[n][m];
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = s.charAt(j);
                if(map[i][j] == JH)
                    jh.add(new Pos(i, j));
                if(map[i][j] == FIRE)
                    fire.add(new Pos(i, j));
            }
        }

        int time = 0;
        BFS:
        while(!jh.isEmpty()) {
            time++;
            Set<Pos> nextJh = new HashSet<>();
            Set<Pos> nextFire = new HashSet<>();

            for (Object obj : fire.toArray()) {
                Pos pos = (Pos) obj;
                for (int i = 0; i < 4; i++) {
                    if(pos.x + dx[i] < 0 || pos.x + dx[i] >= n || pos.y + dy[i] < 0 || pos.y + dy[i] >= m) continue;
                    if(map[pos.x + dx[i]][pos.y + dy[i]] == WALL) continue;
                    if(map[pos.x + dx[i]][pos.y + dy[i]] == FIRE) continue;
                    nextFire.add(new Pos(pos.x + dx[i], pos.y + dy[i]));
                    map[pos.x + dx[i]][pos.y + dy[i]] = FIRE;
                }
            }

            for (Object obj : jh.toArray()) {
                Pos pos = (Pos) obj;
                for (int i = 0; i < 4; i++) {
                    if(pos.x + dx[i] < 0 || pos.x + dx[i] >= n || pos.y + dy[i] < 0 || pos.y + dy[i] >= m) {
                        break BFS;
                    }
                    if(map[pos.x + dx[i]][pos.y + dy[i]] == WALL) continue;
                    if(map[pos.x + dx[i]][pos.y + dy[i]] == FIRE) continue;
                    if(map[pos.x + dx[i]][pos.y + dy[i]] == JH) continue;
                    nextJh.add(new Pos(pos.x + dx[i], pos.y + dy[i]));
                    map[pos.x + dx[i]][pos.y + dy[i]] = JH;
                }
            }

            jh.clear();
            fire.clear();
            jh.addAll(nextJh);
            fire.addAll(nextFire);
        }


        if(jh.isEmpty())
            System.out.println("IMPOSSIBLE");
        else
            System.out.println(time);
    }
}

import java.io.*;
import java.util.*;

/**
 * N*N의 땅
 *
 * 인구이동
 * - 인접한 두 나라의 인구차이가 L명 이상, R명 이하면 국경선을 열음
 * - 국경선이 모두 열리고 난 후 인구 이동
 * - 국경선이 열린 각 나라의 인구수를 (총 인구수) / (국경선이 열린 나라의 개수) 로 변경 (소수점 버림)
 * - 인구이동일 없을때까지 반복
 * 인구이동이 며칠동안 발생하는지 반환
 */
public class Main {
    static int N, L, R;
    static Country[][] countries;
    static int dx[] = {0,1,0,-1}, dy[] = {1,0,-1,0};
    static class Country {
        int population;
        boolean openedDir[] = new boolean[4];
        Country() {}
    }
    static boolean open() {
        boolean notEnd = false;
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                for (int d = 0; d < 4; d++) {
                    int nx = x + dx[d], ny = y + dy[d];
                    if (nx < 0 || N <= nx || ny < 0 || N <= ny) continue;
                    int diff = Math.abs(countries[x][y].population - countries[nx][ny].population);
                    if (diff < L || R < diff) continue;
                    countries[x][y].openedDir[d] = true;
                    notEnd = true;
                }
            }
        }
        return notEnd;
    }
    static class Position {
        int x, y;
        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
        @Override
        public boolean equals(Object other) {
            Position otherP = (Position) other;
            return x == otherP.x && y == otherP.y;
        }
        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
    static void average() {
        for(int x=0; x<N; x++) {
            for(int y=0; y<N; y++) {
                Set<Position> allianceCountries = new HashSet<>();
                allianceCountries.add(new Position(x,y));
                int allianceSum = 0;
                int allianceCnt = 0;

                Queue<Position> q = new LinkedList<>();
                q.add(new Position(x,y));
                Position curPos;
                while((curPos = q.poll()) != null) {
                    allianceSum += countries[curPos.x][curPos.y].population;
                    allianceCnt++;
                    for(int d=0; d<4; d++) {
                        if(!countries[curPos.x][curPos.y].openedDir[d]) continue;
                        countries[curPos.x][curPos.y].openedDir[d] = false;
                        int nx = curPos.x + dx[d], ny = curPos.y + dy[d];
                        Position newPosition = new Position(nx,ny);
                        if(allianceCountries.contains(newPosition)) continue;
                        allianceCountries.add(newPosition);
                        q.add(newPosition);
                    }
                }

                int averaged = Math.floorDiv(allianceSum, allianceCnt);
                for(Position p : allianceCountries) {
                    countries[p.x][p.y].population = averaged;
                }
            }
        }
    }
    static int move() {
        int cnt = 0;
        while(open()) {
            average();
            cnt++;
        }
        return cnt;
    }
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        countries = new Country[N][N];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                countries[i][j] = new Country();
                countries[i][j].population = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(move());
    }
}

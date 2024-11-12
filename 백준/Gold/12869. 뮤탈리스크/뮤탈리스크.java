import java.io.*;
import java.util.*;

/**
 * 수빈 -> 뮤탈리스크 1개
 * 강호 -> SCV N개
 *
 * 각각 SCV의 남아있는 체력 주어짐
 * 뮤탈리스크를 공격할 수는 없음
 *
 * 뮤탈리스크가 공격할 때 세개의 SCV를 공격할 수 있음
 * - 공격받는 순서대로 SCV는 체력 -9, -3, -1
 * 한번에 하나의 SCV를 여러번 공격 불가능
 */
public class Main {
    static int N;
    static int minDepth = Integer.MAX_VALUE;
    static Set<State> dp = new HashSet<>();
    static final int mutalriskDamage[] = {9, 3, 1};
    static final int comb2[][] = {{0, 1}, {1, 0}};
    static final int comb3[][] = {{0, 1, 2}, {0, 2, 1}, {1, 0, 2}, {2, 0, 1}, {1, 2, 0}, {2, 1, 0}};
    static class State {
        ArrayList<Integer> csvs;
        int depth;
        State(ArrayList<Integer> csvs, int depth) {
            this.csvs = csvs;
            this.depth = depth;
        }
        @Override
        public boolean equals(Object o) {
            State s = (State)o;
            if(this.depth != s.depth) return false;
            if(this.csvs.size() != s.csvs.size()) return false;
            if(s.csvs.containsAll(this.csvs)) return true;
            return false;
        }
        @Override
        public int hashCode() {
            return Objects.hash(csvs, depth);
        }
    }
    static void func(Queue<State> q) {
        State state;
        while((state = q.poll()) != null) {
            ArrayList<Integer> csvs = state.csvs;
            int depth = state.depth;
            if (csvs.isEmpty()) {
                minDepth = Math.min(minDepth, depth);
                return;
            }
            if (depth > minDepth) return;
            if (csvs.size() == 1) {
                csvs.set(0, csvs.get(0) - mutalriskDamage[0]);
                State newState = new State(getNewState(csvs), depth + 1);
                if(dp.add(newState))
                    q.add(newState);
            } else if (csvs.size() == 2) {
                for (int[] comb : comb2) {
                    csvs.set(0, csvs.get(0) - mutalriskDamage[comb[0]]);
                    csvs.set(1, csvs.get(1) - mutalriskDamage[comb[1]]);
                    State newState = new State(getNewState(csvs), depth + 1);
                    if(dp.add(newState))
                        q.add(newState);
                    csvs.set(0, csvs.get(0) + mutalriskDamage[comb[0]]);
                    csvs.set(1, csvs.get(1) + mutalriskDamage[comb[1]]);
                }
            } else {
                for (int[] comb : comb3) {
                    csvs.set(0, csvs.get(0) - mutalriskDamage[comb[0]]);
                    csvs.set(1, csvs.get(1) - mutalriskDamage[comb[1]]);
                    csvs.set(2, csvs.get(2) - mutalriskDamage[comb[2]]);
                    State newState = new State(getNewState(csvs), depth + 1);
                    if(dp.add(newState))
                        q.add(newState);
                    csvs.set(0, csvs.get(0) + mutalriskDamage[comb[0]]);
                    csvs.set(1, csvs.get(1) + mutalriskDamage[comb[1]]);
                    csvs.set(2, csvs.get(2) + mutalriskDamage[comb[2]]);
                }
            }
        }
    }
    static ArrayList<Integer> getNewState(ArrayList<Integer> state) {
        ArrayList<Integer> newState = new ArrayList<>(3);
        for(Integer s : state) {
            if(s > 0)
                newState.add(s);
        }
        return newState;
    }
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        ArrayList<Integer> state = new ArrayList<>(3);
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++)
            state.add(Integer.parseInt(st.nextToken()));
        func(new LinkedList<>(List.of(new State(state, 0))));
        System.out.println(minDepth);
    }
}

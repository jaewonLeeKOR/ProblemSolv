import java.io.*;
import java.util.*;

/**
 * 알칼리와 산성 용액의 혼합액이 산성도가 0에 가장 가까운 조합
 */
public class Main {
    static int N;
    static List<Integer> alkalies, acids;
    static class Mixture {
        int alkali, acid;
        Mixture(int alkali, int acid) {
            this.alkali = alkali;
            this.acid = acid;
        }
    }
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        alkalies = new ArrayList<>(100_000);
        acids = new ArrayList<>(100_000);
        for(int i=0; i<N; i++) {
            int tmp = Integer.parseInt(st.nextToken());
            if(tmp < 0) {
                alkalies.add(tmp);
            }
            else {
                acids.add(tmp);
            }
        }

        alkalies.sort(Integer::compare);
        acids.sort(Integer::compare);
        PriorityQueue<Mixture> mixturePQ = new PriorityQueue<>((m1,m2) -> Integer.compare(Math.abs(m1.alkali + m1.acid), Math.abs(m2.alkali + m2.acid)));
        if(alkalies.size() >= 2) {
            mixturePQ.add(new Mixture(alkalies.get(alkalies.size()-2), alkalies.get(alkalies.size()-1)));
        }
        if(acids.size() >= 2) {
            mixturePQ.add(new Mixture(acids.get(0), acids.get(1)));
        }

        if(alkalies.size() >= 1 && acids.size() >= 1) {
            for (Integer alkali : alkalies) {
                int insertionPoint = Collections.binarySearch(acids, -1 * alkali);
                if(insertionPoint < 0) {
                    insertionPoint = -1 * (insertionPoint + 1);

                    if (insertionPoint != 0) {
                        mixturePQ.add(new Mixture(alkali, acids.get(insertionPoint - 1)));
                    }
                    if (insertionPoint != acids.size()) {
                        mixturePQ.add(new Mixture(alkali, acids.get(insertionPoint)));
                    }
                }
                else {
                    mixturePQ.add(new Mixture(alkali, acids.get(insertionPoint)));
                }
            }
        }

        System.out.println(mixturePQ.peek().alkali + " " + mixturePQ.peek().acid);
    }
}

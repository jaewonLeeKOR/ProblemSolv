import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static List<Integer> realNums;
    static List<Num> idxedNums;
    static class Num {
        int num, idx;
        int targetIdx;
        Num(int num, int idx) {
            this.num = num;
            this.idx= idx;
        }
        Num(int num, int idx, int targetIdx) {
            this(num, idx);
            this.targetIdx = targetIdx;
        }
    }
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        realNums = new ArrayList<>(N);
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            realNums.add(Integer.parseInt(st.nextToken()));
        }
        realNums.sort(Integer::compare);

        idxedNums = new ArrayList<>(N);
        for(int i=0; i<N; i++) {
            idxedNums.add(new Num(realNums.get(i), i));
        }

        int cnt = 0, prevNum = Integer.MIN_VALUE;
        boolean prevGOOD = false;
        targetFor:
        for(int targetIdx=0; targetIdx<N; targetIdx++) {
            if(idxedNums.get(targetIdx).num == prevNum) {
                if(prevGOOD) cnt++;
                continue;
            }
            prevNum = idxedNums.get(targetIdx).num;
            for(int aIdx=0; aIdx<N; aIdx++) {
                if(targetIdx==aIdx) continue;
                int num = idxedNums.get(targetIdx).num - idxedNums.get(aIdx).num;
                prevGOOD = false;
                int bIdx = Collections.<Num>binarySearch(idxedNums, new Num(num, aIdx, targetIdx), (o1, o2) -> {
                    if (o1.num == o2.num) {
                        if (o1.idx != o2.idx && o1.idx != o2.targetIdx)
                            return 0;
                        return -1;
                    } else if (o1.num < o2.num) return -1;
                    else return 1;
                });
                if(bIdx < 0) continue;
                cnt++;
                prevGOOD = true;
                continue targetFor;
            }
        }
        System.out.println(cnt);
    }
}

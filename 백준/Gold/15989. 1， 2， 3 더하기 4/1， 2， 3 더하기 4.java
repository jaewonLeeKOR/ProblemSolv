import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 정수 n이 주어졌을 때, 1,2,3의 합으로 나타내는 방법의 수를 구하라
 *
 * base
 * 1 -> 1
 * 2 -> 1+1, 2
 * 3 -> 1+1+1, 1+2, 3
 * 4
 * -> 1+1+1+1, 1+1+2, 1+3
 * -> (2+1+1), 2+2
 * -> (3+1)
 *
 * 5
 * -> 1+1+1+1+1, 1+1+1+2, 1+1+3, 1+2+2
 * -> (2+1+1+1), (2+1+2), 2+3
 * -> (3+1+1), (3+2)
 * => 1+1+1+1+1, 1+1+1+2, 1+1+3, 1+2+2, 2+3
 *
 * 6
 * -> 1+1+1+1+1+1, 1+1+1+1+2, 1+1+1+3, 1+1+2+2, 1+2+3
 * -> (2+1+1+1+1), (2+1+1+2), (2+1+3), 2+2+2
 * -> (3+1+1+1), (3+1+2), 3+3
 * => 1+1+1+1+1+1, 1+1+1+1+2, 1+1+1+3, 1+1+2+2, 1+2+3, 2+2+2, 3+3
 *
 * 7
 * -> 1+1+1+1+1+1+1, 1+1+1+1+1+2, 1+1+1+1+3, 1+1+1+2+2, 1+1+2+3, 1+2+2+2, 1+3+3
 * -> (2+1+1+1+1+1), (2+1+1+1+2), (2+1+1+3), (2+1+2+2), 2+2+3
 * -> (3+1+1+1+1), (3+1+1+2), (3+1+3), (3+2+2)
 * => 1+1+1+1+1+1+1, 1+1+1+1+1+2, 1+1+1+1+3, 1+1+1+2+2, 1+1+2+3, 1+2+2+2, 1+3+3, 2+2+3
 *
 * 8
 * -> 1+1+1+1+1+1+1+1, 1+1+1+1+1+1+2, 1+1+1+1+1+3, 1+1+1+1+2+2, 1+1+1+2+3, 1+1+2+2+2, 1+1+3+3, 1+2+2+3
 * -> (2+1+1+1+1+1+1), (2+1+1+1+1+2), (2+1+1+1+3), (2+1+1+2+2), (2+1+2+3), 2+2+2+2, 2+3+3
 * -> (3+1+1+1+1+1), (3+1+1+1+2), (3+1+1+3), (3+1+2+2), (3+2+3)
 *
 * n
 * -> n-1 중 1이 적어도 하나 들어있는 개수
 * -> n-2 중 1이 없으면서 2가 적어도 하나 들어있는 개수
 * -> n-3 중 3만으로 이루어진 개수
 */
public class Main {
    static Map<Integer, Number> memoization = new HashMap<>();
    static class Number{
        public int include123;
        public int include23;
        public int include3;
        public Number(int n) {
            if(n == 1) { // 1
                include123 = 1;
                include23 = 0;
                include3 = 0;
            }
            else if(n == 2) { // 1+1, 2
                include123 = 1;
                include23 = 1;
                include3 = 0;
            }
            else if(n == 3) { // 1+1+1, 1+2, 3
                include123 = 2;
                include23 = 0;
                include3 = 1;
            }
            else {
                include123 = getNumber(n-1).include123 + getNumber(n-1).include23 + getNumber(n-1).include3;
                include23 = getNumber(n-2).include23 + getNumber(n-2).include3;
                include3 = getNumber(n-3).include3;
            }
            memoization.put(n, this);
        }

        public Integer getTotal() {
            return include123 + include23 + include3;
        }

        @Override
        public String toString() {
            return include123 + "\t" + include23 + "\t" + include3;
        }

        public static Number getNumber(int n) {
            if(memoization.get(n) != null) return memoization.get(n);
            return new Number(n);
        }
    }
    public static void main(String[] args) throws Exception{
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++) {
            int n = Integer.parseInt(br.readLine());
            sb.append(Number.getNumber(n).getTotal() + "\n");
//            System.out.println("n : " + n + ",\t" + Number.getNumber(n));
        }
        System.out.print(sb);
    }
}

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    private static ArrayList<Integer> nums = new ArrayList<>();
    private static Boolean[][] palindromeDp = new Boolean[2_000][2_000];

    private static boolean isPalindrome(int startIdx, int endIdx) {
        boolean result = true;
        for (int i = 0; startIdx + i <= endIdx - i; i++) {
            if(palindromeDp[startIdx + i][endIdx - i] != null) {
                result = palindromeDp[startIdx + i][endIdx - i];
                break;
            }
            if(!nums.get(startIdx + i).equals(nums.get(endIdx - i))) {
                result = false;
                break;
            }
        }
        palindromeDp[startIdx][endIdx] = result;
        return result;
    }

    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n;
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums.add(Integer.parseInt(st.nextToken()));
        }
        StringBuffer sb = new StringBuffer();
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            boolean palindrome = isPalindrome(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1);
            sb.append(palindrome ? "1" : "0");
            if(i != n - 1)
                sb.append("\n");
        }
        System.out.println(sb);
    }
}

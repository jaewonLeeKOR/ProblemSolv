import java.io.*;
import java.util.*;

/**
 * 문자열 S를 T로 바꾸는 게임
 * 가능한 연산
 * - 문자열 뒤에 A를 추가
 * - 문자열의 뒤에 B를 추가하고 문자열을 뒤집음
 */
public class Main {
    static boolean success = false;
    static void dfs(String s, String t) {
        if(s.length() == t.length()) {
            if(s.equals(t)) {
                success = true;
            }
            return;
        }
        if(t.charAt(0) == 'B') {
            String newT = t.substring(1);
            StringBuilder sb = new StringBuilder(newT);
            dfs(s, sb.reverse().toString());
        }
        if(t.charAt(t.length()-1) == 'A') {
            dfs(s, t.substring(0, t.length()-1));
        }
    }

    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String t = br.readLine();
        dfs(s, t);
        System.out.println(success ? "1" : "0");
    }
}

import java.io.*;
import java.util.*;

public class Main {
    static int lcs[][];
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String a = br.readLine();
        String b = br.readLine();
        lcs = new int[a.length()+1][b.length()+1];

        for (int i = 1; i <= a.length(); i++) {
            for (int j = 1; j <= b.length(); j++) {
                int maxLcs = 0;
                if(maxLcs < lcs[i-1][j-1])
                    maxLcs = lcs[i-1][j-1];
                if(a.charAt(i-1) == b.charAt(j-1)) {
                    lcs[i][j] = maxLcs + 1;
                }
                else {
                    if(maxLcs < lcs[i-1][j])
                        maxLcs = lcs[i-1][j];
                    if(maxLcs< lcs[i][j-1])
                        maxLcs = lcs[i][j-1];
                    lcs[i][j] = maxLcs;
                }
            }
        }

        System.out.println(lcs[a.length()][b.length()]);
        if (lcs[a.length()][b.length()] == 0) return;

        StringBuffer sb = new StringBuffer();
        Stack<Character> s = new Stack();
        int x = a.length(), y = b.length();
        while(lcs[x][y] != 0) {
            while (lcs[x][y] == lcs[x-1][y]) x--;
            while (lcs[x][y] == lcs[x][y-1]) y--;
            s.add(a.charAt(x-1));
            x--;
            y--;
        }
        while (!s.isEmpty()) {
            sb.append(s.pop());
        }
        System.out.println(sb);

    }
}

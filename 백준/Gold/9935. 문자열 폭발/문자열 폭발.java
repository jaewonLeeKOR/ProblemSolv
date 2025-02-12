import java.io.*;
import java.util.*;

public class Main {
    static List<Character> word = new ArrayList<>(1_000_000);
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String bomb = br.readLine();
        bombChecker:
        for(char c : input.toCharArray()) {
            word.add(c);
            if(word.size() >= bomb.length()) {
                for(int i=word.size()-bomb.length(), j=0; i< word.size(); i++, j++) {
                    if(word.get(i) != bomb.charAt(j)) continue bombChecker;
                }
                for(int i=0; i<bomb.length(); i++) {
                    word.remove(word.size()-1);
                }
            }
        }
        StringBuffer sb = new StringBuffer();
        for(int i=0; i< word.size(); i++) {
            sb.append(word.get(i));
        }
        if(word.isEmpty()) {
            sb.append("FRULA");
        }
        System.out.println(sb.toString());
    }
}

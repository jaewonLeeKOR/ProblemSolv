import java.util.*;
import java.io.*;

/**
 * 알파벳 소문자로 이루어진 문자열 W
 * 양의 정수 K
 * 어떤 문자를 정확히 K개 포함하는 가장 짧은 연속 문자열의 길이
 * 어떤 문자를 정확히 K개 포함하는 가장 긴 연속 문자열의 길이
 */
public class Main {
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++) {
            List<Integer> indexesLinkedList[] = new List[26];
            for(int i=0; i<26; i++)
                indexesLinkedList[i] = new LinkedList<>();
            String s = br.readLine();
            int K = Integer.parseInt(br.readLine());
            for(int i=0; i<s.length(); i++) {
                indexesLinkedList[s.charAt(i) - 'a'].add(i);
            }

            List<List<Integer>> indexesArrayList = new LinkedList<>();
            for(int i=0; i<26; i++) {
                if(indexesLinkedList[i].size() >= K) {
                    indexesArrayList.add(new ArrayList<>(indexesLinkedList[i]));
                }
            }

            int minLength = Integer.MAX_VALUE, maxLength = Integer.MIN_VALUE;
            for(List<Integer> l : indexesArrayList) {
                for(int i=0; i+(K-1)<l.size(); i++) {
                    int length = l.get(i+(K-1)) - l.get(i) + 1;
                    minLength = Math.min(minLength, length);
                    maxLength = Math.max(maxLength, length);
                }
            }
            if(minLength == Integer.MAX_VALUE) {
                System.out.println(-1);
            }
            else {
                System.out.println(minLength + " " + maxLength);
            }
        }
    }
}

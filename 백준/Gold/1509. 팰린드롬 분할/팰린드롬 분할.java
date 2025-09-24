import java.io.*;
import java.util.*;

public class Main {
    private static List<Integer>[] idxes = new List[26]; // 알파벳 별 인덱스 위치

    private static boolean isPalindrome(String word, int startIdx, int endIdx) {
        for (int i = 0; startIdx + i <= endIdx - i; i++) {
            if(word.charAt(startIdx + i) != word.charAt(endIdx - i))
                return false;
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 26; i++) {
            idxes[i] = new ArrayList();
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String word = br.readLine();

        List<Integer> palindromeCnt = new ArrayList<>(word.length());
        for (int i = 0; i < word.length(); i++) {
            palindromeCnt.add(2_501);
            idxes[word.charAt(i) - 'A'].add(i);
            for (int j = idxes[word.charAt(i) - 'A'].size() - 1; j >= 0 ; j--) {
                if(isPalindrome(word, idxes[word.charAt(i) - 'A'].get(j), i)) {
                    int tmpCnt = 1;
                    if(idxes[word.charAt(i) - 'A'].get(j) != 0)
                        tmpCnt += palindromeCnt.get(idxes[word.charAt(i) - 'A'].get(j) - 1);
                    palindromeCnt.set(i, Math.min(palindromeCnt.get(i), tmpCnt));
                }
            }
        }

        System.out.println(palindromeCnt.get(palindromeCnt.size()-1));
    }
}

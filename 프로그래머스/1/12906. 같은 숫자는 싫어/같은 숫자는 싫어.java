import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        int[] answer;
        List<Integer> l = new ArrayList<>();
        for(int i : arr) {
            if(l.size() == 0 || l.get(l.size()-1) != i) {
                l.add(i);
            }
        }
        answer = new int[l.size()];
        
        for(int i=0; i<l.size(); i++)
            answer[i] = l.get(i);

        return answer;
    }
}
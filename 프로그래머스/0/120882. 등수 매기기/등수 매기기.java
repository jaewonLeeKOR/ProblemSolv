import java.io.*;
import java.util.*;
class Solution {
    
    public int[] solution(int[][] score) {
        int[] answer = {};
        List<Integer> al = new ArrayList<>();
        for(int[] s : score) {
            al.add(s[0]+s[1]);
        }
        answer = new int[al.size()];
        
        List<Integer> sorted_al = new ArrayList<>(al);
        sorted_al.sort(Comparator.reverseOrder());
        
        for(Integer i=0; i<al.size(); i++) {
            al.set(i, sorted_al.indexOf(al.get(i)) + 1);
        }
        
        for(int i=0; i<al.size(); i++) answer[i] = al.get(i);
        
        return answer;
    }
}
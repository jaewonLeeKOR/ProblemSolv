/**
숫자카드 100장

게임 시작 가능 조건
- 2 이상 100 이하의 자연수 하나를 정함
- 그 수보다 작거나 같은 숫자카드들을 준비
- 준비한 카드의 수만큼 작은 상자를 준비

준비된 상자에 카드를 한장씩 넣음
상자를 "무작위"로 섞어 일렬로 나열
상자가 나열된 순서에 따라 순차적으로 증가하는 번호를 붙임

임의의 상자를 하나 선택하여 숫자카드 확인
-> 확인한 카드에 적힌 번호에 해당하는 상자를 열어 담긴 카드에 적힌 숫자 확인
=> 열어야하는 상자가 이미 열려있을때까지 반복

상자 사이클의 상자개수 -> 상자그룹의 점수
두 상자 그룹의 점수 곱의 최대
*/
import java.util.*;

class Solution {
    boolean visited[];
    public int solution(int[] cards) {
        int answer = 0;
        for(int i=0; i<cards.length; i++) {
            cards[i]--;
        }
        visited = new boolean[cards.length];
        List<Integer> scores = new LinkedList();
        for(int i=0; i<cards.length; i++) {
            if(visited[i]) continue;
            int curScore = 0;
            int curIdx = i;
            while(!visited[curIdx]) {
                visited[curIdx] = true;
                curScore++;
                curIdx = cards[curIdx];
            }
            scores.add(curScore);
        }
        for(int i=0; i<scores.size(); i++) {
            for(int j=i+1; j<scores.size(); j++) {
                answer = Math.max(answer, scores.get(i) * scores.get(j));
            }
        }
        return answer;
    }
}
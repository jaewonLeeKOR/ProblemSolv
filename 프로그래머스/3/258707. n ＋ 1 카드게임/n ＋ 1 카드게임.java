/**
1~n 카드 뭉치 순서대로 뽑음 (n 은 6의 배수)
카드와 교환 가능한 동전 coin
1. 카드 뭉치에서 n/3 장 카드 뽑아 모두 가짐
2. 게임 1라운드부터 시작, 각 라운드가 시작할때 카드 두장 뽑음
  - 카드 뭉치에 남은 카드가 없다면 게임 종료
  - 뽑은 카드는 카드 한장당 동전 하나를 소모해서 가지거나, 동전 소모하지 않고 버릴 수 있음
  -> 카드에 적힌 수의 합이 n+1이 되도록 카드 두장을 내고 다음라운드 진행 가능
    - 카드 두장을 낼 수 없다면 게임 종료

return 도달 가능한 라운드 수
*/
import java.util.*;

class Solution {
    int N;
    int maxRound=0;
    void func(int round, int remainCoin, Integer pairCount, TreeSet<Integer> wishCards, TreeSet<Integer> myCards, Queue<Integer> remainCards) {
        maxRound = Math.max(maxRound, round);
        if(remainCards.isEmpty()) return;
        Integer c1 = remainCards.poll();
        Integer c2 = remainCards.poll();
        
        if(remainCoin>0 && myCards.contains((N+1) - c1)) {
            pairCount++;
            myCards.remove((N+1) - c1);
            remainCoin--;
        }
        else wishCards.add(c1);
        
        if(remainCoin>0 && myCards.contains((N+1) - c2)) {
            pairCount++;
            myCards.remove((N+1) - c2);
            remainCoin--;
        }
        else wishCards.add(c2);
        
        if(pairCount == 0 && remainCoin >= 2) {
            for(Integer c : wishCards) {
                if(wishCards.contains((N+1) - c)) {
                    pairCount++;
                    wishCards.remove(c);
                    wishCards.remove((N+1) - c);
                    remainCoin -= 2;
                    break;
                }
            }
        }
        
        if(pairCount==0) return;
        pairCount--;
        func(round+1, remainCoin, pairCount, wishCards, myCards, remainCards);
    }
    public int solution(int coin, int[] cards) {
        N = cards.length;
        Integer pairCount = 0;
        TreeSet<Integer> wishCards = new TreeSet<>();
        TreeSet<Integer> myCards = new TreeSet<>();
        Queue<Integer> remainCards = new LinkedList<>();
        for(int i=0; i<cards.length; i++) {
            if(i<cards.length/3) {
                myCards.add(cards[i]);
            }
            else
                remainCards.add(cards[i]);
        }
        for(Object o : myCards.toArray()) {
            Integer v = (Integer) o;
            if(myCards.contains((N+1)- v)) {
                pairCount++;
                myCards.remove(v);
                myCards.remove((N+1)- v);
            }
        }
        
        func(1, coin, pairCount, wishCards, myCards, remainCards);
        return maxRound;
    }
}
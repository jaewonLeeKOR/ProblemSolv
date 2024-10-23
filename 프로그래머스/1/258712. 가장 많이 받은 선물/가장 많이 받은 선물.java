/**
이번 달까지 선물을 주고받은 기록을 바탕으로 다음달에 누가 선물을 많이 받을지 예측

두사람이 선물을 주고 받은 기록이 있다면
- 이번 달까지 두사람 사이에 더 많은 선물을 준 사람이 다음달에 선물을 하나 받음

두사람이 선물을 주고 받은 기록이 같다면
- 선물 지수가 더 큰사람이 선물 지수가 더 작은 사람에게 선물 하나를 받음
- 선물 지수가 같다면 선물을 주고받지 않음

선물 지수 = (준 선물의 수) -  (받은 선물의 수)

input
friends : 친구들의 이름 1차원 문자열 배열
gift : 이번달까지 친구들이 주고 받은 선물의 기록을 담은 1차원 문자열 배열
- ({선물 준 친구 이름} {선물 받은 친구 이름})

output
다음달에 선물을 가장 많이 받을 친구가 받을 선물의 수
*/
import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        int one2One[][] = new int[friends.length][friends.length];
        int send[] = new int[friends.length];
        int receive[] = new int[friends.length];
        Map<String, Integer> indexedFriends = new HashMap();
        for(int i=0; i<friends.length; i++)
            indexedFriends.put(friends[i], i);
        for(String g : gifts) {
            String sender = g.split(" ")[0];
            String receiver = g.split(" ")[1];
            send[indexedFriends.get(sender)]++;
            receive[indexedFriends.get(receiver)]++;
            one2One[indexedFriends.get(sender)][indexedFriends.get(receiver)]++;
        }
        for(int target=0; target<friends.length; target++) {
            int score=0;
            for(int opponent=0; opponent<friends.length; opponent++) {
                if(target == opponent) continue;
                if(one2One[target][opponent] > one2One[opponent][target]) {
                    score++;
                }
                else if(one2One[target][opponent] == one2One[opponent][target]) {
                    if((send[target] - receive[target]) > (send[opponent] - receive[opponent]))
                        score++;
                }
            }
            answer = Math.max(answer, score);
        }
        return answer;
    }
}
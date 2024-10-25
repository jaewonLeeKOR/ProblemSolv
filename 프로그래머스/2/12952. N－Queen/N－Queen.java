/**
같은 열, 같은 행, 같은 대각 상에 있으면 안됨
x 동일(case1), y 동일(case2), x-y 동일(case3), x+y 동일 불가(case4)
0-(n-1), 0-(n-1), [0](-2n)-[4n](2n), [0](-2n)-[4n](2n)

x-y
최대 (n-1) - 0 = n-1 => 2*(n-1)
최소 0 - (n-1) = -n+1 => 0 + (n-1) + (-n+1)

x+y
최대 (n-1) + (n-1) = 2*(n-1) => 2*(n-1)
최소 0 + 0 = 0 => 0

12! = 479,001,600
*/
class Solution {
    int N;
    boolean c2[], c3[], c4[];
    int func(int x) {
        if(x == N) {
            return 1;
        }
        int cnt=0;
        for(int y=0; y<N; y++) {
            if(c2[y]) continue;
            if(c3[x-y + (N-1)]) continue;
            if(c4[x+y]) continue;
            c2[y] = true;
            c3[x-y + (N-1)] = true;
            c4[x+y] = true;
            cnt += func(x+1);
            c2[y] = false;
            c3[x-y + (N-1)] = false;
            c4[x+y] = false;
        }
        return cnt;
    }
    public int solution(int n) {
        int answer = 0;
        N = n;
        c2 = new boolean[(N-1) + 1]; //     case2
        c3 = new boolean[2*(N-1) + 1]; //  case3
        c4 = new boolean[2*(N-1) + 1]; //  case4
        answer = func(0);
        return answer;
    }
}
class Solution {
    public int solution(int n) {
        int res = 1, i;
        for(i=1; i<n; i++) {
            res++;
            while(res % 3 == 0 || Integer.toString(res).contains("3"))
                res++;
        }
        return res;
    }
}
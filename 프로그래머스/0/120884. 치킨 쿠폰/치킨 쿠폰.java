class Solution {
    public int solution(int chicken) {
        int coupon_chicken = 0;
        int coupon = chicken;
        while(coupon >= 10) {
            int new_coupon = coupon / 10;
            coupon %= 10;
            coupon += new_coupon;
            coupon_chicken += new_coupon;
        }
        return coupon_chicken;
    }
}
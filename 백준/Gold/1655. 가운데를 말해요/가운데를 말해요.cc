#include<iostream>
#include<deque>
#include<queue>
using namespace std;
#define NONE 22222

struct compGreater{
    bool operator()(int a, int b) {
        return a > b;
    }
};

struct compLesser{
    bool operator()(int a, int b) {
        return a < b;
    }
};

int main() {
    ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
    priority_queue<int,deque<int>,compGreater> rpq;
    priority_queue<int,deque<int>,compLesser> lpq;
    int n, mid = NONE, lmid = NONE, rmid = NONE;
    cin >> n;
    for(int i=0, t; i<n; i++) {
        cin >> t;
        if(i%2==0) {
//            lqp -> k, lmid, rmid, rpq -> k
            if(i==0) mid = t;
            else if(t <= lmid) {
                lpq.push(t);
                rpq.push(rmid);
                mid = lmid;
            }
            else if(lmid < t && t <= rmid) {
                lpq.push(lmid);
                rpq.push(rmid);
                mid = t;
            }
            else if(rmid < t) {
                lpq.push(lmid);
                rpq.push(t);
                mid = rmid;
            }
            cout << mid << "\n";
        }
        else {
//            lpq -> k, mid, rpq -> k
            if(i == 1) {
                if(t <= mid) {
                    lmid = t;
                    rmid = mid;
                }
                else if(mid < t) {
                    lmid = mid;
                    rmid = t;
                }
            }
            else if(t <= lpq.top()) {
                lpq.push(t);
                lmid = lpq.top();
                lpq.pop();
                rmid = mid;
            }
            else if(lpq.top() < t && t <= mid) {
                lmid = t;
                rmid = mid;
            }
            else if(mid < t && t <= rpq.top()) {
                lmid = mid;
                rmid = t;
            }
            else if(rpq.top() < t) {
                rpq.push(t);
                rmid = rpq.top();
                rpq.pop();
                lmid = mid;
            }
            cout << lmid << "\n";
        }
    }
}
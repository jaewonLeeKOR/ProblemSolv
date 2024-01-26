#include<iostream>
#include<queue>
#include<deque>
using namespace std;

struct greaterComp{
    bool operator()(int a, int b) {
        return a < b;
    }
};

struct lesserComp{
    bool operator()(int a, int b) {
        return a > b;
    }
};

int main() {
    ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
    int tc;
    cin >> tc;
    while(tc--) {
        int n, mid;
        cin >> n;
        cout << n/2 + 1 << "\n";
        priority_queue<int, deque<int>, greaterComp> lesserPq;
        priority_queue<int, deque<int>, lesserComp> greaterPq;
        cin >> mid;
        cout << mid << ' ';
        for(int i=1; i<=n/2; i++) {
            int a1, a2;
            cin >> a1 >> a2;
            if(a1 <= mid && mid <= a2) {
                lesserPq.push(a1);
                greaterPq.push(a2);
            }
            else if(a2 <= mid && mid <= a1) {
                lesserPq.push(a2);
                greaterPq.push(a1);
            }
            else if(mid <= a1 && a1 <= a2) {
                lesserPq.push(mid);
                if(greaterPq.size() && greaterPq.top() < a1) {
                    mid = greaterPq.top();
                    greaterPq.pop();
                    greaterPq.push(a1);
                }
                else {
                    mid = a1;
                }
                greaterPq.push(a2);
            }
            else if(mid <= a2 && a2 <= a1) {
                lesserPq.push(mid);
                if(greaterPq.size() && greaterPq.top() < a2) {
                    mid = greaterPq.top();
                    greaterPq.pop();
                    greaterPq.push(a2);
                }
                else {
                    mid = a2;
                }
                greaterPq.push(a1);
            }
            else if(a1 <= a2 && a2 <= mid) {
                greaterPq.push(mid);
                if(lesserPq.size() && a2 < lesserPq.top()) {
                    mid = lesserPq.top();
                    lesserPq.pop();
                    lesserPq.push(a2);
                }
                else {
                    mid = a2;
                }
                lesserPq.push(a1);
            }
            else if(a2 <= a1 && a1 <= mid) {
                greaterPq.push(mid);
                if(lesserPq.size() && a1 < lesserPq.top()) {
                    mid = lesserPq.top();
                    lesserPq.pop();
                    lesserPq.push(a1);
                }
                else {
                    mid = a1;
                }
                lesserPq.push(a2);
            }
            if(i%10==0)
                cout << "\n";
            cout << mid << " ";
        }
        cout << "\n";
    }
}
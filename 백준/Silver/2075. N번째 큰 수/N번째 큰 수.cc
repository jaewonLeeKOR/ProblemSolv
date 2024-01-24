#include<iostream>
#include<deque>
#include<queue>
using namespace std;

struct cmp{
    bool operator()(int a, int b) {
        return a>b;
    }
};
int main() {
    ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
    int n;
    cin >> n;
    priority_queue<int, deque<int>, cmp> pq;
    for(int i=0; i<n*n; i++) {
        int k;
        cin >> k;
        if(pq.size() < n || pq.top() <= k)
            pq.push(k);
        if(pq.size() > n)
            pq.pop();
    }
    cout << pq.top();
}
#include<iostream>
#include<deque>
#include<queue>
using namespace std;

struct N {
    int data;
    int pos;
};

struct comp {
    bool operator()(const N a, const N b) {
        return a.data > b.data;
    }
};

int main() {
    ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
    int n, l;
    cin >> n >> l;
    deque<int> res;
    priority_queue<N,deque<N>,comp> pq;
    for(int i=0; i<n; i++) {
        int a;
        cin >> a;
        pq.push({a,i});
        while(pq.top().pos + l <= i) {
            pq.pop();
        }
        res.push_back(pq.top().data);
    }
    for(int i=0; i<res.size(); i++)
        cout << res[i] << " ";
}
#include<iostream>
#include<queue>
#include<deque>
using namespace std;
#define IN true
#define OUT false

struct EVENT {
    int inTime, outTime, idx;
    bool type;
};

struct comp {
    bool operator()(EVENT a, EVENT b) {
        if((a.type ? a.inTime : a.outTime) == (b.type ? b.inTime : b.outTime)) return b.type == OUT;
        return (a.type ? a.inTime : a.outTime) > (b.type ? b.inTime : b.outTime);
    }
};
struct cmp {
    bool operator()(int a, int b) {
        return a > b;
    }
};

int main() {
    ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
    int n;
    cin >> n;
    priority_queue<EVENT, deque<EVENT>, comp> events;
    deque<int> computers;
    priority_queue<int, deque<int>, cmp> candidateIdx;
    for(int i=0; i<n; i++) {
        int p, q;
        cin >> p >> q;
        events.push({p,q,-1, IN});
    }
    while(events.size()) {
        EVENT cur = events.top();
        events.pop();
        if(cur.type == IN) {
            if(candidateIdx.empty()) {
                int newIdx = computers.size();
                computers.push_back(1);
                events.push({cur.inTime, cur.outTime, newIdx, OUT});
            }
            else {
                int idx = candidateIdx.top();
                candidateIdx.pop();
                computers[idx]++;
                events.push({cur.inTime, cur.outTime, idx, OUT});
            }
        }
        else {
            candidateIdx.push(cur.idx);
        }
    }
    cout << computers.size() << "\n";
    for(int computer : computers) {
        cout << computer << " ";
    }
}
#include<iostream>
#include<queue>
#include<deque>
using namespace std;
struct PROB {
    int problemNo;
    int level;
};
struct greaterComp {
    bool operator()(PROB a, PROB b) {
        if(a.level == b.level) return a.problemNo < b.problemNo;
        return a.level < b.level;
    }
};
struct lesserComp {
    bool operator()(PROB a, PROB b) {
        if(a.level == b.level) return a.problemNo > b.problemNo;
        return a.level > b.level;
    }
};
int levels[100010] = {-1,};
int main() {
    ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
    int n, m;
    cin >> n;
    priority_queue<PROB, deque<PROB>, greaterComp> greaterPq;
    priority_queue<PROB, deque<PROB>, lesserComp> lesserPq;
    for(int i=0; i<n; i++) {
        int p, l;
        cin >> p >> l;
        greaterPq.push({p,l});
        lesserPq.push({p,l});
        levels[p] = l;
    }
    cin >> m;
    for(int i=0; i<m; i++) {
        string s;
        cin >> s;
        if(s == "add") {
            int p, l;
            cin >> p >> l;
            greaterPq.push({p,l});
            lesserPq.push({p,l});
            levels[p] = l;
        }
        else if(s == "recommend") {
            int x;
            cin >> x;
            if(x>0) {
                while(levels[greaterPq.top().problemNo] != greaterPq.top().level) {
                    levels[greaterPq.top().problemNo] = -1;
                    greaterPq.pop();
                }
                cout << greaterPq.top().problemNo << "\n";
            }
            if(x<0) {
                while(levels[lesserPq.top().problemNo] != lesserPq.top().level) {
                    levels[lesserPq.top().problemNo] = -1;
                    lesserPq.pop();
                }
                cout << lesserPq.top().problemNo << "\n";
            }
        }
        else if(s == "solved") {
            int p;
            cin >> p;
            levels[p] = -1;
        }
    }
}
#include<iostream>
#include<stack>
using namespace std;
int main() {
    ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
    int n, res=0;
    cin >> n;
    stack<int> s;
    while(n--) {
        int d, h;
        cin >> d >> h;
        while(s.size() && s.top() > h) {
            s.pop();
            res++;
        }
        if(s.size() && s.top() == h)
            continue;
        if(h == 0)
            continue;
        s.push(h);
    }
    while(s.size()) {
        s.pop();
        res++;
    }
    cout << res;
}
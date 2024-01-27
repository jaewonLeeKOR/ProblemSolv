#include<iostream>
#include<deque>
using namespace std;
int main() {
    ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
    int tc;
    cin >> tc;
    while(tc--) {
        string s;
        cin >> s;
        deque<char> prev;
        deque<char> next;
        for(int i=0; i<s.size(); i++) {
            switch (s[i]) {
                case '<':
                    if(prev.size()) {
                        next.push_front(prev[prev.size()-1]);
                        prev.pop_back();
                    }
                    break;
                case '>':
                    if(next.size()) {
                        prev.push_back(next[0]);
                        next.pop_front();
                    }
                    break;
                case '-':
                    if(prev.size())
                        prev.pop_back();
                    break;
                default:
                    prev.push_back(s[i]);
                    break;
            }
        }
        for(char c : prev)
            cout << c;
        for(char c : next)
            cout << c;
        cout << "\n";
    }
}
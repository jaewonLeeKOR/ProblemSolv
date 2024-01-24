#include<iostream>
#include<map>
#include<math.h>
using namespace std;
int main() {
    ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
    string s;
    int cnt=0;
    map<string, int> m;
    while(getline(cin,s,'\n')) {
        m[s]++;
        cnt++;
    }
    for(auto it=m.begin(); it!=m.end(); it++) {
        cout.precision(4);
        cout << it->first << " " << fixed << round(100* 10000 * (it->second / (double)cnt)) / (double)10000 << "\n";
    }
    return 0;
}
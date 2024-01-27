#include<iostream>
using namespace std;
int main() {
    int n, f;
    cin >> n >> f;
    for(int i=(n/100)*100; i<(1+n/100)*100; i++) {
        if(i%f == 0) {
            if(i==0) cout << "00";
            else if((i%100)/10==0) cout << "0" << i%10;
            else cout << i%100;
            cout << "\n";
            return 0;
        }
    }
}
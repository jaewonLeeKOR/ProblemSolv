#include<iostream>
#include<algorithm>
using namespace std;

struct CIRCLE {
    int srt;
    int lst;
};

bool comp(CIRCLE a, CIRCLE b) {
    if(a.srt == b.srt) return a.lst < b.lst;
    return a.srt < b.srt;
}

int main() {
    ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
    int n;
    cin >> n;
    bool checked = false;
    CIRCLE circles[200001];
    for(int i=0; i<n; i++) {
        int x, r;
        cin >> x >> r;
        circles[i] = {x-r,x+r};
    }
    sort(circles, circles+n, comp);
    /**
     * 시작 지점 같으면 접점
     * 다음 원 시작 <= 이전 원 마지막 <= 다음 원 마지막 -> 접점
     * 이전 원 마지막 < 다음원 시작 -> 이전 원 바꾸기
     */
    for(int i=0; i<n; i++) {
        CIRCLE current_circle = circles[i];
        for(int j=i+1; j<n; j++) {
            CIRCLE next_circle = circles[j];
            if(current_circle.srt == next_circle.srt) {
                checked = true;
                break;
            }
            if(next_circle.srt <= current_circle.lst && current_circle.lst <= next_circle.lst) {
                checked = true;
                break;
            }
            if(current_circle.lst < next_circle.srt)
                break;
        }
        if(checked)
            break;
    }
    cout << (checked ? "NO\n" : "YES\n");
}
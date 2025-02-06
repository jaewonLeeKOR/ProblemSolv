import java.io.*;
import java.util.*;

/**
 * '*' 또는 '/'이 마지막 연산기호인 경우
 * - 다음 문자가 들어왔을때 바로 합쳐줌
 *
 * '+' 또는 '-'가 마지막 연산기호인 경우
 * - 다음 연산 기호가 '*' 또는 '/'가 아니라면 가장 앞의 두 문자와 함께 합쳐줌
 */
public class Main {
    static String plainTxt;
    static int curIdx = 0;
    static String translate() {
        List<String> wl = new ArrayList<>(3); // 피연산자 리스트
        List<Character> sl = new ArrayList<>(2); // 연산기호 리스트
        idxForwardLoop:
        while(curIdx != plainTxt.length() && plainTxt.charAt(curIdx) != ')') {
            // 다음 문자가 연산기호 경우
            switch (plainTxt.charAt(curIdx)) {
                case '+', '-' : {
                    // 이전의 연산자가 존재한다면 합쳐줌
                    if(!sl.isEmpty()) {
                        StringBuffer tmp = new StringBuffer();
                        tmp.append(wl.get(wl.size()-2)).append(wl.get(wl.size()-1)).append(sl.get(sl.size()-1));
                        wl.remove(wl.size()-1);
                        wl.remove(wl.size()-1);
                        sl.remove(sl.size()-1);
                        wl.add(tmp.toString());
                    }

                    sl.add(plainTxt.charAt(curIdx));
                    curIdx++;
                    continue idxForwardLoop;
                }
                case '*', '/' : {
                    sl.add(plainTxt.charAt(curIdx));
                    curIdx++;
                    continue idxForwardLoop;
                }
            }
            // 다음 문자가 피연산자인 경우
            String w;
            if(plainTxt.charAt(curIdx) == '(') { // 괄호인 경우
                curIdx++; // 괄호 시작 기호 다음 부터 시작
                w = translate();
            }
            else {
                w = Character.toString(plainTxt.charAt(curIdx));
            }
            wl.add(w);
            // 마지막 연산 기호가 '*' 또는 '/'인 경우 바로 합쳐줌
            if(!sl.isEmpty() && (sl.get(sl.size()-1) == '*' || sl.get(sl.size()-1) == '/')) {
                StringBuffer tmp = new StringBuffer();
                tmp.append(wl.get(wl.size()-2)).append(wl.get(wl.size()-1)).append(sl.get(sl.size()-1));
                wl.remove(wl.size()-1);
                wl.remove(wl.size()-1);
                sl.remove(sl.size()-1);
                wl.add(tmp.toString());
            }
            curIdx++;
        }

        // 남은 연산기호가 있다면 합쳐줌
        StringBuffer res = new StringBuffer();
        if(!sl.isEmpty()) {
            res.append(wl.get(0)).append(wl.get(1)).append(sl.get(0));
        }
        else {
            res.append(wl.get(0));
        }
        return res.toString();
    }
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        plainTxt = br.readLine();
        System.out.println(translate());
    }
}

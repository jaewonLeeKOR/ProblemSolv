import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 입력: 
 * 		(스위치 개수)
 * 		(스위치 상태; 켜져있음1 꺼져있음0)
 * 		(학생수)
 * 		(성별; 남자1 여자2) (받은 스위치 수)
 * 
 *  남학생: 받은 수의 배수의 상태를 바꿈
 *  여학생: 받은 수의 스위치를 중심으로 최대의 대칭인 구간의 상태를 바꿈
 */

public class Main {	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		List<Integer> switchs = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		switchs.add(-1);
		while(st.hasMoreTokens()) {
			switchs.add(Integer.parseInt(st.nextToken()));
		}

		int P = Integer.parseInt(br.readLine());
		for(int i=0; i<P; i++) {
			st = new StringTokenizer(br.readLine());
			if(Integer.parseInt(st.nextToken()) == 1) { //남자
				int originNum = Integer.parseInt(st.nextToken());
				for(int j=originNum; j<=N; j+=originNum) {
					switchs.set(j, switchs.get(j) == 1 ? 0 : 1);
				}
			}
			else { // 여자
				int originNum = Integer.parseInt(st.nextToken());
				for(int j=0; 0 < originNum-j && originNum+j <= N; j++) {
					if(j==0) {
						switchs.set(originNum, switchs.get(originNum) == 1 ? 0 : 1);
					}
					else if(switchs.get(originNum-j) == switchs.get(originNum+j)) {
						switchs.set(originNum-j, switchs.get(originNum-j) == 1 ? 0 : 1);
						switchs.set(originNum+j, switchs.get(originNum+j) == 1 ? 0 : 1);
					}
					else
						break;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			sb.append(switchs.get(i).toString());
			if(i%20==0) sb.append("\n");
			else if(1!=N) sb.append(" ");
		}
		System.out.println(sb.toString());
		br.close();
	}
}

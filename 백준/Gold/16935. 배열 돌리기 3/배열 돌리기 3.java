import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 1번 연산
 * 		상하 반전
 * 2번 연산
 * 		좌우 반전
 * 3번 연산
 * 		시계 회전
 * 4번 연산
 * 		반시계 회전
 * 5번 연산
 * 		시계 그룹 회전
 * 6번 연산
 * 		반시계 그룹 회전
 */

public class Main {
	private static int N, M, NM;
	private static List<List<Integer>> ll;
	private static List<List<Integer>> tmpll;
	
	private static void print() {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				sb.append(ll.get(i).get(j) + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	private static List<List<Integer>> func1() {
		for(int i=0; i<N/2; i++) {
			List tmp = ll.get(i);
			ll.set(i, ll.get(N-1-i));
			ll.set(N-1-i, tmp);
		}
		return ll;
	}
	private static List<List<Integer>> func2() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M/2; j++) {
				int tmp = ll.get(i).get(j);
				ll.get(i).set(j, ll.get(i).get(M-1-j));
				ll.get(i).set(M-1-j, tmp);
			}
		}
		return ll;
	}
	private static List<List<Integer>> func34() {
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				tmpll.get(i).set(j, ll.get(j).get(i));
			}
		}
		int tmp = N;
		N = M;
		M = tmp;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				ll.get(i).set(j, tmpll.get(i).get(j));
			}
		}
		return ll;
	}
	private static List<List<Integer>> func5() {
		int n=N/2, m=M/2;
		for(int i=0; i<n; i++) {
			for (int j=0; j<m; j++) {
				int tmp = ll.get(i).get(j);
				ll.get(i).set(j, ll.get(i+n).get(j)); 
				ll.get(i+n).set(j, ll.get(i+n).get(j+m)); 
				ll.get(i+n).set(j+m, ll.get(i).get(j+m)); 
				ll.get(i).set(j+m, tmp); 
			}
		}
		return ll;
	}
	private static List<List<Integer>> func6() {
		int n=N/2, m=M/2;
		for(int i=0; i<n; i++) {
			for (int j = 0; j <m; j++) {
				int tmp = ll.get(i).get(j);
				ll.get(i).set(j, ll.get(i).get(j+m)); 
				ll.get(i).set(j+m, ll.get(i+n).get(j+m)); 
				ll.get(i+n).set(j+m, ll.get(i+n).get(j)); 
				ll.get(i+n).set(j, tmp); 
			}
		}
		return ll;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		NM = N > M ? N : M;
		int R = Integer.parseInt(st.nextToken());
		
		ll = new ArrayList<>();
		tmpll = new ArrayList<>();
		for (int i = 0; i < NM; i++) {
			List<Integer> il = new ArrayList<>();
			List<Integer> il2 = new ArrayList<>();
			for (int j = 0; j < NM; j++) {
				il.add(0);
				il2.add(0);
			}
			ll.add(il);
			tmpll.add(il2);
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				ll.get(i).set(j, Integer.parseInt(st.nextToken()));
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < R; i++) {
			int r = Integer.parseInt(st.nextToken());
			switch (r) {
			case 1:
				ll = func1();
				break;
			case 2:
				ll = func2();
				break;
			case 3:
				ll = func34();
				ll = func2();
				break;
			case 4:
				ll = func34();
				ll = func1();
				break;
			case 5:
				ll = func5();
				break;
			case 6:
				ll = func6();
				break;
			default:
				break;
			}
		}
		
		print();
		br.close();
	}
}

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 입력 : 
 * 		N(회의의 수)
 * 		{회의 시작 시간} {회의 종료 시간}
 * 출력 :
 * 		사용할 수 있는 회의의 최대 개수
 */
public class Main {
	public static class Pair{
		long start_time;
		long end_time;
		public Pair(long start_time, long end_time){
			this.start_time = start_time;
			this.end_time = end_time;
		}
		
		public String toString() {
			return new StringBuilder().append("[").append(start_time).append(", ").append(end_time).append("]").toString();
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		List<Pair> pl = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			pl.add(new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		Collections.sort(pl, (a, b) -> {
			if(a.end_time == b.end_time) {
				return Long.compare(a.start_time, b.start_time);
			}
			return Long.compare(a.end_time, b.end_time);
		});
		
		List<Pair> res = new ArrayList<>();
		long current_time = 0;
		int cnt = 0;
		
		for(Pair p : pl) {
			if(current_time <= p.start_time) {
				current_time = p.end_time;
				res.add(p);
				cnt++;
			}
		}
        
		System.out.println(Integer.toString(cnt));
		br.close();
	}
}

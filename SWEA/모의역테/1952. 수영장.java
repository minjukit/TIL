import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
49/50 테케2번에서 틀렸던 이유
일수가 없어도 3달권이 싸면 3달권 끊어놓을 수 있다....!!!!!
*/
public class Solution_1952_수영장 {
	static int min;
	static int[] plan, price; //입력받는 값들
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		for(int tc = 1;tc<=t;tc++) {

			plan = new int[12];
			price = new int[4];
			
			min = 1500000;
			st = new StringTokenizer(br.readLine());
			for(int i =0;i<4;i++) {
				price[i] = Integer.parseInt(st.nextToken());
			}
	
			// input
			st = new StringTokenizer(br.readLine());
			for(int i =0;i<12;i++) {
				plan[i] = Integer.parseInt(st.nextToken());
			}

			//1년 이용권
			min = Math.min(min, price[3]);
			// 3개월치
			getMin(0,0,0);
			sb.append("#"+tc+" "+ min+"\n");
		}
		System.out.println(sb);
	}
	private static void getMin(int cur, int cnt, int curPrice) {
		if(curPrice>min) return;
		if(cnt ==12) {
			min =Math.min(min, curPrice);
		}
		for(int i =cur;i<12;i++) {
		
			if(plan[i]==0) {
				getMin(i+1,cnt+1,curPrice);
				getMin(i+3,cnt+3, curPrice + price[2]); // 수영장안가도 미리 끊어놓는게 쌀때가 있음
			}
			else {
				// 1일
				getMin(i+1,cnt+1,curPrice + plan[i]*price[0]);
				// 1달
				getMin(i+1,cnt+1, curPrice + price[1]);
				// 3달
				if(i+2<12 && plan[i+1] >0 && plan[i+2] >0) {
					getMin(i+3,cnt+3, curPrice + price[2]);
				}
			}
		}
	}
	
	/* 중간에 엎은 코드 조합어쩌고 넘 복잡하게 풀지 말고 설계를 잘하자 */
//	public class Solution_1952_수영장 {
//		static int min;
//		static boolean threeSeq, selected[];
//		static int[] plan, price; //입력받는 값들
//		static List<Integer> monthList = new ArrayList<>();
//		public static void main(String[] args) throws NumberFormatException, IOException {
//			
//			StringBuilder sb = new StringBuilder();
//			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//			
//			int t = Integer.parseInt(br.readLine());
//			StringTokenizer st = null;
//			for(int tc = 1;tc<=t;tc++) {
//
//				plan = new int[12];
//				price = new int[4];
//				//selected = new boolean[12];
//				min = 1500000;
//				st = new StringTokenizer(br.readLine());
//				for(int i =0;i<4;i++) {
//					price[i] = Integer.parseInt(st.nextToken());
//				}
//		
//				// input
//				st = new StringTokenizer(br.readLine());
//				for(int i =0;i<12;i++) {
//					plan[i] = Integer.parseInt(st.nextToken());
//					if(plan[i]>0) {
//						monthList.add(i);
//						if(!threeSeq) {
//							if(i+2<12 && plan[i+1]>0 && plan[i+2]>0) {
//								threeSeq = true;
//							}
//						}
//					}
//
//				}
//
//				//1년 이용권
//				min = Math.min(min, price[3]);
//				// 3개월치
//				getMin(0,0,0);
//				sb.append("#"+tc+" "+ min+"\n");
//			}
//			System.out.println(sb);
//		}
//	
//	
//	private static void getMin() {
//		// 1달 + 1일
//		Arrays.fill(selected, false);
//		getMonthDay(0,0);
//		// 3달 + 1일 (3달 연속일때)
//		Arrays.fill(selected, false);
//		if(threeSeq) {
//			int size = monthList.size();
//			//날짜있는 달들 가지고 3개 조합
//			for(int i =0;i<size-2;i++) {
//				for(int j =i+1;j<size-1;j++) {
//					for(int k = j+1;k<size;k++) {
//						//그조합이 연속이라면
//						if(i == j+1 && j == k+1) {
//							get3monthDay(i,j,k);
//						}
//					}
//				}
//			}
//		}
//	}
//
//	private static void get3monthDay(int one, int two, int three) { //3달짜리
//		int curPrice = 0;
//		for(int i =0;i<12;i++) {
//			if(plan[i]==0) continue;
//			// 날짜있는데
//			if(i != one && i != two && i !=three) { // 달 이용권으로 안 선택했다면
//				curPrice += (plan[i]*price[0]);
//			}else {
//				curPrice += price[1];
//			}
//			if(curPrice>min) return; //현재값이 민보다 크면 보지도 않음
//		}
//	
//		min =Math.min(min, curPrice);
//		return;
//	}
//
//	private static void getMonthDay(int cur ,int cnt) { //전부 1일, 전부 1달, 1일1달섞어서 다됨
//		
//		if(cnt == monthList.size()) { // 달있는만큼만 돌면서
//			int curPrice = 0;
//			for(int i =0;i<12;i++) {
//				if(plan[i]==0) continue;
//				// 날짜있는데
//				if(selected[i]) { // 달 이용권으로 선택했다면
//					curPrice += price[1];
//				}else {
//					curPrice += (plan[i]*price[0]);
//				}
//				if(curPrice>min) return; //현재값이 민보다 크면 보지도 않음
//			}
//			min =Math.min(min, curPrice);
//			return;
//		}
//		
//		for(int i =cur;i<12;i++) {
//			if(plan[i]==0) continue;
//			selected[i] = true;
//			getMonthDay(i+1,cnt+1);
//			selected[i] = false;
//			getMonthDay(i+1,cnt+1);
//		}
//	}
}

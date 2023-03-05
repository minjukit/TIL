import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_1767_프로세서연결하기_김민주 {
	static int n,m, coreNum,result, map[][], clone[][];
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,1,-1};
	static List<Node> coreList;
	static List<Node> coreSelected;

	// 최대한 많은 코어에 연결 & 연결된 코어개수 같다면 길이 합 최소
	// 최대한 많은 코어 연결해야하기 때문에 코어 개수 M개 부터 M-1,M-2 로 탐색하고 그 중 나오면 그 개수안에서 길이 합 최소가 되는 경우만 찾고 리턴
	// 1 코어 2 전선 0 빈칸
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		for(int tc = 1;tc<=t;tc++) {
			//테케마다 초기화
			result = 200; m =0; coreNum=0;
			coreList = new ArrayList<>();
			coreSelected = new ArrayList<>();
			// input
			n = Integer.parseInt(br.readLine());
			
			map = new int[n][n];
			
			for(int i =0;i<n;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j =0;j<n;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 1) {
						coreNum++; // 입력받은 총 코어 개수
						// 코어가 가장자리에 위치해있다면 이미 연결된 코어
						if(i==0 || j ==0 || i== n-1 || j == n-1) {
							m++; 
							continue;
						}
						coreList.add(new Node(i,j));
					}
				}
			}
			
			if(coreNum != m) { //모든 코어가 가장자리에 있는 경우의 수에는 탐색할 필요 없음
				//M개 코어부터 
				for(int i =coreNum;i>=1; i--) { //최소 1개 이상
					if(result != 200) break; // 이미 답 찾았으면 아래 경우는 볼 필요 없음
					comb(0,0,i); // i개 조합뽑기
				}
			}
			
			sb.append("#"+tc+" "+ result+"\n");
		}
		System.out.println(sb);
	}
	
	private static void comb(int cnt,int cur, int total) {
		if(cnt==total-m) { //가장자리 코어 뺀 코어개수랑 같다면
			getMin(0,0); // 선택된 코어 0번부터 확인 연결가능한지
			return;
		}
		
		for(int i =cur;i<coreList.size();i++) {
			Node curNode = coreList.get(i);
			coreSelected.add(new Node(curNode.x,curNode.y));
			comb(cnt+1, i+1,total);
			coreSelected.remove(coreSelected.size()-1);
		}
		
	}

	private static void getMin(int cur, int length) {

		if(cur == coreSelected.size()) {
			result = Math.min(result, length);
			return;
		}
			// 상 하 좌 우 탐색 연결가능한지
			for(int i =0;i<4;i++) {
				int nx = coreSelected.get(cur).x;
				int ny = coreSelected.get(cur).y;
				int lineLen = 0;
				boolean connected = false;
				while(true) {
					nx += dx[i];
					ny += dy[i];
					if(nx<0 || nx>=n || ny<0||ny>=n) {
						connected = true; //테두리까지 갔다는 건 전선 놓을 수 있다는 뜻
						break;
					}
					if(map[nx][ny] != 0) break; // 전선 못놓으면 브레이크
					map[nx][ny] = 2;
					lineLen++;
				}
				if(connected) getMin(cur+1,length+lineLen); // 현재 길이 가지고 다음 코어 확인한다
				while(true){ // 돌려놓기
					nx -= dx[i]; ny-=dy[i]; //더해준만큼 빼서 원상태 만나면 스탑
					if(nx == coreSelected.get(cur).x && ny == coreSelected.get(cur).y) break;
					map[nx][ny] = 0; // 배열로 원래대로
				}
			}

	}
}

class Node{
	int x;
	int y;
	public Node(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	@Override
	public String toString() {
		return "Node [x=" + x + ", y=" + y + "]";
	}
	
}

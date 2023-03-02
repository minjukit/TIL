import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static int[][] map; //실제 맵과 깨뜨릴수있는벽돌의 개수배열
	static int n, w, h, curCrash,max, originNum;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static ArrayList<Integer> marble = new ArrayList<>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		for (int tc = 1; tc <= T; tc++) {

			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken()); // 구슬 개수
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());

			map = new int[h][w];
			originNum =0; max = 0; // 테케마다 초기화
		
			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < w; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j]>0) originNum ++; //초기 벽돌 개수
				}
			}

			// 구슬의 경우의 수 : 중복순열
			permu(0);
			sb.append("#"+tc+" "+(originNum-max)+"\n");
		}
		System.out.println(sb);
	}

	private static void permu(int cnt) {
		if(cnt == n) {
			game(); //중복순열 만들어졌으면 보내기
			return;
		}
		
		for(int i=0;i<w;i++) {
			marble.add(i);
			permu(cnt+1);
			marble.remove(marble.size()-1);
		}
	}

	private static void game() {
		// 구슬개수만큼 벽돌깨기
		int[][] mapClone = new int[h][w];
		for(int i =0;i<h;i++) { //2차원맵 클론
			mapClone[i] = map[i].clone();
		}
		curCrash = 0; //init
		for(int i =0;i<n;i++) {
			int col = marble.get(i); //현재 선택 열
			for(int c=0;c<h;c++) {
				if(mapClone[c][col]==1) {
					mapClone[c][col] = 0;
					curCrash+=1;
					break;
				}
				else if(mapClone[c][col]>1) { // 가장 가까운 벽돌 깨기

					int cur = mapClone[c][col];
					mapClone[c][col] = 0;
					curCrash+=1; // 젤 윗 자기자신 개수 세기
					crash(cur, c, col, mapClone); // 연결된 벽돌깨기
					down(mapClone);
					break; // 열마다 벽돌은 한번만깨야하니 다음 열 선택하도록 브레이크
				}
			}
		}
		
		max = Math.max(max, curCrash);
	}

	private static void crash(int num, int x, int y, int[][] mapClone) {
		if(originNum == curCrash) return;
		// 가중치 수만큼 상하좌우 탐색 후 있으면 재귀 돌아가게
		for (int i = 0; i < 4; i++) {
			for (int d = 1; d < num; d++) {
				int nx = x + d * dx[i];
				int ny = y + d * dy[i];
				if (nx < 0 || ny < 0 || nx >= h || ny >= w) continue;
				if (mapClone[nx][ny] == 0) continue;
				else if (mapClone[nx][ny] == 1) {
					curCrash += 1;
					mapClone[nx][ny] = 0; //0으로 바꿔주기
				}
				else {
					int cur = mapClone[nx][ny];
					curCrash += 1;
					mapClone[nx][ny] = 0; //0으로 바꿔주기
					crash(cur, nx, ny, mapClone);
				}
			}
		}
	}
	private static void down(int[][] mapClone) { // 벽돌깨고 아래로 당기기
		for(int c = 0;c<w;c++) { // 열마다
			List<Integer> col = new ArrayList<>();
			for(int r= h-1;r>=0;r--) { // 맨 아랫줄부터
				if(mapClone[r][c] >0) col.add(mapClone[r][c]);
			}
			int add = h-col.size();
			for(int i =0;i<add;i++) {
				col.add(0);
			}
			int i =0;
			for(int r= h-1;r>=0;r--) { // 맨 아랫줄부터
				mapClone[r][c]=col.get(i);
				i++;
			}
		}
	}
}

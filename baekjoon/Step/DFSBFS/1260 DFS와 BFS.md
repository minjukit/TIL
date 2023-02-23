```java
package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class dfsbfs_1260 {
    static int N;
    static int M;
    static int V;
    static boolean[][] arr;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        arr = new boolean[N + 1][N + 1];
        visited = new boolean[N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            arr[v2][v1] = true;
            arr[v1][v2] = true;

        }

        dfs(V);
        sb.append("\n");
        Arrays.fill(visited,false);
        bfs(V);
        System.out.println(sb);

    }

    private static void dfs(int cur) {
        visited[cur] = true;
        sb.append(cur + " ");

        for (int j = 1; j <= N; j++) {
            if (!visited[j]) {
                if (arr[cur][j]) {
                    dfs(j);
                }
            }
        }
    }

    private static void bfs(int cur){
        Queue<Integer> q = new LinkedList<>();
        q.add(cur);
        visited[cur] = true;

        while(!q.isEmpty()){
            cur = q.poll();
            sb.append(cur + " ");

            for(int j =1;j<=N;j++){
                if(arr[cur][j]){
                    if(!visited[j]){
                        q.add(j);
                        visited[j] = true;
                    }
                }
            }


        }
    }
}
```

# TIL

##0916

백준 1085번 - 직사각형에서 탈출

문제
```
한수는 지금 (x, y)에 있다. 직사각형은 각 변이 좌표축에 평행하고,&nbsp;왼쪽 아래 꼭짓점은 (0, 0), 오른쪽 위 꼭짓점은 (w, h)에 있다. 직사각형의 경계선까지 가는 거리의 최솟값을 구하는 프로그램을 작성하시오.
```
입력
```
첫째 줄에 x, y, w, h가 주어진다.
```
출력
```
첫째 줄에 문제의 정답을 출력한다.
```
<br>

예제 입력
```
6 2 10 3
```
예제 출력
```
1
```
<br>


풀이
```java

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//숫자카드 10815
public class BinaryCard {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        String[] s = br.readLine().split(" ");
        int[] my = new int[n];
        for (int i = 0; i < n; i++) {
            my[i] = Integer.parseInt(s[i]);
        }
        int m = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] guess = new int[m];
        for (int i = 0; i < m; i++) {
            guess[i] = Integer.parseInt(st.nextToken());

        }

        //정렬 후
        Arrays.sort(my);
        // 이분탐색으로 원소 있는지 확인
        for (int i : guess) {
            sb.append(binarySearch(i, my,n) + " ");
        }
        System.out.println(sb);
    }
	//이분탐색
    public static int binarySearch(int n, int[] arr, int len){
        // index
        int start = 0;
        int end = len-1;
        int mid;

        while (end>=start){
            mid = (start + end )/2;

            if(n==arr[mid]) return 1;
            else if (n<arr[mid]) {
                end = mid-1;
            }else start = mid+1;

        }

        return 0;
    }
}

```

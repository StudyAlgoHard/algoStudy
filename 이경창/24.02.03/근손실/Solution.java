package 근손실;

import java.util.*;
import java.io.*;

public class Solution {

    private static int N, K;
    private static int[] arr;
    private static boolean[] visited;
    private static int answer;

    // 순열
    private static void prim(int cnt, int sum){
        if(cnt == arr.length){
            answer += (sum >= 500 ? 1 : 0);
            return;
        }

        for(int i = 0; i < arr.length; i++){
            if(visited[i]) continue;
            if(sum - K + arr[i] < 500) continue;
            visited[i] = true;
            prim(cnt + 1, sum - K + arr[i]);
            visited[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        K = Integer.parseInt(tokenizer.nextToken());

        arr = new int[N];
        visited = new boolean[N];

        tokenizer = new StringTokenizer(reader.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(tokenizer.nextToken());
        }

        prim(0, 500);

        System.out.println(answer);

        reader.close();
    }
}

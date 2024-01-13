package 스타트와_링크;

import java.util.*;
import java.io.*;

public class Solution {

    private static int N;
    private static int[][] arr;
    private static int[] number;
    private static boolean[] visited;

    private static void perm(int cnt, int start){
        if(cnt == arr.length){
            System.out.println(Arrays.toString(number));

            return;
        }

        for(int i = start; i < N; i++){
            number[cnt] = i;
            perm(cnt + 1, start + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(reader.readLine());
        arr = new int[N][N];
        visited = new boolean[N];
        number = new int[N];

        for(int i = 0; i < N; i++){
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for(int j = 0; j < N; j++){
                arr[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        perm(0, 0);

        reader.close();
    }
}

package 진우의_달_여행;

import java.io.*;
import java.util.*;

public class Solution {

    private static int N, M;
    private static int[][] arr;
    private static int[][][] dp;
    private static int[][] direction;

    private static boolean isWithInRange(int row, int col){
        return 0 <= row && 0 <= col && row < N && col < M;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());

        arr = new int[N][M];
        dp = new int[N][M][3];
        direction = new int[N][M];

        for(int i = 0; i < N; i++){
            tokenizer = new StringTokenizer(reader.readLine());
            for(int j = 0; j < M; j++){
                arr[i][j] = Integer.parseInt(tokenizer.nextToken());
                dp[i][j][0] = Integer.MAX_VALUE;
                dp[i][j][1] = Integer.MAX_VALUE;
                dp[i][j][2] = Integer.MAX_VALUE;
                direction[i][j] = -1;
            }
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(i == 0) {
                    dp[i][j][0] = arr[i][j];
                    dp[i][j][1] = arr[i][j];
                    dp[i][j][2] = arr[i][j];
                }
                else {
                    if (j != 0)
                        dp[i][j][0] = Math.min(dp[i - 1][j - 1][1], dp[i - 1][j - 1][2]) + arr[i][j];
                    dp[i][j][1] = Math.min(dp[i - 1][j][0], dp[i - 1][j][2]) + arr[i][j];
                    if (j != M - 1)
                        dp[i][j][2] = Math.min(dp[i - 1][j + 1][0], dp[i - 1][j + 1][1]) + arr[i][j];
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        for(int i = 0; i < M; i++){
            for(int j = 0; j < 3; j++){
                answer = Math.min(answer, dp[N- 1][i][j]);
            }
        }

//        System.out.println(Arrays.deepToString(dp));
        System.out.println(answer);

        reader.close();
    }
}

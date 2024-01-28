package 가장_큰_정사각형;

import java.io.*;
import java.util.*;

public class Solution {

    private static int n, m;
    private static int[][] arr;
    private static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());

        arr = new int[n][m];
        dp = new int[n][m];

        for(int i = 0; i < n; i++){
            char[] cArr = reader.readLine().toCharArray();
            for(int j = 0; j < m; j++){
                arr[i][j] = cArr[j] - '0';
            }
        }

        int count = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(arr[i][j] == 1){
                    dp[i][j] = 1;
                    count++;
                }
            }
        }

        int result = (count == 0 ? 0 : 1);
        for(int i = 1; i < n; i++){
            for(int j = 1; j < m; j++){
                if(dp[i][j] == 1){
                    int minData = Math.min(dp[i - 1][j], dp[i][j - 1]);
                    minData = Math.min(minData, dp[i - 1][j - 1]);
                    dp[i][j] += minData;
                    result = Math.max(result, dp[i][j]);
                }
            }
        }

        System.out.println(result * result);

        reader.close();
    }
}

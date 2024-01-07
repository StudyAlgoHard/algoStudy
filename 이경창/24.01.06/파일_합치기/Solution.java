package 파일_합치기;

import java.util.*;
import java.io.*;

public class Solution {

    private static int T, K;
    private static int[] arr;
    private static int[][] dp;
    private static int[] sum;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder = new StringBuilder();

        T = Integer.parseInt(reader.readLine());

        for(int tk = 1; tk <= T; tk++){
            K = Integer.parseInt(reader.readLine());
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            arr = new int[K];
            sum = new int[K];
            dp = new int[K][K];

            for(int i = 0; i < K; i++){
                arr[i] = Integer.parseInt(tokenizer.nextToken());
            }

            sum[0] = arr[0];
            for(int i = 1; i < K; i++){
                sum[i] = sum[i - 1] + arr[i];
            }

            for(int sectionCnt = 1; sectionCnt <= K; sectionCnt++){
                for(int from = 0; from + sectionCnt < K; from++){
                    int to = from + sectionCnt;
                    dp[from][to] = Integer.MAX_VALUE;
                    for(int section = from; section < to; section++){
                        dp[from][to] = Math.min(dp[from][to], dp[from][section] + dp[section + 1][to] + (sum[to] - (from - 1 < 0 ? 0 : sum[from - 1])));
                    }
                }
            }
            builder.append(dp[0][K - 1]).append("\n");
        }

        System.out.print(builder.toString());
        reader.close();
    }
}

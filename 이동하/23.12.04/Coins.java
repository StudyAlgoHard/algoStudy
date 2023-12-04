import java.util.*;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int testcase = 1; testcase <= T; testcase++) {
            int N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine(), " ");
            int [] coin = new int [N+1];
            for (int i = 1; i <= N; i++) coin[i] = Integer.parseInt(st.nextToken());
            int target = Integer.parseInt(br.readLine());
            int [] [] dp = new int [N+1][target+1];
            dp[0][0] = 1;
            for (int i = 1; i <= N; i++) {
                for (int j = 0; j < coin[i]; j++) {
                    dp[i][j] = dp[i-1][j];
                }
                for (int j = coin[i]; j <= target; j++) {
                    dp[i][j] = dp[i-1][j] + dp[i][j-coin[i]];
                }
            }
            sb.append(dp[N][target]).append("\n");
        }
        System.out.print(sb);
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 파일합치기{
    public static void main(String[] args)throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int T;

        T = Integer.parseInt(bf.readLine());
        for (int tc = 0; tc < T; tc++) {
            int k;
            int[] chap;
            int[] sum;
            int[][] dp;

            k = Integer.parseInt(bf.readLine());
            chap = new int[k + 1];
            dp = new int[k + 1][k + 1];
            sum = new int[k + 1];

            st = new StringTokenizer(bf.readLine());
            for (int i = 1; i <= k; i++) {
                chap[i] = Integer.parseInt(st.nextToken());
                sum[i] = sum[i - 1] + chap[i];
            }

            for (int n = 1; n <= k; n++) {
                for (int from = 1; from + n <= k; from++) {
                    int to = from + n;
                    dp[from][to] = Integer.MAX_VALUE;
                    for (int divide = from; divide < to; divide++) {
                        dp[from][to] = Math.min(dp[from][to], dp[from][divide] + dp[divide + 1][to] + sum[to] - sum[from - 1]);
                    }
                }
            }

            System.out.println(dp[1][k]);
        }
    }
}

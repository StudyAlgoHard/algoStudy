
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int T = Integer.parseInt(st.nextToken());

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(bf.readLine());

            int N = Integer.parseInt(st.nextToken());

            int [] arr = new int [N+1];

            st = new StringTokenizer(bf.readLine());
            for (int j = 1; j <= N ; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(bf.readLine());
            int target = Integer.parseInt(st.nextToken());


            int [][] dp = new int [N+1][target+1];

            for (int j = 1; j <= N; j++) {
                dp[j][arr[j]]++;
                for (int k = 1; k <= target; k++) {
                    if(k-arr[j]<0){
                        dp[j][k] = dp[j-1][k];
                        continue;
                    }
                    dp[j][k] += dp[j-1][k] + dp[j][k-arr[j]];
                }
            }
            System.out.println(dp[N][target]);
        }
    }
}

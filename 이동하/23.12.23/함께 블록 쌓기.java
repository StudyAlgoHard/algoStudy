import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        int [] [] dp = new int [N+1] [H+1];

        ArrayList<Integer> [] data = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            data[i] = new ArrayList<Integer>();
            st = new StringTokenizer(br.readLine(), " ");
            while (st.hasMoreTokens()) data[i].add(Integer.parseInt(st.nextToken()));
        }

        dp[0][0] = 1;
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= H; j++) {
                dp[i][j] = dp[i-1][j];
                for (int k : data[i]) if (j-k >= 0) dp[i][j] = (dp[i][j] + dp[i-1][j-k]) % 10007;
            }
        }

//        for (int [] i : dp) System.out.println(Arrays.toString(i));
        System.out.println(dp[N][H]);
    }
}
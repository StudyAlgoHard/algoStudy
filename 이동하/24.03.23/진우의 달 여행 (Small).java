import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int [] [] data = new int [N] [M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                data[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int [] [] [] dp = new int [N] [M] [3];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < 3; k++) {
                    dp[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }
        for (int i = 0; i < M; i++) {
            for (int j = 1, k = 0; j >= -1; j--, k++) {
                if (i + j < 0 || i + j >= M) continue;
                dp[1][i][k] = data[1][i] + data[0][i+j];
            }
        }

        for (int i = 2; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (j > 0) dp[i][j][2] = Math.min(dp[i-1][j-1][0], dp[i-1][j-1][1]) + data[i][j];
                dp[i][j][1] = Math.min(dp[i-1][j][0], dp[i-1][j][2]) + data[i][j];
                if (j < M-1) dp[i][j][0] = Math.min(dp[i-1][j+1][1], dp[i-1][j+1][2]) + data[i][j];
            }
        }

//        for (int j = 0; j < N; j++) {
//            for (int i = 0; i < 4; i++) System.out.println(Arrays.toString(dp[j][i]));
//            System.out.println();
//        }

        int answer = Integer.MAX_VALUE;

        for (int i = 0; i < M; i++) {
            for (int k : dp[N-1][i]) {
                answer = Math.min(answer, k);
            }
        }
        System.out.println(answer);
    }
}

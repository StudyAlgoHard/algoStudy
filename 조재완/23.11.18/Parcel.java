import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Parcel {
    public static void main(String[] args)throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int W = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int weights[] = new int[N];
        int dp[][] = new int[400001][2];
        int answer = 0;

        st = new StringTokenizer(bf.readLine());
        for(int i = 0 ; i < N ; i++){
            weights[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < N; i++) {
            for (int j = i+1; j <N ; j++) {
                int weight = weights[i] + weights[j];
                if(dp[weight][0]==0){
                    dp[weight][0] = i;
                    dp[weight][1] = j;
                }
            }
        }

        loop:
        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                int weight = W-(weights[i] + weights[j]);
                if (weight > 400000 || weight < 0 || dp[weight][0] == 0) continue;
                if(dp[weight][0] != i && dp[weight][0] !=j && dp[weight][1] != i && dp[weight][1] != j){
                    answer = 1;
                    break loop;
                }
            }
        }
        if(answer == 1){
            System.out.println("YES");
        }else{
            System.out.println("NO");
        }

    }
}

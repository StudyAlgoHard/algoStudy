import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 순열의순서 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        long [] f = new long[21];

        boolean[] v = new boolean[21];
        Arrays.fill(f,1);

        for(int i = 1;i<=20; i++){
            f[i] = f[i-1]*i;
        }


        int n = Integer.parseInt(bf.readLine());
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int [] num = new int[n];
        int flag = Integer.parseInt(st.nextToken());

        if(flag == 2){
            for (int i = 0; i < n; i++) {
                num[i] = Integer.parseInt(st.nextToken());
            }

            long ans = 1;
            for (int i = 0; i < n; i++) {
                for (int j = 1; j < num[i]; j++) {
                    if(!v[j]){
                        ans += f[n-i-1];
                    }
                }
                v[num[i]] = true;
            }
            System.out.println(ans);
        }
        else{
            long k = Long.parseLong(st.nextToken());
            for (int i = 0; i < n; i++) {
                for (int j = 1; j <= n ; j++) {
                    if(v[j]){
                        continue;
                    }
                    if(f[n-i-1] < k){
                        k -= f[n-i-1];
                    }else{
                        num[i] = j;
                        v[j] = true;
                        break;
                    }
                }
            }
            for (int i = 0; i < n; i++) {
                System.out.print(num[i] + " ");

            }
        }

    }
}

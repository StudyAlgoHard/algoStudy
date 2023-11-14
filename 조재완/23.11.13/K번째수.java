

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());
        int K = Integer.parseInt(bf.readLine());

        long left = 1;
        long right = K;

        while (left < right){
            long mid = (left + right)/2;
            long count = 0;
            for (int i = 1; i <= N; i++) {
                count += Math.min(mid/i,N);
            }

            if(K <= count){
                right=mid;
            }else{
                left = mid+1;
            }
        }

        System.out.println(left);

    }
}

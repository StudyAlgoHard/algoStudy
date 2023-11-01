

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_N포커_16565 {
    public static void main(String[] args)throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());


        int iCk [][] = new int [53][53];

        for(int i = 0; i <= 52; i++){
            iCk[i][0] = 1;
            iCk[i][i] = 1;
            for(int k = 1;  k < i; k++){
                iCk[i][k] = iCk[i-1][k-1] + iCk[i-1][k];
                iCk[i][k] %= 10007;
                iCk[i][i-k] = iCk[i][k];
            }
        }

        int ans = 0;
        for(int i = 4; i <= N; i +=4){
            if((i/4)%2 == 1){
                ans = (ans + iCk[13][i/4] * iCk[52-i][N-i])%10007;
            }else{
                ans = (ans - (iCk[13][i/4] * iCk[52-i][N-i]))%10007;
            }
        }

        if(ans < 0){
            ans += 10007;
        }

//        for (int i = 0; i < 53; i++) {
//            for (int j = 0; j < 53; j++) {
//                System.out.print(iCk[i][j] + " ");
//            }
//            System.out.println();
//        }

        System.out.println(ans);


    }
}

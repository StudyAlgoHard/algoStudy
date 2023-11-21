import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int testcase = 1; testcase <= T; testcase++) {
             int K = Integer.parseInt(br.readLine());
             int [] data = new int [K];
             st = new StringTokenizer(br.readLine(), " ");
             int [] [] summation = new int [K] [K];
             for (int i = 0; i < K; i++) {
                 data[i] = Integer.parseInt(st.nextToken());
                 summation[i][i] = data[i];
             }
             for (int i = 0; i < K; i++) {
                  for (int j = i+1; j < K; j++) {
                      summation[i][j] = summation[i][j-1] + data[j];
                  }
             }
             for (int i = 0; i < K; i++) {
                 summation[i][i] = 0;
             }
             int r, c;
             for (int i = 2; i < K; i++) {
                 r = 0; c = i;
                 for (int j = 0; j < K-i; j++) {
//                     System.out.print("(" + r + ", " + c + ") ");
                     int temp = Integer.MAX_VALUE;
                     for (int k = 0; k < i; k++) {
                         temp = Math.min(temp, summation[r][r+k] + summation[r+k+1][c]);
                     }
                     summation[r][c] += temp;
                     r++;
                     c++;
                 }
//                 System.out.println();
             }
//             for (int [] s : summation) System.out.println(Arrays.toString(s));
//             System.out.println(summation[0][K-1]);
             sb.append(summation[0][K-1] + "\n");
        }
        System.out.println(sb);
    }
}

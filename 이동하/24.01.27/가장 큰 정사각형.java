import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int [] [] board = new int [N+1] [M+1];
        for (int i = 1; i <= N; i++) {
            String s = br.readLine();
            for (int j = 1; j <= M; j++) {
                board[i][j] = s.charAt(j-1) - '0';
            }
        }

        int answer = 0;
        int [] [] marker = new int [N+1] [M+1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (board[i][j] == 0) continue;
                int temp = Integer.MAX_VALUE;
                temp = Math.min(temp, marker[i-1][j]);
                temp = Math.min(temp, marker[i][j-1]);
                temp = Math.min(temp, marker[i-1][j-1]);
                marker[i][j] = temp+1;
                answer = Math.max(answer, marker[i][j]);
            }
        }
//        for (int [] k : marker) System.out.println(Arrays.toString(k));
        System.out.println(answer * answer);
    }
}
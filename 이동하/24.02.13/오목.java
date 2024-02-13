import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int [] [] board = new int [19][19];
        for (int i = 0; i < 19; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 19; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int [] [] delta = {{0,1}, {1,1}, {1,0}};
        boolean [] [] [] check6 = new boolean [19] [19] [4];
        Loop2:
        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 19; j++) {
                if (board[i][j] == 0) continue;
                Loop1:
                for (int d = 0; d < 3; d++) {
                    if (check6[i][j][d]) continue;
                    for (int k = 4; k >= 1; k--) {
                        int next_r = i + delta[d][0]*k;
                        int next_c = j + delta[d][1]*k;
                        if (next_r >= 19 || next_c >= 19) continue Loop1;
                        if (board[next_r][next_c] != board[i][j]) continue Loop1;
                    }
                    if ((i + delta[d][0]*5 < 19 && j + delta[d][1]*5 < 19) && board[i + delta[d][0]*5][j + delta[d][1]*5] == board[i][j]) {
                        int c = 1;
                        while (true) {
                            int n_r = i + delta[d][0] * c;
                            int n_c = j + delta[d][1] * c;
                            if (n_r >= 19 || n_c >= 19) continue Loop1;
                            if (board[n_r][n_c] != board[i][j]) continue Loop1;
                            check6[n_r][n_c][d] = true;
                            c++;
                        }
                    }
                    System.out.println(board[i][j]);
                    System.out.println((i+1) + " " + (j+1));
                    return;
                }
            }
        }

        int [] d = {-1, 1};
        Loop4:
        for (int j = 0; j < 19; j++) {
            Loop3:
            for (int i = 18; i >= 0; i--) {
                if (board[i][j] == 0) continue;
                if (check6[i][j][3]) continue;
                for (int k = 4; k >= 1; k--) {
                    int next_r = i + d[0]*k;
                    int next_c = j + d[1]*k;
                    if (next_r < 0 || next_c >= 19) continue Loop3;
                    if (board[next_r][next_c] != board[i][j]) continue Loop3;
                }
                if ((i + d[0]*5 >= 0 && j + d[1]*5 < 19) && board[i + d[0]*5][j + d[1]*5] == board[i][j]) {
                    int c = 1;
                    while (true) {
                        int n_r = i + d[0] * c;
                        int n_c = j + d[1] * c;
                        if (n_r < 0 || n_c >= 19) continue Loop3;
                        if (board[n_r][n_c] != board[i][j]) continue Loop3;
                        check6[n_r][n_c][3] = true;
                        c++;
                    }
                }
                System.out.println(board[i][j]);
                System.out.println((i+1) + " " + (j+1));
                return;
            }
        }
        System.out.println(0);
    }
}
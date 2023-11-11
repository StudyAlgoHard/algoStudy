import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
//    static int [] [] temp_board;
    static int mandering(int r, int c, int d1, int d2, int total, int N, int [] [] board) {
        int [] summation = new int [5];
        if (r + d1 + d2 >= N || c - d1 < 0 || c + d2 >= N) return - 1;

        //1
        for (int i = 0; i < r; i++) {
            for (int j = 0; j <= c; j++) {
                summation[1] += board[i][j];
//                temp_board[i][j] = 1;
            }
        }
        int limit = c - 1;
        for (int i = r; i < r + d1; i++) {
            for (int j = 0; j <= limit; j++) {
//                temp_board[i][j] = 1;
                summation[1] += board[i][j];
            }
            limit--;
        }

        //3
        for (int i = r + d1; i < r + d1 + d2; i++) {
            for (int j = 0; j <= limit; j++) {
                summation[3] += board[i][j];
//                temp_board[i][j] = 3;
            }
            limit++;
        }
        for (int i = r + d1 + d2; i < N; i++) {
            for (int j = 0; j < c - d1 + d2; j++) {
                summation[3] += board[i][j];
//                temp_board[i][j] = 3;
            }
        }

        //2
        for (int i = 0; i < r; i++) {
            for (int j = c + 1; j < N; j++) {
                summation[2] += board[i][j];
//                temp_board[i][j] = 2;
            }
        }
        int start = c;
        for (int i = r; i <= r + d2; i++) {
            start++;
            for (int j = start; j < N; j++) {
                summation[2] += board[i][j];
//                temp_board[i][j] = 2;
            }
        }

        //4
        start--;
        for (int i = r + d2 + 1; i <= r + d1 + d2; i++) {
//            System.out.println(start);
             for (int j = start; j < N; j++) {
                 summation[4] += board[i][j];
//                 temp_board[i][j] = 4;
             }
             start--;
        }
        for (int i = r + d1 + d2 + 1; i < N; i++) {
            for (int j = c - d1 + d2; j < N; j++) {
                summation[4] += board[i][j];
//                temp_board[i][j] = 4;
            }
        }

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= 4; i++) {
            min = Math.min(min, summation[i]);
            max = Math.max(max, summation[i]);
            total -= summation[i];
        }

        min = Math.min(min, total);
        max = Math.max(max, total);
//        for (int [] i : temp_board) System.out.println(Arrays.toString(i));
        return max - min;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int [] [] board = new int [N] [N];
        int total = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                total += board[i][j];
            }
        }
        int [] [] [] [] answer = new int [N] [N] [N] [N];
        int answer_value = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int d1 = 1; d1 < N-1; d1++) {
                    for (int d2 = 1; d2 < N-1; d2++) {
//                        temp_board = new int [N] [N];
                        answer[i][j][d1][d2] = mandering(i, j, d1, d2, total, N, board);
                        if (answer[i][j][d1][d2] == -1) {
//                            System.out.println("no");
                            continue;
                        }
                        answer_value = Math.min(answer_value, answer[i][j][d1][d2]);
                    }
                }
            }
        }
        System.out.println(answer_value);
//        int s = mandering(3, 2, 2, 1, total, N, board);
    }
}

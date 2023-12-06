import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

class Paper {
    int r, c, size;
    Paper(int r, int c, int size) {
        this.r = r;
        this.c = c;
        this.size = size;
    }
}
public class Main {
    static int answer;
    static int [] used;
    static int [] [] board;
    static boolean [] [] [] possible;
    static boolean [] [] visit;
    static ArrayDeque<Paper> q;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        board = new int [10] [10];
        for (int i = 0; i < 10; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 10; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        possible = new boolean [10][10][6];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (board[i][j] == 0) continue;
                possible[i][j][1] = true;
                Loop1:
                for (int k = 2; k <= 5; k++) {
                    if (i+k-1 >= 10 || j+k-1 >= 10) break;
                    for (int a = 0; a < k; a++) {
                        if (board[i+a][j+k-1] == 0) break Loop1;
                    }
                    for (int a = 0; a < k-1; a++) {
                        if (board[i+k-1][j+a] == 0) break Loop1;
                    }
                    possible[i][j][k] = true;
                }
            }
        }
//        for (int k = 1; k <= 5; k++) {
//            for (int i = 0; i < 10; i++) {
//                for (int j = 0; j < 10; j++) {
//                    System.out.print((possible[i][j][k]) ? 1 : 0);
//                }
//                System.out.println();
//            }
//            System.out.println();
//
//        }
        visit = new boolean [10] [10];
        used = new int [6];

        answer = 101;
        dfsing(0, 0);
        System.out.println((answer == 101) ? -1 : answer);
    }
    static void dfsing(int start, int count) {
        boolean flag = false;
//        System.out.println(Arrays.toString(used));
        for (int i = start; i < 100; i++) {
            int r = i / 10;
            int c = i % 10;
            if (!possible[r][c][1]) continue;
            if (visit[r][c]) continue;

            if (used[1] < 5) {
                visit[r][c] = true;
                used[1]++;
//                System.out.println(r + " " + c + " 1");
                dfsing(r * 10 + c + 1, count + 1);
                used[1]--;
                visit[r][c] = false;
            }
            Loop2:
            for (int s = 2; s <= 5; s++) {
                if (!possible[r][c][s]) break;
                if (used[s] >= 5) continue;
                for (int ii = r; ii < r+s; ii++) {
                    for (int jj = c; jj < c+s; jj++) {
                        if (visit[ii][jj]) break Loop2;
                    }
                }
                for (int ii = r; ii < r+s; ii++) {
                    for (int jj = c; jj < c+s; jj++) {
                        visit[ii][jj] = true;
                    }
                }
                used[s]++;
//                System.out.println(r + " " + c + " " + s);
                dfsing(r*10 + c + 1, count+1);
                used[s]--;
                for (int ii = r; ii < r+s; ii++) {
                    for (int jj = c; jj < c+s; jj++) {
                        visit[ii][jj] = false;
                    }
                }
            }
            flag = true;
            break;
        }
        if (!flag) {
            for (int i = 1; i < 5; i++) {
                if (used[i] > 5) return;
            }
//            for (int i = 0; i < 10; i++) {
//                for (int j = 0; j < 10; j++) {
//                    System.out.print((visit[i][j]) ? "1 " : "0 ");
//                }
//                System.out.println();
//            }
            answer = Math.min(answer, count);
//            System.out.println(count);
//            System.out.println(Arrays.toString(used));
//            System.out.println();
        }
    }
}

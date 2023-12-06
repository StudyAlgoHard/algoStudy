import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

class Node {
    int r, c;
    Node (int r, int c) {
        this.r = r;
        this.c = c;
    }
}
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int [] [] delta = {{0,1},{1,0},{0,-1},{-1,0}};

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        boolean [] [] [] board = new boolean[10][N][M];
        for (int i = 0; i < N; i++) {
            String t = br.readLine();
            for (int j = 0; j < M; j++) {
                int tt = t.charAt(j) - '0';
                for (int k = 1; k <= tt; k++) {
                    board[k][i][j] = true;
                }
            }
        }
        int count = 0;
        for (int k = 1; k <= 9; k++) {
//            System.out.println(k);
//            for (boolean [] j : board[k]) {
//                for (boolean b : j) {
//                    System.out.print((b) ? 1 : 0);
//                }
//                System.out.println();
//            }

            boolean [] [] visit = new boolean [N] [M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (board[k][i][j]) continue;
                    if (visit[i][j]) continue;
                    int temp_count = 1;
                    ArrayDeque<Node> q = new ArrayDeque<>();
                    visit[i][j] = true;
                    q.offerLast(new Node(i, j));
                    boolean flag = false;
                    while (!q.isEmpty()) {
                        Node node = q.pollFirst();
                        for (int [] d : delta) {
                            int next_r = node.r + d[0];
                            int next_c = node.c + d[1];
                            if (next_r < 0 || next_r >= N || next_c < 0 || next_c >= M) {
                                flag = true;
                                continue;
                            }
                            if (board[k][next_r][next_c]) continue;
                            if (visit[next_r][next_c]) continue;
                            visit[next_r][next_c] = true;
                            temp_count++;
                            q.offerLast(new Node(next_r, next_c));
                        }
                    }
                    if (flag) continue;
                    count += temp_count;
//                    System.out.println(i + " " + j + " " + temp_count);
                }
            }
//            System.out.println(count);
//            System.out.println();
        }
        System.out.println(count);
    }
}

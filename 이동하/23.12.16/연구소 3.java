import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Node {
    int r, c;
    Node(int r, int c) {
        this.r = r;
        this.c = c;
    }

}
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int [] [] delta = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        st = new StringTokenizer(br.readLine(),  " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int [] [] board = new int [N] [N];
        ArrayList<Node> virus = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 2) virus.add(new Node(i, j));
            }
        }

        ArrayList<int []> virus_combination = new ArrayList<>();
        dfsing(0, virus.size(), 0, M, new int [M], virus_combination);
//        for (int [] v : virus_combination) System.out.println(Arrays.toString(v));

        int [] [] [] board_weight = new int [virus.size()] [N] [N];
        for (int k = 0; k < virus.size(); k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    board_weight[k][i][j] = Integer.MAX_VALUE;
                }
            }
            Node n = virus.get(k);
            board_weight[k][n.r][n.c] = 0;
            ArrayDeque<Node> q = new ArrayDeque<>();
            q.offerLast(new Node(n.r, n.c));
            while (!q.isEmpty()) {
                Node node = q.pollFirst();
                for (int [] d : delta) {
                    int next_r = node.r + d[0];
                    int next_c = node.c + d[1];
                    if (next_r < 0 || next_r >= N || next_c < 0 || next_c >= N) continue;
                    if (board[next_r][next_c] == 1) continue;
                    if (board_weight[k][next_r][next_c] < Integer.MAX_VALUE) continue;
                    board_weight[k][next_r][next_c] = board_weight[k][node.r][node.c] + 1;
                    q.offerLast(new Node(next_r, next_c));
                }
            }
//            for (int [] i : board_weight[k]) System.out.println(Arrays.toString(i));
//            System.out.println();
        }

        int answer = Integer.MAX_VALUE;
        Loop1:
        for (int [] v : virus_combination) {
            int [] [] temp_answer_board = new int [N] [N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    temp_answer_board[i][j] = Integer.MAX_VALUE;
                    for (int k : v) {
                        temp_answer_board[i][j] = Math.min(temp_answer_board[i][j], board_weight[k][i][j]);
                    }
                    if (temp_answer_board[i][j] == Integer.MAX_VALUE) continue;
                    if (board[i][j] == 2) temp_answer_board[i][j] = Integer.MAX_VALUE;
                }
            }
            for (int vv : v) {
                temp_answer_board[virus.get(vv).r][virus.get(vv).c] = 0;
            }

            int temp_answer = -1;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (temp_answer_board[i][j] == Integer.MAX_VALUE && board[i][j] == 0) continue Loop1;
                    if (temp_answer_board[i][j] == Integer.MAX_VALUE) continue;
                    temp_answer = Math.max(temp_answer, temp_answer_board[i][j]);
                }
            }

            answer = Math.min(answer, temp_answer);

//            for (int i = 0; i < N; i++) {
//                for (int j = 0; j < N; j++) {
//                    System.out.print((temp_answer_board[i][j] == Integer.MAX_VALUE) ? "- " : (temp_answer_board[i][j] + " "));
//                }
//                System.out.println();
//            }
//            System.out.println(temp_answer);
//            System.out.println();

        }
        System.out.println((answer == Integer.MAX_VALUE) ? -1 : answer);
    }
    static void dfsing(int start, int total_virus, int level, int M, int [] temp, ArrayList<int []> virus_combination) {
        if (level >= M) {
            int [] t = new int [M];
            for (int i = 0; i < M; i++) {
                t[i] = temp[i];
            }
            virus_combination.add(t);
            return;
        }
        for (int i = start; i < total_virus; i++) {
            temp[level] = i;
            dfsing(i+1, total_virus, level+1, M, temp, virus_combination);
        }
    }
}
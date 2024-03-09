import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

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
        st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int [] [] delta = {{0,1},{1,0},{0,-1},{-1,0}};
        boolean [] [] [] board = new boolean [N] [N] [4];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int r1 = Integer.parseInt(st.nextToken()) - 1;
            int c1 = Integer.parseInt(st.nextToken()) - 1;
            int r2 = Integer.parseInt(st.nextToken()) - 1;
            int c2 = Integer.parseInt(st.nextToken()) - 1;
            if (c1 + 1 == c2) {
                board[r1][c1][0] = true;
                board[r2][c2][2] = true;
            } else if (r1 + 1 == r2) {
                board[r1][c1][1] = true;
                board[r2][c2][3] = true;
            } else if (c1 - 1 == c2) {
                board[r1][c1][2] = true;
                board[r2][c2][0] = true;
            } else if (r1 - 1 == r2) {
                board[r1][c1][3] = true;
                board[r2][c2][1] = true;
            }
        }
        Node [] cows = new Node [K];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            cows[i] = new Node(a, b);
        }
        int answer = 0;
        for (Node cow : cows) {
            boolean [] [] visit = new boolean [N] [N];
            visit[cow.r][cow.c] = true;
            ArrayDeque<Node> q = new ArrayDeque<>();
            q.offer(cow);
            while (!q.isEmpty()) {
                Node node = q.pollFirst();
                for (int i = 0; i < 4; i++) {
                    if (board[node.r][node.c][i]) continue;
                    int next_r = node.r + delta[i][0];
                    int next_c = node.c + delta[i][1];
                    if (next_r < 0 || next_r >= N || next_c < 0 || next_c >= N) continue;
                    if (visit[next_r][next_c]) continue;
                    visit[next_r][next_c] = true;
                    q.offerLast(new Node(next_r, next_c));
                }
            }
            for (Node c : cows) {
                if (!visit[c.r][c.c]) answer++;
            }
        }
        System.out.println(answer / 2);
    }
}
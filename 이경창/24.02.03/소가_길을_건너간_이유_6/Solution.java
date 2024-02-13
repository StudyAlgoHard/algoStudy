package 소가_길을_건너간_이유_6;

import java.util.*;
import java.io.*;

public class Solution {
    public static class Node {
        public final int x;
        public final int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static int N, K, R;
    private static int[][] arr;
    private static boolean[][][] visited;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, -1, 0, 1};

    private static boolean isWithInRange(int row, int col) {
        return 0 <= row && 0 <= col && row < N && col < N;
    }

    private static boolean bfs(int row, int col) {
        System.out.println("row : " + row + " col : " + col);
        Queue<Node> queue = new LinkedList<>();
        boolean[][] visitedBfs = new boolean[N][N];
        queue.add(new Node(col, row));
        int count = 0;
        while (queue.size() > 0) {
            Node node = queue.poll();

            if ((node.x != col && node.y != row) && arr[node.y][node.x] == 1) return false;

            count++;
            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + node.x;
                int ny = dy[i] + node.y;

                if(visited[node.y][node.x][i]) continue;
                if (!isWithInRange(ny, nx)) continue;
                if (visitedBfs[ny][nx]) continue;
                if (arr[ny][nx] == 1) continue;
                visitedBfs[ny][nx] = true;
                System.out.println(ny + " " + nx + " " + i);
                queue.add(new Node(nx, ny));
            }
        }

        System.out.println("결과 : " + row  + " " + col + " " + count);
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        N = Integer.parseInt(tokenizer.nextToken());
        K = Integer.parseInt(tokenizer.nextToken());
        R = Integer.parseInt(tokenizer.nextToken());

        arr = new int[N][N];
        visited = new boolean[N][N][4];

        for (int i = 0; i < R; i++) {
            tokenizer = new StringTokenizer(reader.readLine());

            int y1 = Integer.parseInt(tokenizer.nextToken()) - 1;
            int x1 = Integer.parseInt(tokenizer.nextToken()) - 1;
            int y2 = Integer.parseInt(tokenizer.nextToken()) - 1;
            int x2 = Integer.parseInt(tokenizer.nextToken()) - 1;


            int curIdx = 0;
            for (int k = 0; k < 4; k++) {
                if (y2 - y1 == dy[k] && x2 - x1 == dx[k]) {
                    curIdx = k;
                    break;
                }
            }

            System.out.println("curIdx : " + curIdx);
            visited[y1][x1][curIdx] = true;
            visited[y2][x2][(curIdx + 2) % 4] = true;
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                System.out.print(Arrays.toString(visited[i][j]) + " ");
            }
            System.out.println();
        }

        for (int i = 0; i < K; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int y = Integer.parseInt(tokenizer.nextToken()) - 1;
            int x = Integer.parseInt(tokenizer.nextToken()) - 1;
            arr[y][x] = 1;
        }

        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] == 0) continue;

                result += (bfs(i, j) == true ? 1 : 0);
            }
        }

        System.out.println(result);

        reader.close();
    }
}

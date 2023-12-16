import java.util.*;
import java.io.*;

public class Main{
    static int N, M, min, zeroCount;
    static int[][] map, map_copy;
    static int[] dc = { -1, 0, 1, 0 }, dr = { 0, 1, 0, -1 };
    static List<Point> virus;
    static Point[] output;

    public static void main(String[] args) throws Exception {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        map_copy = new int[N][N];
        output = new Point[M];
        min = Integer.MAX_VALUE;
        virus = new ArrayList<>();
        zeroCount = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 2) {
                    virus.add(new Point(i, j));
                    map[i][j] = -2;
                } else if (map[i][j] == 1) {
                    map[i][j] = -1;
                } else {
                    zeroCount++;
                }
            }
        }
        
        combi(0, 0, virus.size(), M);
        
        if (min == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(min);
        }
    }

    static void combi(int start, int depth, int n, int r) {
        if (depth == r) {
            bfs();
            return;
        }
        for (int i = start; i < n; i++) {
            output[depth] = virus.get(i);
            combi(i + 1, depth + 1, n, r);
        }
    }

    static void bfs() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map_copy[i][j] = map[i][j];
            }
        }

        Queue<Point> q = new LinkedList<>();	
        boolean[][] visited = new boolean[N][N];

       
        for (int i = 0; i < M; i++) {
            q.offer(output[i]);
            map_copy[output[i].x][output[i].y] = 0;
            visited[output[i].x][output[i].y] = true;
        }

        int count = 0;
        while (!q.isEmpty()) {
            Point cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dc[i];
                int ny = cur.y + dr[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                    continue;
                }
                if (visited[nx][ny]) {
                    continue;
                }
                if (map_copy[nx][ny] == -1) {
                    continue;
                }
                if (map_copy[nx][ny] != -2) {
                    count++;
                }
                map_copy[nx][ny] = map_copy[cur.x][cur.y] + 1;
                visited[nx][ny] = true;
                q.offer(new Point(nx, ny));
            }
        }
        
        if (count != zeroCount) {
            return;
        }
        
        for (int i = 0; i < virus.size(); i++) {
            map_copy[virus.get(i).x][virus.get(i).y] = 0;
        }
        
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (max < map_copy[i][j]) {
                    max = map_copy[i][j];
                }
            }
        }
        
        if (max < min) {
            min = max;
        }
    }
    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}


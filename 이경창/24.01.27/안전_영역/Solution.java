package 안전_영역;

import java.util.*;
import java.io.*;

public class Solution {

    private static class Node{
        public final int x;
        public final int y;

        Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    private static int N;
    private static int[][] arr;
    private static int[] dx = { -1, 0, 1, 0};
    private static int[] dy = { 0, -1, 0, 1};
    private static HashSet<Integer> set;
    private static boolean[][] visited;

    private static boolean isWithInRange(int row, int col){
        return 0 <= row && 0 <= col && row < N && col < N;
    }
    private static void bfs(int row, int col, int number){
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(col, row));

        while(queue.size() > 0){
            Node node = queue.poll();

            for(int i = 0; i < 4; i++){
                int nx = dx[i] + node.x;
                int ny = dy[i] + node.y;

                if(!isWithInRange(ny, nx)) continue;
                if(visited[ny][nx]) continue;
                if(number >= arr[ny][nx]) continue;
                visited[ny][nx] = true;
                queue.add(new Node(nx, ny));

            }
        }

    }
    private static int gameStation(){

        int result = 1;
        visited = new boolean[N][N];
        for(int number : set){

            for(int i = 0; i < N; i++) Arrays.fill(visited[i], false);

            int count = 0;
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    if(arr[i][j] > number && !visited[i][j]){
                        bfs(i, j, number);
                        count++;
                    }
                }
            }
            result = Math.max(result, count);
        }

        return result;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(reader.readLine());

        arr = new int[N][N];
        set = new HashSet<>();

        for(int i = 0; i < N; i++){
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for(int j = 0; j < N; j++){
                arr[i][j] = Integer.parseInt(tokenizer.nextToken());
                set.add(arr[i][j]);
            }
        }

        System.out.println(gameStation());


        reader.close();

    }
}

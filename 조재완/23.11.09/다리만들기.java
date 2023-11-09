package Untitle;

import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[][] Map;
    static boolean[][] visited;
    static Queue<int[]> q;
    static int[] dc = {-1,0,1,0};
    static int[] dr = {0,-1,0,1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        Map = new int[N][N];
        visited = new boolean[N][N];
        q = new LinkedList<>();


        StringTokenizer st;
        for(int i = 0; i< N; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j< N; j++) {
                Map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        isLand();

        int min = Integer.MAX_VALUE;

        for(int i = 0; i< N; i++) {
            for(int j = 0; j< N; j++) {
                if(Map[i][j] > 0) {
                    visited = new boolean[N][N];
                    int temp = bridge(i,j);
                    if(temp == -1)continue;
                    if(min > temp) {
                        min = temp;
                    }
                }
            }
        }
        System.out.println(min-1);
    }

    static int bridge(int r, int c) {
        q = new LinkedList<>();

        int num = Map[r][c];
        visited[r][c] =true;
        q.add(new int[]{r,c,0});

        while(!q.isEmpty()) {
            int[] pos = q.poll();
            int pr = pos[0];
            int pc = pos[1];
            int bridge = pos[2];

            if(Map[pr][pc] != 0 && Map[pr][pc] != num) {
                return bridge;
            }

            for(int i=0; i<4; i++) {
                int nr = pr + dr[i];
                int nc = pc + dc[i];

                if(nr<0 || nr> N -1 || nc <0 || nc> N -1) continue;
                if(visited[nr][nc] || Map[nr][nc] == num) continue;

                visited[nr][nc] = true;
                q.add(new int[] {nr,nc, bridge+1});
            }

        }
        return -1;

    }

    static void isLand() {

        int idx = 2;

        for(int i = 0; i< N; i++) {
            for(int j = 0; j< N; j++) {
                if(!visited[i][j] && Map[i][j] != 0) {
                    Map[i][j] = idx;
                    visited[i][j] =true;
                    q.add(new int[] {i,j});

                    while(!q.isEmpty()) {
                        int[] pos = q.poll();
                        int pr = pos[0];
                        int pc = pos[1];

                        for(int d=0; d<4; d++) {
                            int nr = pr + dr[d];
                            int nc = pc + dc[d];

                            if(nr<0 || nr> N -1 || nc <0 || nc> N -1) continue;
                            if(visited[nr][nc]) continue;

                            if(Map[nr][nc] == 1) {
                                visited[nr][nc] = true;
                                Map[nr][nc] = idx;
                                q.add(new int[] {nr,nc});
                            }
                        }
                    }
                    idx++;
                }
            }

        }

    }
}

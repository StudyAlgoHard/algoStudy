import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static int N, M, answer, maxHeight, arr[][];
    static boolean check;
    static Queue<int[]> q;
    static boolean vtd[][];
    static int dx[] = {-1, 0, 1, 0};
    static int dy[] = {0, -1, 0, 1};


    public static int solve(int value) {
        int size = 1;
        while(!q.isEmpty()) {
            int tmp[] = q.poll();
            int x = tmp[0];
            int y = tmp[1];
            
            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if(nx < 0 || ny < 0 || nx > N-1 || ny > M-1) {// 수영장이 될수 없는 곳 테두리랑 연결된 곳
                    check = true;  // 참으로 바꿔주고
                    continue;
                }
                else if(!vtd[nx][ny] && arr[nx][ny] < value){
                    vtd[nx][ny] = true;
                    q.add(new int[] {nx,ny});
                    size++;
                }
            }
        }
        if(check) size = 0; //수영장 될 수 없으니까 0
        return size;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
 
        answer = 0;
        maxHeight = -987654321;
        arr = new int[N][M];
        vtd = new boolean[N][M];
        q = new LinkedList<>();
        
        for(int i = 0; i < N; i++) {
            String str = bf.readLine();
            for(int j = 0; j < M; j++) {
                arr[i][j] = str.charAt(j) -'0';
                if(maxHeight < arr[i][j]) maxHeight = arr[i][j];
            }
        }
 
        for(int p = 2; p <= maxHeight; p++) {
            vtd = new boolean[N][M];
            for(int i = 1; i < N-1; i++) {
                for(int j = 1; j < M-1; j++) {
                    check = false;
                    if(arr[i][j] < p && !vtd[i][j]) { //p 보다 낮은 높이이면서 방문안한 칸
                        vtd[i][j] = true;
                        q.add(new int[] {i, j});
                        answer += solve(p);
                    }
                }
            }
        }
        
        System.out.println(answer);
    }

}

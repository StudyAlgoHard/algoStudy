import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N,Total;
    static int [][] Map;
    public static void main(String[] args)throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(bf.readLine());
        Map = new int [N+1][N+1];

        StringTokenizer st = null;
        Total = 0;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 1; j <= N; j++) {
                Map[i][j] = Integer.parseInt(st.nextToken());
                Total += Map[i][j];
            }
        }

        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                int temp = solve(i,j);

                min = Math.min(min,temp);
            }
        }
        System.out.println(min);
    }
    public static int solve(int R, int C){
        int min = Integer.MAX_VALUE;
        boolean bound [][] = new boolean[N+1][N+1];
        for(int d1 = 1; d1 <= N; d1++){
            for(int d2 = 1; d2 <= N; d2++){
                if(R+d1+d2 > N || C-d1 < 1 || C+d2 > N){
                    continue;
                }

                int state [] = new int [5];
                bound = new boolean[N+1][N+1];

                for(int i = 0; i <= d1; i++){
                    bound[R+i][C-i]= true;
                    bound[R+d2+i][C+d2-i] = true;
                }
                
                for(int i = 0; i <= d2; i++){
                    bound[R+i][C+i] = true;
                    bound[R+d1+i][C-d1+i] = true;
                }
                
                // for 문을 가로로 돌던 세로로 돌던 결과가 같은지 생각해보자
                for (int i = 1; i < R+d1 ; i++) {
                    for(int j = 1; j <=C; j++){
                        if(bound[i][j]){break;}
                        state[0] += Map[i][j];
                    }
                }

                for (int i = 1; i <= R+d2 ; i++) {
                    for(int j = N; j > C; j--){
                        if(bound[i][j]){break;}
                        state[1] += Map[i][j];
                    }
                }

                for (int i = R+d1; i <= N ; i++) {
                    for(int j = 1; j <C-d1+d2; j++){
                        if(bound[i][j]){break;}
                        state[2] += Map[i][j];
                    }
                }

                for (int i = R+d2+1; i <= N ; i++) {
                    for(int j = N; j >= C-d1+d2; j--){
                        if(bound[i][j]){break;}
                        state[3] += Map[i][j];
                    }
                }

                state[4] = Total;

                for(int i = 0; i < 4; i++){
                    state[4] -= state[i];
                }

                Arrays.sort(state);

                min = Math.min(min,state[4]-state[0]);
            }
        }



        return min;
    }
}

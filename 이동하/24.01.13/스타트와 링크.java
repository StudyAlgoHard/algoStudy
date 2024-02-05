import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int [] [] data = new int [N] [N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                data[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int [] team = new int [N/2];
        boolean [] visit = new boolean[N];
        answer = Integer.MAX_VALUE;
        dfsing(0, 0, N, team, data, visit);
        System.out.println(answer);
    }
    static void dfsing(int start, int count, int N, int [] team, int [] [] data, boolean [] visit) {
        if (count >= N/2) {
            int team_point = 0;
            for (int i = 0; i < N/2; i++) {
                for (int j = i+1; j < N/2; j++) {
                    team_point += data[team[i]][team[j]];
                    team_point += data[team[j]][team[i]];
                }
            }
            ArrayList<Integer> away_team = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                if (visit[i]) continue;
                away_team.add(i);
            }
            int away_team_point = 0;
            for (int i = 0; i < N/2; i++) {
                for (int j = i+1; j < N/2; j++) {
                    int away_i = away_team.get(i);
                    int away_j = away_team.get(j);
                    away_team_point += data[away_i][away_j];
                    away_team_point += data[away_j][away_i];
                }
            }
            answer = Math.min(answer, Math.abs(team_point - away_team_point));
            return;
        }
        for (int i = start; i < N; i++) {
            team[count] = i;
            visit[i] = true;
            dfsing(i+1, count+1, N, team, data, visit);
            visit[i] = false;
        }
    }
}
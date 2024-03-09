import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int [] data = new int [N];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            data[i] = Integer.parseInt(st.nextToken());
        }

        boolean [] visit = new boolean[N];
        answer = 0;
        dfsing(500, 0, N, K, data, visit);
        System.out.println(answer);
    }
    static void dfsing(int weight, int count, int N, int K, int [] data, boolean [] visit) {
        if (count >= N) {
            answer++;
            return;
        }
        for (int i = 0; i < N; i++) {
            if (visit[i]) continue;
            if (weight + data[i] - K < 500) continue;
            visit[i] = true;
            dfsing(weight + data[i] - K, count+1, N, K, data, visit);
            visit[i] = false;
        }
    }
}
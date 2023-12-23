import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int [] inbound = new int [N+1];
        ArrayList<Integer> [] outbound = new ArrayList [N+1];
        for (int i = 1; i <= N; i++) {
            outbound[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            inbound[b]++;
            outbound[a].add(b);
        }

        boolean [] visit = new boolean [N+1];
        int [] answer = new int [N];
        for (int i = 0; i < N; i++) {
            for (int j = 1; j <= N; j++) {
                if (visit[j]) continue;
                if (inbound[j] == 0) {
                    answer[i] = j;
                    visit[j] = true;
                    for (int k : outbound[j]) {
                        inbound[k]--;
                    }
                    break;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i : answer) {
            sb.append(i + " ");
        }
        System.out.print(sb);
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        long [] factorial = new long [21];
        factorial[0] = 1;
        for (int i = 1; i <= 20; i++) {
            factorial[i] = factorial[i-1] * i;
        }

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");
        int type = Integer.parseInt(st.nextToken());
        if (type == 1) {
            long query = Long.parseLong(st.nextToken()) - 1;
            boolean [] used = new boolean [N+1];
            int [] answer = new int [N];
            for (int i = 1; i <= N; i++) {
                long d = factorial[N-i];

//                    System.out.println(query + " " + factorial[N-i]);
                long order = query / factorial[N-i] + 1;
//                    System.out.println(order);
                long temp_count = 0;
                for (int j = 1; j <= N; j++) {
                    if (used[j] == true) continue;
                    temp_count++;
                    if (temp_count == order) {
                        used[j] = true;
//                        System.out.println(j);
                        sb.append(j).append(" ");
                        break;
                    }
                }
                query -= query / factorial[N-i] * factorial[N-i];
            }
        }
        else if (type == 2) {
            int [] query = new int [N];
            for (int i = 0; i < N; i++) {
                query[i] = Integer.parseInt(st.nextToken());
            }
            long order = 1;
            boolean [] used = new boolean [N+1];
            for (int i = 0; i < N; i++) {
                long d = factorial[N - i - 1];
                int temp_count = 0;
                for (int j = 1; j <= N; j++) {
                    if (used[j]) continue;
                    temp_count++;
                    if (j == query[i]) {
                        used[j] = true;
                        break;
                    }
                }
//                System.out.println(temp_count);
                order += (temp_count-1) * d;
            }
//            System.out.println(order);
            sb.append(order);
        }
        System.out.println(sb);
    }
}
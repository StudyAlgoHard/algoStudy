import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        char [] data = br.readLine().toCharArray();
        boolean [] eat = new boolean [N];
        int answer = 0;

        for (int i = 0; i < N; i++) {
            if (data[i] == 'H') continue;
            for (int j = i-K; j<=i+K; j++) {
                if (j < 0 || j >= N) continue;
                if (data[j] == 'P') continue;
                if (eat[j]) continue;
                eat[j] = true;
                answer++;
                break;
            }
        }
        System.out.println(answer);
    }
}

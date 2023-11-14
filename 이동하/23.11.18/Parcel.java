import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine(), " ");
        int W = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int [] data = new int [N+1];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) data[i] = Integer.parseInt(st.nextToken());
        boolean [] check = new boolean [400_001];
        int [] sum1 = new int [400_001];
        int [] sum2 = new int [400_001];
        for (int i = 1; i < N; i++) {
            for (int j = i+1; j <= N; j++) {
                int w = data[i] + data[j];
                if (check[w]) continue;
                check[w] = true;
                sum1[w] = i;
                sum2[w] = j;
            }
        }
//        System.out.println(Arrays.toString(sum1));
//        System.out.println(Arrays.toString(sum2));

        for (int i = 1; i < N; i++) {
            for (int j = i+1; j <= N; j++) {
                int w = W - (data[i] + data[j]);
//                System.out.println("*" + w + " " + i + " " + j);
                if (w < 0) continue;
                if (w > 400_000) continue;
                if (!check[w]) continue;
                if (sum1[w] == i || sum1[w] == j || sum2[w] == i || sum2[w] == j) continue;
                System.out.println("YES");
                return;
            }
        }
        System.out.println("NO");
    }
}

/*
10 6
5 10 7 3 2 1

7(5+2) + 3(2+1)
  1 5      5 6
 */
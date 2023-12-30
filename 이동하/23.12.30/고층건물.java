import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Pair {
    int a, b;
    Pair(int a, int b) {
        this.a = a;
        this.b = b;
    }
}
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] data = new int[N];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            data[i] = Integer.parseInt(st.nextToken());
        }

        Pair [][] tilt = new Pair[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                tilt[i][j] = new Pair(data[j] - data[i], j - i);
                tilt[j][i] = new Pair(data[i] - data[j], -i + j);
            }
        }
//        for (Pair [] i : tilt) {
//            for (Pair j : i) {
//                if (j == null) {
//                    System.out.print("    ");
//                    continue;
//                }
//                System.out.print("(" + j.a + "," + j.b + ")");
//            }
//            System.out.println();
//        }

        int [] answer = new int [N];
        for (int i = 0; i < N; i++) {
            Pair d = new Pair(-2_000_000_001, 0);
            for (int j = i+1; j < N; j++) {
                if ((long)d.a*tilt[i][j].b < (long)d.b*tilt[i][j].a) {
                    answer[i]++;
                    d = tilt[i][j];
                }
            }
            d = new Pair(-2_000_000_001, 0);
            for (int j = i-1; j >= 0; j--) {
                if ((long)d.a*tilt[i][j].b < (long)d.b*tilt[i][j].a) {
                    answer[i]++;
                    d = tilt[i][j];
                }
            }
        }
//        System.out.println(Arrays.toString(answer));
        int r = Integer.MIN_VALUE;
        for (int i : answer) {
            r = Math.max(r, i);
        }
        System.out.println(r);
    }
}
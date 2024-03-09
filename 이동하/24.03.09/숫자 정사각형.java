import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int answer = 1;
        st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int [] [] data = new int [N] [M];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                data[i][j] = s.charAt(j) - '0';
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int d = answer;
                while(true) {
//                    System.out.println(d);
                    if (j+d >= M) break;
                    if (i+d >= N) break;
                    int p1 = data[i][j];
                    int p2 = data[i][j+d];
                    int p3 = data[i+d][j];
                    int p4 = data[i+d][j+d];
                    d++;
                    if (p1 == p2 && p2 == p3 && p3 == p4) {
                        answer = d;
//                        System.out.println(i + " " + j + " " + (d));
                    }
                    else continue;
                }
            }
        }
        System.out.println(answer * answer);
    }
}

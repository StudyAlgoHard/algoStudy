import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        long N = Long.parseLong(br.readLine());
        int K = Integer.parseInt(br.readLine());

        long left = 0;
        long right = N * N;
        while (left + 1 < right) {
//            System.out.print(left + " " + right + " ");
            long mid = (left + right) / 2;
            long count = 0;
            for (int i = 1; i <= N; i++) {
                count += Math.min(N, mid / i);
//                System.out.print("--" + count);
            }
//            System.out.println(mid + " " + count);
            if (count >= K) right = mid;
            else left = mid;
        }
//        System.out.println(left + " " + right);
        System.out.println(right);
    }
}

/*
  1  2  3  4  5
  2  4  6  8 10
  3  6  9 12 15
  4  8 12 16 20
  5 10 15 20 25
10 6 4 3 2 1 1 1 1 1 = 25+5
10,000,000,000
 */
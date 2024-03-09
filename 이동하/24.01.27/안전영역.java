import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int pack = Integer.MAX_VALUE;
        int unit = Integer.MAX_VALUE;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            pack = Math.min(pack, Integer.parseInt(st.nextToken()));
            unit = Math.min(unit, Integer.parseInt(st.nextToken()));
        }

        if (pack > unit * 6) {
            System.out.println(unit * N);
        } else {
            System.out.println(Math.min(pack * (N / 6) + unit * (N % 6), pack * ((N+5) / 6)));
        }
    }
}
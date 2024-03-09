// 4번

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
        int W = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        ArrayDeque<Integer> truck = new ArrayDeque<>();
        for (int i = 0; i < N; i++) truck.offerLast(Integer.parseInt(st.nextToken()));

        int current_weight = 0;
        ArrayDeque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < W; i++) {
            q.offerLast(0);
        }
        int answer = 0;
        while (!truck.isEmpty()) {
            current_weight -= q.pollFirst();

            int i = truck.peekFirst();
            if (current_weight + i <= L) {
                q.offerLast(truck.pollFirst());
                current_weight += i;
            } else {
                q.offerLast(0);
            }

            answer++;
        }
        System.out.println(answer + W);
    }
}
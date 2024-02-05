// 4번

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        int K = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int testcase = 1; testcase <= K; testcase++) {
            String answer = solution() ? "YES" : "NO";
            sb.append(answer).append("\n");
        }
        System.out.print(sb);
    }
    static boolean solution() throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (graph.get(a) == null) graph.put(a, new ArrayList<>());
            if (graph.get(b) == null) graph.put(b, new ArrayList<>());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        int start = 1;
        int [] team = new int [N+1];
        for (int k = start; k <= N; k++) {
            if (team[k] != 0) continue;
            if (graph.get(k) == null) continue;
            ArrayDeque<Integer> q = new ArrayDeque<>();
            q.offerLast(k);
            team[k] = 1;
            while (!q.isEmpty()) {
                int poll_num = q.pollFirst();
                for (int i : graph.get(poll_num)) {
                    if (team[i] != 0) {
                        if (team[i] == team[poll_num]) return false;
                        else continue;
                    }
                    team[i] = -team[poll_num];
                    q.offerLast(i);
                }
            }
//            System.out.println(Arrays.toString(team));
        }
        return true;
    }
}
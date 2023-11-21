import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Vertex {
    int target, weight;
    Vertex (int target, int weight) {
        this.target = target;
        this.weight = weight;
    }
}
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        ArrayList<Vertex> [] graph = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new Vertex(b, c));
            graph[b].add(new Vertex(a, c));
        }
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        PriorityQueue<Vertex> pq = new PriorityQueue<>((o1, o2) -> o2.weight - o1.weight);
        int [] weight_limit = new int [N+1];
        weight_limit[1] = 1_000_000_001;

        for (Vertex i : graph[start]) {
            int temp_new = i.weight;
            if (weight_limit[i.target] < temp_new) {
                weight_limit[i.target] = temp_new;
                pq.offer(new Vertex(i.target, temp_new));
            }
        }

        while (!pq.isEmpty()) {
            Vertex v = pq.poll();

            // 이미 판단했는 값이 더 크다면 더 볼것도 없지
            if (weight_limit[v.target] > v.weight) continue;

            for (Vertex i : graph[v.target]) {
                int temp_new = Math.min(weight_limit[v.target], i.weight);
                if (weight_limit[i.target] >= temp_new) continue;

                weight_limit[i.target] = temp_new;
                pq.offer(new Vertex(i.target, temp_new));

            }

//            System.out.println(Arrays.toString(weight_limit));
        }
        System.out.println(weight_limit[end]);
    }
}

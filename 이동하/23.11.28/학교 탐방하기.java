import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Pair {
    int start;
    int end;
    Pair(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int [] [] graph = new int [N+1] [N+1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                graph[i][j] = -1;
            }
        }
        for (int i = 0; i <= M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a][b] = c;
            graph[b][a] = c;
        }
        PriorityQueue<Pair> [] pq = new PriorityQueue[2];
        pq[0] = new PriorityQueue<>((o1, o2) -> graph[o1.start][o1.end] - graph[o2.start][o2.end]);
        pq[1] = new PriorityQueue<>((o1, o2) -> graph[o2.start][o2.end] - graph[o1.start][o1.end]);

        int [] route_point = new int [2];
        for (int i = 0; i <= 1; i++) {
            boolean [] visit = new boolean[N+1];
            visit[0] = true;
            int point = 0;
            pq[i].offer(new Pair(0,1));
            while (!pq[i].isEmpty()) {
                Pair p = pq[i].poll();
                if (visit[p.end]) continue;
//                System.out.println(p.start + " " + p.end + " " + graph[p.start][p.end]);
                visit[p.end] = true;
                if (graph[p.start][p.end] == 0) point++;
//                System.out.println(graph[p.start][p.end]);
//                System.out.println("point:" + point);
                for (int j = 1; j <= N; j++) {
                    if (graph[p.end][j] == -1) continue;
                    if (visit[j]) continue;
                    pq[i].offer(new Pair(p.end, j));
                }
//                for (Pair pair : pq[i]) System.out.println("(" + pair.start + ":" + pair.end + ") ");
            }
//            System.out.println(point);
            route_point[i] = point * point;
        }
        System.out.println(route_point[0] - route_point[1]);
    }
}

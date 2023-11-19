import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Vertex {
    int node;
    int weight;
    Vertex (int node, int weight) {
        this.node = node;
        this.weight = weight;
    }
}

public class Main {
    static HashMap<Integer, ArrayList<Vertex>> graph;
    static boolean [] visit;
    static int far_node;
    static int far_node_weight;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int V = Integer.parseInt(br.readLine());
        graph = new HashMap<>();
        visit = new boolean [V+1];
        for (int i = 0; i < V; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int n = Integer.parseInt(st.nextToken());
            graph.put(n, new ArrayList<>());
            while (true) {
                int node = Integer.parseInt(st.nextToken());
                if (node == -1) break;
                int cost = Integer.parseInt(st.nextToken());
                graph.get(n).add(new Vertex(node, cost));
            }
        }

        visit[1] = true;
        far_node = 0;
        far_node_weight = -1;
        finding_far(1, 0);
//        System.out.println(far_node + " " + far_node_weight);

        int far = far_node;
        visit = new boolean[V+1];
        visit[far] = true;
        far_node = 0;
        far_node_weight = -1;
        finding_far(far, 0);
//        System.out.println(far_node + " " + far_node_weight);
//        System.out.println(graph);
//        for(int [] i : weight) System.out.println(Arrays.toString(i));
        System.out.println(far_node_weight);
    }
    static void finding_far(int node, int total_weight) {
//        System.out.println("*" + node);
        boolean flag = false;
        for (Vertex i : graph.get(node)) {
            if (visit[i.node] == true) continue;
            flag = true;
            visit[i.node] = true;
            finding_far(i.node, total_weight + i.weight);
        }
        if (flag == false) {
//            System.out.println(node + " " + total_weight);
            if (far_node_weight < total_weight) {
                far_node = node;
                far_node_weight = total_weight;
            }
        }
    }
}
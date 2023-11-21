import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 트리의지름 {

    static ArrayList<Node>[] list;
    static int[] visited;
    static int ans;
    static int node;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());
        list = new ArrayList[N+1];
        for(int i = 0; i < N+1; i++){
            list[i] = new ArrayList<Node>();
        }
        StringTokenizer st = null;
        for (int i = 0; i < N; i++) {

            st = new StringTokenizer(bf.readLine());
            int s = Integer.parseInt(st.nextToken());
            while(true){
                int e = Integer.parseInt(st.nextToken());
                if(e == -1){
                    break;
                }
                int cost = Integer.parseInt(st.nextToken());
                list[s].add(new Node(e,cost));
            }
        }
        visited = new int [N+1];
        dfs(1,0);

        visited = new int [N+1];
        dfs(node,0);
        System.out.println(ans);
    }


    static void dfs(int s, int length){
        if(length > ans){
            ans = length;
            node = s;
        }

        visited[s] = 1;

        for (int i = 0; i < list[s].size(); i++) {
            Node now = list[s].get(i);
            if(visited[now.e] == 0){
                dfs(now.e, now.cost+length);
            }
        }
    }
    static class Node{
        int e;
        int cost;
        public Node(int e, int cost){
            this.e = e;
            this.cost = cost;
        }
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 중량제한 {
    static ArrayList<Node>[] list;
    static int [] visited;
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        list = new ArrayList [N+1];

        for (int i = 1; i < N+1; i++) {
            list[i] = new ArrayList<>();
        }
        int l = 0;
        int r = 0;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer( bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            list[a].add(new Node(b,w));
            list[b].add(new Node(a,w));
            r = Math.max(r,w);
        }

        st = new StringTokenizer(bf.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        while(l <= r){
            int mid = (l + r)/2;
            ans = -1;
            visited = new int[N+1];
            dfs(start, end, mid);
            if(ans != -1){
                l = mid +1;
            }else{
                r = mid -1;
            }
        }
        System.out.println(r);
    }

    static void dfs(int start, int end, int lim){
        if(start == end){
            ans = start;
            return;
        }
        visited[start] = 1;
        for (Node n : list[start]) {
            if(visited[n.to] != 1 && lim <= n.w){
                dfs(n.to,end,lim);
            }
        }
    }
    static class Node{
        int to;
        int w;
        public Node(int to, int w){
            this.to = to;
            this.w = w;
        }
    }
}

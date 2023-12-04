
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        boolean[] Know = new boolean[N + 1]; // 인덱스 1부터 N까지
        ArrayList<Integer>[] parties = new ArrayList[M + 1];

        for (int i = 1; i <= M; i++) {
            parties[i] = new ArrayList<Integer>();
        }
        //System.out.println(N +" "+M);
        st = new StringTokenizer(bf.readLine());
        int konwNum = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= konwNum; i++) {
            int people_num = Integer.parseInt(st.nextToken());
            Know[people_num] = true;
        }
        //System.out.println(konwNum);
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        for (int party = 1; party <= M; party++) {
            st = new StringTokenizer(bf.readLine());
            int cap_party = Integer.parseInt(st.nextToken());
            //System.out.println(cap_party);
            if (cap_party <= 1) {
                parties[party].add(Integer.parseInt(st.nextToken()));
                continue;
            }

            int temp = Integer.parseInt(st.nextToken());
            for (int i = 1; i < cap_party; i++) {
                int a = temp;
                int b = Integer.parseInt(st.nextToken());
                if (find(a) != find(b)) {
                    union(a, b);
                }
                parties[party].add(a);
                parties[party].add(b);
                temp = b;
            }

        }

        boolean[] v = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            if (Know[i] && !v[i]) {
                int root = find(i);
                for (int j = 1; j <= N; j++) {
                    if (find(j) == root) {
                        Know[j] = true;
                        v[j] = true;
                    }
                }
            }
        }

        int ans = 0;
        for (int i = 1; i <= M; i++) {
            boolean flag = false;
            for (int people : parties[i]) {
                if (Know[people]) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                ans++;
            }
        }
        System.out.println(ans);
    }

    public static int find(int idx){
        if(parent[idx] == idx){
            return idx;
        }
        parent[idx] = find(parent[idx]);
        return parent[idx];
    }

    public static void union(int a, int b){
        int parent_b = find(b);
        parent[parent_b] = a;
    }
}

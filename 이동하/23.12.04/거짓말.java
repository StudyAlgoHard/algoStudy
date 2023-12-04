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
        int [] [] data = new int [M] [];

        st = new StringTokenizer(br.readLine(), " ");
        int know_people_count = Integer.parseInt(st.nextToken());
        int [] know_people_array = new int [know_people_count];
        for (int i = 0; i < know_people_count; i++) {
            know_people_array[i] = Integer.parseInt(st.nextToken());
        }

        boolean [] hogu = new boolean [N];
        HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();
        for (int i = 1; i <= N; i++) graph.put(i, new ArrayList<Integer>());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int party_people_count = Integer.parseInt(st.nextToken());
            int [] arr = new int [party_people_count];
            data[i] = arr;
            for (int j = 0; j < party_people_count; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }
            for (int a : arr) {
                for (int b : arr) {
                    if (a == b) continue;
                    graph.get(a).add(b);
                }
            }
        }

        boolean [] people = new boolean [N + 1];
        for (int i : know_people_array) {
            LinkedList<Integer> q = new LinkedList<>();
            people[i] = true;
            q.offer(i);
            while (!q.isEmpty()) {
                int node = q.poll();
                for (int j : graph.get(node)) {
                    if (people[j] == true) continue;
                    people[j] = true;
                    q.offer(j);
                }
            }
        }

        int answer = 0;
        Loop1:
        for (int [] i : data) {
            for(int j : i) {
                if (people[j] == true) continue Loop1;
            }
            answer++;
        }
        System.out.println(answer);
    }
}
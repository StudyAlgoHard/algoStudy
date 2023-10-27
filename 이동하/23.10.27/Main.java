import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Unit {
    int start;
    int end;
    Unit (int start, int end) {
        this.start = start;
        this.end = end;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Unit> data = new PriorityQueue<>((o1, o2) -> o1.end - o2.end);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            data.offer(new Unit(Math.min(a, b), Math.max(a, b)));
        }
        int road = Integer.parseInt(br.readLine());
        PriorityQueue<Unit> answer_q = new PriorityQueue<>((o1, o2) -> o1.start - o2.start);
        int answer = 0;
        while (!data.isEmpty()) {
            Unit temp_unit = data.poll();
            answer_q.offer(temp_unit);
            while (true) {
                if (answer_q.isEmpty()) break;
                if (answer_q.peek().start >= temp_unit.end - road) break;
                answer_q.poll();
            }
            answer = Math.max(answer, answer_q.size());
//            System.out.println(answer_q.size());
        }
        System.out.println(answer);
    }
}

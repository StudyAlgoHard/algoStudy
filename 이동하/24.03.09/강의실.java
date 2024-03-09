import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Lecture {
    int start, end;
    Lecture(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        ArrayList<Lecture> data = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            st.nextToken();
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            data.add(new Lecture(s, e));
        }
        data.sort((o1, o2) -> {
            return o1.start - o2.start;
        });

        PriorityQueue<Integer> q = new PriorityQueue<>();
        q.offer(Integer.MAX_VALUE);
        int answer = 0;

        for (Lecture l : data) {
//            System.out.print(q.peek() + " | " + l.start + " " + l.end + " : ");
            if (q.peek() <= l.start) {
                q.poll();
            } else {
                answer++;
            }
//            System.out.println(answer);
            q.offer(l.end);

//            System.out.println(q);
//            System.out.println();
        }
        System.out.println(answer);
    }
}

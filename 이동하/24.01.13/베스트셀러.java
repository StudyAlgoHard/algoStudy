import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            if (map.get(s) == null) map.put(s, 1);
            else map.put(s, map.get(s) + 1);
        }
        String answer = "";
        int answer_count = 0;
        for (String s : map.keySet()) {
            int count = map.get(s);
            if (answer_count <= count) {
                if (answer_count == count) {
                    if (answer.compareTo(s) > 0) {
                        answer = s;
                        answer_count = count;
                    }
                } else {
                    answer = s;
                    answer_count = count;
                }
            }
        }
        System.out.println(answer);
    }
}
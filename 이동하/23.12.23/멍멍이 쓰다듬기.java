import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int temp = M - N;

        if (temp == 0) {
            System.out.println(0);
            return;
        } else if (temp == 1) {
            System.out.println(1);
            return;
        } else if (temp == 2) {
            System.out.println(2);
            return;
        }

//        int [] answer = new int [temp + 1];
//        answer[0] = 0;
//        answer[1] = 1;
//        answer[2] = 2;
//        for (temp = 3; temp <= 4000; temp++) {
            int d = 2;
            int value = 3;
            Loop1:
            for (int i = 3; i <= temp; i++) {
                for (int j = 0; j < d; j++) {
                    if (i + j >= temp) {
//                                            System.out.println("*"+(i+j));
                        break Loop1;
                    }
                    //                answer[i+j] = value;
                }
                value++;
                for (int j = 0; j < d; j++) {
                    if (i + d + j >= temp) {
//                                            System.out.println("*"+(i+d+j));
                        break Loop1;
                    }
                    //                answer[i+d+j] = value;
                }
                value++;
                i = i + d + d - 1;
                d++;
                //            System.out.println(i);
                //            System.out.println(Arrays.toString(answer));
            }
            //        System.out.println(Arrays.toString(answer));
            System.out.println(value);

    }
}

package 함께_블록_쌓기;


import java.util.*;
import java.io.*;

public class Solution {

    private static long X, Y;
    private static int answer;

    private static void dfs(long monkeyCm, long sum, int day){
//        System.out.println(monkeyCm + " " + sum);
        if(monkeyCm + 1 == Y){
            if(answer > day + 1){
                answer = day + 1;
//                System.out.println(builder.toString());
//                System.out.println(monkeyCm + " " + sum);
            }
            return;
        }

        if(monkeyCm >= Y) return;

        if(sum == 0) return;

        dfs(monkeyCm + sum, sum + 1, day + 1);
        dfs(monkeyCm + sum, sum, day + 1);
        dfs(monkeyCm + sum, sum - 1, day + 1);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        X = Integer.parseInt(tokenizer.nextToken());
        Y = Integer.parseInt(tokenizer.nextToken());

        answer = Integer.MAX_VALUE;

        dfs(X + 1, 2, 1);

        if(X == Y) answer = 0;
        else if(X + 1 == Y) answer = 1;

        System.out.println(answer);

        reader.close();
    }
}

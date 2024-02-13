package 토너먼트;

import java.util.*;
import java.io.*;

public class Solution {

    private static int N, start, end;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        N = Integer.parseInt(tokenizer.nextToken());
        start = Integer.parseInt(tokenizer.nextToken());
        end = Integer.parseInt(tokenizer.nextToken());

        int count = 0;

        while(start != end){
            start = start / 2 + start % 2;
            end = end / 2 + end % 2;
            count++;
        }

        System.out.println(count);

        reader.close();
    }
}

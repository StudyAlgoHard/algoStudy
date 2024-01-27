package 기타줄;

import java.io.*;
import java.util.*;

public class Solution {

    private static int N, M;
    private static int minData;
    private static ArrayList<Integer> totalPrice;

    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        totalPrice = new ArrayList<>();
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        minData = Integer.MAX_VALUE;

        for(int i = 0; i < M; i++){
            tokenizer = new StringTokenizer(reader.readLine());
            int first = Integer.parseInt(tokenizer.nextToken());
            int second = Integer.parseInt(tokenizer.nextToken());

            totalPrice.add(first);
            totalPrice.add(second * 6);
            minData = Math.min(minData, second);
        }

        Collections.sort(totalPrice);

        int answer = (N / 6) * totalPrice.get(0);

        // ex) 1개 * 3 값 > 6개 기타 값 일때
        if((N % 6) * minData > totalPrice.get(0)) answer += totalPrice.get(0);
        else answer += (N % 6) * minData;

        System.out.println(answer);

        reader.close();
    }
}

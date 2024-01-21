package 트럭;

import java.io.*;
import java.util.*;

public class Solution {

    private static int n, w, L;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        n = Integer.parseInt(tokenizer.nextToken());
        w = Integer.parseInt(tokenizer.nextToken());
        L = Integer.parseInt(tokenizer.nextToken());

        arr = new int[n];

        tokenizer = new StringTokenizer(reader.readLine());

        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(tokenizer.nextToken());
        }

        Queue<Integer> queue = new LinkedList<>(); // 트럭 갯수가 길이


        // (1) 다리 빈 자리는 0으로 추가
        for(int i = 0; i < w; i++) queue.add(0);

        int time = 0;
        int weightIdx = 0;
        int bridgeWeight = 0;

        while(weightIdx < n){
            // (1) queue에서 front를 꺼냄, 총 합에서 제거
            bridgeWeight -= queue.poll();
            int weight = arr[weightIdx];

            // (2) 현재 다리에 트럭을 넣을 수 있다면 넣기
            if(bridgeWeight + weight <= L){
                queue.add(weight);
                bridgeWeight += weight;
                weightIdx++;
            }else{
                // queue의 빈공간은 0
                queue.add(0);
            }

//            System.out.println(time + " 인덱스 : " + weightIdx + " bridgeWeight : " + bridgeWeight);
            time++;
        }

        while(bridgeWeight > 0){
            bridgeWeight -= queue.poll();
            time++;
        }

        System.out.println(time);
        reader.close();
    }
}

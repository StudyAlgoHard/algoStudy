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

        int left = 0;
        int right = 0;
        int sum = 0;
        int time = 0;
        Queue<Integer> queue = new LinkedList<>();

        while(right < n){

            /*
            (1)
            - right가 총 길이 보다 작을 때
            - right - left + 1이 다리 길이보다 작을 때
            - 현재 트럭 무게 + 다리에 있는 트럭 무게 < 최대하중

            (2)
            - 아니라면 left 증가
            * */

            if((right - left + 1) <= w && sum + arr[right] <= L){
                queue.add(right);
                sum += arr[right++];
                time++;
            }else{
                while(queue.size() > 0){
                    sum -= arr[left++];
                    queue.poll();
                    time++;
                }
//                while(sum + arr[right] > L){
//                    time++;
//                    System.out.println(arr[left] + " 가 빠지는 시간");
//                    sum -= arr[left++];
//                }


                System.out.println("left : " + left + " right : " + right);
            }
            System.out.println("시간 : " + time + " 총합 : "  + sum + " 남은 갯수 : " + (right - left));
        }

//        time += w;
        System.out.println(time);

        reader.close();
    }
}

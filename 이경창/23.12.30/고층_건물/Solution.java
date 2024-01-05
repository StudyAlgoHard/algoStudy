package 고층_건물;

import java.util.*;
import java.io.*;

public class Solution {
    private static int N;
    private static int[] arr;

    private static int checkingBuilding(int curIndex){

//        System.out.println("시작 인덱스 : " + curIndex + " 값 : " + arr[curIndex]);
        /*
            (a, b), (c, d)
            왼쪽
            |b - d|/|a - c|

            오른쪽
            -|b - d|/|a - c|
        * */

        int a = curIndex;
        int b = arr[curIndex];
        double totalCalc = 0;
        int count = 0;

//        System.out.println("왼쪽 검토");
        // 왼쪽
        for(int i = curIndex - 1; i >= 0; i--){
            int c = i;
            int d = arr[i];

            double calc = (double)(b - d)/(a - c);

            // 현재 다음번 째 노드가 이전 각도보다 기울기가 작으면 횟수 추가
            if(i == curIndex - 1 || totalCalc > calc){
//                System.out.print(arr[i] + " ");
                totalCalc = calc;
                count++;
            }
        }
//        System.out.println();

//        System.out.println("오른쪽 검토");
        // 오른쪽
        for(int i = curIndex + 1; i < N; i++){
            int c = i;
            int d = arr[i];

            double calc = (double)(b - d)/(a - c);

            // 각도
            if(i == curIndex + 1 || totalCalc < calc){
//                System.out.print(arr[i] + " ");
                totalCalc = calc;
                count++;
            }
        }
//        System.out.println();

        return count;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(reader.readLine());
        arr = new int[N];

        int result = 0;

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int inputCount = tokenizer.countTokens();
        for(int i = 0; i < inputCount; i++){
            arr[i] = Integer.parseInt(tokenizer.nextToken());
        }

        for(int i = 0; i < N; i++){
            result = Math.max(result, checkingBuilding(i));
        }

        System.out.println(result);

        reader.close();
    }
}

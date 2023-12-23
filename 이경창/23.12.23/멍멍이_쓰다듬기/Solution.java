package 멍멍이_쓰다듬기;


import java.io.*;
import java.util.*;

public class Solution {

    private static int N, M, H;
    private static ArrayList<Integer> userList[];
    private static int answer;

    private static void dfs(int index, int sum){
//        System.out.println("현재 인덱스 : " + index + " 목표 지점 : " + N);
        if(sum == H){
//            System.out.println("현재 결과 : " + sum);
//            System.out.println(builder.toString());
            answer += 1;
            return;
        }

        if(index == N || sum > H){
            // 범위를 벗어나거나 인덱스를 넘었을 때 out
//            System.out.println("실행");
            return;
        }

        for(int i = index; i < N; i++){
            for(int inUser : userList[i]){
//                System.out.println(builder.toString());
//                StringBuilder newBuilder = new StringBuilder(builder).append(" ").append(inUser);
                dfs( i + 1, sum + inUser);
            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        H = Integer.parseInt(tokenizer.nextToken());

        userList = new ArrayList[N + 1];

        for(int i = 0; i < N; i++){
            userList[i] = new ArrayList<>();
            tokenizer = new StringTokenizer(reader.readLine());
            int nextSize = tokenizer.countTokens();
            for(int j = 0; j < nextSize; j++){
                userList[i].add(Integer.parseInt(tokenizer.nextToken()));
            }
        }

//        for(int i = 0; i < N; i++){
//            System.out.println(Arrays.toString(userList[i].toArray()));
//        }


        // 탐색
        dfs(0, 0);

        System.out.println(answer % 10007);


        reader.close();
    }

}

package 햄버거_분배;

import java.io.*;
import java.util.*;



public class Solution {

    private static int N, K;
    private static char[] cArr;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        K = Integer.parseInt(tokenizer.nextToken());

        cArr = reader.readLine().toCharArray();

        int leftHCnt = 0;
        int result = 0;
        visited = new boolean[N];

        for(int i = 0; i < cArr.length; i++){
            if(cArr[i] == 'P'){
                for(int j = K; j > 0; j--){
                    if(i - j < 0) continue;
                    if(visited[i - j]) continue;
                    if(cArr[i - j] == 'P') continue;
                    visited[i - j] = true;
                    visited[i] = true;
                    result++;
                    break;
                }
            }else{
                for(int j = K; j > 0; j--){
                    if(i - j < 0) continue;
                    if(visited[i - j]) continue;
                    if(cArr[i - j] == 'H') continue;
                    visited[i - j] = true;
                    visited[i] = true;
                    result++;
                    break;
                }
            }
        }

        System.out.println(result);

        reader.close();
    }
}

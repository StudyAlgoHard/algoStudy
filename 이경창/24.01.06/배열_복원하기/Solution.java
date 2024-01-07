package 배열_복원하기;

import jdk.swing.interop.SwingInterOpUtils;

import java.util.*;
import java.io.*;

public class Solution {

    private static int H, W, X, Y;
    private static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        H = Integer.parseInt(tokenizer.nextToken());
        W = Integer.parseInt(tokenizer.nextToken());
        X = Integer.parseInt(tokenizer.nextToken());
        Y = Integer.parseInt(tokenizer.nextToken());


        arr = new int[H + X][W + Y];

        for(int i = 0; i < H + X; i++){
            tokenizer = new StringTokenizer(reader.readLine());
            for(int j =0 ; j < W + Y; j++){
                arr[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        // (1) i - x, j - y >= 0 이고 i, j 가 H와 W이내라면
        // (2) i - x < 0 또는 j - y < 0 이지만 i, j가 H와 W이내라면
        // (3) 나머지는 pass

        for(int i = 0; i < H; i++){
            for(int j = 0; j < W; j++){
                if(i - X >= 0 && j - Y >= 0){
                    // 이전의 i - x, j - y 위치 값이 업데이트되고, 그 값을 i, j에 뺄셈한다.
                    arr[i][j] -= arr[i - X][j - Y];
                }
            }
        }

        for(int i = 0; i < H; i++){
            for(int j = 0; j < W; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }

        reader.close();
    }
}

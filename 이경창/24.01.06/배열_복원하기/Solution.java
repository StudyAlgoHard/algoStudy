package 배열_복원하기;

import jdk.swing.interop.SwingInterOpUtils;

import java.util.*;
import java.io.*;

public class Solution {

    private static int H, W, X, Y;
    private static int[][] bArr;
    private static int[][] aArr;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        H = Integer.parseInt(tokenizer.nextToken());
        W = Integer.parseInt(tokenizer.nextToken());
        X = Integer.parseInt(tokenizer.nextToken());
        Y = Integer.parseInt(tokenizer.nextToken());

        aArr = new int[H][W];
        bArr = new int[H + X][W + Y];

        for(int i = 0; i < H + X; i++){
            tokenizer = new StringTokenizer(reader.readLine());
            for(int j =0 ; j < W + Y; j++){
                bArr[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        // (1) i - x, j - y >= 0 이고 i, j 가 H와 W이내라면
        // (2) i - x < 0 또는 j - y < 0 이지만 i, j가 H와 W이내라면
        // (3) 나머지는 pass

        for(int i = 0; i < H; i++){
            for(int j = 0; j < W; j++){
                if(i - X >= 0 && j - Y >= 0){
//                    System.out.println(i + " " + j);
                    aArr[i][j] = bArr[i][j] - bArr[i - X][j - Y];
                }else if(i - X < 0 || j - Y < 0){
                    aArr[i][j] = bArr[i][j];
                }
            }
        }

        for(int[] arr : aArr){
            for(int num : arr){
                System.out.print(num + " ");
            }
            System.out.println();
        }
//        System.out.println(Arrays.deepToString(aArr));

        reader.close();
    }
}

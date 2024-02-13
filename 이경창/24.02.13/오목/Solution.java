package 오목;

import java.util.*;
import java.io.*;

public class Solution {

    private static int[][] arr;
    private static int[][] dxy = {
            {1, 0}, {0, 1}, {1, 1}, {-1, 1}
    };

    private static boolean isWithInRange(int row, int col){
        return 0 <= row && 0 <= col && row < 19 && col < 19;
    }

    private static boolean checkFun(int x, int y, int index, int data){
//        System.out.println("체크구간");
        for(int i = 0; i < 4; i++){
            x = x + dxy[index][0];
            y = y + dxy[index][1];
//            System.out.println(y +"  " + x);
            if(!isWithInRange(y, x)) return false;
            if(arr[y][x] != data) return false;
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        arr = new int[19][19];

        for(int i = 0; i < 19; i++){
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for(int j = 0; j < 19; j++){
                arr[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        int result = 0;
        int row = 0;
        int col = 0;

        Loop1:
        for(int i = 0; i < 19; i++){
            for(int j = 0; j < 19; j++){
                if(arr[i][j] == 0) continue;
                // ->, <-, 오른쪽 아랫방향 대각선, 왼쪽 아랫방향 대각선
//                System.out.println(i + " " + j);
                for(int k = 0; k < 4; k++){
                    if(checkFun(j, i, k, arr[i][j])){
//                        System.out.println("체크");
                        result = arr[i][j];
                        if(k == 3){
                            row = i + 4;
                            col = j - 4;
                        }else{
                            row = i;
                            col = j;
                        }
                        break Loop1;
                    }
//                    System.out.println();
                }
            }
        }

        if(result == 0) System.out.println(0);
        else{
            System.out.println(result);
            System.out.println((row + 1) + " " + (col + 1));
        }

        reader.close();
    }
}

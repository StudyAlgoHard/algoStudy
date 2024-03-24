package 숫자_정사각형;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

    private static int N, M;
    private static int[][] arr;
    private static int[][] square;

    private static boolean isWithInRange(int row, int col){
        return 0 <= row && 0 <= col && row < N && col < M;
    }

    // 정사각형 검토
    private static int squareFunc(int row, int col){
        int size = Math.max(row, col);
        int bigLen;
        if(size == row) bigLen = N;
        else bigLen = M;
        //int diamondLen = diamond(row, col, size, bigLen);
        int squareLen = 0;

        for(int i = size + 1; i < bigLen; i++){
            int curRow = row + (i - size);
            int curCol = col + (i - size);

            if(!isWithInRange(curRow, curCol)) break;

            if(arr[row][col] == arr[curRow][col] && arr[row][col] == arr[row][curCol] && arr[row][col] == arr[curRow][curCol]) squareLen = i - size;
        }


        //return Math.max(squareLen, diamondLen);
        return squareLen;
    }

    // 마름모 검토
    private static int diamond(int row, int col, int size, int nMSize){
        int count = 0;

        for(int i = size + 1; size < nMSize; size++){
            // 왼쪽
            int topRow = row + (i - size);
            int leftCol = col - (i - size);
            int rightCol = col + (i + size);
            int bottomRow = row + (i - size) * 2;

            if(!isWithInRange(topRow, leftCol) || !isWithInRange(topRow, rightCol) || !isWithInRange(bottomRow, col)) return count;

            if(arr[row][col] == arr[topRow][leftCol] && arr[row][col] == arr[topRow][rightCol] && arr[row][col] == arr[bottomRow][col]){
                count = (i - size);
            }
        }

        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        arr = new int[N][M];
        square = new int[N][M];

        for(int i = 0; i < N; i++){
            StringBuilder builder = new StringBuilder(reader.readLine());
            char[] c = builder.toString().toCharArray();
            for(int j = 0; j < M; j++){
                arr[i][j] = c[j] - '0';
            }
        }

        int result = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                result = Math.max(result, squareFunc(i, j));
            }
        }

        result += 1;
        System.out.println(result * result);

        reader.close();
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main{
    static int bizMount[];
    static long dp[][][][][][][] = new long[6][6][11][11][11][11][11];

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        bizMount = new int[5];

        for (int i = 0; i < N; i++) {
            bizMount[i] = Integer.parseInt(bf.readLine());
        }

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                for (int k = 0; k < 11; k++) {
                    for (int x = 0; x < 11; x++) {
                        for (int y = 0; y < 11; y++) {
                            for (int z = 0; z < 11; z++) {
                                Arrays.fill(dp[i][j][k][x][y][z], -1);
                            }
                        }
                    }
                }
            }
        }

        System.out.println(iterDP(0, 0, bizMount[0], bizMount[1], bizMount[2], bizMount[3], bizMount[4]));
        //System.out.println(recurDP(0, 0, bizMount[0], bizMount[1], bizMount[2], bizMount[3], bizMount[4]));
    }

    static long iterDP(int ppr, int pr, int b1, int b2, int b3, int b4, int b5) {

        for (int i = 0; i <= b1; i++) {
            for (int j = 0; j <= b2; j++) {
                for (int k = 0; k <= b3; k++) {
                    for (int x = 0; x <= b4; x++) {
                        for (int y = 0; y <= b5; y++) {
                            for (int p = 0; p < 6; p++) {
                                for (int q = 0; q < 6; q++) {
                                    if (((p != 0 && q != 0) && p == q)) {
                                        dp[p][q][i][j][k][x][y] = 0;
                                        continue;
                                    }

                                    if (i == 0 && j == 0 && k == 0 && x == 0 && y == 0) {
                                        dp[p][q][i][j][k][x][y] = 1;
                                    } else {
                                        dp[p][q][i][j][k][x][y] = 0;
                                        if (i > 0 && p != 1) {
                                            dp[p][q][i][j][k][x][y] += dp[q][1][i - 1][j][k][x][y];
                                        }
                                        if (j > 0 && p != 2) {
                                            dp[p][q][i][j][k][x][y] += dp[q][2][i][j - 1][k][x][y];
                                        }
                                        if (k > 0 && p != 3) {
                                            dp[p][q][i][j][k][x][y] += dp[q][3][i][j][k - 1][x][y];
                                        }
                                        if (x > 0 && p != 4) {
                                            dp[p][q][i][j][k][x][y] += dp[q][4][i][j][k][x - 1][y];
                                        }
                                        if (y > 0 && p != 5) {
                                            dp[p][q][i][j][k][x][y] += dp[q][5][i][j][k][x][y - 1];
                                        }
                                    }

                                }
                            }
//                            for (int p = 0; p < 6; p++) {
//                                for (int q = 0; q < 6; q++) {
//                                    System.out.print(dp[p][q][i][j][k][x][y] + " ");
//                                }
//                                System.out.println();
//                            }
//                            System.out.println(i + " " + j + " "+ k + " "+x + " " + y);
//                            System.out.println("-------------");

                        }
                    }
                }
            }
        }
        return dp[0][0][b1][b2][b3][b4][b5];
    }// 이전까지 만들 수 있는 목걸이 수 인덱스 찾으면 끝 넘은 이후로는 이우 만들 수 있는 목걸이 수를 구한다. 그로인해 이전 값을 찾을 수 있으며

    static long recurDP(int ppr, int pr, int b1, int b2, int b3, int b4, int b5) {
        if ((b1 < 0 || b2 < 0 || b3 < 0 || b4 < 0 || b5 < 0)) return 0;
        if (b1 == 0 && b2 == 0 && b3 == 0 && b4 == 0 && b5 == 0) return 1;

        long temp = dp[ppr][pr][b1][b2][b3][b4][b5];
        if (temp != -1) {
            return temp;
        }
        temp = 0;

        if (b1 > 0 && ppr != 1 && pr != 1) {
            temp += recurDP(pr, 1, b1 - 1, b2, b3, b4, b5);
        }
        if (b2 > 0 && ppr != 2 && pr != 2) {
            temp += recurDP(pr, 2, b1, b2 - 1, b3, b4, b5);
        }
        if (b3 > 0 && ppr != 3 && pr != 3) {
            temp += recurDP(pr, 3, b1, b2, b3 - 1, b4, b5);
        }
        if (b4 > 0 && ppr != 4 && pr != 4) {
            temp += recurDP(pr, 4, b1, b2, b3, b4 - 1, b5);
        }
        if (b5 > 0 && ppr != 5 && pr != 5) {
            temp += recurDP(pr, 5, b1, b2, b3, b4, b5 - 1);
        }

        return dp[ppr][pr][b1][b2][b3][b4][b5] = temp;
    }
}

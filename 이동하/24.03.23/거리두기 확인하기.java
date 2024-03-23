class Solution {
    public int[] solution(String[][] places) {
        int [] answer = new int [5];
        Loop1:
        for (int index = 0; index < 5; index++) {
            String [] data = places[index];
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (data[i].charAt(j) != 'P') continue;
                    if(checking(i, j, data)) continue Loop1;
                }
            }
            answer[index] = 1;
        }
        return answer;
    }
    boolean checking(int r, int c, String [] data) {
        for (int i = -2; i <= 2; i++) {
            for (int j = -2; j <= 2; j++) {
                int ki = r+i;
                int kj = c+j;
                if (i == 0 && j == 0) continue;
                if (ki < 0 || ki >= 5 || kj < 0 || kj >= 5) continue;
                if (data[ki].charAt(kj) != 'P') continue;
                int d = Math.abs(i) + Math.abs(j);
                if (d == 1) return true;
                if (d > 2) continue;
                if (i == 0) {
                    int new_j = (kj + c) / 2;
                    if (data[r].charAt(new_j) != 'X') {
                        // System.out.println("!!!");
                        return true;
                    }
                } else if (j == 0) {
                    int new_i = (ki + r) / 2;
                    if (data[new_i].charAt(c) != 'X') {
                        // System.out.println("###");
                        return true;
                    }
                } else {
                    char a = data[r].charAt(kj);
                    char b = data[ki].charAt(c);
                    if (a != 'X' || b != 'X') {
                        // System.out.println("****");
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
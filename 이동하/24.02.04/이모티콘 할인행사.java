import java.util.*;

class Solution {
    int answer_newbie;
    int answer_total_amount;
    void applying(int level, int emoticons_len, int [] waribiki_list, int [] waribiki_apply, int users_len, int [] [] users, int [] emoticons) {
        if (level >= emoticons_len) {
            // System.out.println(Arrays.toString(waribiki_apply));
            int newbie = 0;
            int total_amount = 0;
            int [] buy_amount = new int [users_len];
            for (int i = 0; i < users_len; i++) {
                for (int j = 0; j < emoticons_len; j++) {
                    if (waribiki_apply[j] >= users[i][0]) {
                        buy_amount[i] += emoticons[j] * (100 - waribiki_apply[j]) / 100;
                    }
                }
                if (buy_amount[i] >= users[i][1]) {
                    newbie++;
                } else {
                    total_amount += buy_amount[i];
                }
            }
            // System.out.println(newbie + " " + total_amount);
            if (answer_newbie <= newbie) {
                if (answer_newbie < newbie) answer_total_amount = 0;
                answer_newbie = newbie;
                answer_total_amount = Math.max(answer_total_amount, total_amount);
            }
            return;
        }
        // System.out.println("왔음");
        for (int i = 0; i < 4; i++) {
            waribiki_apply[level] = waribiki_list[i];
            applying(level + 1, emoticons_len, waribiki_list, waribiki_apply, users_len, users, emoticons);
        }
    }
    public int[] solution(int[][] users, int[] emoticons) {
        answer_newbie = 0;
        answer_total_amount = 0;
        int [] waribiki_list = {10, 20, 30, 40};
        int emoticons_len = emoticons.length;
        int [] waribiki_apply = new int [emoticons_len];
        int users_len = users.length;
        int [] buy_amount = new int [users_len];
        applying(0, emoticons_len, waribiki_list, waribiki_apply, users_len, users, emoticons);
        // System.out.println(answer_newbie + "  " + answer_total_amount);
        int [] answer = {answer_newbie, answer_total_amount};
        return answer;
    }
}
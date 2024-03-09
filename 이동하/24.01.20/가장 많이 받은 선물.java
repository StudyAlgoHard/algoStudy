import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        HashMap<String, HashMap<String, Integer>> map = new HashMap<>();
        HashMap<String, Integer> answer = new HashMap<>();
        HashMap<String, Integer> gift_point = new HashMap<>();
        for (String s : friends) {
            map.put(s, new HashMap<>());
            answer.put(s, 0);
            gift_point.put(s, 0);
        }
        
        int friend_length = friends.length;
        for (int i = 0; i < friend_length; i++) {
            for (int j = 0; j < friend_length; j++) {
                if (i == j) continue;
                String a = friends[i];
                String b = friends[j];
                map.get(a).put(b, 0);
            }
        }
        
        for (String s : gifts) {
            String [] k = s.split(" ");
            String a = k[0];
            String b = k[1];
            map.get(a).put(b, map.get(a).get(b)+1);
            gift_point.put(a, gift_point.get(a) + 1);
            gift_point.put(b, gift_point.get(b) - 1);
        }
        // System.out.println(gift_point);
        // System.out.println(map);
        
        for (int i = 0; i < friend_length; i++) {
            for (int j = i+1; j < friend_length; j++) {
                String a = friends[i];
                String b = friends[j];
                
                int a_give = map.get(a).get(b);
                int b_give = map.get(b).get(a);
                if (a_give + b_give > 0 && a_give != b_give) {
                    if (a_give > b_give) {
                        answer.put(a, answer.get(a) + 1);
                    } else if (a_give < b_give) {
                        answer.put(b, answer.get(b) + 1);
                    }
                } else {
                    if (gift_point.get(a) > gift_point.get(b)) {
                        answer.put(a, answer.get(a) + 1);
                    } else if (gift_point.get(a) < gift_point.get(b)) {
                        answer.put(b, answer.get(b) + 1);
                    }
                }
            }
        }
        int real_answer = -1;
        for (int i : answer.values()) real_answer = Math.max(real_answer, i);
        // System.out.println(real_answer);
        return real_answer;
    }
}
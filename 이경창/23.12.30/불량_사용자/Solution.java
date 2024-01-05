package 불량_사용자;

import java.util.*;
import java.util.stream.Collectors;

class Solution {

    private String[] banned_id;
    private String[] user_id;
    private int count;
    private boolean[] visited;
    private Set<String> set;

    private void dfs(int bannedIdx, int[] number, StringBuilder builder){
        if(bannedIdx == banned_id.length){
            // System.out.println(Arrays.toString(number));
            int[] inputNumber = Arrays.stream(number).boxed().mapToInt(Integer::intValue).toArray();
            Arrays.sort(inputNumber);
            String s = Arrays.stream(inputNumber).mapToObj(String::valueOf).collect(Collectors.joining());
            set.add(s);
            return;
        }


        for(int i = 0; i < user_id.length; i++){
            if(visited[i]) continue;
            if(user_id[i].length() != banned_id[bannedIdx].length()) continue;

            // 현재 문자 탐색
            int idx = 0;
            char[] cUser = user_id[i].toCharArray();
            char[] cBan = banned_id[bannedIdx].toCharArray();
            boolean checkBox = true;
            for(int j = 0; j < Math.min(cUser.length, cBan.length); j++){
                char curUserData = cUser[j];
                // * 이면
                if(cBan[j] == '*') continue;

                // 두개가 다르다면 false 처리 후 out
                if(cBan[j] != curUserData){
                    checkBox = false;
                    break;
                };
            }

            // 남은 불량 사용자 인덱스에서 * 이 아니라면 불량임
            for(int j = Math.min(cUser.length, cBan.length); j < cBan.length; j++){
                if(cBan[j] != '*'){
                    checkBox = false;
                    break;
                }
            }

            if(!checkBox) continue;

            visited[i] = true;
            number[bannedIdx] = i;
            dfs(bannedIdx + 1, number, new StringBuilder(builder + " " + i));
            visited[i] = false;
        }
    }

    public int solution(String[] _user_id, String[] _banned_id) {
        user_id = _user_id;
        banned_id = _banned_id;
        visited = new boolean[user_id.length];
        set = new HashSet<>();

        dfs(0, new int[banned_id.length], new StringBuilder());
        // for(String s : set){
        //     System.out.println(s);
        // }

        return set.size();
    }
}
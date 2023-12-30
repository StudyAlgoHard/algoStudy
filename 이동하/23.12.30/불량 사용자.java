import java.util.*;

class Solution {
    String [] temp;
    boolean [] visit;
    public int solution(String[] user_id, String[] banned_id) {
        int N = user_id.length;
        int M = banned_id.length;
        temp = new String [M];
        visit = new boolean [M];
        ArrayList<String []> user_id_comb = new ArrayList<>();
        dfsing(0, 0, N, M, user_id, user_id_comb);
        for (String [] s : user_id_comb) System.out.println(Arrays.toString(s));
        ArrayList<String []> banned_id_comb = new ArrayList<>();
        dfsing2(0, M, M, banned_id, banned_id_comb);
        for (String [] s : banned_id_comb) System.out.println(Arrays.toString(s));
        
        int answer = 0;
        Loop1:
        for (String [] i : user_id_comb) {
            for (String [] j : banned_id_comb) {
                boolean flag = confirm(i, j, M);
                if (flag == true) {
                    answer++;
                    continue Loop1;
                }
            }
        }
        // System.out.println(answer);
        return answer;
    }
    void dfsing(int start, int count, int N, int M, String[] id, ArrayList list) {
        if (count >= M) {
            String [] s = new String[M];
            for (int i = 0; i < M; i++) {
                s[i] = temp[i];
            }
            list.add(s);
            return;
        }
        for (int i = start; i < N; i++) {
            temp[count] = id[i];
            dfsing(i+1, count+1, N, M, id, list);
            temp[count] = "";
        }
    }
    
    void dfsing2(int count, int N, int M, String[] id, ArrayList list) {
        if (count >= M) {
            String [] s = new String[M];
            for (int i = 0; i < M; i++) {
                s[i] = temp[i];
            }
            list.add(s);
            return;
        }
        for (int i = 0; i < N; i++) {
            if (visit[i]) continue;
            visit[i] = true;
            temp[count] = id[i];
            dfsing2(count+1, N, M, id, list);
            temp[count] = "";
            visit[i] = false;
        }
    }
    
    boolean confirm (String [] a, String [] b, int M) {
        for (int i = 0; i < M; i++) {
            int al = a[i].length();
            int bl = b[i].length();
            if (al != bl) return false;
            for (int j = 0; j < al; j++) {
                if (b[i].charAt(j) != '*' && a[i].charAt(j) != b[i].charAt(j)) return false;
            }
        }
        return true;
    }
}
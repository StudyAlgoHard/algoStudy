import java.util.*;

class Solution {
    public int[] solution(int[][] edges) {
        int [] answer = new int [4];
        
        HashMap<Integer, ArrayList<Integer>> in = new HashMap<>();
        HashMap<Integer, ArrayList<Integer>> out = new HashMap<>();
        
        int N = -1;
        
        for (int [] e : edges) {
            if (in.get(e[1]) == null) in.put(e[1], new ArrayList<Integer>());
            in.get(e[1]).add(e[0]);
            if (out.get(e[0]) == null) out.put(e[0], new ArrayList<Integer>());
            out.get(e[0]).add(e[1]);
            N = Math.max(N, e[0]);
            N = Math.max(N, e[1]);
        }
        
        int root = -1;
        
        for (int i : out.keySet()) {
            if (out.get(i).size() >= 2 && in.get(i) == null) root = i;
        }
        System.out.println(root);
        
        boolean [] visit = new boolean [N+1]; 
        for (int i : out.get(root)) {
            int p = i;
            while (true) {
                if (out.get(p) == null) {
                    answer[2]++;
                    break;
                }
                if (out.get(p).size() == 2) {
                    answer[3]++;
                    break;
                }
                int next = out.get(p).get(0);
                if (visit[next] == true) {
                    answer[1]++;
                    break;
                }
                visit[next] = true;
                p = next;
            }
        }
        
        answer[0] = root;
        return answer;
    }
}
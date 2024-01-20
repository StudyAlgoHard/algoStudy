package 가장_많이_받은_선물;

import java.util.*;

class Solution {

    private static class Node{
        public final int input; // 선물 받은 횟수
        public final int output; // 선물 준 횟수

        Node(int input, int output){
            this.input = input;
            this.output = output;
        }

        Node add(int addInput, int addOutput){
            return new Node(this.input + addInput, this.output + addOutput);
        }
    }

    private int[][] arr; // 선물 준 횟수

    public int solution(String[] friends, String[] gifts) {
        // (1) map을 활용하여 사람이름 마다 인덱스를 발급받는다.
        Map<String, Integer> userIdxMap = new HashMap<>();
        Node[] nodes = new Node[friends.length];

        for(int i = 0; i < friends.length; i++){
            nodes[i] = new Node(0, 0);
        }

        for(int i = 0; i < friends.length; i++){
            userIdxMap.put(friends[i], i);
        }

        arr = new int[friends.length][friends.length];
        // (2) [a][b], [b][a] : a -> b a는 b에게 선물을 준 횟수
        for(String gift : gifts){
            StringTokenizer tokenizer = new StringTokenizer(gift);
            String left = tokenizer.nextToken();
            String right = tokenizer.nextToken();

            int leftIdx = userIdxMap.get(left);
            int rightIdx = userIdxMap.get(right);
            nodes[leftIdx] = nodes[leftIdx].add(0, 1);
            nodes[rightIdx] = nodes[rightIdx].add(1, 0);
            arr[leftIdx][rightIdx]++;
        }

        int[] present = new int[friends.length];
        // (3) 선물 주고 받은 기록 검토
        for(int i = 0; i < friends.length; i++){
            for(int j = 0; j < friends.length; j++){
                if(i == j) continue;

                // i가 j 보다 선물 준 횟수가 많은 경우
                if(arr[i][j] > arr[j][i]){
                    present[i]++;
                }else if(arr[i][j] == arr[j][i]){
                    // i가 j가 선물 준 횟수가 같은 경우
                    // 선물 지수 = 준 선물 수 - 받은 선물 수
                    int iSize = nodes[i].output - nodes[i].input;
                    int jSize = nodes[j].output - nodes[j].input;

                    if(iSize > jSize) present[i]++;
                }
            }
        }


        return Arrays.stream(present).boxed().sorted((a, b) -> b - a).mapToInt(Integer::intValue).toArray()[0];
    }
}

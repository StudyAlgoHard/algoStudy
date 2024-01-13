package 베스트셀러;

import java.io.*;
import java.util.*;

public class Solution {

    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Integer> map = new HashMap<>();

        N = Integer.parseInt(reader.readLine());

        for(int i = 0; i < N; i++){
            String s = reader.readLine();
            map.putIfAbsent(s, 0);
            map.put(s, map.get(s) + 1);
        }

        int result = 0;
        String answer = "";

        for(Map.Entry<String, Integer> m : map.entrySet()){
            if(m.getValue() > result){
                result = m.getValue();
                answer = m.getKey();
            }else if(m.getValue() == result && answer.compareTo(m.getKey()) > 0){
                result = m.getValue();
                answer = m.getKey();
            }
        }

        System.out.println(answer);

        reader.close();
    }
}

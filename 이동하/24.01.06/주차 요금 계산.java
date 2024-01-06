import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        HashSet<Integer> car = new HashSet<>();
        boolean [] garage_in = new boolean [10000]; 
        int [] time_in = new int [10000];
        int [] time_acc = new int [10000];
        for (String s : records) {
            String [] temp = s.split(" ");
            String [] time = temp[0].split(":");
            int h = Integer.parseInt(time[0]);
            int m = Integer.parseInt(time[1]);
            int t = h*60 + m;
            int num = Integer.parseInt(temp[1]);
            car.add(num);
            String type = temp[2];
            if (type.equals("IN")) {
                time_in[num] = t;
                garage_in[num] = true;
            } else if (type.equals("OUT")) {
                time_acc[num] += t - time_in[num];
                time_in[num] = 0;
                garage_in[num] = false;
            }
        }
        int [] [] helper = new int [car.size()] [2];
        int index = 0;
        for (int i : car) {
            if (garage_in[i]) time_acc[i] += 23*60+59 - time_in[i];
            if (time_acc[i] <= fees[0]) {
                time_acc[i] = fees[1];
            } else {
                time_acc[i] = fees[1] + ((time_acc[i] - fees[0] + fees[2] - 1) / fees[2]) * fees[3];
            }
            helper[index][0] = i;
            helper[index++][1] = time_acc[i];
        }
        Arrays.sort(helper, (o1, o2) -> o1[0] - o2[0]);
        int [] answer = new int [helper.length];
        index = 0;
        for (int i = 0; i < helper.length; i++) answer[i] = helper[i][1];
        return answer;
        
    }
}
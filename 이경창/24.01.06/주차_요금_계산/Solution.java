package 주차_요금_계산;

import java.util.*;

class Solution {

    private static class CarCenter implements Comparable<CarCenter>{
        public final int carNumber;
        public final int time; // 분 처리해서 삽입
        public final int inOut;

        CarCenter(int carNumber, int time, int inOut){
            this.carNumber = carNumber;
            this.time = time;
            this.inOut = inOut;
        }

        public int compareTo(CarCenter car){
            // 차량 번호로 먼저 정렬
            if(car.carNumber != this.carNumber) return this.carNumber - car.carNumber;
            else{
                // 같다면 시각으로 정렬
                return this.time - car.time;
            }
        }
    }

    private int[] fees;
    private ArrayList<CarCenter> center = new ArrayList<>();

    private int checkOutDefaultMinute(int curTime){

        int plusCoin = (curTime - fees[0]) / fees[2];
        // 추가요금 / 단위시간이 나머지가 나왔을 경우 올림
        if(fees[0] < curTime && (curTime - fees[0]) % fees[2] > 0) plusCoin += 1;

        // 기본시간, 기본요금, 단위시간, 단위요금
        return (fees[0] >= curTime ? fees[1] : fees[1] + plusCoin * fees[3]);
    }

    // 0, 1, 2, 3
    public int[] solution(int[] _fees, String[] records) {
        fees = _fees;
        for(String record : records){
            String[] inRecord = record.split(" ");

            String[] hM = inRecord[0].split(":");

            // 차가 inOut 시간
            int time = Integer.parseInt(hM[0]) * 60 + Integer.parseInt(hM[1]);
            int carNumber = Integer.parseInt(inRecord[1]);

            // IN : 0, OUT : 1
            int inOut = (inRecord[2].equals("IN") ? 0 : 1);

            center.add(new CarCenter(carNumber, time, inOut));
        }

        Collections.sort(center);


        int userSize = 1;
        Map<Integer, Integer> mapUserIdx = new HashMap<>();
        mapUserIdx.put(center.get(0).carNumber, 0);
        // 사용자 차량번호에 해당되는 인덱스
        for(int i = 0; i < center.size() - 1; i++){
            if(center.get(i).carNumber != center.get(i + 1).carNumber){
                mapUserIdx.put(center.get(i + 1).carNumber, userSize++);
            }
        }

        // 출차된 내역이 없는 경우 23:59
        int lastTime = 23 * 60 + 59;
        int[] userTime = new int[records.length];
        int userIdx = 0;

        for(int i = 0; i < center.size(); i += 2){
            CarCenter inCenter = center.get(i);
            CarCenter outCenter = null;
            if(i + 1 < center.size()) outCenter = center.get(i + 1);

            // 마지막인경우 or inCenter 차번호와 outCenter 차번호가 다른 경우
            if(i + 1 == center.size() || inCenter.carNumber != outCenter.carNumber){
                int curTime = lastTime - inCenter.time;

                // 만약 현재 이용 시간이 기본 시간을 넘은 경우
                userTime[mapUserIdx.get(inCenter.carNumber)] += curTime;
                i -= 1;
            }else{
                int curTime = outCenter.time - inCenter.time;
                userTime[mapUserIdx.get(outCenter.carNumber)] += curTime;
            }

        }

        ArrayList<Integer> answer = new ArrayList<>();
        for(int i = 0; i < userSize; i++){
            answer.add(checkOutDefaultMinute(userTime[i]));
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}
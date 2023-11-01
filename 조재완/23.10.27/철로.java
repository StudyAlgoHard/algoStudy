import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_철로_13334 {

    public static class Road implements Comparable<Road>{
        int left;
        int right;

        public Road(int l, int r){
            this.left = l;
            this.right = r;
        }

        @Override
        public int compareTo(Road o) {
            if(this.right == o.right){
                return this.left - o.left;
            }
            return this.right - o.right;
        }
    }

    public static void main(String[] args)throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        StringTokenizer st;
        ArrayList<Road> list = new ArrayList<>();
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(bf.readLine());
            int r = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            list.add(new Road(Math.min(r,l), Math.max(r,l)));
        }
        Collections.sort(list);
        int d = Integer.parseInt(bf.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        int ans = 0;
        for(int i = 0; i < N; i++){
            Road road = list.get(i);
            if(road.right-road.left>d){
                continue;
            }

            pq.add(road.left);

            while(!pq.isEmpty()){
                if(road.right-pq.peek()<=d){
                    break;
                }
                pq.poll();
            }
            ans = Math.max(ans,pq.size());
        }
        System.out.println(ans);
    }
}

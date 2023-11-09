package Untitle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_구간합구하기_2042 {

    static long[] arr, tree;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

       arr = new long[N+1];
       for (int i = 1; i <= N; i++){
           arr[i] = Long.parseLong(bf.readLine());
       }

       tree = new long[N*4];

       makeTree(1,N,1);
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(bf.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if(a == 1){
                long temp = c-arr[b];
                arr[b] = c;
                alter(1,N,1,b,temp);
            }else{
                sb.append(sum(1,N,1,b,(int)c) + "\n");
            }

        }
        System.out.println(sb.toString());

    }
    public static long makeTree(int s, int e, int idx){
        if(s == e){
            return tree[idx] = arr[s];
        }
        int mid = (s + e)/2;

        return tree[idx] = makeTree(s,mid,idx*2) + makeTree(mid+1,e,idx *2 +1);
    }

    public static long sum(int s, int e, int idx, int l, int r){
        if (l >e || r < s){
            return 0;
        }
        if(l <= s && r >= e){
            return tree[idx];
        }

        int mid = (s + e)/2;
        return sum(s,mid,idx*2,l,r) + sum(mid+1,e,idx*2+1,l,r);
    }

    public static void alter(int s, int e, int idx, int arrIdx, long dif){
        if(arrIdx < s || arrIdx > e){
            return;
        }

        tree[idx] += dif;

        if(s == e){
            return;
        }

        int mid =(s + e)/2;
        alter(s,mid,idx*2,arrIdx,dif);
        alter(mid+1,e,idx*2+1,arrIdx,dif);
    }

}

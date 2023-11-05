import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader bf  = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        int ans[] = new int[10];

        int start = 1;
        int end = N;
        int point = 1;
        while(start <= end){
            while(end % 10 != 9 && start <= end){
                cal(end, ans, point);
                end--;
            }

            if(end < start){
                break;
            }

            while(start % 10 !=0 && start <= end){
                cal(start, ans, point);
                start++;
            }

            start /= 10;
            end /= 10;

            for(int i = 0; i < 10; i++){
                ans[i] += (end-start+1)*point;
            }
            point *= 10;
        }

        for (int i = 0; i < 10; i++) {
            System.out.print(ans[i] + " ");
        }
    }
    public static void cal(int x, int [] ans, int point){
        while(x > 0){
            ans[x%10] += point;
            x /= 10;
        }
    }
}

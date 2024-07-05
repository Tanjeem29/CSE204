import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int n,k;
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();

        int[] prices = new int[n];

        for(int i=0;i<n;i++){
            prices[i] = sc.nextInt();
        }

        sorts.msort(prices, 0, n-1);

        int f = 0;
        int j = 0;
        int fl = 0;
        long ans = 0;
        for(f = 0; fl == 0 ;f++){

            for(j = 0; j<k;j++){
                if(f * k + j >= n){
                    fl++;
                    break;
                }
                ans = ans + (long) (1 + f) * prices[f*k + j];

            }
        }
        System.out.println(ans);

    }

}

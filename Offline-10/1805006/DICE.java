import java.util.Scanner;

public class DICE {
    static int MOD = 1000000007;
    public static void main(String[] args) {
        int n, s;
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        s = sc.nextInt();

        int sum[][]= new int[n][s+1];
        int csum[] = new int[s+1];
        int d[] = new int[n];

        for(int i = 0; i < n; i++)
            d[i] = sc.nextInt();


        //Populate 1st Row, 1s for 1 to d[0], 0 otherwise.
        for(int i=1; i<=d[0] && i<=s;i++){
            sum[0][i]=1;
            csum[i] = csum[i-1] + sum[0][i];
        }


        int i,j;
        int hi =0, lo = 0, add = 0;

        for(i = 1; i < n; i++){
            for(int k=1;k<=i;k++)
                csum[k]= sum[i-1][k];
            for(j = i + 1; j <= s ; j++){
                hi = csum[j-1];
                if(j - d[i] -1 < 0)
                    lo = 0;
                else
                    lo = csum[j-d[i]-1];

                add = hi-lo;
                if(add<0) add+=MOD;

                sum[i][j] = add;
                sum[i][j] %=MOD;


                csum[j] = csum[j-1] + sum[i-1][j];
                csum[j] %= MOD;

            }
        }

//        for(i = 0; i<n;i++){
//            for(j = 0;j<=s;j++){
//                System.out.printf(sum[i][j]+ " \t");
//            }
//            System.out.println();
//        }

        System.out.println(sum[n-1][s]);
    }
}

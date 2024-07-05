import java.util.Random;
import java.util.Scanner;

class GenNum{
    static void generate(int a1[], int a2[], int n, int order){
        //int[] a1 = new int[n];
        int range = Integer.MAX_VALUE / n;
        Random rand = new Random();
        if(order == 1){
            int r = rand.nextInt(range);
            a1[0] = r;
            a2[0] = r;
            for(int i = 1; i < n; i++){
                r = rand.nextInt(range);
                a1[i]= a1[i-1] + r;
                a2[i]= a2[i-1] + r;
            }
        }
        else if(order == 2){
            int r;
            for(int i = 0; i < n; i++){
                r = rand.nextInt(Integer.MAX_VALUE);
                a1[i] = r;
                a2[i] = r;
            }
        }
        else if(order == 3){
            int r = rand.nextInt(range);
            a1[n-1] = r;
            a2[n-1] = r;
            for(int i = n-2; i >= 0; i--){
                r = rand.nextInt(range);
                a1[i]= a1[i+1] + r;
                a2[i]= a2[i+1] + r;
            }
        }
        //return a1;
    }
}

class sorts{
    static void merge(int a[],int  p, int  q, int r){
        int n1 = q-p+1;                 //p = start of 1st array (inclusive) & q = end of 1st array (inclusive)
        int n2 = r - q;                 //q+1 = start of 2st array (inclusive) & r = end of 2st array (inclusive)
        int[] L = new int[n1];
        int[] R = new int[n2];

        //System.out.println("test1");
        for(int i = 0; i < n1; i++){
            L[i] = a[p+i];
        }

        //System.out.println("test2");
        for(int i = 0; i < n2; i++){
            R[i] = a[q+i+1];
        }

        //int t;
        int idx1;
        int idx2;
        int flag = 0;
        int i1=0, i2=0;
        while(i1+i2<n1+n2){

            if(L[i1] < R[i2]){
                a[p+i1+i2] = L[i1];
                i1++;
                if(i1==n1){
                    flag = 1;
                    break;
                }
            }
            else{
                a[p+i1+i2] = R[i2];
                i2++;
                if(i2==n2){
                    flag = 2;
                    break;
                }
            }
        }
        if(flag == 1){
            while(i2<n2){
                a[p+i1+i2] = R[i2];
                i2++;
            }
        }
        else if(flag == 2){
            while(i1<n1){
                a[p+i1+i2] = L[i1];
                i1++;
            }
        }
    }

    static void msort(int[] a, int p, int r){
            if(p<r){
                int q = (p+r)/2;
                msort(a,p,q);
                msort(a,q+1,r);
                merge(a,p,q,r);
            }
    }


    static int Part(int a[], int p, int r){
        int i = p - 1;                  //p = start of subarray (inclusive), r = end of subarray(inclusive)
        int j;                           //Smaller than region: p = start(inclusive), i = end(inclusive)
        int temp;
        int val = a[r];                 //Greater than region: start: i+1 (inclusive), end: j-1 (inclusive)
        for(j = p; j < r; j++) {                    //Dont care region: start j(inclusive), end: r-1 (inclusive)
            if(a[j]<val){
                i++;
                temp = a[j];
                a[j] = a[i];
                a[i] = temp;
            }
        }
        temp = a[i+1];
        a[i+1] = a[r];
        a[r] = temp;
        return i+1;
    }

    static void qSort(int a[], int p, int r){
        if(p < r){
            int q = Part(a, p, r);
            qSort(a,p,q-1);
            qSort(a,q+1,r);
        }
    }
}


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("Display Generated Array? 1.Yes 2.No");
            int disp = sc.nextInt();
            System.out.println("Enter Number of Integers to be generated:");
            int n = sc.nextInt();
            System.out.println("Enter order of generation\n1.Ascending Order\t2.Random Order\t\t3.Descending Order");
            int order = sc.nextInt();
            int[] num1 = new int[n];
            int[] num2 = new int[n];
            GenNum.generate(num1, num2, n, order);

            if(disp == 1) {
                System.out.println("Before Sort:");
                for (int i = 0; i < n; i++) {
//                    System.out.println(num1[i] + "\t\t\t" + num2[i]);
                    System.out.printf("%12d", num1[i]);
                    System.out.printf("%14d\n", num2[i]);
                }
                System.out.println("-------------------------");
            }


            double startMergeSort = System.nanoTime()/1000000000.0;
            sorts.msort(num1, 0, n - 1);
            double endMergeSort = System.nanoTime()/1000000000.0;
            double MergeSortTime = (endMergeSort - startMergeSort);

            double startQuickSort = System.nanoTime()/1000000000.0;
            sorts.qSort(num2, 0, n - 1);
            double endQuickSort = System.nanoTime()/1000000000.0;
            double QuickSortTime = (endQuickSort - startQuickSort);


            System.out.println("\n\nAfter Sort:");
            System.out.println("  Merge Sort:\tQuick Sort:"    );
            for (int i = 0; i < n; i++) {
//                System.out.println(num1[i] + "\t\t\t" + num2[i]);
                System.out.printf("%12d", num1[i]);
                System.out.printf("%14d\n", num2[i]);
            }
            System.out.println("-------------------------");


            String str = "Sorted "+ n + " Integers generated in ";
            String temp = "";
            if(order == 1)
                temp = "Ascending ";
            else if(order == 2)
                temp = "Random";
            else if (order == 3)
                temp = "Descending";
            str = str + temp + " Order:";
            System.out.println(str);

            System.out.println("Time taken for merge sort: " + MergeSortTime + " Seconds ");
            System.out.println("Time taken for quick sort: " + QuickSortTime + " Seconds ");
            System.out.println("-------------------------");
            System.out.println("0.Stop\n1.Continue");
            int stop = sc.nextInt();
            if(stop == 0)
                break;
        }
    }
}

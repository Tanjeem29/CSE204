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

            if(L[i1] > R[i2]){
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

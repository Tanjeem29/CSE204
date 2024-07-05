class sorts{
    static int Part(Point a[], int p, int r, int fl){
        int i = p - 1;                  //p = start of subarray (inclusive), r = end of subarray(inclusive)
        int j;                           //Smaller than region: p = start(inclusive), i = end(inclusive)
        Point temp;
        if(fl==1) {
            int val = a[r].x;                 //Greater than region: start: i+1 (inclusive), end: j-1 (inclusive)
            for (j = p; j < r; j++) {                    //Dont care region: start j(inclusive), end: r-1 (inclusive)
                if (a[j].x < val) {
                    i++;
                    temp = a[j];
                    a[j] = a[i];
                    a[i] = temp;
                }
            }
        }
        else {
            int val = a[r].y;                 //Greater than region: start: i+1 (inclusive), end: j-1 (inclusive)
            for (j = p; j < r; j++) {                    //Dont care region: start j(inclusive), end: r-1 (inclusive)
                if (a[j].y < val) {
                    i++;
                    temp = a[j];
                    a[j] = a[i];
                    a[i] = temp;
                }
            }
        }
        temp = a[i+1];
        a[i+1] = a[r];
        a[r] = temp;
//        if(fl==1) a[r].xidx = i+1;
        return i+1;
    }

    static void qsort(Point a[], int p, int r, int fl){
        if(p < r){
            int q = Part(a, p, r, fl);
            qsort(a,p,q-1, fl);
            qsort(a,q+1,r, fl);
        }
    }

    static void merge(Point a[],int  p, int  q, int r, int fl){
        int n1 = q-p+1;                 //p = start of 1st array (inclusive) & q = end of 1st array (inclusive)
        int n2 = r - q;                 //q+1 = start of 2st array (inclusive) & r = end of 2st array (inclusive)
        Point[] L = new Point[n1];
        Point[] R = new Point[n2];

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
            if(fl == 1) {
                if (L[i1].x < R[i2].x) {
                    a[p + i1 + i2] = L[i1];
                    i1++;
                    if (i1 == n1) {
                        flag = 1;
                        break;
                    }
                } else {
                    a[p + i1 + i2] = R[i2];
                    i2++;
                    if (i2 == n2) {
                        flag = 2;
                        break;
                    }
                }
            }
            else{
                if (L[i1].y < R[i2].y) {
                    a[p + i1 + i2] = L[i1];
                    i1++;
                    if (i1 == n1) {
                        flag = 1;
                        break;
                    }
                } else {
                    a[p + i1 + i2] = R[i2];
                    i2++;
                    if (i2 == n2) {
                        flag = 2;
                        break;
                    }
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

    static void msort(Point[] a, int p, int r, int fl){
        if(p<r){
            int q = (p+r)/2;
            msort(a,p,q, fl);
            msort(a,q+1,r, fl);
            merge(a,p,q,r, fl);
        }
    }
}
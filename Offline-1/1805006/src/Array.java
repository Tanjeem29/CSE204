import java.util.Arrays;

public class Array {
    String[] s;
    private int len;
    private int capacity;
    private int padding = 10;
    int length(){
        return len;
    }
    boolean isEmpty(){
        return len == 0;
    }


    Array(){
        len = 0;
        //padding = 10;
        capacity = len + padding;
        s = new String[capacity];
    }
    Array(int n){
        len = n;
        //padding = 10;
        capacity = len + padding;
        s = new String[capacity];
    }
    Array(Array Arg){
        len = Arg.len;
        capacity = Arg.capacity;
        //padding = Arg.padding;
        s = new String[capacity];
        for(int i = 0; i<len; i++)
        {
            s[i] = Arg.s[i];
        }
    }
    Array(String[] Arg){
        len = Arg.length;
        capacity = len + padding;
        //padding = Arg.padding;
        s = new String[capacity];
        for(int i = 0; i<len; i++)
        {
            s[i] = Arg[i];
        }
    }

    Array getArray(){
        return this;
    }
    String[] getArrayStrings(){
        String[] ans = new String[len];
        for(int i = 0; i < len;i++){
            ans[i] = s[i];
        }
        return ans;
    }

    String getAnElement(int i) throws Exception{
        if(i >= len || i < 0)
            throw new Exception();
        else
            return this.s[i];
    }

    void add(String element){
        if (len == capacity) {
            capacity = capacity + padding;
            Array temp = new Array(this);
//            String[] Temp = s;
//            s = new String[capacity];
//            for (int i = 0; i < len; i++) {
//                s[i] = Temp[i];
//            }
            s = new String[capacity];
            for (int i = 0; i < len; i++) {
                s[i] = temp.s[i];
            }
        }
        s[len++] = element;
    }

    void add(int at, String element) throws Exception{
        if(at > len || at < 0){
            throw new Exception();
        }
        else if(at == len){
            this.add(element);
        }
        else
        {
//            if (len == capacity) {
//                System.out.println("len == cap");
//                capacity = capacity + padding;
//                String[] Temp = s;
//                s = new String[capacity];
//                for (int i = at; i < len; i++) {
//                    s[i+1] = Temp[i];
//                }
//            }
            String[] temp = s;
            if(len == capacity) {
                capacity = capacity + padding;
                s = new String[capacity];
                for (int i = 0; i < at; i++)
                    s[i] = temp[i];
                s[at] = element;
                for (int i = at; i < len; i++)
                    s[i + 1] = temp[i];
                len++;
            }
            else
            {
                for(int i = len-1; i >= at; i--)
                    s[i+1] = s[i];
            }
            s[at] = element;
            len++;
        }
    }
    void remove(String temp){
        for(int i = 0; i < len; i++)
        {
            if(s[i].compareTo(temp)==0)
            {
                for(int j = i; j < len-1; j++)
                {
                    s[j] = s[j+1];
                }
                s[--len] = null;
                i--;        //For Consecutive Elements
            }

        }
    }

    int[] findIndex(String temp){
        int count = 0;
        for(int i=0; i < len; i++)
        {
            if(s[i].compareTo(temp)==0)
                count++;
        }
        int[] ret = new int[count];
        int curr = 0;
        for(int i=0; i < len; i++)
        {
            if(s[i].compareTo(temp)==0)
                ret[curr++] = i;
        }
        return ret;
    }

    Array subArray(int start, int end){
        int numel = end-start;
        Array ans = new Array(numel);
        for(int i = start; i < end; i++)
        {
            ans.s[i-start] = s[i];
        }
        return ans;
    }

    void merge(Array A1, Array A2){
        len = A1.len + A2.len;
        capacity = len + padding;
        s = new String[capacity];
        //Array ans = new Array(len);
        int c1 = 0, c2 = 0;
        while(c1 < A1.len && c2 < A2.len){
            if(A1.s[c1].compareTo(A2.s[c2])<0)
            {
                this.s[c1+c2] = A1.s[c1++];
            }
            else
            {
                this.s[c1+c2] = A2.s[c2++];
            }
            if(c1 == A1.len){
                for(int i = c2; i < A2.len; i++){
                    this.s[c1+i] = A2.s[i];
                }
            }
            else{
                for(int i = c1; i < A1.len; i++){
                    this.s[c2+i] = A1.s[i];
                }
            }

        }
    }

    @Override
    public String toString() {
        return "Array{" +
                "s=" + Arrays.toString(s) +
                ", len=" + len +
                ", capacity=" + capacity +
                ", padding=" + padding +
                '}';
    }

//    @Override
//    public String toString() {
//        String s2 = "";
//        for(int i = 0; i< len; i++)
//        {
//            s2 = s2 + i + ": " + s[i] + ", ";
//        }
//        return "Array{" +
//                s2 +
//                ", len=" + len +
//                ", capacity=" + capacity +
//                ", padding=" + padding +
//                '}';
//    }

    public static void main(String[] args) throws Exception {
//        Array A1 = new Array();
//        Array A2 = new Array(10);
//        int stop = A2.length()+10;
//        for(int i = 0; i < stop; i++){
//
//            A2.add(i, Integer.toString(i));
//            System.out.println(A2);
//        }
//        Array A3 = new Array(A2);
//
//        A2.add(0, "Start");
//        A2.add(A2.length()/2, "Middle");
//        A2.add(A2.length()/2+1, "Middle");
//        A2.add(A2.length()/2+7, "Middle");
//        A2.add(A2.length()-1, "End");
//
//        System.out.println("A3"+ A3.toString());
//        System.out.println("A2"+ A2.toString());
//
//        int[] idx = A2.findIndex("End");
//        for(int i : idx)
//            System.out.println(i);
//        A2.remove("Middle");
//
//
//        System.out.println("A3"+ A3.toString());
//        System.out.println("A2"+ A2.toString());
//
//        A2.add(17, "20");
//        System.out.println("A2"+ A2.toString());
//
//
//        Array A4 = new Array(A2.subArray(5,15));
//        System.out.println("A4"+ A4.toString());
//
//
//
//
//
//        Array S = new Array(5);
//        for(int i = 0;i<5; i++ ){
//            S.add(i, Integer.toString(2*i));
//        }
//        System.out.println("S"+ S.toString());
//
//        Array S2 = new Array(10);
//        for(int i = 0;i<10; i++ ){
//            S2.add(i, Integer.toString(2*i+1));
//        }
//        System.out.println("S2"+ S2.toString());
//
//        Array A5 = new Array();
//        A5.merge(S2,S);
//        System.out.println("A5"+ A5.toString());
//
//
//        //A5.merge();
//
//
////        System.out.println(A1);
//        System.out.println(A1.isEmpty());
//        System.out.println(A2.isEmpty());
        String[] s = new String[]{"Enan","Ibrahim", "Sakib", "Sinan", "Tanjeem", "Tasnim"};

        Array cons = new Array();
        System.out.println("Cons Length:" + cons.length());
        //System.out.println(cons);
        Array cons1 = new Array(10);
        System.out.println("Cons1 Length:" + cons1.length());
        //System.out.println(cons1);
        Array strArr = new Array(s);
        System.out.println(strArr);
        strArr.add("Zinan");
        System.out.println(strArr);
        strArr.add(6,"Zyro");//OK
        System.out.println(strArr);
        strArr.add(0,"A");
        System.out.println(strArr);
        strArr.add(3,"Shamit");//OK
        System.out.println(strArr);
        try{
            strArr.add(strArr.length()+1, "WontWork");
        }catch (Exception e){
            System.out.println("Array Out of Bounds");
        }
        System.out.println(strArr);
        Array Cons2 = new Array(strArr);
        System.out.println(Cons2);

        Array MergeArr1 = new Array(15);
        Array Arr2 = new Array(new String[]{"Aadib","Abyad","Fahim","Nirjhor","Maruf" });
        System.out.println(Arr2);
        MergeArr1.merge(strArr, Arr2);
        System.out.println(MergeArr1);


    }
}



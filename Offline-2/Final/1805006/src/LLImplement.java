import java.util.Scanner;

class node{
    node prev;
    node next;
    int ref;
    int id;
    node(node p, node n, int v, int i){
        prev = p;
        next = n;
        ref = v;
        id = i;
    }
    node(){
        prev = null;
        next = null;
        ref = 0;
        id = 0;
    }
    @Override
    public String toString(){
        return "Player: " + Integer.toString(id) + '(' + Integer.toString(ref) + ')';
    }
}
class DLL{
    node head;
    //node tail;
    private node temptail;
    node curr;
    int tCount ; // Total Count
    int pCount ; // Present Count
    int dir = 1; //1 for forward, -1 for reverse;
    int iswon = 0; //If there's ever just 1 person remaining
    DLL(){
        head = null;
        temptail = null;
        tCount = 0;
        pCount = 0;

    }
    void dirChange(){
        if(iswon == 1)
            return;
        dir *= -1;
    }
    void del(){
        if(iswon == 1)
            return;
        curr.prev.next = curr.next;
        curr.next.prev = curr.prev;
        if(curr == head)
            head = head.next;
        if(dir == 1)
            curr = curr.next;
        if(dir == -1)
            curr = curr.prev;
        pCount--;
        if(pCount == 1)
            iswon = 1;
    }

    void addfirst(int refVal){
        head = new node();
        pCount++;
        tCount ++;
        head.id = pCount;
        head.next = head;
        head.prev = head;
        head.ref = refVal;
        curr = head;
    }


    void insert(int reflex){
//        if(iswon == 1)
//            return;
        node newN = new node();
        if(tCount>1){
            //System.out.println(temp);

            newN.prev = temptail;
            temptail.next = newN;
            newN.next = head;
            head.prev = newN;
            temptail = newN;

        }

        else{
            newN.prev = head;
            newN.next = head;
            head.prev = newN;
            head.next = newN;
            temptail = newN;

        }

        newN.ref = reflex;
        newN.id = ++tCount;
        //System.out.println(newN);
        pCount++;
    }

    public void insert2(int reflex){
        if(iswon == 1)
            return;
        node newN = new node();
        node temp = curr;
        if(dir == 1){
            curr.prev.next = newN;
            newN.prev = curr.prev;
            newN.next = curr;
            curr.prev = newN;
        }
        else
        {
            curr.next.prev = newN;
            newN.next = curr.next;
            newN.prev = curr;
            curr.next = newN;
        }
        newN.ref = reflex;
        newN.id = ++tCount;
        //System.out.println(newN);
        pCount++;
    }

//    @Override
//    public String toString(){
//        String out = "pillow passing Sequence = Player " + curr.id ;
//        curr = curr.next;
//        for(int i = 1; i < pCount;i++)
//        {
//            out += ',' + Integer.toString(curr.id);
//        }
//        return out;
//    }
@Override
public String toString(){

    node temp = curr;
    //String out = "pillow passing Sequence = Player " + temp.id + "(" + temp.ref + ')';
    String out = "Pillow passing Sequence = Player " + temp.id;
    if (dir == 1 )
        temp = temp.next;
    else
        temp = temp.prev;
    for(int i = 1; i < pCount;i++)
    {
        //out += ", " + Integer.toString(temp.id) + "(" + Integer.toString(temp.ref) + ')';
        out += ", " + Integer.toString(temp.id);
        if (dir == 1 )
            temp = temp.next;
        else
            temp = temp.prev;
    }
    return out;
}

}

public class LLImplement {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int Count  = in.nextInt();
        DLL list = new DLL();
//        list.pCount = Count;
//        list.tCount = Count;
        if(Count == 1)
            list.iswon = 1;
        int refVal = in.nextInt();
        list.addfirst(refVal);

        while(list.tCount < Count){
            refVal = in.nextInt();
            list.insert(refVal);
            //list.tCount++;
            //list.pCount++;
            //System.out.println(list.tCount);
            //System.out.println(Count);
        }
        int pt = 0;
        int ct = 0;
        int diff = 0;
        char c;
        while(true) {
            ct = in.nextInt();
            diff = diff + ct - pt;
            while (diff > list.curr.ref) {
                diff -= list.curr.ref;
                if (list.dir == 1)
                    list.curr = list.curr.next;
                else
                    list.curr = list.curr.prev;
            }
            c = in.next().charAt(0);
            if (c == 'M' && list.iswon==0) {
                System.out.println("Player " + Integer.toString(list.curr.id) + " has been eliminated at t = " + ct);
                list.del();
                diff = 0;
            } else if (c == 'R' && list.iswon==0) {
                list.dirChange();
            } else if (c == 'I') {
                int x = in.nextInt();
                if(list.iswon == 0)
                list.insert2(x);
            }
            else if(c == 'P' ){
                System.out.println("Player " + Integer.toString(list.curr.id) + " is holding the pillow at t = " + ct);
            }
            else if(c == 'F'){

                if(list.iswon==0) {
                    System.out.println("Game Over : Player " + Integer.toString(list.curr.id) + " is holding the pillow at t = " + ct);
                    System.out.println(list);
                }
                else
                    System.out.println("Game Over!! Player " + Integer.toString(list.curr.id) + " Wins!!!");
                break;
            }
            else if(list.iswon==0)
            {
                System.out.println("Error! Wrong Input!! " + list);
            }
            pt = ct;
        }







//        list.curr =list.curr.next;
//        list.dirChange();
//        list.del();
//        System.out.println(list.curr);
        //System.out.println(list);
        //System.out.println(list.tCount);
        //System.out.println(list.pCount);
//        while(list.pCount-->0){
//            System.out.println(list.curr.id + ":" + list.curr.ref);
//            list.curr = list.curr.next;
//        }


    }
}
/*
3
        4 5 2

 */
import java.io.*;
import java.util.Scanner;

class Util{
    static Point[] input(String in) throws IOException {
        File file = new File(in);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        line = br.readLine();
        int n = Integer.parseInt(line);
        int x,y;
        int i=0;
        Point[] p = new Point[n];
        while((line = br.readLine()) != null) {
            String[] str = line.split(" ");
            x = Integer.parseInt(str[0]);
            y = Integer.parseInt(str[1]);
            p[i] = new Point(x,y);
            i++;
        }
        return p;
    }
}
class Point{
    int x;
    int y;
    int xidx=0;
    public Point(int x1, int y1){
        x = x1;
        y = y1;
    }
    public Point(){
        x = 0;
        y = 0;
    }
    public Point(Point P){
        setX(P.getX());
        setY(P.getY());
    }
    int getX(){return x;}
    int getY(){return y;}
    void setX(int a){ x = a; }
    void setY(int b){ y = b; }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

class myAns{
    double d;
    Point p1;
    Point p2;
    public myAns(double dist, Point P1, Point P2){
        d = dist;
        p1 = new Point(P1);
        p2 = new Point(P2);
    }
    public myAns(){
        d = Integer.MAX_VALUE;
        p1 = new Point();
        p2 = new Point();
    }
    public void SetMyAns(double dist, Point P1, Point P2){
        d = dist;
        p1 = new Point(P1);
        p2 = new Point(P2);
    }

    @Override
    public String toString() {
        return "myAns{" +
                "d=" + d +
                ", p1=" + p1 +
                ", p2=" + p2 +
                '}';
    }
}

class ClosestPair{
    static double Err = Math.sqrt(20000*20000 + 20000 * 20000) + 10;
    static double dist(Point p1, Point p2){
        int dx = p1.getX() - p2.getX();
        int dy = p1.getY() - p2.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }


    static myAns FindClosestPair(int nth, int Xstart, int Xend, Point[] px, Point[] py, double minD){  //xstart and xend are start and end indices of current subarray in this function, inclusive
        int n = Xend - Xstart + 1;
        if(n==2){
            if(nth == 1 || (nth == 2 && (dist(px[Xstart], px[Xend]) > minD)))
                return new myAns(dist(px[Xstart], px[Xend]), px[Xstart], px[Xend]) ;
            else
                return new myAns(Err, px[Xstart], px[Xend]) ;
        }

        if(n==3){
            double d1 = dist(px[Xstart],px[Xend]);
            double d2 = dist(px[Xstart+1],px[Xend]);
            double d3 = dist(px[Xstart],px[Xend-1]);

            if(nth == 2){
                if(d1 == minD){
                    if(d2 == minD) return new myAns(d3, px[Xstart], px[Xend-1]);
                    if(d3 == minD || d2<d3 ) return new myAns(d2, px[Xstart+1], px[Xend]);
                    return new myAns(d3, px[Xstart], px[Xend-1]);
                }
                if(d2 == minD){
                    if(d3 == minD || d1 < d3) return  new myAns(d1, px[Xstart], px[Xend]);
                    return new myAns(d3, px[Xstart], px[Xend-1]);
                }
                if(d3 == minD){
                    if(d1<d2) return  new myAns(d1, px[Xstart], px[Xend]);
                    return new myAns(d2, px[Xstart+1], px[Xend]);
                }
            }
            if(d1<=d2 && d1<=d3){
                return new myAns(d1, px[Xstart], px[Xend]);
            }
            if(d2<=d1 && d2<=d3){
                return new myAns(d2, px[Xstart+1], px[Xend]);
            }
                return new myAns(d3, px[Xstart], px[Xend-1]);
        }

        int  Xmid = (Xstart + Xend)/2;

        Point[] pylow = new Point[Xmid - Xstart +1];
        Point[] pyhi = new Point[Xend - Xmid];
        for(int i=0;i<Xmid - Xstart +1; i++){
            pylow[i] = new Point();
        }
        for(int i = 0; i < Xend - Xmid; i++){
            pyhi[i] = new Point();
        }
        int nylo = 0;
        int nyhi = 0;




        for(int i = 0; i <n;i++){

            if(py[i].xidx <= px[Xmid].xidx ) {
                pylow[nylo] = py[i];
                nylo++;
            }
            else if(py[i].xidx > px[Xmid].xidx){
                pyhi[nyhi]= py[i];
                nyhi++;
            }

        }


        myAns a1 = ClosestPair.FindClosestPair(nth, Xstart, Xmid, px, pylow, minD);
        myAns a2 = ClosestPair.FindClosestPair(nth, Xmid+1, Xend, px, pyhi, minD);
        myAns a3 = new myAns();

        if(a1.d<a2.d){                  ///WATCH
            a3 = a1;
        }
        else if(a2.d <= a1.d){          ///WATCH
            a3 = a2;
        }
        Point[] newpy = new Point[py.length];
        int sizey = 0;

        for(Point p : py ){
            if(p.getX()>= (px[Xmid].x - a3.d) && p.getX() <= (px[Xmid].x + a3.d)){
                newpy[sizey] = p;
                sizey++;
            }
        }
        int comp = 7;
        if(nth == 2) comp = 15;
        for(int i = 0; i<sizey; i++){
            for(int j = 1; j<=comp;j++){
                if(i+j == sizey) break;
                if(dist(newpy[i], newpy[i+j])<=a3.d){           ///WATCH
                    if(nth == 1 || (nth == 2 && (dist(newpy[i], newpy[i+j]) > minD))){
                        a3.d = dist(newpy[i], newpy[i+j]);
                        a3.p1 = newpy[i];
                        a3.p2 = newpy[i+j];
                    }

                }
            }
        }
        return a3;
    }

    static myAns FindSecondClosest(int Xstart, int Xend, Point[] px, Point[] py){
        myAns A = FindClosestPair(1, Xstart, Xend, px, py,0);
        myAns A2 = FindClosestPair(2, Xstart, Xend, px, py, A.d);
        return A2;
    }

}



public class Main {
    public static void main(String[] args) throws IOException {

        String in = "Input.txt";
//        String in = "D:\\L2T1\\CSE-204\\Offline-8\\Test Cases\\input_test 1";
        Scanner sc = new Scanner(System.in);
        Point[] P = Util.input(in);

        int n = P.length;

        Point[] Px = P.clone();
        sorts.msort(Px, 0, n-1, 1);
        for(int i =0;i < n;i++) Px[i].xidx = i;

        Point[] Py = Px.clone();
        sorts.msort(Py, 0, n-1, 2);


        myAns Answer = ClosestPair.FindSecondClosest(0, n-1, Px, Py);
        int ind1 = 0, ind2 = 0;
        for(int i =0; i<n; i++){
            if(Answer.p1.x == P[i].x && Answer.p1.y == P[i].y)
                ind1 = i;
            if(Answer.p2.x == P[i].x && Answer.p2.y == P[i].y)
                ind2 = i;

        }
        if(Answer.d == ClosestPair.Err){
            System.out.println("All Houses are Equidistant");
            System.out.println(ind1 + " " + ind2);
            System.out.println(String.format("%.4f", ClosestPair.dist(P[ind1],P[ind2])));
            return;
        }

        if(ind1>ind2){
            int temp = ind1;
            ind1 = ind2;
            ind2 = temp;
        }

        System.out.println(Integer.toString(ind1) + " " + Integer.toString(ind2));
        //System.out.println(Answer);
        System.out.println(String.format("%.4f", Answer.d));

    }
}

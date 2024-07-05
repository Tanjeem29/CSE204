import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Graph{
    ArrayList<ArrayList<Integer>> Adj;
    boolean visited[];
    int size;
    int cityPieces[];
    int friendPieces[];
    public Graph(int n, int F){
        cityPieces = new int[n];
        friendPieces = new int [F];
        Adj = new ArrayList<>(n);
        visited = new boolean[n];
        size = n;
        for(int i =0;i <n; i++)
            Adj.add(new ArrayList<Integer>());
    }

    public void edgeAdd(int u, int v){
        Adj.get(u).add(v);
        Adj.get(v).add(u);
    }

}

class UtilityBFS{

    static void BFS(Graph G, int start, int fID){
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(start);
        while(!(q.isEmpty())){
            int curr = q.remove();

            if(G.visited[curr])
                continue;

            G.visited[curr] = true;
            G.friendPieces[fID] += G.cityPieces[curr];

            //System.out.println(curr);

            for(int i=0; i < G.Adj.get(curr).size(); i++){
                if(!(G.visited[G.Adj.get(curr).get(i)])){
                    q.add(G.Adj.get(curr).get(i));
                }
            }
        }

    }
}

public class BFSImplement {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        int C = in.nextInt();
        int R = in.nextInt();
        int L = in.nextInt();
        int F = in.nextInt();
        //System.out.println(C + " " + R + " " + L + " " + F + " ");


        int totalPieces = 0;
        int collectedPieces = 0;

        Graph G = new Graph(C,F);

        int i;
        //int trueL = 0;
        for(i = 0; i < R; i++){
            int x = in.nextInt();
            int y = in.nextInt();
            G.edgeAdd(x,y);
        }

        for(i = 0; i < L; i++){
            int c = in.nextInt();
            int p = in.nextInt();

            if(p>0){
                G.cityPieces[c] += p;
                totalPieces += p;
            }

        }

        int cityStart[] = new int[F];

        for(i = 0; i < F; i++){
            int c2 = in.nextInt();
            int f = in.nextInt();
            //System.out.println("Test");
            cityStart[f] = c2;
            //System.out.println("Test2");
        }

        for(i = 0; i < F; i++){
            UtilityBFS.BFS(G, cityStart[i], i);
        }

        for(i = 0; i < F; i++){
            collectedPieces += G.friendPieces[i];
        }

        PrintWriter out = new PrintWriter("outputBFS.txt");

        String s;
        if(collectedPieces == totalPieces){
            s = "Mission Accomplished";
        }
        else{
            s = "Mission Impossible";
        }
        //System.out.println(s);
        out.println(s);


        s = collectedPieces + " out of " + totalPieces + " pieces are collected";
        //System.out.println(s);
        out.println(s);


        for(i = 0; i<F; i++){
            s = i + " collected " + G.friendPieces[i] + " pieces";
            //System.out.println(s);
            out.println(s);
        }

        out.close();
    }
}

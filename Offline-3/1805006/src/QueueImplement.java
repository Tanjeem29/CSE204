import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.util.Scanner;

class node{
    char c;
    node front;
    node back;
    node(){
        c = '/';
        front = null;
        back = null;
    }
    node(char ch){
        c = ch;
        front = null;
        back = null;
    }

    @Override
    public String toString() {
        return " " + c;
    }
}
class Queue{
    node head;
    node tail;
    int length;
    Queue(){
        length = 0;
        head = null;
        tail = null;
    }

    void enqueue(char ch){
        if(length == 0){
            head = new node(ch);
            tail = head;
        }
        else if(length == 1){
            tail = new node(ch);
            tail.front = head;
            tail.back = null;
            head.back = tail;
            head.front = null;
        }
        else
        {
            node temp = new node(ch);
            temp.front = tail;
            tail.back = temp;
            temp. back = null;
            tail = temp;
        }
        length++;
    }

    char dequeue(){
        char ret;
        if(length == 0){
            return '#';
        }
        else if (length == 1){
            ret = head.c;
            head = null;
            length--;
            return ret;
        }
        else{
            ret = head.c;
            head = head.back;
            head.front = null;
            length--;
            return ret;

        }

    }
    boolean isEmpty(){
        if (length == 0) return true;
        else return false;
    }

    @Override
    public String toString() {
        node it = head;
        String ans = new String();
        while(it!=tail){
            ans += it.c;
            it = it.back;
        }
        ans+=it.c;
        return ans;

    }
}
public class QueueImplement {


    public static void main(String[] args) {
        int[] freq = new int[26];
        char ch;
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        int numel = input.length();


        Queue given = new Queue();
        Queue out = new Queue();

        for(int i = 0; i < numel; i++){

            ch = input.charAt(i);

            freq[ch-'a']++;
            given.enqueue(ch);

            //Disregard elements with Freq > 1
            while(!(given.isEmpty()) && freq[given.head.c-'a']>1){
                given.dequeue();
            }


            if(given.isEmpty()){
                out.enqueue('#');
            }
            else{
                out.enqueue(given.head.c);
            }

        }

        System.out.println(out);

    }


}

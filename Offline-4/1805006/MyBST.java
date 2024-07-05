import java.util.Scanner;

class node {
    int value;
    node parent;
    node leftChild;
    node rightChild;

    node(int v) {
        value = v;
        parent = null;
        leftChild = null;
        rightChild = null;
    }
}

class BST{
    node root;
    BST(){
        root = null;
    }
    BST(node r){
        root = r;
    }

    void insertItem(int x){

        if(root == null){
            node temp = new node(x);
            root = temp;
        }

        else{
            node curr = root;
            while(true){
                if(x < curr.value){
                    if(curr.leftChild == null){
                        node temp = new node(x);
                        temp.parent = curr;
                        curr.leftChild = temp;
                        break;
                    }
                    else{
                        curr = curr.leftChild;
                    }
                }
                else if(x > curr.value){
                    if(curr.rightChild == null){
                        node temp = new node(x);
                        temp.parent = curr;
                        curr.rightChild = temp;
                        break;
                    }
                    else{
                        curr = curr.rightChild;
                    }
                }
                else{
                    System.out.println("Identical element already exists");
                    break;
                }
            }

        }

    }

    node searchItem(int x){
        node curr = root;
        while(true){
            if(curr == null){
                System.out.println(x + " Has not been found");
                return null;
            }
            else if(x == curr.value){
                System.out.println(x + " Has been found");
                return curr;
            }
            else if(x < curr.value){
                curr = curr.leftChild;
            }
            else {
                curr = curr.rightChild;
            }
        }
    }

    int getInOrderSuccessor(int x) {
        node curr = searchItem(x);
        if (curr == null) return -1;
        if(curr.rightChild == null){
            while(curr.parent!=null){
                if(curr.parent.value>curr.value) return curr.parent.value;
                curr = curr.parent;
            }
            return -1;
        }
        else return getMinItem(curr.rightChild).value;

    }



    int getInOrderPredecessor(int x) {
        node curr = searchItem(x);
        if (curr == null) return -1;
        if (curr.leftChild == null) {
            while (curr.parent != null) {
                if (curr.parent.value < curr.value) return curr.parent.value;
                curr = curr.parent;
            }
            return -1;
        }
        else return getMaxItem(curr.leftChild).value;
    }


    int getMaxItem(){
        if(root == null){
            System.out.println("Empty BST. Returning -1");
            return -1;
        }
        return getMaxItem(root).value;
    }
    node getMaxItem(node n){
        node curr = n;
//        if(curr == null){
//            //System.out.println("Empty BST. Returning -1");
//            return -1;
//        }
        while(true){
            if(curr.rightChild == null){
                return curr;
            }
            curr = curr.rightChild;
        }
    }

    int getMinItem(){
        if(root == null){
            System.out.println("Empty BST. Returning -1");
            return -1;
        }
        return getMinItem(root).value;
    }
    node getMinItem(node n){
        node curr = n;
//        if(curr == null){
//            System.out.println("Empty BST. Returning -1");
//            return -1;
//        }
        while(true){
            if(curr.leftChild == null){
                return curr;
            }
            curr = curr.leftChild;
        }
    }



    int getDepth(int x){
        node curr = searchItem(x);
        if(curr == null) return -1;
        return getDepth(curr);
    }
    int getDepth(node n){
        node curr = n;
        int ans = 0;
        while(curr.parent!=null){
            ans++;
            curr = curr.parent;
        }
        return ans;
    }

    int height(){
        return this.height(root);
    }

    int height(node curr){
        if(curr == null) return -1;
        return Utility.max( height(curr.leftChild), height(curr.rightChild)) + 1;
    }

    void printInOrder(){
        System.out.print("In-Order Traversal: ");
        printInOrder(root);
    }

    void printInOrder(node curr)
    {
        if(curr == null) return;
        printInOrder(curr.leftChild);
        System.out.print(" "+ curr.value);
        printInOrder(curr.rightChild);
    }


    void printPreOrder(){
        System.out.print("Pre-Order Traversal: ");
        printPreOrder(root);
    }

    void printPreOrder(node curr)
    {
        if(curr == null) return;
        System.out.print(" "+ curr.value);
        printPreOrder(curr.leftChild);
        printPreOrder(curr.rightChild);
    }

    void printPostOrder(){
        System.out.print("Post-Order Traversal: ");
        printPostOrder(root);
    }

    void printPostOrder(node curr)
    {
        if(curr == null) return;
        printPostOrder(curr.leftChild);
        printPostOrder(curr.rightChild);
        System.out.print(" "+ curr.value);
    }

    int getSize(int x){
        node curr = searchItem(x);
        if(curr == null) return -1;
        return getSize(curr);
    }
    int getSize(node curr){
        if(curr == null) return 0;
        return getSize(curr.leftChild) + getSize(curr.rightChild) + 1;
    }

    int deleteItem(int x){
        node curr = searchItem(x);
        if(curr == null) return -1;
        else{
            deleteItem(curr);
            return 1;
        }
    }
    node deleteItem(node curr){ //See slide 7(2) page 37. Just copy paste value of min item right, into the slot, and delete minitemRight
        if(curr.leftChild == null && curr.rightChild == null){
            if(curr.parent == null){
                root = null;
                System.out.println("Deleting root");
                return curr;
            }
            else{
                if(curr.parent.value > curr.value){
                    curr.parent.leftChild = null;
                }
                else{
                    curr.parent.rightChild = null;
                }
                return curr;
            }
        }
        else if(curr.leftChild == null){
            if(curr.parent == null){
                root = curr.rightChild;
                root.parent = null;
                System.out.println("Deleting root");
                return curr;
            }
            else{
                if(curr.parent.value > curr.value){
                    curr.parent.leftChild = curr.rightChild;
                    curr.rightChild.parent = curr.parent;
                    return curr;
                }
                else{
                    curr.parent.rightChild = curr.rightChild;
                    curr.rightChild.parent = curr.parent;
                    return curr;
                }

            }

        }
        else if(curr.rightChild == null){
            if(curr.parent == null){
                root = curr.leftChild;
                root.parent = null;
                System.out.println("Deleting root");
                return curr;
            }
            else{
                if(curr.parent.value > curr.value){
                    curr.parent.leftChild = curr.leftChild;
                    curr.leftChild.parent = curr.parent;
                    return curr;
                }
                else{
                    curr.parent.rightChild = curr.leftChild;
                    curr.leftChild.parent = curr.parent;
                    return curr;
                }
            }
        }
        else{

            node n = getMinItem(curr.rightChild);
            n = deleteItem(n);
            n.rightChild = curr.rightChild;
            n.leftChild = curr.leftChild;
            if(n.rightChild!=null){
                n.rightChild.parent = n;
                //System.out.println("right "+ n.rightChild.value);
            }
            if(n.leftChild!= null) {
                n.leftChild.parent = n;
                //System.out.println("left: "+ n.leftChild.value);
            }
            //System.out.println("l: " +n.leftChild.value + " r: "+ n.rightChild.value);

            if(curr.parent == null) {
                n.parent = null;
                System.out.println("Deleting root");
                root = n;
            }
            else{
                if(curr.parent.value>curr.value){
                    curr.parent.leftChild = n;
                }
                else{
                    curr.parent.rightChild = n;
                }
                n.parent = curr.parent;
            }
            return curr;

        }

    }


}

class Utility{
    static int max(int a, int b){
        if(a>b) return a;
        return b;
    }
}

public class MyBST {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int cmd;
        int n;
        boolean disp = true;
        BST bst = new BST();
        System.out.println("Enter an integer corresponding to your desired function ");
        while(true){
            if(disp) {
                System.out.println("1. Insert Item");
                System.out.println("2. Search Item");
                System.out.println("3. In Order Successor Item");
                System.out.println("4. In Order Predecessor Item");
                System.out.println("5. Delete Item");
                System.out.println("6. Get Depth of Item");
                System.out.println("7. Get Max Item");
                System.out.println("8. Get Min Item");
                System.out.println("9. Get Height");
                System.out.println("10. Print In-Order Traversal");
                System.out.println("11. Print Pre-Order Traversal");
                System.out.println("12. Print Post-Order Traversal");
                System.out.println("13. Get Size");
                System.out.println("-1 to end");
            }
            cmd = in.nextInt();
            if( cmd==1 ){
                n = in.nextInt();
                bst.insertItem(n);
            }
            else if( cmd==2 ){
                n = in.nextInt();
                bst.searchItem(n);
            }
            else if( cmd==3 ){
                n = in.nextInt();
                int s = bst.getInOrderSuccessor(n);
                if(s == -1) {
                    System.out.println("No in-order successor");
                }
                else{
                    System.out.println("In order successor is " + s);
                }
            }
            else if(cmd==4){
                n = in.nextInt();
                int s = bst.getInOrderPredecessor(n);
                if(s == -1) {
                    System.out.println("No in-order predecessor");
                }
                else{
                    System.out.println("In order Predecessor is " + s);
                }
            }
            else if(cmd==5){
                n = in.nextInt();
                int x = bst.deleteItem(n);
                if(x==-1) System.out.println("Delete Unsuccessful. Not found: " + n);
                else System.out.println("Deleted" + n);
            }
            else if(cmd==6){
                n = in.nextInt();
                int s = bst.getDepth(n);
                if(s == -1) {
                    System.out.println("No Proper Depth");
                }
                else{
                    System.out.println("Depth of " + n + " is: " + s);
                }
            }
            else if(cmd==8){
                int s = bst.getMinItem();
                if(s == -1)
                    System.out.println("No Minimum Item");
                else
                    System.out.println("Minimum Item is " + s);
            }
            else if(cmd==7){
                int s = bst.getMaxItem();
                if(s == -1)
                    System.out.println("No Maximum Item");
                else
                    System.out.println("Maximum Item is " + s);
            }
            else if(cmd==9){
                int h = bst.height();
                System.out.println("Height is " + h);
            }
            else if(cmd==10){
                bst.printInOrder();
                System.out.println();
            }
            else if(cmd==11){
                bst.printPreOrder();
                System.out.println();
            }
            else if(cmd==12){
                bst.printPostOrder();
                System.out.println();
            }
            else if(cmd==13){
                n = in.nextInt();
                int s = bst.getSize(n);
                if(s==-1){
                    System.out.println("No valid size");
                }
                else{
                    System.out.println("Size of subtree at " + n + " is: " + s) ;
                }
            }
            else if(cmd == -1){
                break;
            }
            else
                System.out.println("Invalid input. Enter an integer between 1 and 13. Enter -1 to quit");

        }
    }
}

void swap(int &a, int &b){
    int t;
    t = a;
    a = b;
    b = t;
}

class Heap{
    int* a;
    int s;
    int capacity;
public:
    Heap(int n){
        capacity = n;
        a = new int[n];
        s = 0;
    }
    ~Heap(){
        delete a;
    }
    int size(){
        return s;
    }

    int getMax(){
        if(s>0)
            return a[1];
        else
            return -1;
    }

    void Max_Heapify_Up(int i){
        while(i/2>0){
            if(a[i]>a[i/2]){
                swap(a[i],a[i/2]);
                i = i/2;
            }
            else{
                break;
            }
        }
    }

    void Max_Heapify_Down(int i){
        while(true){
            if(2*i+1>s){
                if(2*i>s){
                    break;
                }
                else{
                    if(a[2*i]>a[i]){
                        swap(a[2*i], a[i]);
                        i=2*i;
                    }
                    else{
                        break;
                    }
                }
            }
            else{
                if(a[i]>a[2*i] && a[i]>a[2*i+1]){
                    break;
                }
                else if(a[i]>a[2*i]&&a[i]<a[2*i+1]){
                    swap(a[i],a[2*i+1]);
                    i = 2*i+1;
                }
                else if(a[i]>a[2*i+1]&&a[i]<a[2*i]){
                    swap(a[i],a[2*i]);
                    i = 2*i;
                }
                else{
                    if(a[2*i]>a[2*i+1]){
                        swap(a[i],a[2*i]);
                        i = 2*i;
                    }
                    else{
                        swap(a[i],a[2*i+1]);
                        i = 2*i+1;
                    }
                }
            }
        }
    }

    void deleteKey(){
        a[1] = a[s--];
        this->Max_Heapify_Down(1);
    }

    void insert(int x){
        a[++s] = x;
        this->Max_Heapify_Up(s);
    }

};

using namespace std;
void heapsort(vector<int>&v){
    Heap h(v.size());
    while(!v.empty()){
        h.insert(v.back());
        v.pop_back();
    }
    while(h.size()>0){
        v.push_back(h.getMax());
        h.deleteKey();
    }
}



package datastructure;
public class Queue{
    private int size;
    private String[] queArray;
    private int front,rear,items;
           
    public Queue(int s){
        size = s;
        queArray=new String[size];
        front=0;
        rear=-1;
        items=0;
    }
    public boolean isEmpty(){
	return (items==0);
    }
    public boolean isFull(){
        return (items==size);
    }
    public int size(){
        return items;
    }
    public String peek(){
        return queArray[front];
    }
    public void enqueue(String x){
        if(isFull()==false){
                queArray[++rear]=x;
                items++;
        }
    }
    public String dequeue(){
        String temp=null;
        if(isEmpty()==false){
                temp=queArray[front++];
                items--;
        }
        return temp;
    }
        
}


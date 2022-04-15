package consumerProducer;

import java.util.Arrays;

public class BierStack {
    private int tos = 0;
    private int[] values;

    public BierStack(int size) {
        values = new int[size];
    }

    public boolean isEmpty(){
        return tos == 0;
    }

    public boolean isFull(){
        return tos == values.length;
    }

    public void push(int value){
        if(isFull()){
            throw new RuntimeException("Stack is Full");
        }
        values[tos++] = value;
    }

    public int pop(){
        if(isEmpty()){
            throw new RuntimeException("Stack is Empty");
        }

        return values[--tos];
    }

    @Override
    public String toString() {
        return "BierStack{" +
                "tos=" + tos +
                ", values=" + Arrays.toString(values) +
                '}';
    }
}

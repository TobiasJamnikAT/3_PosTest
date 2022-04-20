package LetzteWildeSession.ConsumerProducer;

import java.util.Arrays;

public class Stack {
    private String[] products;
    private int tos;

    public Stack(int size) {
        this.products = new String[size];
        this.tos = 0;
    }

    public boolean isEmpty(){
        return tos == 0;
    }

    public boolean isFull(){
        return tos == products.length;
    }

    public void addProduct(){
        if(isFull()){
            throw new RuntimeException("Stack is Full");
        }

        products[tos++] = "Bier";
    }

    public String removeProduct(){
        if(isEmpty()){
            throw new RuntimeException("Stack is Empty");
        }

        return products[--tos];
    }

    @Override
    public String toString() {
        return "Stack{" +
                "products=" + Arrays.toString(products) +
                '}';
    }
}

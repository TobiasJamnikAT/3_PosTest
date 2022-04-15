package consumerProducer;

import java.util.Random;

public class BierProducer implements Runnable{ // Runnable --> man kann Thread machen
    private BierStack stack;
    private int noIterations;

    public BierProducer(BierStack stack, int noIterations) {
        this.stack = stack;
        this.noIterations = noIterations;
    }

    @Override
    public void run() {
        Random randy = new Random();
        for(int i = 0; i < noIterations; i++){
            synchronized (stack){
                while (stack.isFull()){
                    System.out.println("Produzent kann nicht produzieren - Stack is full");
                    try {
                        stack.wait(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                stack.push(randy.nextInt(10));
                stack.notify();
                System.out.println("Produzent hat Bier produziert - " +stack);
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

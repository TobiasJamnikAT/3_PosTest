package LetzteWildeSession.ConsumerProducer;

public class Producer implements Runnable{
    private Stack stack;
    private int noIterations;

    public Producer(Stack stack, int noIterations) {
        this.stack = stack;
        this.noIterations = noIterations;
    }

    @Override
    public void run() {
        for (int i = 0; i < noIterations; i++){
            synchronized (stack){
                if(stack.isFull()){
                    System.out.println("Stack is Full - Producer must wait");
                    try {
                        stack.wait(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println("Producer added Prodcut");
                stack.addProduct();
                stack.notify();
                System.out.println("Stack: " +stack);
            }
        }

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

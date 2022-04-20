package LetzteWildeSession.ConsumerProducer;

public class Consumer implements Runnable{
    private Stack stack;
    private int noIterations;

    public Consumer(Stack stack, int noIterations) {
        this.stack = stack;
        this.noIterations = noIterations;
    }

    @Override
    public void run() {
        for (int i = 0; i < noIterations; i++){
            synchronized (stack){
                if(stack.isEmpty()){
                    System.out.println("Consumer must wait - Stack empty");
                    try {
                        stack.wait(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                String product = stack.removeProduct();
                System.out.println("Consumer removed " +product);

                stack.notifyAll();
                System.out.println("Stack:" +stack.toString());
            }
        }

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

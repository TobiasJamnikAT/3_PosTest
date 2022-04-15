package consumerProducer;

public class BierConsumer implements Runnable{
    private BierStack stack;
    private int noIterations;

    public BierConsumer(BierStack stack, int noIterations) {
        this.stack = stack;
        this.noIterations = noIterations;
    }

    @Override
    public void run() {
        for(int i = 0; i < noIterations; i++){
            synchronized (stack){
                while (stack.isEmpty()){
                    System.out.println("Biertrinker - Stack is empty");
                    try {
                        stack.wait(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Biertrinker macht weiter");
                }

                int value = stack.pop();
                stack.notify();
                System.out.println("Biertrinker genießt Bier - " +stack);
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

package LetzteWildeSession.ConsumerProducer;

public class Launcher {
    public static void main(String[] args) {
        Stack stack = new Stack(5);
        Thread t1 = new Thread(new Consumer(stack, 10));
        Thread t2 = new Thread(new Producer(stack, 10));

        t1.start();
        t2.start();
    }
}

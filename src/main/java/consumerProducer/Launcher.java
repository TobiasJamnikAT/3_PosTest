package consumerProducer;

public class Launcher {
    public static void main(String[] args) {
        BierStack bierli = new BierStack(5);
        int noIterations = 10;

        BierConsumer bierliGenieser = new BierConsumer(bierli, noIterations);
        BierProducer bierliMacher = new BierProducer(bierli, noIterations);

        Thread genieser = new Thread(bierliGenieser);
        Thread macher = new Thread(bierliMacher);


        genieser.start();
        macher.start();
    }
}

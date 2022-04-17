package Bsp_403_Konto;

public class Launcher {
    public static void main(String[] args) {
        Konto konto = new Konto(50, "AT19480567234");

        User user1 = new User(konto, "Tobias", 10);
        User user2 = new User(konto, "Simon", 10);
        User user3 = new User(konto, "Franziska", 10);
        User user4 = new User(konto, "Günter", 10);

       Thread thread1 = new Thread(user1);
       Thread thread2 = new Thread(user2);
       Thread thread3 = new Thread(user3);
       Thread thread4 = new Thread(user4);

       thread1.start();
       thread2.start();
       thread3.start();
       thread4.start();

        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("################################################################");
        System.out.println("Balance of " +konto.getIban() + " = " +konto.getBalance());
    }
}

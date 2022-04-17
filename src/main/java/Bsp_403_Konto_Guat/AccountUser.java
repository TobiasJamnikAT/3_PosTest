package Bsp_403_Konto_Guat;

import java.util.Random;

public class AccountUser implements Runnable{
    private BankAccount account;
    private String name;
    private int noIterations;

    public AccountUser(BankAccount account, String name, int noIterations) {
        this.account = account;
        this.name = name;
        this.noIterations = noIterations;
    }

    @Override
    public void run() {
        Random random = new Random();
        for (int i = 0; i < noIterations; i++){
            synchronized (account){
                int operation = random.nextInt(2);

                if(operation == 0){
                    //Abheben
                    double value = random.nextInt(500) * -1;
                    boolean trasnferable = account.transfer(value);

                    if(trasnferable){
                        System.out.println(name + ": retreved " + value + " to " +account.getIBAN());
                        account.notify();
                    } else{
                        System.out.println(name + ": could not retreve " + value + " to " +account.getIBAN());
                        try {
                            account.wait(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                } else{
                    //Einzahlen
                    double value = random.nextInt(500);
                    boolean trasnferable = account.transfer(value);

                    if(trasnferable){
                        System.out.println(name + ": transfered " + value + " to " +account.getIBAN());
                        account.notify();
                    } else {
                        System.out.println(name + ": could not transfer " + value + " to " +account.getIBAN());
                    }
                }

                System.out.println(account.getIBAN() + " - Balance:" +account.getBalance());
            }
        }
    }
}

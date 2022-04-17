package Bsp_403_Konto;

import java.util.Random;

public class User implements Runnable{
    private Konto konto;
    private String name;
    private int noIterations;

    public User(Konto konto, String name, int noIterations) {
        this.konto = konto;
        this.name = name;
        this.noIterations = noIterations;
    }

    @Override
    public void run() {
        Random randy = new Random();

        for(int i = 0; i < noIterations; i++){
            synchronized (konto){
                int operation = randy.nextInt(2);
                if(operation == 1){
                    userGetMoney(konto, randy.nextInt(500));
                } else {
                    userAddtMoney(konto, randy.nextInt(500));
                }
            }
        }
    }

    public void userAddtMoney(Konto account, double value){
        account.addMoney(value);
        konto.notify();
        System.out.println(name + " added " +value + " to account " +account.getIban());
    }

    public boolean userGetMoney(Konto account, double value){
        boolean possible = account.retrieveMoney(value);

        if(possible == true){
            konto.notify();
            System.out.println(name + " retrieved " +value + " from account " +account.getIban());
            return true;
        } else{
            System.out.println("Not enough balance to retrieve " +value + " - available amount = " +account.getBalance());
            try {
                account.wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return false;
        }
    }
}

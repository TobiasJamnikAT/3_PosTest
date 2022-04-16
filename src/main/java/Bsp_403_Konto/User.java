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
                    
                }
            }
        }
    }
}

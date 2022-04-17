package Bsp_403_Konto_Guat;

import euler39_new.Triangle;
import euler39_new.TriangleWorker;

import java.util.Random;
import java.util.Set;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Launcher {
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount("AT19 3805 6000 0310 0815", 50);

        ExecutorService pool = Executors.newFixedThreadPool(12);

        for(int i = 0; i <= 10; i++){
            pool.submit(new Thread(new AccountUser(bankAccount, "user" +i, 5)));
        }

        pool.shutdown();
    }
}

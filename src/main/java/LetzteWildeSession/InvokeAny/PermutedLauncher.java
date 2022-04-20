package LetzteWildeSession.InvokeAny;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PermutedLauncher {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(12);
        List<Callable<Integer>> workerList = new LinkedList<>();

        int increment = 10000;

        for(int i = 0; i <= 100; i++){
            workerList.add(new PermutedWorker(increment * i + 1, (i + 1) * increment));
        }

        int res = pool.invokeAny(workerList);
        System.out.println("Smallest int:" +res);
        pool.shutdown();
    }
}

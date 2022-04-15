package euler52;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class NumberLauncher {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(12);
        int increment = 10000;
        List<Callable<Integer>> workerList = new ArrayList<>();

        for (int i = 0; i < 100; i++){
            workerList.add(new NumberWorker(increment * i + 1, (i + 1) * increment));
        }
        try {
            Integer result = pool.invokeAny(workerList);
            System.out.println("Result - " +result);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        pool.shutdown();
    }
}
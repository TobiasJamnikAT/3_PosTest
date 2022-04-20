package LetzteWildeSession.CompletionService;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class NameLauncher {
    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        NameReader reader = new NameReader("src/main/java/LetzteWildeSession/CompletionService/p022_names.txt");
        List<String> names = reader.readNames();

        ExecutorService pool = Executors.newFixedThreadPool(12);
        CompletionService<Integer> workerList = new ExecutorCompletionService<>(pool);
        Collections.sort(names);

        for(int i = 0; i < names.size(); i++){
            workerList.submit(new NameWorker(names.get(i), i));
        }
        pool.shutdown();

        int res = 0;
        for(int i = 0; i < names.size(); i++){
            res += workerList.take().get();
        }

        System.out.println("Final result = " +res);
    }
}

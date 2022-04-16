package euler59;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Launcher {
    public static void main(String[] arg) throws IOException {
        Path location =  Path.of("C:\\Users\\Tobias Jamnik\\IdeaProjects\\3_PosTest\\src\\main\\java\\euler59\\p059_cipher.txt");
        int[] values = Arrays.stream(Files.readString(location).split(",")).mapToInt(Integer::parseInt).toArray();

        ExecutorService pool =  Executors.newFixedThreadPool(12);
        List<Callable<String>> workerList = new ArrayList<>();
        for(int i = 97; i < 123; i++){
            Worker woker = new Worker(i);
            woker.setValues(values);
            workerList.add(woker);
        }

        try {
            String result = pool.invokeAny(workerList);
            System.out.println("Result: " +result);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        pool.shutdown();
    }
}

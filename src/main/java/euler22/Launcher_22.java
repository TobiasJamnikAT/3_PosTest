package euler22;

import java.text.Collator;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class Launcher_22 {
    public static void main(String[] args) throws Exception {
        LocalTime startTime = LocalTime.now();

        Reader_22 reader_22 = new Reader_22("C:\\Users\\Tobias Jamnik\\IdeaProjects\\3_PosTest\\src\\main\\java\\euler22\\p022_names.txt");
        List<String> unsortedNames = reader_22.readPersons();
        List<String> personList = unsortedNames.stream().sorted().collect(Collectors.toList());

        ExecutorService pool = Executors.newFixedThreadPool(personList.size());
        CompletionService<Integer> service = new ExecutorCompletionService<>(pool);

        for(int i = 0; i < personList.size(); i++){
            service.submit(new Worker_22(i + 1, personList.get(i)));//weil *0 unguat
        }
        pool.shutdown();

        int entireValue = 0;

        for (int i = 0; i < personList.size(); i++){
            int result = service.take().get();
            entireValue = entireValue + result;
        }

        LocalTime endTime = LocalTime.now();
        System.out.println("Final value = " + entireValue);
        System.out.println("Time in mili :" +endTime.getSecond() * startTime.getSecond());
    }
}

package euler52;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

public class NumberWorker implements Callable<Integer> {
    private int start;
    private int range;

    public NumberWorker(int start, int range) {
        this.start = start;
        this.range = range;
    }

    @Override
    public Integer call() throws Exception {
        for(int i = start; i <= start + range; i++){
            Set<String> sortedStrings = new HashSet<>();
            for(int j = 2; j <= 6; j++){
                int number = i * j;
                sortedStrings.add(intoSortedString(number));
            }

            if(sortedStrings.size() == 1){
                return i;
            }
        }

        throw new Exception("No res found");
    }

    public String intoSortedString(int value){
        return Integer.toString(value).chars().map(v -> Integer.valueOf(v - '0')).sorted().mapToObj(i -> Integer.toString(i)).collect(Collectors.joining(""));
    }
}

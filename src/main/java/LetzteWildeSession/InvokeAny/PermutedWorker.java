package LetzteWildeSession.InvokeAny;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

public class PermutedWorker implements Callable<Integer> {
    private int startNum;
    private int endNum;

    public PermutedWorker(int startNum, int increment) {
        this.startNum = startNum;
        this.endNum = startNum + increment;
    }

    @Override
    public Integer call() throws Exception {
        for(int i = startNum; i < endNum; i++){
            Set<int[]> numbers = new HashSet<>();
            int[] digits = Integer.toString(i).chars().toArray();
            Arrays.sort(digits);
            numbers.add(digits);

            for(int j = 2; j <= 6; j++){
                digits = Integer.toString(i * j).chars().toArray();
                Arrays.sort(digits);
                numbers.add(digits);
            }

            if(numbers.size() == 6){
                return i;
            }
        }

        throw new RuntimeException();
    }
}

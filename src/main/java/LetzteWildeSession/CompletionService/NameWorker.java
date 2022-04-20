package LetzteWildeSession.CompletionService;

import java.util.Locale;
import java.util.concurrent.Callable;

public class NameWorker implements Callable<Integer> {
    private String name;
    private int position;

    public NameWorker(String name, int position) {
        this.name = name.toUpperCase(Locale.ROOT);
        this.position = position;
    }

    @Override
    public Integer call() throws Exception {
        int numericalValue = 0;

        int[] values = name.chars().toArray();
        for(int i = 0; i < values.length; i++){
            numericalValue += values[i] - 64;
        }

        return numericalValue * position;
    }
}

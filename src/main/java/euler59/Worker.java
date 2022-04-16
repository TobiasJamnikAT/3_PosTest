package euler59;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Worker implements Callable<String> {
    private int startChar;
    private int[] values;
    private int[] key;
    private Set<String> commonEnglishWords;

    public void setValues(int[] values) {
        this.values = values;
    }

    public Worker(int startChar) {
        this.startChar = startChar;
        this.key = new int[]{startChar, 'a', 'a'};
        commonEnglishWords = new HashSet<>();
        this.commonEnglishWords.add("there");
        this.commonEnglishWords.add("at");
        this.commonEnglishWords.add("each");
        this.commonEnglishWords.add("make");
        this.commonEnglishWords.add("how");
        this.commonEnglishWords.add("look");
        this.commonEnglishWords.add("will");
        this.commonEnglishWords.add("was");
        this.commonEnglishWords.add("that");
        this.commonEnglishWords.add("find");
        this.commonEnglishWords.add("word");
    }


    @Override
    public String call() throws Exception {
        ArrayList<Integer> xorValues = new ArrayList<>();

        while(true){
            for(int value : values){
                for(int i = 0; i < 3; i++){
                    //xorValues.add(value^key[i % 3]);
                    xorValues.add(value^key[i]);
                }
            }

            String isEnglishText = null;
            isEnglishText = checkXorValues(xorValues);

            if(isEnglishText != null){
                return isEnglishText;
            }

            key = getNextKey(key);
        }
    }

    private String checkXorValues(ArrayList<Integer> xorValues) throws Exception {
        String text = xorValues.stream().map(value -> {
            return Character.toString((char) value.intValue());
        }).collect(Collectors.joining());

        int foundWords = 0;

        for (String word : commonEnglishWords){
            if(text.contains(word)){
                foundWords++;
            }
        }

        if(foundWords > 6){
            return text;
        } else {
            return null;
        }
    }

    public int[] getNextKey(int[] key) throws Exception {
        if (key[2] == 122) {
            if (key[1] == 122) {
                throw new RuntimeException();
            }
            key[2] = 97;
            key[1] = key[1] + 1;
            return key;
        }
        key[2]++;
        return key;
    }
}

package euler59_new;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

public class Worker implements Callable<String> {
    private int startChar;
    private int[] values;
    private int[] key;

    public Worker(int startChar) {
        this.startChar = startChar;
        this.key = new int[]{startChar, 'a', 'a'};
    }

    public void setValues(int []values) {
        this.values = values;
    }

    @Override
    public String call() throws Exception {
        ArrayList<Integer> xorValues = new ArrayList<>();

        while(true){
            for(int i = 0; i < values.length; i = i + 3){
                //value^key[i]
                xorValues.add(values[i]^key[0]);
                xorValues.add(values[i+1]^key[1]);
                xorValues.add(values[i+2]^key[2]);
            }

            String text = xorValues.stream().map(value -> {
                return Character.toString((char) value.intValue());
            }).collect(Collectors.joining());

            String englishText = isEnglishText(text);
            if(englishText != null){
                return englishText;
            }

            key = generateNextKey(key);
            xorValues.clear();
        }
    }

    public String isEnglishText(String text){
        Set<String> commonEnglishWords = new HashSet<>();
        commonEnglishWords.add("there");
        commonEnglishWords.add("at");
        commonEnglishWords.add("each");
        commonEnglishWords.add("make");
        commonEnglishWords.add("how");
        commonEnglishWords.add("look");
        commonEnglishWords.add("will");
        commonEnglishWords.add("was");
        commonEnglishWords.add("that");
        commonEnglishWords.add("find");
        commonEnglishWords.add("word");

        int wordCount = 0;

        for(String word : commonEnglishWords){
            if(text.contains(word)){
                wordCount++;
            }
        }

        if(wordCount > 5){
            return text;
        } else{
            return null;
        }
    }

    public int[] generateNextKey(int[] key) throws RuntimeException {
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

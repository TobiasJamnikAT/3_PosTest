package LetzteWildeSession.CompletionService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

public class NameReader {
    private Path path;

    public NameReader(String path) {
        this.path = Path.of(path);
    }

    public List<String> readNames() throws IOException {
        String line = Files.readString(path);
        String[] parts = line.split("\",\"");

        LinkedList<String> list = new LinkedList<>();
        for(int i = 0; i < parts.length; i++){
            if(i == 0){
                StringBuffer name = new StringBuffer(parts[i]);
                name.deleteCharAt(0);
                list.add(name.toString());
            } else{
                list.add(parts[i]);
            }
        }

        return list;
    }


}

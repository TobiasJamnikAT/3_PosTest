package euler22;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Reader_22 {
    private String path;

    public Reader_22(String path) {
        this.path = path;
    }

    public List<String> readPersons() throws Exception {
        Path path = Path.of(this.path);
        try {
            String text = Files.readString(path);
            String[] strings = text.split(",");

            for(int i = 0; i < strings.length; i++){
                StringBuffer buffer = new StringBuffer(strings[i]);
                buffer.deleteCharAt(buffer.length() - 1);
                buffer.deleteCharAt(0);
                strings[i] = buffer.toString();
            }

            return List.of(strings);
        } catch (IOException e) {
            e.printStackTrace();
        }

        throw new Exception();
    }
}

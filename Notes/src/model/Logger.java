package model;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Logger {
    public static void logger(String string) {
        List<String> list = new ArrayList<>();
        LocalDateTime ldt = LocalDateTime.now();
        list.add(ldt + " " + string);
        saveLog(list);
    }

    static void saveLog(List<String> strings) {
        try (FileWriter writer = new FileWriter("Log.txt", true)) {
            for (String string : strings) {
                writer.write(string);
                writer.append('\n');
            }
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
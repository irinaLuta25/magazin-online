package file;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    public static void writeLines(String filename, List<String> lines) {
        try(PrintWriter printWriter = new PrintWriter(filename)) {
            for(String line : lines) {
                printWriter.println(line);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static List<String> readLines(String filename) {
        List<String> lines = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while((line = reader.readLine())!=null) {
                lines.add(line);
            }

        }  catch(Exception e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static <T> void writeObject(String filename, T obj) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(obj);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static <T> T readObject(String filename, Class<T> clazz) {
        File file = new File(filename);
        if (!file.exists()) {
            if (clazz == List.class) {
                return clazz.cast(new ArrayList<>());
            }
            return null;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return clazz.cast(ois.readObject());
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

package task.core.repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DBConnector {
    private final String fileName;

    public DBConnector(String fileName) {
        this.fileName = fileName;
        connectDB();
    }
    public void connectDB() {
        try {
            File db = new File(fileName);
            if (db.createNewFile()) {
                System.out.println("New DB created");
            }
            else {
                System.out.println("DB already exists");
            }
        }
        catch (Exception e) {
            System.err.println(e);
        }
    }
    @SuppressWarnings("UnusedReturnValue")
    public boolean add(String userRecord){
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(userRecord + '\n');
            writer.flush();
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    public List<String> readAll() {
        List<String> lines = new ArrayList<>();
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            if (line != null) {
                lines.add(line);
            }
            while (line != null) {
                line = reader.readLine();
                if (line != null) {
                    lines.add(line);
                }
            }
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}

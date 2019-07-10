package main;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class MyWriter {
    private static final String FILE_NAME = "results.csv";
    private static final String NEW_LINE = "\n";
    private static final String INVERTED_COMMA = "\"";

    public static <T> void writeCSV(List<T> allElements) {
        FileWriter csvWriter;
        try {
            csvWriter = new FileWriter(FILE_NAME);

            for (T item : allElements) {
                csvWriter.append(INVERTED_COMMA).append(item.toString()).append(INVERTED_COMMA);
                csvWriter.append(NEW_LINE);

            }
            csvWriter.flush();
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

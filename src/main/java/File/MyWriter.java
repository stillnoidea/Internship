package File;


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MyWriter {

    private static final String FILE_NAME = "validity_states.csv";
    private static final String SPLITTER = ",";
    private static final String NEW_LINE = "\n";
    private static final String INVERTED_COMMA = "\"";

    public void write(ArrayList<Pass> list) {
        FileWriter csvWriter;

        try {
            csvWriter = new FileWriter(FILE_NAME);

            for (Pass item : list) {
                csvWriter.append(INVERTED_COMMA).append(item.getLogin()).append(INVERTED_COMMA);
                csvWriter.append(SPLITTER);
                csvWriter.append(INVERTED_COMMA+"="+INVERTED_COMMA);
                csvWriter.append(INVERTED_COMMA).append(item.getPasswordLastChars()).append(INVERTED_COMMA+INVERTED_COMMA+INVERTED_COMMA);
                csvWriter.append(SPLITTER);
                csvWriter.append(INVERTED_COMMA).append(item.getValidityStatus()).append(INVERTED_COMMA);
                if (item.getActivationDate() != null) {
                    csvWriter.append(SPLITTER);
                    csvWriter.append(INVERTED_COMMA).append(item.getActivationDate().toString()).append(INVERTED_COMMA);
                }
                csvWriter.append(NEW_LINE);

            }

            csvWriter.flush();
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}


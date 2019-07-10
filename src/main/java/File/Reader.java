package File;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Reader {
    private static final String INVALID_PASSPORT_NO = "Inavalid Passpport no., at pass number: ";
    private static final String FILE_NAME = "rozszerzona.txt";
    private static final String SPLITTER = "\"";

    private ArrayList<Pass> passes = new ArrayList<>();
    private Scanner scanner = null;

    public Reader() {
        File file = new File(
                Objects.requireNonNull(getClass().getClassLoader().getResource(FILE_NAME)).getFile()
        );
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void readTxt() {
        ArrayList<String> lines = new ArrayList<>();
        while (scanner.hasNext()) {
            lines.add(scanner.nextLine());
        }
        parseToPasses(lines);
    }

    private void parseToPasses(ArrayList<String> lines) {
        for (String line : lines) {
            String[] result = line.split(SPLITTER);

            try {
                if (result.length == 4) {
                    passes.add(new Pass(result[1], result[3]));
                } else {
                    throw new InvalidPassData(INVALID_PASSPORT_NO + result[1]);
                }
            } catch (InvalidPassData invalidPassData) {
                invalidPassData.printStackTrace();
            }
        }
    }

    public Pass getPass(int index) {
        return passes.get(index);
    }

    public ArrayList<Pass> getPasses() {
        return passes;
    }
}

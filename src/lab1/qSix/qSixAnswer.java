package lab1.qSix;

import java.io.*;
import java.util.regex.*;

public class qSixAnswer {
    public static void main(String[] args) throws IOException {
        // hard-coded paths
        String filePath_izvor = "src/lab1/qSix/izvor.txt";
        String filePath_destinacija = "src/lab1/qSix/destinacija.txt";

        filterInput(filePath_izvor, filePath_destinacija);
    }
    public static void filterInput(String filePath_izvor, String filePath_destinacija) throws IOException {
        BufferedReader reader_stdInput = null;
        BufferedWriter writer_destinacija = null;
        BufferedWriter writer_izvor = null;

        try {
            reader_stdInput = new BufferedReader(new InputStreamReader(System.in));
            writer_izvor = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath_izvor)));
            writer_destinacija = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath_destinacija)));
            String line = null;

            // ^ <- Matches the beginning of the line.
            // [...] <- Matches any single character in brackets.
            Pattern r = Pattern.compile("^[0-9]");
            Matcher m = null;

            while((line = reader_stdInput.readLine()) != null) {

                // for testing purposes
                if(line.equalsIgnoreCase("END"))
                    break;

                writer_izvor.write(line);
                writer_izvor.newLine();

                m = r.matcher(line);
                if(m.find()) {
                    writer_destinacija.write(line);
                    writer_destinacija.newLine();
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(reader_stdInput != null)
                reader_stdInput.close();
            if(writer_izvor != null) {
                writer_izvor.flush();
                writer_izvor.close();
            }
            if(writer_destinacija != null) {
                writer_destinacija.flush();
                writer_destinacija.close();
            }
        }

    }
}

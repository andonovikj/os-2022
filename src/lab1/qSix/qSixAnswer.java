package lab1.qSix;

import java.io.*;
import java.util.regex.*;

public class qSixAnswer {
    public static void main(String[] args) throws IOException {

        File izvor_File = new File("src/lab1/qSix/izvor.txt");
        File destinacija_File = new File("src/lab1/qSix/destinacija.txt");

        stdInputToFile(izvor_File.getAbsolutePath());
        filterInput(izvor_File.getAbsolutePath(),destinacija_File.getAbsolutePath());

    }

    public static void stdInputToFile(String filePath_izvor) throws IOException {
        BufferedReader reader_stdInput = null;
        BufferedWriter writer_izvor = null;

        try {
            reader_stdInput = new BufferedReader(new InputStreamReader(System.in));
            writer_izvor = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath_izvor)));
            String line = null;

            while((line = reader_stdInput.readLine()) != null) {

                writer_izvor.write(line);
                writer_izvor.newLine();

                // for testing purposes
                if (line.equalsIgnoreCase("END"))
                    break;

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader_stdInput != null)
                reader_stdInput.close();
            if (writer_izvor != null) {
                writer_izvor.flush();
                writer_izvor.close();
            }
        }

    }

    public static void filterInput(String filePath_izvor, String filePath_destinacija) throws IOException {
        BufferedReader reader_izvor = null;
        BufferedWriter writer_destinacija = null;

        try {
            reader_izvor = new BufferedReader(new FileReader(filePath_izvor));
            writer_destinacija = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath_destinacija)));
            String line = null;

            // ^ <- Matches the beginning of the line.
            // [...] <- Matches any single character in brackets.
            Pattern r = Pattern.compile("^[0-9]");
            Matcher m = null;

            while((line = reader_izvor.readLine()) != null) {

                // for testing purposes
                if(line.equalsIgnoreCase("END"))
                    break;

                m = r.matcher(line);
                if(m.find()) {
                    writer_destinacija.write(line);
                    writer_destinacija.newLine();
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(reader_izvor != null)
                reader_izvor.close();
            if(writer_destinacija != null) {
                writer_destinacija.flush();
                writer_destinacija.close();
            }
        }

    }
}

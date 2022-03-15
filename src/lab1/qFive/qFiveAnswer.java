package lab1.qFive;

import java.io.*;

public class qFiveAnswer {
    public static void main(String[] args) throws IOException {
        readFromStdInput("FolderTest\\in.txt");
    }
    public static void readFromStdInput(String outputFilePath) throws IOException {
        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            reader = new BufferedReader(new InputStreamReader(System.in));
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFilePath)));
            String line = null;

            while((line = reader.readLine()) != null) {

                // for testing purposes
                if(line.equalsIgnoreCase("END"))
                    break;

                writer.write(line);
                writer.newLine();

            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(reader != null)
                reader.close();
            if(writer != null) {
                writer.flush();
                writer.close();
            }
        }

    }
}

package mk.ukim.finki.os.streams;

import java.io.*;

public class IOStreamManagerImplementation implements IOStreamManager {

    // tested - working as documented
    @Override
    public void copyFileByteByByte(File from, File to) throws IOException {

        // input stream reads byte by byte
        // output stream write byte by byte

        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            inputStream = new FileInputStream(from);
            outputStream = new FileOutputStream(to);
            int c = -1;
            while ((c = inputStream.read()) != -1) {
                outputStream.write(c);
                outputStream.flush();
            }
        } catch (IOException exception) {
            System.out.println("IOException thrown - copyFromByteByByte");
        } finally {
            // Finally is always executed, even when an exception is thrown.
            if (inputStream != null) inputStream.close();
            if (outputStream != null) outputStream.close();
        }

    }

    // tested - working as documented
    @Override
    public void printContentOfTxtFile(File f, PrintStream printer) throws IOException {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(f));
            String line = null;
            while ((line = reader.readLine()) != null) {
                printer.println(line);
                printer.flush();
            }
        } catch (IOException exception) {
            System.out.print("IOException thrown - printContentOfTxtFile");
        } finally {
            if (reader != null)
                reader.close();
        }
    }

    // tested - working as documented
    @Override
    public void readContentFromStdInput(OutputStream to) throws IOException {
        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            reader = new BufferedReader(new InputStreamReader(System.in));
            writer = new BufferedWriter(new OutputStreamWriter(to));
            String line;

            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
                writer.flush();
            }

        } catch (IOException exception) {
            System.out.print("IOException thrown - readContentFromStdInput");
        } finally {
            if (reader != null)
                reader.close();
            if (writer != null)
                writer.close();
        }
    }

    // tested - working as documented
    @Override
    public void writeToTextFile(File to, String text, Boolean append) throws IOException {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(to, append));
            writer.write(text);
            writer.close();
        } catch (IOException exception) {
            System.out.print("IOException thrown - writeToTextFile");
        } finally {
            if (writer != null)
                writer.close();
        }
    }

    // not tested
    @Override
    public void memoryUnsafeTextFileCopy(File from, File to) throws IOException {
        BufferedReader reader = null;
        BufferedWriter writer = null;
        StringBuilder builder = new StringBuilder();

        // if the content of reader is larger than the maximum allowed size for the builder the excess will not be included.
        try {
            reader = new BufferedReader(new FileReader(from));
            writer = new BufferedWriter(new FileWriter(to));
            String line = null;

            while ((line = reader.readLine()) != null)
                builder.append(line).append(" ");

            writer.write(builder.toString());

        } catch (IOException exception) {
            System.out.print("IOException thrown - memoryUnsafeTextFileCopy");
        } finally {
            if (reader != null)
                reader.close();
            if (writer != null) {
                writer.flush();
                writer.close();
            }
        }
    }

    // not tested
    @Override
    public void memorySafeTextFileCopy(File from, File to) throws IOException {
        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            reader = new BufferedReader(new FileReader(from));
            writer = new BufferedWriter(new FileWriter(to));
            String line = null;

            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.flush();
            }

        } catch (IOException exception) {
            System.out.print("IOException thrown - memorySafeTextFileCopy");
        } finally {
            if (reader != null)
                reader.close();
            if (writer != null) {
                writer.flush();
                writer.close();
            }
        }
    }

    // tested - working as documented
    @Override
    public void readFileWithLineNumber(File from, OutputStream is) throws IOException {
        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            reader = new BufferedReader(new FileReader(from));
            writer = new BufferedWriter(new OutputStreamWriter(is));
            int lineCount = 1;
            String line = null;

            while ((line = reader.readLine()) != null) {
                writer.write(lineCount++ + ":" + line + "\n");
            }

        } catch (IOException exception) {
            System.out.println("IOException thrown - readFileWithLineNumber");
        } finally {
            if (reader != null)
                reader.close();
            if (writer != null) {
                writer.flush();
                writer.close();
            }
        }

    }

    // tested - working as documented
    // ... <- one or more arguments of the same type
    @Override
    public void writeBinaryDataToBFile(File to, Object... objects) throws IOException {
        DataOutputStream dataOutputStream = null;

        try {
            dataOutputStream = new DataOutputStream(new FileOutputStream(to));

            for (Object o : objects) {
                if (o instanceof String) dataOutputStream.writeUTF((String) o);
                else if (o instanceof Integer) dataOutputStream.writeInt((Integer) o);
                else if (o instanceof Double) dataOutputStream.writeDouble((Double) o);
            }

        } catch (IOException exception) {
            System.out.println("IOException thrown - writeBinaryDataToBFile");
        } finally {
            if (dataOutputStream != null) {
                dataOutputStream.flush();
                dataOutputStream.close();
            }
        }
    }

    // tested - working
    @Override
    public void readBinaryDataFromBFile(File from, Object... objects) throws IOException {
        DataInputStream dataInputStream = null;
        try {
            dataInputStream = new DataInputStream(new FileInputStream(from));

            for (Object o : objects) {
                if (o instanceof String) o = dataInputStream.readUTF();
                else if (o instanceof Integer) o = dataInputStream.readInt();
                else if (o instanceof Double) o = dataInputStream.readDouble();
                System.out.println(o);
            }

        } catch (IOException exception) {
            System.out.println("IOException thrown - readBinaryDataFromBFile");
        } finally {
            if (dataInputStream != null)
                dataInputStream.close();
        }
    }

    @Override
    public void writeToRandomAccessFile(File from) throws IOException {
        RandomAccessFile randomAccessFile = null;
        try {
            randomAccessFile = new RandomAccessFile(from, "rw");
            for (int i = 0; i < 10; i++)
                randomAccessFile.writeDouble(i*1.4);
            randomAccessFile.writeUTF("THIS IS THE END");
        } catch (IOException exception) {
            System.out.println("IOException thrown - writeToRandomAccessFile");
        } finally {
            if(randomAccessFile!=null)
                randomAccessFile.close();
        }
    }

    @Override
    public void readFromRandomAccessFile(File from, PrintStream out) throws IOException {
        RandomAccessFile randomAccessFile = null;
        try {
            randomAccessFile = new RandomAccessFile(from, "r");
            for (int i = 0; i < 10; i++)
                System.out.println(randomAccessFile.readDouble());
            System.out.println(randomAccessFile.readUTF());
        } catch (IOException exception) {
            System.out.println("IOException thrown - readFromRandomAccessFile");
        } finally {
            if(randomAccessFile!=null)
                randomAccessFile.close();
        }
    }
}

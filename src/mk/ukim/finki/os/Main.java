package mk.ukim.finki.os;

import mk.ukim.finki.os.streams.IOStreamManagerImplementation;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        IOStreamManagerImplementation streamManager = new IOStreamManagerImplementation();

        File inputFile = new File("in.txt");
        File outputFile = new File("out.txt");

        streamManager.writeToRandomAccessFile(inputFile);
        streamManager.readFromRandomAccessFile(inputFile,System.out);

    }
}

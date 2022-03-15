package lab1.qThree;

import java.io.File;
import java.io.FileNotFoundException;

public class docFilter {

    // for testing purpose
    public static final String filepath = "FolderTest";

    public static void main(String[] args) throws FileNotFoundException {
        printFilteredAbsolutePaths(filepath);
    }

    public static void printFilteredAbsolutePaths(String filepath) throws FileNotFoundException {

        File file = new File(filepath);

        if(!file.isDirectory())
            throw new FileNotFoundException();

        File[] files = file.listFiles();

        if(files!=null) {
            for (File f : files) {

                // enter a subdirectory
                if (f.isDirectory())
                    printFilteredAbsolutePaths(f.getAbsolutePath());

                // (1KB == 1000B) => 50000 == 50KB
                if (f.length() > 50000)
                    System.out.println(f.getAbsolutePath());
            }
        } else {
            System.out.println("Error on line 24: abstract pathname does not denote a directory, or an I/O error occurred");
            System.exit(1);
        }

    }
}

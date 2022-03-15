package lab1.qTwo;

import java.io.File;
import java.io.FileNotFoundException;

public class Lab {
    // public static final String filepath = ".";
    public static final String filepath = "FolderTest";
    public static void main (String[] args) throws FileNotFoundException {
        File f = new File(filepath);

        if(!f.isDirectory())
            throw new FileNotFoundException();

        File[] files = f.listFiles();
        for(File file: files) {
            if(!file.isDirectory())
                continue;
            if(file.exists() && file.getName().contains("finki"))
                System.out.println(file.getAbsolutePath());
        }

    }
}

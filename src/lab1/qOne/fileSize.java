package lab1.qOne;

import java.io.File;
import java.io.FileNotFoundException;

public class fileSize {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(getFileSize("FolderTest\\in.txt"));
    }

    public static long getFileSize(String filepath) throws FileNotFoundException {
        File f = new File(filepath);
        if(!f.exists())
            throw new FileNotFoundException();
        if(!f.isFile())
            throw new FileNotFoundException();
        return f.length();
    }

}

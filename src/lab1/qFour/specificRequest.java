package lab1.qFour;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;

public class specificRequest {

    public static long seven_days_millisecond = 604800000;
    public static long currentTime = new Date().getTime();

    public static void main(String[] args) throws FileNotFoundException {

        String filePath = args[0];
        File parentDirectory = new File(filePath);

        if(!parentDirectory.isDirectory())
            throw new FileNotFoundException();

        filterAndPrint(parentDirectory);

    }

    public static void filterAndPrint (File parentDirectory) {
        File[] files = parentDirectory.listFiles();
        if(files != null) {
            for(File f: files) {
                if(f.isDirectory())
                    filterAndPrint(f);
                else if(f.getName().endsWith(".jpg") || f.getName().endsWith(".bmp")) {
                    if(currentTime - f.lastModified() <= seven_days_millisecond)
                        System.out.println(f.getAbsolutePath());
                }
            }
        } else {
            System.out.println("Error on line 25: abstract pathname does not denote a directory, or an I/O error occurred");
            System.exit(1);
        }
    }
}

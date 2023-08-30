import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;


public class Code_Project_1 {

    public Boolean creationCompare(FileTime a, FileTime b) {
        String x = a.toString();
        String y = b.toString();
        String a1 = x.substring(0, 10);
        String b1 = y.substring(0, 10);
        if (a1.equals(b1)) {
            return true;
        }
        return false;
    }//end creationCompare method


    public ArrayList<File> getAllFiles(File folder) {
        ArrayList<File> fileList = new ArrayList<>();

        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    // If it's a file, add it to the list
                    fileList.add(file);
                } else if (file.isDirectory()) {
                    // If it's a directory, recursively call the method and append the result
                    ArrayList<File> subDirectoryFiles = getAllFiles(file);
                    fileList.addAll(subDirectoryFiles);
                }
            }
        }

        return fileList;
    }

    public static void main(String[] args) throws IOException {

        String x = args[0];//desired file name
        String folder = args[1];//folder path name

        File f = new File(folder);

        Code_Project_1 obj = new Code_Project_1();

        ArrayList folder_files = obj.getAllFiles(f);

        Path file_p = Paths.get(x);

        ArrayList<String> files_same = new ArrayList<String>();

        BasicFileAttributes attr1 = Files.readAttributes(file_p, BasicFileAttributes.class);

        FileTime F1 = attr1.creationTime();

        for (Object fff : folder_files) {

            String i = fff.toString();
            Path i_p = Paths.get(i);
            BasicFileAttributes attr2 = Files.readAttributes(i_p, BasicFileAttributes.class);
            FileTime F2 = attr2.creationTime();
            if (obj.creationCompare(F1, F2)) {
                files_same.add(i);
            }
        }

        for (String ss : files_same) {
            System.out.println(ss);
        }
    }
}

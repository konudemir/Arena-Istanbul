import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FolderFileLineCounter {
    public static int total = 0;

    public static void main(String[] args) {
        File root = new File("."); // replace with your root path
        listAllFoldersAndFiles(root);
        System.out.println(total);
    }

    public static void listAllFoldersAndFiles(File dir) {
        if (!dir.exists() || !dir.isDirectory()) {
            System.out.println("Invalid folder path.");
            return;
        }

        File[] contents = dir.listFiles();
        if (contents == null) return;

        for (File file : contents) {
            if (file.isDirectory()) {
                System.out.println("Folder: " + file.getAbsolutePath());
                listAllFoldersAndFiles(file); // recurse into subfolder
            } else if (file.isFile()) {
                System.out.println("File: " + file.getAbsolutePath());
                int lines = countLinesInFile(file);
                System.out.println(" → Line count: " + lines);
                total += lines;
            }
        }
    }

    public static int countLinesInFile(File file) {
        int lines = 0;
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                scanner.nextLine();
                lines++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Could not read: " + file.getAbsolutePath());
        }
        return lines;
    }
}

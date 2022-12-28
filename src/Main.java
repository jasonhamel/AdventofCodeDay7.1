import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Directory root = new Directory("root");
        try {
            getInput(root);
        } catch (Exception e){
            System.out.println(e);
        }

    }

    public static void getInput(Directory root) throws FileNotFoundException {
        FileInputStream fis = new FileInputStream("test.txt");
        Scanner scan = new Scanner(fis);
        scan.nextLine();
        Directory workingDirectory = root;
        while (scan.hasNextLine()) {
            String line = scan.nextLine();

            if (line.substring(2, 4).equals("cd")) {
                if (line.contains("..")) {
                    continue;
                }
                workingDirectory = workingDirectory.stepIntoDirectory(line.substring(5));
            }

            if (!(line.contains("$"))) {
                if (line.substring(0, 3).equals("dir")) {
                    Directory directory = new Directory(line.substring(4));
                    workingDirectory.putInDirectory(directory);
                    System.out.println(directory);
                }
            }
        }
        System.out.println(root);
        scan.close();
    }

}
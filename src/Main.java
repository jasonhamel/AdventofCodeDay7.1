import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Directory root = new Directory("root");
        try {
            getInput(root);
            //pass in root - loop through root - for each directory add total size - return value of all that are less than 100k
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
                    workingDirectory = workingDirectory.getParent();
                    continue;
                }
                workingDirectory = workingDirectory.stepIntoDirectory(line.substring(5));
            }
            if (!(line.contains("$"))) {
                if (line.substring(0, 3).equals("dir")) {
                    Directory directory = new Directory(line.substring(4), workingDirectory);
                    workingDirectory.putInDirectory(directory);
                } else {
                    String[] split = line.split(" ");
                    File file = new File(split[1], Integer.parseInt(split[0]));
                    workingDirectory.putInDirectory(file);
                }
            }

        }
        treePrint(root, 0);
        scan.close();
    }

    public static void treePrint(Directory node, int tabs) {
        for (int i = 0; i < tabs; i++) {
            System.out.print("  ");
        }
        System.out.println(node.getName() + " " + node.calculateSize() + " " + " (dir)");
        for (File f: node.getFiles().values()) {
            for (int i = 0; i < tabs + 1; i++) {
                System.out.print("  ");
            }
            System.out.println(f);
        }
        for (Directory d: node.getDirectories().values()) {
            treePrint(d, tabs + 1);
        }
    }

}
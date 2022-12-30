import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Directory root = new Directory("root");
        try {
            getInput(root);
            System.out.println(getDirectories(root));
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public static void getInput(Directory root) throws FileNotFoundException {
        FileInputStream fis = new FileInputStream("input.txt");
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
        //treePrint(root, 0);
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

    public static int getDirectories(Directory directory) {
        int sumOfDirectories = 0;
        int passedInSize = directory.calculateSize();
        if (passedInSize < 100001) {
            sumOfDirectories += directory.calculateSize();
        }
            for (Directory d : directory.getDirectories().values()) {
                sumOfDirectories += getDirectories(d);
            }
        return sumOfDirectories;
    }

}
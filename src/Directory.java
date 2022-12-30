import java.util.HashMap;

public class Directory {

    private String name;
    private HashMap<String, File> files;
    private HashMap<String, Directory> directories;
    private Directory parent;
    private int size;

    public Directory(String name) {
        this.name = name;
        this.files = new HashMap<>();
        this.directories = new HashMap<>();
        this.parent = null;
        this.size = 0;
    }

    public Directory(String name, Directory parent) {
        this.name = name;
        this.files = new HashMap<>();
        this.directories = new HashMap<>();
        this.parent = parent;
    }

    public String getName() {
        return name;
    }
    public HashMap<String, File> getFiles() {
        //TODO
        return files;
    }
    public HashMap<String, Directory> getDirectories() {
        //TODO
        return directories;
    }
    public Directory getParent() {
        //TODO
        return parent;
    }
    public int getSize() {
        return this.size;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setFiles(File file) {
        files.put(file.getName(), file);
    }
    public void setDirectories(Directory directory) {
        directories.put(directory.getName(), directory);
    }
    public void setParent(Directory parent) {
        this.parent = parent;
    }
    public void setSize(int size) {
        this.size = size;
    }

    public String toString() {
        String string = "";

        string += this.size;
        return string;
    }

    public void putInDirectory(File file) {
        files.put(file.getName(), file);
        this.size += file.getSize();
    }

    public void putInDirectory(Directory child) {
        directories.put(child.name, child);
    }

    public Directory stepIntoDirectory(String name) {
        return this.directories.get(name);
    }

    public int calculateSize() {
        int sizeOfDirectory = 0;
        for (File f: this.files.values()) {
            sizeOfDirectory += f.getSize();
        }
        for (Directory d : this.directories.values()) {
            sizeOfDirectory += d.calculateSize();
        }
        return sizeOfDirectory;
    }

}

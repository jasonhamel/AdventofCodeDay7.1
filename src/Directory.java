import java.util.HashMap;

public class Directory {

    private String name;
    private HashMap<String, File> files;
    private HashMap<String, Directory> directories;
    private Directory parent;

    public Directory(String name) {
        this.name = name;
        this.files = new HashMap<>();
        this.directories = new HashMap<>();
        this.parent = null;
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

    public String toString() {
        String string = "";

        string += "Name: " + this.name + "\n" +
                "Parent: " + this.parent + "\n" +
                "Contents: " + "\n\t" ;
        if (directories.isEmpty()) {
            string += "EMPTY";
        }
        for (String key : this.directories.keySet()) {
            string += key + "\n\t";
        }
        for (String key: this.files.keySet()) {
            string += key;
        }
        return string;
    }

    public void putInDirectory(File file) {
        files.put(file.getName(), file);
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
        return sizeOfDirectory;
    }

}

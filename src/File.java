public class File {

    private String name;
    private int size;

    public File(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public File(File source) {
        this.name = source.name;
        this.size = source.size;
    }

    public String getName() {
        return this.name;
    }
    public int getSize() {
        return this.size;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSize(int size) {
        this.size = size;
    }

    public String toString() {
        return "File Name: " + this.name + "\n" +
                "File Size: " + this.size;
    }

}

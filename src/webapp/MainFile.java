package webapp;

import java.io.File;

public class MainFile {

    public static void main(String[] args) {
        File file = new File("C:\\projects\\basejava");
        getFilesName(file);
    }

    public static void getFilesName(File file) {
        File[] list = file.listFiles();
        for (File f : list) {
            if (f.isDirectory()) {
                getFilesName(f);
            } else {
                System.out.println(f);
            }
        }
    }
}

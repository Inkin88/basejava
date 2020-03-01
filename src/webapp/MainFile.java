package webapp;

import java.io.File;

public class MainFile {

    public static void main(String[] args) {
        File file = new File("C:\\projects\\basejava");
        String str = "";
        getFilesName(file, str);
    }

    public static void getFilesName(File file, String str) {
        File[] list = file.listFiles();
        if (list != null) {
            for (File f : list) {
                if (f.isDirectory()) {
                    System.out.println(str + "Directory: " + f.getName());
                    getFilesName(f, str + " ");
                } else {
                    System.out.println(str + "File: " + f.getName());
                }

            }
        }
    }
}

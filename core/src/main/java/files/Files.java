package files;

import java.io.*;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Files {

    private static String root = "";

    public static void setRoot(String rootPath) {
        root = rootPath;
    }

    static java.io.File makeDirectory(String path) {

        String fullPath = root + path;
        java.io.File directory = new java.io.File(fullPath);

        if (directory.isDirectory()) {
           // Logger.write("Каталог " + fullPath + " уже существует");
        } else if (!directory.mkdir()) {
           // Logger.write("Каталог " + fullPath + " не был создан");
        } else {
          //  Logger.write("Каталог " + fullPath + " создан");
        }

        return directory;
    }

    static java.io.File makeFile(String filePath) throws IOException {

        filePath = root + filePath;

        java.io.File f = new java.io.File(filePath);

        try {
            if (f.exists() && !f.isDirectory()) {
               // Logger.write("Каталог " + filePath + " уже существует");
            } else if (f.createNewFile()) {
              //  Logger.write("Файл " + filePath + " создан");
            } else {
              //  Logger.write("Файл " + filePath + " не был создан");
                throw new IOException("Ошибка создания файла xs" + filePath);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return f;
    }

    public static void write(String filePath, String content) throws IOException {

        java.io.File f = new java.io.File(root + filePath);

        try {
            if (f.canWrite()) { //canRead, canExecute
                FileWriter writer = new FileWriter(f, true);
                writer.write(content, 0, content.length());
                writer.flush();
                writer.close();
            } else {
              //  Logger.write("Файл " + filePath + " не доступен для записи");
                throw new IOException("Файл " + filePath + " не доступен для записи");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void zipFiles(String archiveFilePath, String[] zippedObjectsPathList) {

        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(archiveFilePath))) {
            for (int i = 0; i < zippedObjectsPathList.length; i++) {

                String filePath = zippedObjectsPathList[i];
                java.io.File datFile = new java.io.File(filePath);

                try (FileInputStream fis = new FileInputStream(filePath)) {
                    ZipEntry entry = new ZipEntry(datFile.getName());
                    zout.putNextEntry(entry);
                    // считываем содержимое файла в массив byte
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    // добавляем содержимое к архиву
                    zout.write(buffer);
                    // закрываем текущую запись для новой записи
                    zout.closeEntry();
                }

                datFile.delete();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    public static void openZip(String zipFilePath, String unzipToPath) throws IOException {
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(zipFilePath))) {

            ZipEntry entry;
            String name;
            long size;
            while ((entry = zin.getNextEntry()) != null) {

                name = entry.getName(); // получаем название файла

                // распаковка
                FileOutputStream fout = new FileOutputStream(unzipToPath + name);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    //@TODO
    public static void getInfo() {
        //listFiles

        //getParent
        //getName
        //length
    }

    public static String read(String filePath) {
        try {
            File f = new File(filePath);

            StringBuilder content = new StringBuilder();
            Scanner myReader = new Scanner(f);
            while (myReader.hasNextLine()) {
                content.append(myReader.nextLine());
            }
            myReader.close();

            return content.toString();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return null;
    }

}

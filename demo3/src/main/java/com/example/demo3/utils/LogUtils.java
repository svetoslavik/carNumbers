package com.example.demo3.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

//класс по работе с файлом
public class LogUtils {

    static String filePart = "CarNumberFile.log";
    static String title = "carNumber";

    //поиск сгенерированного номера на наличие в файле
    public static boolean fileContainsWord(String word) throws IOException {
        File file = new File(filePart);
        if (file.exists()) {
            return false;
        } else {
            return Files
                    .lines(Paths.get(filePart))
                    .anyMatch(e -> e.contains(word));
        }
    }

    //получение последнего созраненного номера
    public static String ReadLastLine() throws FileNotFoundException, IOException {
        String result = null;
        File file = new File(filePart);
        if (file.exists()) {
            try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
                result = null;
                long startIdx = file.length();
                System.out.println("startIdx: " + startIdx);
                if (startIdx != 0) {
                    while (result == null || result.length() == 0) {
                        raf.seek(startIdx--);
                        raf.readLine();
                        result = raf.readLine();

                    }
                }
                System.out.println("Line: " + result);
                result = new String(result.getBytes("ISO-8859-1"), "UTF-8");
                System.out.println("UTF8: " + result);
            }
        } else {
            return null;
        }
        return result;
    }

    //логирование
    public static void writeLog(String logInfo) {
        Logger logger = Logger.getLogger(title);
        FileHandler fh;

        try {
            fh = new FileHandler(filePart);
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            logger.info(logInfo);

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

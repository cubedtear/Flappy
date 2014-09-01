package io.github.aritzhack.flappy.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Aritz Lopez
 */
public class FileUtils {

    private FileUtils() {}

    public static String loadAsString(String file) {
        String result = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                result += line + "\n";
            }
            reader.close();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}

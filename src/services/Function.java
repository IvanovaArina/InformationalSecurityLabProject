package services;

import entity.Result;

import javax.crypto.SecretKey;
import java.io.*;

public interface Function {
    Result execute(String[] parameters);
    default String readTextFromFile(File fileName) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }
    default void rewriteTextToFile(File fileName, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, false))) {
            writer.write(content);
        }
    }



}

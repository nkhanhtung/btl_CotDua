package Appfunction.Support;

import Appfunction.Dictionaries.Word;

import java.io.*;
import java.util.ArrayList;

public class IOHTML {
    public static ArrayList<Word> readFile(String path) {
        ArrayList<Word> lines = new ArrayList<>();

        try (FileReader fileReader = new FileReader(path);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                int pos = line.indexOf("<html>");
                if (pos > 0 && pos < line.length()) {
                    String word_target = line.substring(0, pos).trim();
                    String word_explain = line.substring(pos).trim();
                    Word word = new Word(word_target, word_explain);
                    lines.add(word);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }

    public static void writeFile(ArrayList<Word> words, String path) {
        try (FileWriter fileWriter = new FileWriter(path);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            for (Word word : words) {
                // Convert Word object to string representation
                String line = word.getWord_target() + ": " + word.getWord_explain();
                // Write the string representation to the file
                bufferedWriter.write(line);
                // Write a new line character to separate entries
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package App.Supporting;

import App.dictionaries.Base.Word;

import java.io.*;
import java.util.ArrayList;
import java.util.Dictionary;

public class ReadWordsFromFile {

    public static ArrayList<Word> read(String path) {
        ArrayList<Word> Store = new ArrayList<>();

        try {
            File file = new File(path);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.trim().split("\\s+", 2);
                if (parts.length >= 2) {
                    String target = parts[0];
                    String explain = parts[1];
                    Store.add(new Word(target, explain));
                }
            }

        } catch (IOException e) {
            System.out.println("Error when importing from file");
        }

        return Store;
    }
}

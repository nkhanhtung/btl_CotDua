package Appfunction.save;

import Appfunction.Dictionaries.Word;
import Appfunction.Support.IOHTML;
import Appfunction.Support.Sortwords;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class BookmarkSave extends dictionarySave {


    public BookmarkSave() throws IOException {
    }
    public void IsertFromFile() {
        IOHTML reader = new IOHTML();
        ArrayList<Word> adds = reader.readFile("src/main/resources/data/BookmarkList.txt");
        Collections.sort(adds, new Sortwords());
        dictionary.setWords(adds);
        System.out.println("End insert bookmark from file.");
    }
    public void IsertToFile() {
        IOHTML writer = new IOHTML();
        writer.writeFile(dictionary.getWords(), "src/main/resources/data/BookmarkList.txt");
    }

    @Override
    public ArrayList<String> StingSearcher(String word_target) {
        return super.StingSearcher(word_target);
    }
}

package Appfunction.save;

import Appfunction.Dictionaries.HTMLDictionaryCommanline;
import Appfunction.Dictionaries.Word;

import java.io.IOException;
import java.util.ArrayList;

public class dictionarySave extends HTMLDictionaryCommanline {


    public dictionarySave() throws IOException {
    }
    public void show(String word_target) {
        ArrayList<Word> words = this.dictionarySearcher(word_target);
        for (Word word : words) {
            System.out.println(word.getWord_target());
        }
    }
    public ArrayList<String> StingSearcher(String word_target) {
        ArrayList<Word> words = this.dictionarySearcher(word_target);
        ArrayList<String> result = new ArrayList<>();
        for (Word word : words) {
            result.add(word.getWord_target());
        }
        return result;
    }
}

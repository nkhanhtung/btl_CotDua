package Appfunction.Dictionaries;

import java.util.ArrayList;

public class Dictionary {
    public ArrayList<Word> words = new ArrayList<>();

    public void addWord(Word word) {
        words.add(word);
    }

    public Word findWord(String word_target) {
        for (Word word : words) {
            if (word.getWord_target().equals(word_target)) {
                return word;
            }
        }
        return null;
    }

    public void removeWord(String word_target) {
        Word word = findWord(word_target);
        if (word != null) {
            words.remove(word_target);
        }
    }

    public void editWord(String word_target, String newWord) {
        Word word = findWord(word_target);
        if (word != null) {
            word.setWord_explain(newWord);
        }
    }

}
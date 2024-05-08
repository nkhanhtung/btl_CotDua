package App.dictionaries.Base;

import java.util.ArrayList;

public class Dictionary {
    public ArrayList<Word> words = new ArrayList<>();

    public void add(Word word) {
        this.getWords().add(word);
    }

    public void addWordsFromList(ArrayList<Word> wordList) {
        words.addAll(wordList);
    }

    public ArrayList<Word> getWords() {
        return words;
    }

    public void setWords(ArrayList<Word> words) {
        this.words = words;
    }
}

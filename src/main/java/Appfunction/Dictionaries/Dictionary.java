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
    public void addWordsFromList(ArrayList<Word> wordList) {
        words.addAll(wordList);
    }
    public void setWords(ArrayList<Word> words) { this.words = words; }


    public ArrayList<Word> getWords() {
        return words;
    }
    public void push(Word word) {
        int length = words.size();
        int index = searchIndexInsert(0, length - 1, word.getWord_target());
        if (index <= length && index >= 0) words.add(index, word);
    }
    private Word binaryLookup(int start, int end, String spelling) {
        if (end < start) return null;
        int mid = start + (end - start) / 2;
        Word word = words.get(mid);
        String currentSpelling = word.getWord_explain();
        int compare = currentSpelling.compareTo(spelling);
        if (compare == 0) return word;
        if (compare > 0) return binaryLookup(start, mid - 1, spelling);
        return binaryLookup(mid + 1, end, spelling);
    }
    public Word lookup(String spelling) {
        return binaryLookup(0, words.size() - 1, spelling);
    }
    private int searchIndexInsert(int start, int end, String spelling) {
        if (end < start) return start;

        int mid = start + (end - start) / 2;
        if (mid >= words.size()) return mid;

        Word word = words.get(mid);
        int compare = word.getWord_target().compareTo(spelling);

        if (compare == 0) return -1;
        if (compare > 0) return searchIndexInsert(start, mid - 1, spelling);

        return searchIndexInsert(mid + 1, end, spelling);
    }
}
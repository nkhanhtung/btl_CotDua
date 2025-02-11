package App.dictionaries.Base;

import App.Supporting.ReadWordsFromFile;
import App.Supporting.SortWords;
import org.example.demo2.AddScreenController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class DictionaryCommandline {
    Dictionary dictionary = new Dictionary();

    public void insertFromFile() {
        ReadWordsFromFile rd = new ReadWordsFromFile();
        ArrayList<Word> data = rd.read("Dictionary.txt");
        Collections.sort(data, new SortWords());
        dictionary.addWordsFromList(data);
    }

    public ArrayList<Word> InsertFromFile() {
        ReadWordsFromFile rd = new ReadWordsFromFile();
        ArrayList<Word> finish = rd.read("Dictionary.txt");
        Collections.sort(finish, new SortWords());
        return finish;
    }

    public Word binaryLookup(int start, int end, String word_input) {
        if (end < start) return null;
        int mid = start + (end - start) / 2;
        Word word = dictionary.getWords().get(mid);
        String currentTarget = word.getWord_target();
        int compare = currentTarget.compareTo(word_input);
        if (compare == 0) return word;
        if (compare > 0) return binaryLookup(start, mid - 1, word_input);
        return binaryLookup(mid + 1, end, word_input);
    }

    public Word lookup(String spelling) {
        this.insertFromFile();
        return binaryLookup(0, dictionary.getWords().size() - 1, spelling);
    }


    public int binarySearcher(int start, int end, String word_input) {
        if (end < start) return -1;
        int mid = start + (end - start) / 2;
        Word word = dictionary.getWords().get(mid);
        String currentSpelling = word.getWord_target();
        if (currentSpelling.startsWith(word_input)) {
            return mid;
        }
        int compare = currentSpelling.compareTo(word_input);
        if (compare == 0) return mid;
        if (compare > 0) return binarySearcher(start, mid - 1, word_input);
        return binarySearcher(mid + 1, end, word_input);
    }

    public ArrayList<Word> searcher(String word_input) {
        ArrayList<Word> result = new ArrayList<>();
        int index = binarySearcher(0, dictionary.getWords().size() - 1, word_input);
        if (index >= 0) {
            result.add(dictionary.getWords().get(index));
            int left = index - 1, right = index + 1;

            while (left >= 0) {
                Word leftWord = dictionary.getWords().get(left--);
                if (leftWord.getWord_target().startsWith(word_input))
                    result.add(leftWord);
                else
                    break;
            }

            int length = dictionary.getWords().size();
            while (right < length) {
                Word RightWord = dictionary.getWords().get(right++);
                if (RightWord.getWord_target().startsWith(word_input))
                    result.add(RightWord);
                else
                    break;
            }
        }
        Collections.sort(result, new SortWords());
        return result;
    }

    public void addWord(Word word) {
        dictionary.add(word);
    }

    public void removeWord(Word word) {
        dictionary.getWords().remove(word);
    }

    public void editWord(Word word, String explain) {
        word.setWord_explain(explain);
    }
}

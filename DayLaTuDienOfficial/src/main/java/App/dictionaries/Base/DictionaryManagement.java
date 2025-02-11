package App.dictionaries.Base;

import App.Supporting.WriteWordsToFile;
import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryManagement {
    Dictionary dictionary = new Dictionary();
    DictionaryCommandline dictionaryCommandline = new DictionaryCommandline();

    public void insertFromCommandline() {
        Scanner scanner = new Scanner(System.in);
        String word_target = scanner.nextLine();
        String word_explain = scanner.nextLine();
        Word word = new Word(word_target, word_explain);
        dictionary.add(word);
    }

    public void SaveWordToFile(ArrayList<Word> updatedWords) {
        WriteWordsToFile pen = new WriteWordsToFile();
        pen.write(updatedWords);
    }

    public void EditWord(String oldWord, String target, String explain) {
        Word word_need_edit = dictionaryCommandline.lookup(oldWord);
        ArrayList<Word> datta = new ArrayList<>();
        datta = dictionaryCommandline.InsertFromFile();
        for (Word word : datta) {
            if (word.getWord_target().equals(oldWord)) {
                word.setWord_target(target);
                word.setWord_explain(explain);
                break;
            }
        }
        this.SaveWordToFile(datta);
    }

    public void DeleteWord(String delete) {
        ArrayList<Word> tips = new ArrayList<>();
        tips = dictionaryCommandline.InsertFromFile();
        for (Word word : tips) {
            if (word.getWord_target().equals(delete)) {
                tips.remove(word);
                break;
            }
        }
        this.SaveWordToFile(tips);
    }
}

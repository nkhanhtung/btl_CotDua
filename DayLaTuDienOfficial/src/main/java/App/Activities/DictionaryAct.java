package App.Activities;

import App.Supporting.ReadWordsFromFile;
import App.Supporting.SortWords;
import App.Supporting.WriteWordsToFile;
import App.dictionaries.Base.Dictionary;
import App.dictionaries.Base.DictionaryCommandline;
import App.dictionaries.Base.DictionaryManagement;
import App.dictionaries.Base.Word;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public class DictionaryAct extends DictionaryManagement {
    DictionaryCommandline dictionaryCommandline = new DictionaryCommandline();

    public ArrayList<String> getStringListSearch(String Input) throws IOException {
        dictionaryCommandline.insertFromFile();
        ArrayList<String> result = new ArrayList<>();
        if (Input.equals("")) {
            return new ArrayList<>();
        }
        ArrayList<Word> data = dictionaryCommandline.searcher(Input);
        for (Word word : data) {
            result.add(word.getWord_target());
        }
        return result;
    }
}

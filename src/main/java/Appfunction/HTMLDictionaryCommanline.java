package Appfunction;

import Appfunction.Dictionaries.DictionaryManagement;
import Appfunction.Dictionaries.Word;
import Appfunction.Support.IOHTML;
import Appfunction.Support.Sortwords;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class HTMLDictionaryCommanline extends DictionaryManagement {
    public HTMLDictionaryCommanline() throws IOException {
    }

    @Override
    public void insertFromFile() throws IOException {
        IOHTML rd = new IOHTML();
        ArrayList<Word> adds = rd.readFile("");
        Collections.sort(adds, new Sortwords());
        dictionary.setWords(adds);
        System.out.println("End insert from file");
    }
    public void saveWord() {
        IOHTML rd = new IOHTML();
        rd.writeFile(dictionary.getWords(),"");
    }
    public void addWord(Word word) {
        String explain = word.getWord_explain();
        if (!explain.startsWith("<html>") || !explain.contains("</html>")) {
            word.setWord_explain("<html>" + explain + "</html>");
        }

        dictionary.push(word);
        this.saveWord();
    }
    public void removeWord(Word word) {
        if (word != null) {
            dictionary.getWords().remove(word);
            this.saveWord();
        }
    }

    public void removeWord(String spelling) {
        Word word = dictionary.lookup(spelling);
        if (word != null) {
            this.removeWord(word);
        }
    }


}

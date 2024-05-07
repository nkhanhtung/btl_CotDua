package org.example.demo2;

import App.Supporting.WriteWordsToFile;
import App.dictionaries.Base.Dictionary;
import App.dictionaries.Base.DictionaryCommandline;
import App.dictionaries.Base.DictionaryManagement;
import App.dictionaries.Base.Word;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class EditScreenController {
    @FXML
    protected Button submit;
    @FXML
    protected TextField old_word_input;
    @FXML
    protected TextField new_word_input;
    @FXML
    protected TextArea meaning;
    @FXML
    protected TextArea alert;

    App.dictionaries.Base.Dictionary dictionary = new Dictionary();
    DictionaryCommandline dictionaryCommandline = new DictionaryCommandline();
    DictionaryManagement d = new DictionaryManagement();
    WriteWordsToFile wr = new WriteWordsToFile();

    public boolean isExisted(String spelling) {
        dictionaryCommandline.insertFromFile();
        Word word = dictionaryCommandline.lookup(spelling);
        return word != null && word.getWord_target().equals(spelling);
    }

    @FXML
    public void onClickEdit() {
        String oldWord = old_word_input.getText();
        String newWord = new_word_input.getText();
        String definition = meaning.getText();
        if (oldWord.isEmpty() || newWord.isEmpty() || definition.isEmpty()) {
            alert.setText("Please enter enough detail !");
        } else {
            if (!isExisted(oldWord)) {
                alert.setText("The word you try to edit not existed !");
            } else {
                d.EditWord(oldWord, newWord, definition);
                old_word_input.setText("");
                new_word_input.setText("");
                meaning.setText("");
                alert.setText("Change successfully !");
            }
        }
    }
}

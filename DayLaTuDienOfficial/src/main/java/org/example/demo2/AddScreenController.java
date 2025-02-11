package org.example.demo2;

import App.Supporting.WriteWordsToFile;
import App.dictionaries.Base.DictionaryCommandline;
import App.dictionaries.Base.Word;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AddScreenController {
    @FXML
    protected TextField word_to_add;
    @FXML
    protected TextArea definitions;
    @FXML
    protected Button save_btn;

    DictionaryCommandline dictionaryCommandline = new DictionaryCommandline();
    WriteWordsToFile wrt = new WriteWordsToFile();
    String English;
    String Vietnamese;
    @FXML
    protected TextArea message;

    protected boolean Existed(String spelling) {
        dictionaryCommandline.insertFromFile();
        Word word = dictionaryCommandline.lookup(spelling);
        return word != null && word.getWord_target().equals(spelling);
    }

    @FXML
    public void HandleSaveButton() {
        String target = word_to_add.getText();
        String explain = definitions.getText();
        if (target.isEmpty() || explain.isEmpty()) {
            message.setText("Please enter enough word and definition!");
        } else {
            if (!Existed(target)) {
                this.English = target;
                this.Vietnamese = explain;
                Word word = new Word(English, Vietnamese);
                wrt.SaveAWord(word, "Dictionary.txt");
                word_to_add.clear();
                definitions.clear();
                message.setText("Word added successfully!");
            } else {
                message.setText("Word existed! Try with other word!");
            }
        }
    }

    public void refresh() {
        message.setText("");
        word_to_add.setText("");
        definitions.setText("");
    }
}

package org.example.demo2;

import App.Activities.DictionaryAct;
import App.Activities.TextSpeech;
import App.Supporting.WriteWordsToFile;
import App.dictionaries.Base.Word;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class WordViewController {
    @FXML
    protected Label EnglishWord;
    @FXML
    protected TextArea explore;
    @FXML
    protected Button audio_btn;
    @FXML
    protected Button RemoveWord;
    @FXML
    protected Button outstanding;

    private TextSpeech textSpeech = new TextSpeech();
    DictionaryAct dictionaryAct = new DictionaryAct();
    WriteWordsToFile writeWordsToFile = new WriteWordsToFile();

    public void setWordAndDefinition(String vocab, String meaning) {
        EnglishWord.setText(vocab);
        explore.setText(meaning);
    }

    @FXML
    public void AudioClickHandle(ActionEvent event) {
        if (event.getSource() == audio_btn) {
            String spelling = EnglishWord.getText();
            if (!spelling.isEmpty()) {
                textSpeech.Speak(spelling);
            }
        }
    }

    @FXML
    public void RemoveClickHandle(ActionEvent event) {
        if (event.getSource() == RemoveWord) {
            String spelling = EnglishWord.getText();
            dictionaryAct.DeleteWord(spelling);
        }
    }

    @FXML
    public void BookmarkClickHandle(ActionEvent event) {
        if (event.getSource() == outstanding) {
            String spelling = EnglishWord.getText();
            String mean = explore.getText();
            Word word = new Word(spelling, mean);
            writeWordsToFile.SaveAWord(word, "src/main/resources/Data/BookmarkList.txt");
        }
    }
}

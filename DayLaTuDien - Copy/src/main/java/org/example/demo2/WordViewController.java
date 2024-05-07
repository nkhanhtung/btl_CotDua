package org.example.demo2;

import App.Activities.TextSpeech;
import App.dictionaries.Base.DictionaryManagement;
import com.almasb.fxgl.entity.action.Action;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
    DictionaryManagement dictionaryManagement = new DictionaryManagement();

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
            dictionaryManagement.DeleteWord(spelling);
        }
    }
}

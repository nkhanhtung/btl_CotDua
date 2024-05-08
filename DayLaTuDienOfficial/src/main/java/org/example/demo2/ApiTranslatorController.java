package org.example.demo2;

import App.Activities.GoogleTransAPI;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ApiTranslatorController {
    @FXML
    protected TextField first_lang;
    @FXML
    protected TextField second_lang;
    @FXML
    protected Label foreign;
    @FXML
    protected Label local;
    @FXML
    protected Button translation;
    @FXML
    protected TextArea text1;
    @FXML
    protected TextArea text2;

    GoogleTransAPI tran = new GoogleTransAPI();

    @FXML
    public void TranslateOnClick() throws IOException {
        String type_lang1 = first_lang.getText();
        String type_lang2 = second_lang.getText();
        String input = text1.getText();
        if (!type_lang1.isEmpty() && !type_lang2.isEmpty() && !input.isEmpty()) {
            foreign.setText(type_lang1);
            local.setText(type_lang2);
            String output = tran.translate(input, type_lang1, type_lang2);
            text2.setText(output);
        }
    }

    public void clear() {
        first_lang.clear();
        second_lang.clear();
        foreign.setText("");
        local.setText("");
        text1.clear();
        text2.clear();
    }
}

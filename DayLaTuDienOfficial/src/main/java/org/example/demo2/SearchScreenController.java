package org.example.demo2;

import App.Activities.DictionaryAct;
import App.dictionaries.Base.DictionaryCommandline;
import App.dictionaries.Base.Word;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class SearchScreenController {

    @FXML
    protected TextField input_typed;
    @FXML
    protected ListView<String> list_words;
    @FXML
    protected AnchorPane MainPane;

    DictionaryAct dictionaryAct = new DictionaryAct();
    DictionaryCommandline dictionaryCommandline = new DictionaryCommandline();

    @FXML
    public void TypingToSearch(ActionEvent event) {
        if (event.getSource() == input_typed) {
            System.out.println("please wait");
        }
    }

    @FXML
    public void ShowTheList() throws IOException {
        String spelling = input_typed.getText();
        ArrayList<String> data = dictionaryAct.getStringListSearch(spelling);
        ObservableList<String> result = FXCollections.observableArrayList();
        result.addAll(data);
        if (!spelling.isEmpty()) {
            ObservableList<String> uniqueResult = FXCollections.observableArrayList(result.stream().distinct().collect(Collectors.toList()));
            list_words.getItems().clear();
            list_words.setItems(uniqueResult);
        } else {
            list_words.getItems().clear();
        }
    }

    @FXML
    public void HandleSelectItem(MouseEvent event) {
        String spelling = list_words.getSelectionModel().getSelectedItem();
        Word word = dictionaryCommandline.lookup(spelling);
        if (word != null) {
            try {
                input_typed.setText(spelling);
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("WordView.fxml"));
                Parent parent = fxmlLoader.load();
                WordViewController controller = fxmlLoader.getController();
                controller.setWordAndDefinition(spelling, word.getWord_explain());
                MainPane.getChildren().setAll(parent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void reset() {
        input_typed.setText("");
        list_words.getItems().clear();
    }
}

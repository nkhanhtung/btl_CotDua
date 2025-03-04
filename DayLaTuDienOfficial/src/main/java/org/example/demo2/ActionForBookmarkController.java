package org.example.demo2;

import App.Supporting.ReadWordsFromFile;
import App.Supporting.SortWords;
import App.dictionaries.Base.Word;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public class ActionForBookmarkController {
    @FXML
    protected ListView<String> noted_words;
    @FXML
    protected AnchorPane Explaining;
    @FXML
    protected Button btn_show;

    ReadWordsFromFile readWordsFromFile = new ReadWordsFromFile();

    @FXML
    public void bookmarkClickOn(ActionEvent event) {
        if (event.getSource() == btn_show) {
            ArrayList<Word> focus;
            focus = readWordsFromFile.read("BookmarkList.txt");
            Collections.sort(focus, new SortWords());
            ArrayList<String> number = new ArrayList<>();
            for (Word word : focus) {
                number.add(word.getWord_target());
            }
            ObservableList<String> result = FXCollections.observableArrayList();
            result.addAll(number);
            ObservableList<String> unique = FXCollections.observableArrayList(result.stream().distinct().collect(Collectors.toList()));
            noted_words.getItems().clear();
            noted_words.setItems(unique);
        }
    }
}

package org.example.demo2;

import App.dictionaries.Base.DictionaryCommandline;
import App.dictionaries.Base.Word;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class BookmarkController extends ActionForBookmarkController {
    DictionaryCommandline dictionaryCommandline = new DictionaryCommandline();

    @FXML
    public void Select(MouseEvent event) {
        String target = noted_words.getSelectionModel().getSelectedItem();
        Word word = dictionaryCommandline.lookup(target);
        if (word != null) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("WordView.fxml"));
                Parent root = fxmlLoader.load();
                WordViewController wordViewController = fxmlLoader.getController();
                wordViewController.setWordAndDefinition(target, word.getWord_explain());
                Explaining.getChildren().setAll(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void resetBookmark() {
        noted_words.getItems().clear();
    }
}

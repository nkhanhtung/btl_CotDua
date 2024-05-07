package org.example.demo2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable {
    @FXML
    private Button search_button;
    @FXML
    private Button addWord_button;
    @FXML
    private Button edit_button;
    @FXML
    private Button bookmark_button;
    @FXML
    private Button settings_button;
    @FXML
    private AnchorPane content_pane;

    public SearchScreenController searchScreenController;
    public AddScreenController addScreenController;
    public EditScreenController editScreenController;
    private AnchorPane SearchPane;
    private AnchorPane AddWordPane;
    private AnchorPane EditPane;
    private AnchorPane BookmarkPane;
    private AnchorPane SettingsPane;

    public void Search() {
        addScreenController.refresh();
        this.setContentPane(SearchPane);
    }

    public void AddWord() {
        searchScreenController.reset();
        this.setContentPane(AddWordPane);
    }

    public void Edit() {
        addScreenController.refresh();
        searchScreenController.reset();
        this.setContentPane(EditPane);
    }

    public void Bookmark() {
    }

    public void Settings() {
    }

    private void setContentPane(AnchorPane anchorPane) {
        this.content_pane.getChildren().setAll(anchorPane);
    }

    @FXML
    public void onClickHandle(ActionEvent event) {
        if (event.getSource() == search_button) {
            Search();
        } else if (event.getSource() == addWord_button) {
            AddWord();
        } else if (event.getSource() == edit_button) {
            Edit();
        } else if (event.getSource() == bookmark_button) {
            Bookmark();
        } else if (event.getSource() == settings_button) {
            Settings();
        }
    }

    @FXML
    public void onClicktoExit(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("IntroScreen.fxml"));
        Parent parent = fxmlLoader.load();
        Scene FirstScene = new Scene(parent, 900, 650);
        Stage OldStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        OldStage.setScene(FirstScene);
        OldStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SearchScreen.fxml"));
            Parent searchRoot = fxmlLoader.load();
            searchScreenController = fxmlLoader.getController();
            SearchPane = (AnchorPane) searchRoot;
        } catch (IOException e) {
            System.out.println("Error load the screen");
            e.printStackTrace();
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddScreen.fxml"));
            Parent addRoot = fxmlLoader.load();
            addScreenController = fxmlLoader.getController();
            AddWordPane = (AnchorPane) addRoot;
        } catch (IOException e) {
            System.out.println("Error load the screen");
            e.printStackTrace();
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditScreen.fxml"));
            Parent editRoot = fxmlLoader.load();
            editScreenController = fxmlLoader.getController();
            EditPane = (AnchorPane) editRoot;
        } catch (IOException e) {
            System.out.println("Error load the screen");
            e.printStackTrace();
        }
    }
}

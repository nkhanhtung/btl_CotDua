package org.example.demo2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class IntroScreenController {
    @FXML
    private Button StartButton;
    @FXML
    private Button GameButton;

    @FXML
    public void onClicktolearn(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainScreen.fxml"));
        Parent parent = fxmlLoader.load();
        Scene learning_scene = new Scene(parent, 900, 650);
        Stage primaryStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        primaryStage.setScene(learning_scene);
        primaryStage.show();
    }

    @FXML
    public void onClicktoGames(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("crossword.fxml"));
        Parent game = fxmlLoader.load();
        Scene game_scene = new Scene(game, 900, 650);
        Stage primaryStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        primaryStage.setScene(game_scene);
        primaryStage.show();
    }
}

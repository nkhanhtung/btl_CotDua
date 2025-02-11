package org.example.demo2;

import App.Activities.CrosswordGame;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class CrosswordController {
    @FXML
    private Label clueLabel;
    @FXML
    private Label messageLabel;
    @FXML
    private Label achieve;

    @FXML
    private TextField inputField;

    @FXML
    private Label resultLabel;

    @FXML
    private Label cell00, cell01, cell02, cell03, cell04, cell05, cell06, cell07, cell08, cell09, cell010;
    @FXML
    private Label cell10, cell11, cell12, cell13, cell14, cell15, cell16, cell17, cell18, cell19, cell110;
    @FXML
    private Label cell20, cell21, cell22, cell23, cell24, cell25, cell26, cell27, cell28, cell29, cell210;
    @FXML
    private Label cell30, cell31, cell32, cell33, cell34, cell35, cell36, cell37, cell38, cell39, cell310;
    @FXML
    private Label cell40, cell41, cell42, cell43, cell44, cell45, cell46, cell47, cell48, cell49, cell410;
    @FXML
    private Label cell50, cell51, cell52, cell53, cell54, cell55, cell56, cell57, cell58, cell59, cell510;
    @FXML
    private Label cell60, cell61, cell62, cell63, cell64, cell65, cell66, cell67, cell68, cell69, cell610;
    @FXML
    private Label cell70, cell71, cell72, cell73, cell74, cell75, cell76, cell77, cell78, cell79, cell710;

    private CrosswordGame game;
    private int wrongAnswerCount;
    private int previousScore = 0;
    private String san = "Correct answers :\n";

    public void initialize() {
        game = new CrosswordGame();
        game.setScore(previousScore);

        wrongAnswerCount = 0;

        // Lấy các ký tự từ đề bài Crossword và gán cho các Label tương ứng
        char[][] crossword = game.getCrosswordGrid();
        for (int i = 0; i < crossword.length; i++) {
            for (int j = 0; j < crossword[i].length; j++) {
                Label currentLabel = getLabelForPosition(i, j);
                if (currentLabel != null) {
                    currentLabel.setText(String.valueOf(crossword[i][j]));
                }
            }
        }
    }
    private void displayClues() {
        Map<String, String> clues = game.getClues();
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : clues.entrySet()) {
            stringBuilder.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        // Hiển thị gợi ý trên giao diện người dùng, có thể sử dụng một Label hoặc TextArea để hiển thị
        // Ví dụ: clueLabel.setText(stringBuilder.toString());

        // Ví dụ sử dụng TextArea để hiển thị gợi ý
        TextArea textArea = new TextArea();
        textArea.setText(stringBuilder.toString());
        textArea.setEditable(false);
        textArea.setWrapText(true);
        // Thêm textArea vào layout hoặc container tương ứng trong giao diện của bạn
    }

    public void submitAnswer(ActionEvent actionEvent) {
        String userInput = inputField.getText();
        if (!userInput.isEmpty()) {
            boolean isCorrect = game.checkAnswer(userInput);
            if (isCorrect) {
                game.saveCorrectAnswer(userInput);
                game.increaseScore(10);
                previousScore = game.getScore();
                san = san + userInput + "\n";
                achieve.setText(san);
                inputField.clear();
                messageLabel.setText("That's a correct answer!");
                if (game.isAllAnsweredCorrectly()) {
                    showConfirmationDialog();
                    san = "";
                    achieve.setText("");
                }
            } else {
                wrongAnswerCount++;
                if (wrongAnswerCount < 2) {
                    messageLabel.setText("Incorrect answer. Try again!");
                } else {
                    String s = "";
                    Map<String, String> data = game.getClues();
                    for (Map.Entry<String, String> entry : data.entrySet()) {
                        s = s + entry.getKey() + " : " + entry.getValue() + "\n";
                    }
                    messageLabel.setText(s);
                    endGame();
                }
            }
        }
    }
    private void showConfirmationDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Congratulations!");
        alert.setHeaderText("You have completed the crossword!");
        alert.setContentText("Do you want to continue to earn more points?");

        ButtonType buttonTypeYes = new ButtonType("Yes");
        ButtonType buttonTypeNo = new ButtonType("No");

        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        alert.showAndWait().ifPresent(response -> {
            if (response == buttonTypeYes) {
                restartGame();
            } else {
                endGame();
            }
        });
    }

    private void restartGame() {
        // Tạo đề mới và bắt đầu chơi lại
        initialize();
    }

    private void replaceWithSpace() {
        // Lấy lưới từ
        char[][] crosswordGrid = game.getCrosswordGrid();

        // Lấy danh sách các từ đã trả lời đúng
        ArrayList<CrosswordGame.Word1> correctAnswers = game.getCorrectAnswers();

        // Duyệt qua từng ô trong lưới từ
        for (int i = 0; i < crosswordGrid.length; i++) {
            for (int j = 0; j < crosswordGrid[i].length; j++) {
                Label currentLabel = getLabelForPosition(i, j);

                // Kiểm tra xem ô hiện tại có chứa từ đã trả lời đúng không
                for (CrosswordGame.Word1 word : correctAnswers) {
                    if (wordContainsPosition(word, i, j)) {
                        if (currentLabel != null) {
                            currentLabel.setText(" ");
                        }
                        deleteWordFromGrid(word);
                        break;
                    }
                }
            }
        }
    }

    // Phương thức để kiểm tra xem một từ có chứa vị trí (row, col) không
    private boolean wordContainsPosition(CrosswordGame.Word1 word, int row, int col) {
        int wordRow = word.getRow();
        int wordCol = word.getCol();
        int direction = word.getDirection();
        String target = word.getWordTarget();

        if (direction == 0) {
            // Kiểm tra nếu vị trí nằm trong khoảng cột của từ
            if (row == wordRow && col >= wordCol && col < wordCol + target.length()) {
                return true;
            }
        } else {
            // Kiểm tra nếu vị trí nằm trong khoảng hàng của từ
            if (col == wordCol && row >= wordRow && row < wordRow + target.length()) {
                return true;
            }
        }
        return false;
    }


    private Label getLabelForPosition(int row, int col) {
        switch (row) {
            case 0:
                switch (col) {
                    case 0: return cell00;
                    case 1: return cell01;
                    case 2: return cell02;
                    case 3: return cell03;
                    case 4: return cell04;
                    case 5: return cell05;
                    case 6: return cell06;
                    case 7: return cell07;
                    case 8: return cell08;
                    case 9: return cell09;
                    case 10: return cell010;
                }
                break;
            case 1:
                switch (col) {
                    case 0: return cell10;
                    case 1: return cell11;
                    case 2: return cell12;
                    case 3: return cell13;
                    case 4: return cell14;
                    case 5: return cell15;
                    case 6: return cell16;
                    case 7: return cell17;
                    case 8: return cell18;
                    case 9: return cell19;
                    case 10: return cell110;
                }
                break;
            case 2:
                switch (col) {
                    case 0: return cell20;
                    case 1: return cell21;
                    case 2: return cell22;
                    case 3: return cell23;
                    case 4: return cell24;
                    case 5: return cell25;
                    case 6: return cell26;
                    case 7: return cell27;
                    case 8: return cell28;
                    case 9: return cell29;
                    case 10: return cell210;
                }
                break;
            case 3:
                switch (col) {
                    case 0: return cell30;
                    case 1: return cell31;
                    case 2: return cell32;
                    case 3: return cell33;
                    case 4: return cell34;
                    case 5: return cell35;
                    case 6: return cell36;
                    case 7: return cell37;
                    case 8: return cell38;
                    case 9: return cell39;
                    case 10: return cell310;
                }
                break;
            case 4:
                switch (col) {
                    case 0: return cell40;
                    case 1: return cell41;
                    case 2: return cell42;
                    case 3: return cell43;
                    case 4: return cell44;
                    case 5: return cell45;
                    case 6: return cell46;
                    case 7: return cell47;
                    case 8: return cell48;
                    case 9: return cell49;
                    case 10: return cell410;
                }
                break;
            case 5:
                switch (col) {
                    case 0: return cell50;
                    case 1: return cell51;
                    case 2: return cell52;
                    case 3: return cell53;
                    case 4: return cell54;
                    case 5: return cell55;
                    case 6: return cell56;
                    case 7: return cell57;
                    case 8: return cell58;
                    case 9: return cell59;
                    case 10: return cell510;
                }
                break;
            case 6:
                switch (col) {
                    case 0: return cell60;
                    case 1: return cell61;
                    case 2: return cell62;
                    case 3: return cell63;
                    case 4: return cell64;
                    case 5: return cell65;
                    case 6: return cell66;
                    case 7: return cell67;
                    case 8: return cell68;
                    case 9: return cell69;
                    case 10: return cell610;
                }
                break;
            case 7:
                switch (col) {
                    case 0: return cell70;
                    case 1: return cell71;
                    case 2: return cell72;
                    case 3: return cell73;
                    case 4: return cell74;
                    case 5: return cell75;
                    case 6: return cell76;
                    case 7: return cell77;
                    case 8: return cell78;
                    case 9: return cell79;
                    case 10: return cell710;
                }
                break;
        }
        return null;
    }

    @FXML
    public void exitGame(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("IntroScreen.fxml"));
        Parent root = fxmlLoader.load();
        Scene FirstScene = new Scene(root, 900, 650);
        Stage OldStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        OldStage.setScene(FirstScene);
        OldStage.show();
    }

    private void endGame() {
        resultLabel.setText("Game over. Your final score: " + game.getScore());
        inputField.setDisable(true);
    }

    private void deleteWordFromGrid(CrosswordGame.Word1 word) {
        int row = word.getRow();
        int col = word.getCol();
        int direction = word.getDirection();
        String target = word.getWordTarget();

        if (direction == 0) {
            // Xóa từ theo hàng ngang
            for (int i = 0; i < target.length(); i++) {
                game.getCrosswordGrid()[row][col + i] = ' ';
            }
        } else {
            // Xóa từ theo hàng dọc
            for (int i = 0; i < target.length(); i++) {
                game.getCrosswordGrid()[row + i][col] = ' ';
            }
        }
    }


}

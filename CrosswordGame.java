import java.util.*;

public class CrosswordGame {
    private char[][] crosswordGrid; // Lưới ô chữ
    private List<Word> words; // Danh sách từ vựng
    private Map<String, String> clues; // Gợi ý
    private int wrongAnswerLimit; // Số lần trả lời sai tối đa cho phép
    private int wrongAnswerCount; // Số lần trả lời sai đã xảy ra
    private int score; // Điểm số của người chơi

    public CrosswordGame() {
        crosswordGrid = new char[10][10]; // Kích thước lưới ô chữ
        words = new ArrayList<>();
        clues = new HashMap<>();
        wrongAnswerLimit = 3; // Số lần trả lời sai tối đa cho phép (có thể thay đổi)
        wrongAnswerCount = 0;
        score = 0;
        loadWordsAndClues(); // Tạo danh sách từ vựng và gợi ý
        createCrossword(); // Tạo ô chữ
    }

    // Tạo danh sách từ vựng và gợi ý
    private void loadWordsAndClues() {
        // Thêm một số từ vựng và gợi ý vào danh sách
        words.add(new Word("house", "ngôi nhà"));
        words.add(new Word("cat", "con mèo"));
        words.add(new Word("car", "xe hơi"));
        words.add(new Word("dog", "con chó"));
        words.add(new Word("book", "quyển sách"));

        // Thêm từ vựng và gợi ý vào từ điển
        for (Word word : words) {
            clues.put(word.getWordTarget(), word.getWordExplain());
        }
    }

    // Tạo ô chữ bằng cách điền các từ vào lưới
    private void createCrossword() {
        // Khởi tạo lưới ô chữ rỗng
        for (int i = 0; i < crosswordGrid.length; i++) {
            Arrays.fill(crosswordGrid[i], ' '); // Điền khoảng trống vào lưới
        }

        // Điền các từ vựng vào lưới theo hướng ngang hoặc dọc
        Random rand = new Random();
        for (Word word : words) {
            boolean placed = false;

            while (!placed) {
                // Lấy vị trí ngẫu nhiên để điền từ
                int row = rand.nextInt(crosswordGrid.length);
                int col = rand.nextInt(crosswordGrid[0].length);
                // Xác định hướng ngẫu nhiên (0: ngang, 1: dọc)
                int direction = rand.nextInt(2);

                // Kiểm tra xem từ có thể được điền vào lưới ở vị trí đó không
                if (canPlaceWord(word.getWordTarget(), row, col, direction)) {
                    placeWord(word.getWordTarget(), row, col, direction);
                    word.setPosition(row, col, direction); // Lưu vị trí của từ đã điền vào lưới
                    placed = true; // Đã điền thành công
                }
            }
        }
    }

    // Kiểm tra xem từ có thể được điền vào lưới không
    private boolean canPlaceWord(String word, int row, int col, int direction) {
        // Kiểm tra kích thước của từ và hướng điền
        if (direction == 0) {
            // Kiểm tra hàng ngang
            if (col + word.length() > crosswordGrid[0].length) {
                return false; // Quá dài để điền theo chiều ngang
            }
            // Kiểm tra xem từ có thể được điền vào lưới không
            for (int i = 0; i < word.length(); i++) {
                if (crosswordGrid[row][col + i] != ' ' && crosswordGrid[row][col + i] != word.charAt(i)) {
                    return false;
                }
            }
        } else {
            // Kiểm tra hàng dọc
            if (row + word.length() > crosswordGrid.length) {
                return false; // Quá dài để điền theo chiều dọc
            }
            // Kiểm tra xem từ có thể được điền vào lưới không
            for (int i = 0; i < word.length(); i++) {
                if (crosswordGrid[row + i][col] != ' ' && crosswordGrid[row + i][col] != word.charAt(i)) {
                    return false;
                }
            }
        }
        return true;
    }

    // Điền từ vào lưới ô chữ
    private void placeWord(String word, int row, int col, int direction) {
        if (direction == 0) {
            // Điền theo hàng ngang
            for (int i = 0; i < word.length(); i++) {
                crosswordGrid[row][col + i] = word.charAt(i);
            }
        } else {
            // Điền theo hàng dọc
            for (int i = 0; i < word.length(); i++) {
                crosswordGrid[row + i][col] = word.charAt(i);
            }
        }
    }

    // Hiển thị ô chữ cho người dùng
    private void displayCrossword() {
        System.out.println("\nCrossword Puzzle:");
        for (int i = 0; i < crosswordGrid.length; i++) {
            for (int j = 0; j < crosswordGrid[i].length; j++) {
                System.out.print(crosswordGrid[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Hiển thị gợi ý cho người dùng
    private void displayClues() {
        System.out.println("\nClues:");
        int clueIndex = 1;
        for (Map.Entry<String, String> entry : clues.entrySet()) {
            System.out.println(clueIndex + ". " + entry.getKey() + ": " + entry.getValue());
            clueIndex++;
        }
    }

    // Bắt đầu trò chơi ô chữ
    public void startGame() {
        Scanner scanner = new Scanner(System.in);

        // Hiển thị ô chữ và gợi ý
        displayCrossword();


        // Bắt đầu trò chơi
        while (true) {
            // Kiểm tra nếu người chơi đã vượt quá giới hạn trả lời sai
            if (wrongAnswerCount >= wrongAnswerLimit) {
                System.out.println("You've reached the maximum number of incorrect answers. Game over.");
                System.out.println("Your final score: " + score);
                break;
            }

            // Nếu người chơi muốn thoát trò chơi, nhập "quit"
            System.out.print("Enter a word (or 'quit' to exit): ");
            String userInput = scanner.next().toLowerCase();

            if (userInput.equals("quit")) {
                System.out.println("Thank you for playing!");
                break;
            }

            // Kiểm tra đáp án của người dùng
            if (checkAnswer(userInput)) {
                System.out.println("Correct! Well done.");
                score += 10; // Cộng điểm khi trả lời đúng

                // Xóa từ vừa điền sau khi trả lời đúng
                removeWord(userInput);
            } else {
                System.out.println("Incorrect. Try again.");
                wrongAnswerCount++; // Tăng số lần trả lời sai
            }

            // Hiển thị lại ô chữ và gợi ý
            displayCrossword();
            if (wrongAnswerCount >= wrongAnswerLimit) {
                displayClues();
            }

            // Kiểm tra nếu trò chơi đã hoàn thành
            if (isCrosswordComplete()) {
                System.out.println("Congratulations! You've completed the crossword.");
                System.out.println("Your final score: " + score);
                break;
            }
        }
    }

    // Kiểm tra đáp án của người chơi
    private boolean checkAnswer(String userInput) {
        // Kiểm tra nếu từ vựng người dùng nhập có trong danh sách từ vựng
        for (Word word : words) {
            if (word.getWordTarget().equalsIgnoreCase(userInput)) {
                return true;
            }
        }
        return false;
    }

    // Hàm để xóa từ vừa điền sau khi trả lời đúng
    private void removeWord(String wordToRemove) {
        // Tìm từ trong danh sách từ vựng
        for (int i = 0; i < words.size(); i++) {
            if (words.get(i).getWordTarget().equalsIgnoreCase(wordToRemove)) {
                Word word = words.get(i);
                // Xóa từ khỏi lưới ô chữ
                deleteWordFromGrid(word);
                // Xóa từ khỏi danh sách từ vựng
                words.remove(i);
                // Xóa từ khỏi danh sách gợi ý
                clues.remove(word.getWordTarget());
                break; // Thoát vòng lặp sau khi xóa
            }
        }
    }

    // Hàm để xóa từ khỏi lưới ô chữ
    private void deleteWordFromGrid(Word word) {
        int row = word.getRow();
        int col = word.getCol();
        int direction = word.getDirection();
        String target = word.getWordTarget();

        // Xóa từ khỏi lưới ô chữ bằng cách điền khoảng trống
        if (direction == 0) {
            // Xóa từ theo hàng ngang
            for (int i = 0; i < target.length(); i++) {
                crosswordGrid[row][col + i] = ' ';
            }
        } else {
            // Xóa từ theo hàng dọc
            for (int i = 0; i < target.length(); i++) {
                crosswordGrid[row + i][col] = ' ';
            }
        }
    }

    // Kiểm tra xem ô chữ đã hoàn thành chưa
    private boolean isCrosswordComplete() {
        return words.isEmpty();
    }

    // Hàm main để bắt đầu trò chơi
    public static void main(String[] args) {
        CrosswordGame game = new CrosswordGame();
        game.startGame();
    }

    // Lớp Word để lưu trữ từ vựng và thông tin về vị trí của từ
    class Word {
        private String wordTarget; // Từ vựng tiếng Anh
        private String wordExplain; // Nghĩa tiếng Việt
        private int row; // Vị trí hàng của từ
        private int col; // Vị trí cột của từ
        private int direction; // Hướng của từ (0: ngang, 1: dọc)

        // Constructor
        public Word(String wordTarget, String wordExplain) {
            this.wordTarget = wordTarget;
            this.wordExplain = wordExplain;
        }

        // Getter và setter cho các thuộc tính của từ
        public String getWordTarget() {
            return wordTarget;
        }

        public void setWordTarget(String wordTarget) {
            this.wordTarget = wordTarget;
        }

        public String getWordExplain() {
            return wordExplain;
        }

        public void setWordExplain(String wordExplain) {
            this.wordExplain = wordExplain;
        }

        // Các phương thức để lưu trữ thông tin về vị trí và hướng của từ trong lưới
        public int getRow() {
            return row;
        }

        public void setRow(int row) {
            this.row = row;
        }

        public int getCol() {
            return col;
        }

        public void setCol(int col) {
            this.col = col;
        }

        public int getDirection() {
            return direction;
        }

        public void setDirection(int direction) {
            this.direction = direction;
        }
        public void setPosition(int row, int col, int direction) {
            this.row = row;
            this.col = col;
            this.direction = direction;
        }
    }
}

package App.Activities;

import java.io.*;
import java.util.*;

public class CrosswordGame {
    private char[][] crosswordGrid;
    private List<Word1> words;
    private List<Word1> allwords;
    private Map<String, String> clues;
    private int wrongAnswerLimit;
    private int wrongAnswerCount;
    private int score = 0;
    public List<Word1> correctAnswers;

    public void setClues(Map<String, String> clues) {
        this.clues = clues;
    }

    public List<Word1> reader(String path) {
        List<Word1> Store = new ArrayList<>();
        try {
            File file = new File(path);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.trim().split("\\s+", 2);
                if (parts.length >= 2) {
                    String target = parts[0];
                    String explain = parts[1];
                    Store.add(new Word1(target, explain));
                }
            }

            bufferedReader.close();
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        } catch (IOException e) {
            System.out.println("Error when import from file");
        }
        return Store;
    }

    public List<Word1> Insert() {
        List<Word1> result = this.reader("src/main/resources/Data/Dictionary.txt");
        return result;
    }

    public CrosswordGame() {
        crosswordGrid = new char[8][11];
        correctAnswers = new ArrayList<>();
        words = new ArrayList<>();
        allwords = new ArrayList<>();
        clues = new HashMap<>();
        wrongAnswerLimit = 3;
        wrongAnswerCount = 0;
        loadWordsAndClues();
        createCrossword();
    }

    private void loadWordsAndClues() {
        allwords = this.Insert();
        Random random = new Random();
        while (words.size() < 6) {
            int index = random.nextInt(allwords.size());
            Word1 selectedWord = allwords.get(index);
            if (!words.contains(selectedWord)) {
                words.add(selectedWord);
                allwords.remove(index);
            }
        }

        for (Word1 word : words) {
            clues.put(word.getWordTarget(), word.getWordExplain());
        }
    }



    private void createCrossword() {
        char[] emptyChars = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        Random rand = new Random();

        for (int i = 0; i < crosswordGrid.length; i++) {
            Arrays.fill(crosswordGrid[i], ' '); // Điền khoảng trống vào lưới
        }

        // Điền các từ vựng vào lưới theo hướng ngang hoặc dọc
        for (Word1 word : words) {
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

        // Thay thế các ô trống bằng các ký tự ngẫu nhiên từ tập hợp emptyChars
        for (int i = 0; i < crosswordGrid.length; i++) {
            for (int j = 0; j < crosswordGrid[i].length; j++) {
                if (crosswordGrid[i][j] == ' ') {
                    crosswordGrid[i][j] = emptyChars[rand.nextInt(emptyChars.length)];
                }
            }
        }
    }

    // Kiểm tra xem từ có thể được điền vào lưới không
    private boolean canPlaceWord(String word, int row, int col, int direction) {
        if (direction == 0) {
            if (col + word.length() > crosswordGrid[0].length) {
                return false; // Quá dài để điền theo chiều ngang
            }
            for (int i = 0; i < word.length(); i++) {
                if (crosswordGrid[row][col + i] != ' ' && crosswordGrid[row][col + i] != word.charAt(i)) {
                    return false;
                }
            }
        } else {
            if (row + word.length() > crosswordGrid.length) {
                return false; // Quá dài để điền theo chiều dọc
            }
            for (int i = 0; i < word.length(); i++) {
                if (crosswordGrid[row + i][col] != ' ' && crosswordGrid[row + i][col] != word.charAt(i)) {
                    return false;
                }
            }
        }
        return true;
    }

    private void placeWord(String word, int row, int col, int direction) {
        if (direction == 0) {
            for (int i = 0; i < word.length(); i++) {
                crosswordGrid[row][col + i] = word.charAt(i);
            }
        } else {
            for (int i = 0; i < word.length(); i++) {
                crosswordGrid[row + i][col] = word.charAt(i);
            }
        }
    }



    // Hiển thị ô chữ cho người dùng
    public String displayCrossword() {
        System.out.println("\nCrossword Puzzle:");
        for (int i = 0; i < crosswordGrid.length; i++) {
            for (int j = 0; j < crosswordGrid[i].length; j++) {
                System.out.print(crosswordGrid[i][j] + " ");
            }
            System.out.println();
        }
        return null;
    }

    // Hiển thị gợi ý cho người dùng
    public String displayClues() {
        System.out.println("\nClues:");
        int clueIndex = 1;
        for (Map.Entry<String, String> entry : clues.entrySet()) {
            System.out.println(clueIndex + ". " + entry.getKey() + ": " + entry.getValue());
            clueIndex++;
        }
        return null;
    }

    // Bắt đầu trò chơi ô chữ
    private void createNewPuzzle() {
        words.clear(); // Xóa danh sách từ vựng hiện tại
        clues.clear(); // Xóa danh sách gợi ý hiện tại
        loadWordsAndClues(); // Tải từ và gợi ý mới
        createCrossword(); // Tạo lưới ô chữ mới
        wrongAnswerCount = 0; // Đặt lại số lần trả lời sai
    }
    public void Yes() {
        createNewPuzzle();
    }
    // Cập nhật phương thức startGame()


    // Kiểm tra đáp án của người chơi
    public boolean checkAnswer(String userInput) {
        // Kiểm tra nếu từ vựng người dùng nhập có trong danh sách từ vựng
        for (Word1 word : words) {
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
                Word1 word = words.get(i);
                // Xóa từ khỏi lưới ô chữ

                // Xóa từ khỏi danh sách từ vựng
                words.remove(i);
                // Xóa từ khỏi danh sách gợi ý
                clues.remove(word.getWordTarget());
                break; // Thoát vòng lặp sau khi xóa
            }
        }
    }

    // Hàm để xóa từ khỏi lưới ô chữ
    private void deleteWordFromGrid(Word1 word) {
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
    public void saveCorrectAnswer(String word) {
        // Tìm từ trong danh sách từ vựng
        for (Word1 w : words) {
            if (w.getWordTarget().equalsIgnoreCase(word)) {
                correctAnswers.add(w); // Thêm từ đã trả lời đúng vào danh sách correctAnswers
                removeWord(w.getWordTarget()); // Xóa từ đã trả lời đúng khỏi lưới và danh sách từ vựng
                break;
            }
        }
    }
    public Word1 getLastCorrectAnswer(int i, int j) {
        for (int k = correctAnswers.size() - 1; k >= 0; k--) {
            Word1 word = correctAnswers.get(k);
            // Kiểm tra xem từ có bắt đầu tại (i, j) không
            if (word.getRow() == i && word.getCol() == j) {
                return word; // Trả về từ cuối cùng được trả lời đúng ở vị trí (i, j)
            }
        }
        return null; // Trả về null nếu không tìm thấy từ nào ở vị trí (i, j)
    }


    // Kiểm tra xem ô chữ đã hoàn thành chưa
    private boolean isCrosswordComplete() {
        return words.isEmpty();
    }

    public int getScore() {
        return score;
    }
    public char[][] getCrosswordGrid() {
        return crosswordGrid;
    }
    public ArrayList<Word1> getCorrectAnswers() {
        return (ArrayList<Word1>) correctAnswers;
    }
    public boolean isAllAnsweredCorrectly() {
        // Kiểm tra xem tất cả các từ đã được trả lời đúng hay không
        for (Word1 word : words) {
            if (!correctAnswers.contains(word)) {
                return false;
            }
        }
        return true;
    }

    public void increaseScore(int i) {
        score = score + i;
    }

    public Map<String, String> getClues() {
        return clues;
    }

    public void setScore(int previousScore) {
        this.score = previousScore;
    }

    // Lớp Word để lưu trữ từ vựng và thông tin về vị trí của từ
    public class Word1 {
        private String wordTarget; // Từ vựng tiếng Anh
        private String wordExplain; // Nghĩa tiếng Việt
        private int row; // Vị trí hàng của từ
        private int col; // Vị trí cột của từ
        private int direction; // Hướng của từ (0: ngang, 1: dọc)

        // Constructor
        public Word1(String wordTarget, String wordExplain) {
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

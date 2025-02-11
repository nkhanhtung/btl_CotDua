package App.Supporting;

import App.dictionaries.Base.Word;

import java.io.*;
import java.util.ArrayList;

public class WriteWordsToFile {
    public void write(ArrayList<Word> words) {
        this.write(words, "D:/UET_PROJECTS/CotDua_OOP/btl_CotDua/Dictionary.txt");
    }

    public void write(ArrayList<Word> words, String path) {
        try {
            FileWriter fileWriter = new FileWriter(path);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Word word : words) {
                bufferedWriter.write(word.getWord_target() + "\t" + word.getWord_explain());
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void SaveAWord(Word word, String path) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path, true));
            writer.write(word.getWord_target() + "\t" + word.getWord_explain());
            writer.newLine(); // Thêm dòng mới sau mỗi lần ghi dữ liệu
            writer.close();
        } catch (IOException e) {
            System.out.println("Không thể lưu từ vào tệp");
        }
    }
}

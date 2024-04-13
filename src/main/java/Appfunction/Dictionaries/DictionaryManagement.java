package Appfunction.Dictionaries;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryManagement {

    public Dictionary dictionary = new Dictionary();

    public DictionaryManagement() throws IOException {
        this.insertFromFile();
    }

    ArrayList<Word> words_database = dictionary.words;

    public void insertFromCommandline() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            String English;
            String Vietnamese;
            English = sc.nextLine();
            Vietnamese = sc.nextLine();
            Word word = new Word(English, Vietnamese);
            dictionary.addWord(word);
        }
    }

    public void insertFromFile() throws IOException {
        ArrayList<Word> result = new ArrayList<>();
        try (FileReader fr = new FileReader("C:\\oop-main\\oop-main\\baitaplon\\src\\dictionaries.txt");
             BufferedReader br = new BufferedReader(fr)) {

            String line;

            // Đọc dữ liệu từng dòng
            while ((line = br.readLine()) != null) {
                String[] words = line.split("\t");

                if (words.length >= 2) {
                    String englishWord = words[0];
                    String vietnameseExplanation = words[1];

                    result.add(new Word(englishWord, vietnameseExplanation));
                } else {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void saveWord() throws IOException {
        FileWriter fw = new FileWriter("C:\\Users\\your_username\\Desktop\\dictionaries.txt"); // Thay thế bằng đường dẫn thực tế đến file của bạn
        BufferedWriter bw = new BufferedWriter(fw);


        for (Word word : dictionary.words) {
            String englishWord = word.getWord_target();
            String vietnameseExplanation = word.getWord_explain();

            bw.write(englishWord + "\t" + vietnameseExplanation + "\n");


            bw.write(englishWord + "\t" + vietnameseExplanation + "\n");
        }

        bw.close();
        fw.close();

        System.out.println("Xuất dữ liệu từ điển thành công!");
    }

    private Word BinaryLookup(int begin, int end, String word_target) {
        if (end < begin) {
            return null;
        } else {
            int middle = (begin + end) / 2;
            Word word = words_database.get(middle);
            int compare = word.getWord_target().compareTo(word_target);
            if (compare == 0) {
                return word;
            } else if (compare > 0) {
                return BinaryLookup(begin, middle - 1, word_target);
            } else {
                return BinaryLookup(middle + 1, end, word_target);
            }
        }
    }

    public Word Lookup(String word_target) {
        return BinaryLookup(0, words_database.size() - 1, word_target);
    }

    public void dictionaryExportToFile(Dictionary dictionary, String filename) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            for (Word word : dictionary.words) {
                writer.write(word.getWord_target() + "\t" + word.getWord_explain() + "\n");
            }
            writer.close();
            System.out.println("Dictionary exported to file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
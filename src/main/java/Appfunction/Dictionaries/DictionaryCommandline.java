package Appfunction.Dictionaries;

import Appfunction.Support.Sortwords;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class DictionaryCommandline {
    public Dictionary dictionary = new Dictionary();
    DictionaryManagement E_V_dictionary = new DictionaryManagement();
    DictionaryCommandline English_Dictionary = new DictionaryCommandline();
    int count = 1;
    ArrayList<Word> wordlist = dictionary.words;

    public DictionaryCommandline() throws IOException {
    }

    public void showAllWords() {
        System.out.println("No" + "\t" + "| " + "English" + "\t" + "| " + "Vietnamese");
        Collections.sort(wordlist, new Sortwords());
        for (Word word : wordlist) {
            System.out.println(count + "\t" + "| " + word.getWord_target() + "\t" + "| " + word.getWord_explain());
            count++;
        }
    }

    public void dictionaryBasic() {
        E_V_dictionary.insertFromCommandline();
        English_Dictionary.showAllWords();
    }

    public void dictionaryAdvanced() throws IOException {
        Dictionary dictionary1 = new Dictionary();
        DictionaryManagement dictionary2 = new DictionaryManagement();
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to My Application!\n"
                );

        int action = sc.nextInt();

        if (action > 9 || action < 0) {
            System.out.println("Action not supported");
        } else {
            switch (action) {
                case 1: // Add
                    System.out.println("Enter the word: ");
                    String wordTarget = sc.nextLine();
                    System.out.println("Enter the definition: ");
                    String wordExplain = sc.nextLine();

                    Appfunction.Dictionaries.Word word = new Appfunction.Dictionaries.Word(wordTarget, wordExplain);
                    dictionary.addWord(word);
                    break;
                case 2: // Remove
                    System.out.println("Enter the word to remove: ");
                    String wordToRemove = sc.nextLine();
                    dictionary1.removeWord(wordToRemove);
                    break;
                case 3: // Update
                    System.out.println("Enter the word to update: ");
                    String wordToUpdate = sc.nextLine();
                    System.out.println("Enter the new definition: ");
                    String newDefinition = sc.nextLine();
                    dictionary1.editWord(wordToUpdate, newDefinition);
                    break;
                case 5: // Lookup
                    System.out.println("Enter the word to lookup: ");
                    String wordToLookup = sc.nextLine();
                    dictionary2.Lookup(wordToLookup);
                    break;
                case 6: // Search
                    System.out.println("Enter the search term: ");
                    String searchTerm = sc.nextLine();
                    dictionary1.findWord(searchTerm);
                    break;
                // Add other cases for actions as needed
                default:
                    System.out.println("Functionality not yet implemented");
            }
        }
    }

    public void dictionarySearcher() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Nhập chuỗi cần tìm kiếm: ");
        String searchString = scanner.nextLine();
        ArrayList<Appfunction.Dictionaries.Word> results = new ArrayList<>();
        for (Appfunction.Dictionaries.Word word : dictionary.words) {
            String word_target = word.getWord_target();

            if (word_target.startsWith(searchString)) {
                results.add(word);
            }
        }
    }
}





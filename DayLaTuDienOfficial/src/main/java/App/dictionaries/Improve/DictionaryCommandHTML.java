package App.dictionaries.Improve;

import App.dictionaries.Base.Dictionary;
import App.dictionaries.Base.DictionaryCommandline;
import App.dictionaries.Base.DictionaryManagement;
import App.dictionaries.Base.Word;

import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryCommandHTML extends DictionaryCommandline {
    Dictionary dictionary = new Dictionary();
    DictionaryManagement dictionaryManagement = new DictionaryManagement();
    public void DictionaryAdvanced() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to My Application!\n" +
                "[0] Exit\n" +
                "[1] Add\n" +
                "[2] Remove\n" +
                "[3] Update\n" +
                "[4] Display\n" +
                "[5] Lookup\n" +
                "[6] Search\n" +
                "[7] Game\n" +
                "[8] Import from file\n" +
                "[9] Export to file\n" +
                "Your action:");

        int action = sc.nextInt();
        if (action > 9 || action < 0) {
            System.out.println("Action not supported");
        } else {
            switch (action) {
                case 1:
                    System.out.println("Enter the word: ");
                    String wordTarget = sc.nextLine();
                    System.out.println("Enter the definition: ");
                    String wordExplain = sc.nextLine();
                    Word word = new Word(wordTarget, wordExplain);
                    this.addWord(word);
                    break;
                case 2:
                    System.out.println("Enter the word to remove: ");
                    String wordToRemove = sc.nextLine();
                    Word word1 = this.lookup(wordToRemove);
                    removeWord(word1);
                    break;
                case 3:
                    System.out.println("Enter the word to update: ");
                    String wordToUpdate = sc.nextLine();
                    System.out.println("Enter the new definition: ");
                    String newDefinition = sc.nextLine();
                    Word word2 = this.lookup(wordToUpdate);
                    editWord(word2, newDefinition);
                    break;
                case 4:
                    System.out.println("This is the list of words:\n");
                    for (Word word3 : dictionary.getWords()) {
                        System.out.println(word3.getWord_target() + "\t" + word3.getWord_explain());
                    }
                    break;
                case 5:
                    System.out.println("Enter the word to lookup: ");
                    String wordToLookup = sc.nextLine();
                    Word word4 = this.lookup(wordToLookup);
                    System.out.println(word4.getWord_target() + "\t" + word4.getWord_explain());
                    break;
                case 6:
                    System.out.println("Enter the search term: ");
                    String searchTerm = sc.nextLine();
                    ArrayList<Word> result = this.searcher(searchTerm);
                    for (Word wordtoshow : result) {
                        System.out.println(wordtoshow.getWord_target());
                    }
                    break;
                case 7:
                    System.out.println("Game is only available on the application");
                    break;
                case 8:
                    this.insertFromFile();
                    break;
                case 9:
                    dictionaryManagement.SaveWordToFile(dictionary.getWords());
                    break;
            }
        }
    }
}

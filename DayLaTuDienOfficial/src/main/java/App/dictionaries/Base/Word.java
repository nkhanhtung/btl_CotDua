package App.dictionaries.Base;

public class Word {
    private String word_target;
    private String word_explain;

    public Word(String word, String definition) {
        word_target = word;
        word_explain = definition;
    }

    public String getWord_target() {
        return word_target;
    }

    public void setWord_target(String word_target) {
        this.word_target = word_target;
    }

    public String getWord_explain() {
        return word_explain;
    }

    public void setWord_explain(String word_explain) {
        this.word_explain = word_explain;
    }
}

package cz.vutbr.fit.gja.noDepedencyInjection;


public class TextEditor {

    private SpellChecker spellChecker;

    public TextEditor() {
        this.spellChecker = new SpellChecker();
    }

    public void spellCheck() {
        spellChecker.checkSpelling();
    }
}


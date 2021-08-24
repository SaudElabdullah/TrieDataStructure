import java.util.HashMap;

public class TrieNode {

    private char letter;
    private HashMap<Character, TrieNode> children = new HashMap<>();
    private boolean isEndOfWord;

    public TrieNode() {
    }

    public TrieNode(char letter) {
        this.letter = letter;
    }

    public boolean isEndOfWord() {
        return isEndOfWord;
    }

    public void setIsEndOfWord(boolean endOfWord) {
        isEndOfWord = endOfWord;
    }

    public char getLetter() {
        return letter;
    }

    public HashMap<Character, TrieNode> getChildren() {
        return children;
    }

    public void addChildren(Character letter, TrieNode trieNode) {
        this.children.put(letter, trieNode);
    }
}
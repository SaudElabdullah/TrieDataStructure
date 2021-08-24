import java.util.ArrayList;
import java.util.HashMap;

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public boolean contains(String word) {
        word = word.toUpperCase();
        TrieNode parent = root;
        HashMap<Character, TrieNode> children = parent.getChildren();
        for (int loop = 0; loop < word.length(); loop++) {
            Character letter = word.charAt(loop);
            if (!children.containsKey(letter) ||
                    (loop == word.length() - 1 && !children.get(letter).isEndOfWord())) {
                return false;
            } else {
                parent = children.get(letter);
                children = parent.getChildren();
            }
        }
        return true;
    }

    public boolean isPrefix(String word) {
        word = word.toUpperCase();
        TrieNode parent = root;
        HashMap<Character, TrieNode> children = parent.getChildren();
        for (int loop = 0; loop < word.length(); loop++) {
            Character letter = word.charAt(loop);
            if (!children.containsKey(letter) ||
                    (loop == word.length() - 1 && children.get(letter).isEndOfWord())) {
                return false;
            } else {
                parent = children.get(letter);
                children = parent.getChildren();
            }
        }
        return true;
    }

    public void insert(String word) {
        word = word.toUpperCase();
        TrieNode parent = root;
        HashMap<Character, TrieNode> children = parent.getChildren();
        for (int loop = 0; loop < word.length(); loop++) {
            Character letter = word.charAt(loop);
            if (!children.containsKey(letter)) {
                TrieNode trieNode = new TrieNode(letter);
                parent.addChildren(letter, trieNode);
            }
            parent = children.get(letter);
            children = parent.getChildren();
        }
        parent.setIsEndOfWord(true);
    }

    public void delete(String word) {
        word = word.toUpperCase();
        TrieNode parent = root;
        HashMap<Character, TrieNode> children = parent.getChildren();
        ArrayList<TrieNode> deleteNodes = new ArrayList<>();
        if (this.contains(word)) {
            for (int loop = 0; loop < word.length(); loop++) {
                Character letter = word.charAt(loop);
                if (loop == word.length() - 1) {
                    children.get(letter).setIsEndOfWord(false);
                }
                if (loop != word.length() - 1) {
                    deleteNodes.add(children.get(letter));
                }
                parent = children.get(letter);
                children = parent.getChildren();
            }
        } else {
            System.out.println("The word does not exist!");
        }
        for (int loop = deleteNodes.size() - 1; children.size() == 0 && loop > 0; ) {
            if (deleteNodes.get(loop).getChildren().size() <= 1) {
                loop--;
            } else {
                deleteNodes.get(loop + 1).getChildren().clear();
                deleteNodes.get(loop).getChildren().remove(deleteNodes.get(loop + 1).getLetter());
                break;
            }
        }
    }

    public boolean isEmpty() {
        return root.getChildren().isEmpty();
    }

    public void clear() {
        root.getChildren().clear();
    }

    public ArrayList<String> allWordsPrefix(String prefix) {
        prefix = prefix.toUpperCase();
        if (this.isPrefix(prefix)) {
            ArrayList<String> words = new ArrayList<>();
            TrieNode root = this.root;
            for (int loop = 0; loop < prefix.length(); loop++) {
                Character letter = prefix.charAt(loop);
                root = root.getChildren().get(letter);
            }
            allWordsPrefixRecursively(prefix, words, root, false);
            return words;
        } else {
            System.out.println("There is no prefix!");
            return null;
        }
    }

    protected ArrayList<String> allWordsPrefixRecursively(String prefix, ArrayList<String> words,
                                                          TrieNode root, boolean check) {
        if (check) {
            prefix = prefix + root.getLetter();
        }
        for (char loop = 'A'; loop <= 'Z'; loop++) {
            if (root.getChildren().size() == 1 && root.getChildren().containsKey(loop)) {
                prefix = prefix + root.getChildren().get(loop).getLetter();
                if (root.getChildren().get(loop).isEndOfWord()) {
                    words.add(prefix);
                }
                root = root.getChildren().get(loop);
            } else if (root.getChildren().containsKey(loop)) {
                allWordsPrefixRecursively(prefix, words, root.getChildren().get(loop), true);
            }
        }
        return words;
    }

    public int size() {
        if (!this.isEmpty()) {
            int numberOfNodes = 0;
            return sizeRecursively(this.root, numberOfNodes);
        } else {
            return 1;
        }
    }

    protected int sizeRecursively(TrieNode root, int numberOfNodes) {
        if (root.getChildren().size() == 0) {
            numberOfNodes++;
        } else {
            numberOfNodes = numberOfNodes + 1;
            for (char loop = 'A'; loop <= 'Z'; loop++) {
                if (root.getChildren().containsKey(loop)) {
                    numberOfNodes = sizeRecursively(root.getChildren().get(loop), numberOfNodes);
                }
            }
        }
        return numberOfNodes;
    }
}
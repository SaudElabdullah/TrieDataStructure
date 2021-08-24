import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Controller_MainScreen {
    private Trie trie = new Trie();

    @FXML
    private TextField insertTextField;
    @FXML
    private TextField containsTextField;
    @FXML
    private TextField isPrefixTextField;
    @FXML
    private TextField deleteTextField;
    @FXML
    private TextField allWordsPrefixTextField;

    @FXML
    public void insert() throws IOException {
        if (!trie.contains(insertTextField.getText()) && check(insertTextField.getText())) {
            trie.insert(insertTextField.getText());
            this.openMessageWindow("Done Successfully");
        } else {
            this.openMessageWindow("Failed!");
        }
    }

    @FXML
    public void contains() throws IOException {
        if (trie.contains(containsTextField.getText())) {
            this.openMessageWindow("Yes, It contains the word");
        } else {
            this.openMessageWindow("No, It does not contain the word!");
        }
    }

    @FXML
    public void isPrefix() throws IOException {
        if (trie.isPrefix(isPrefixTextField.getText())) {
            this.openMessageWindow("Yes, It contains the prefix");
        } else {
            this.openMessageWindow("No, It does not contain the prefix!");
        }
    }

    @FXML
    public void delete() throws IOException {
        if (trie.contains(deleteTextField.getText())) {
            trie.delete(deleteTextField.getText());
            this.openMessageWindow("Done Successfully");
        } else {
            this.openMessageWindow("Failed!");
        }
    }

    @FXML
    public void allWordsPrefix() throws IOException {
        if (trie.isPrefix(allWordsPrefixTextField.getText())) {
            this.openMessageWindow(trie.allWordsPrefix(allWordsPrefixTextField.getText()).toString());
        } else {
            this.openMessageWindow("Failed!");
        }
    }

    @FXML
    public void isEmptyButtonAction() throws IOException {
        if (trie.isEmpty()) {
            this.openMessageWindow("Yes, It is empty");
        } else {
            this.openMessageWindow("No, there are elements in the trie!");
        }
    }

    @FXML
    public void clearButtonAction() throws IOException {
        trie.clear();
        this.openMessageWindow("Done Successfully");
    }

    @FXML
    public void sizeButtonAction() throws IOException {
        this.openMessageWindow(trie.size() + "");
    }

    @FXML
    public void openMessageWindow(String message) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MessageWindow.fxml"));
        Stage massageWindow = new Stage();
        massageWindow.setScene(new Scene(loader.load()));
        Controller_MessageWindow massage =
                loader.<Controller_MessageWindow>getController();
        massage.initialize(message);
        massageWindow.show();
    }

    @FXML
    public boolean check(String word) throws FileNotFoundException {
        word = word.toUpperCase();
        Scanner scanner = new Scanner(new FileInputStream("C:\\Users\\Saud\\IdeaProjects\\ICS202_Project_InterFace\\src\\Dictionary"));
        while (scanner.hasNext()) {
            String wordFromDictionary = scanner.next();
            if (wordFromDictionary.equals(word)) {
                return true;
            }
        }
        return false;
    }
}
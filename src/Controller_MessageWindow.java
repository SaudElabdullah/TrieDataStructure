import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Controller_MessageWindow {
    @FXML
    private Label messageLabel;
    @FXML
    private Button okButton;

    public void initialize(String message){
        messageLabel.setText(message);
    }

    @FXML
    public void okButtonAction(){
        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();
    }
}

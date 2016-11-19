package controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Contact;
import ui.StartFxApp;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Imant on 17.11.16.
 */
public class MainViewController implements Initializable {
    @FXML
    private Button btAdd, btDelete;
    @FXML
    private TextField textFieldFindContact;
    @FXML
    private ListView<Contact> contactList;
    @FXML
    private ChoiceBox choiceBox;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage mainStage = StartFxApp.getInstance().getMainStage();
                Scene addContactScene = StartFxApp.getInstance().getAddContactScene();
                mainStage.setScene(addContactScene);
            }
        });
    }
}

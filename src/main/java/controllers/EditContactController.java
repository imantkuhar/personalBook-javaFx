package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.awt.Label;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Imant on 17.11.16.
 */
public class EditContactController implements Initializable {
    @FXML
    private Label labelName, labelPhoneNumber, labelAddress, labelGroup;
    @FXML
    private TextField tfName, tfPhoneNumber, tfAddress, tfGroup;
    @FXML
    private Button btSaveChanges;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}

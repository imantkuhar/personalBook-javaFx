package controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Contact;
import service.ContactServiceImpl;
import ui.StartFxApp;
import utils.PropertiesHolder;
import validators.ContactValidator;

import java.awt.*;
import java.net.URL;
import java.util.PrimitiveIterator;
import java.util.ResourceBundle;

/**
 * Created by Imant on 17.11.16.
 */
public class AddContactController implements Initializable {
    @FXML
    private Label labelName, labelPhoneNumber, labelAddress, labelGroup;
    @FXML
    private TextField tfName, tfPhoneNumber, tfAddress, tfGroup;
    @FXML
    private Button btSave, btGoBack;

    private ContactValidator contactValidator = new ContactValidator();
    private ContactServiceImpl contactService = new ContactServiceImpl();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setButtonSaveListener();
        setButtonGoBackListener();
    }

    private void setButtonSaveListener() {
        btSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Contact newContact = new Contact(tfName.getText(), tfPhoneNumber.getText(), tfAddress.getText(), tfGroup.getText());
                if (contactValidator.checkAllTextField(newContact)) {
                    contactService.addContact(newContact);
                    tfName.setText(""); tfPhoneNumber.setText(""); tfAddress.setText(""); tfGroup.setText("");
                }
                Stage mainStage = StartFxApp.getInstance().getMainStage();
                Scene mainScene = StartFxApp.getInstance().getMainScene();
                mainStage.setScene(mainScene);
            }
        });
    }

    private void setButtonGoBackListener() {
        btGoBack.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage mainStage = StartFxApp.getInstance().getMainStage();
                Scene mainScene = StartFxApp.getInstance().getMainScene();
                mainStage.setScene(mainScene);
            }
        });
    }
}
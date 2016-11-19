package controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Contact;
import service.ContactServiceImpl;
import validators.ContactValidator;

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

    private ContactValidator contactValidator = new ContactValidator();
    private ContactServiceImpl contactService = new ContactServiceImpl();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTextToTableView();
        setButtonSaveChangesListener();
    }

    private void setTextToTableView() {
    }

    private void setButtonSaveChangesListener() {
        btSaveChanges.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String name = tfName.getText();
                String phoneNumber = tfPhoneNumber.getText();
                String address = tfAddress.getText();
                String group = tfGroup.getText();
//                Contact newContact = new Contact(tfName.getText(), tfPhoneNumber.getText(), tfAddress.getText(), tfGroup.getText());
//                if (contactValidator.checkAllTextField(newContact)){
//                    contactService.addContact(newContact);
//                }
            }
        });

    }
}

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
import main.StartFxApp;
import validators.ContactValidator;

import java.awt.*;
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
    private Button btSaveChanges, btGoBack;

    private ContactValidator contactValidator = new ContactValidator();
    private ContactServiceImpl contactService = new ContactServiceImpl();
    private Contact contact;

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initContactInfo();
        setButtonSaveChangesListener();
        setButtonGoBackListener();
    }

    private void initContactInfo() {
        tfName.setText(contact.getName());
        tfPhoneNumber.setText(contact.getPhoneNumber());
        tfAddress.setText(contact.getAddress());
        tfGroup.setText(contact.getGroup());
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

    private void setButtonSaveChangesListener() {
        btSaveChanges.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                contact.setName(tfName.getText());
                contact.setPhoneNumber(tfPhoneNumber.getText());
                contact.setAddress(tfAddress.getText());
                contact.setGroup(tfGroup.getText());
                if (contactValidator.checkAllTextField(contact)){
                    contactService.updateContact(contact);
                }

                Stage mainStage = StartFxApp.getInstance().getMainStage();
                Scene mainScene = StartFxApp.getInstance().getMainScene();
                mainStage.setScene(mainScene);
            }
        });
    }
}

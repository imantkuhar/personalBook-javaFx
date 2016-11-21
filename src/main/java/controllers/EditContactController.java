package controllers;

import com.sun.xml.internal.ws.spi.db.FieldSetter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Contact;
import service.ContactServiceImpl;
import ui.StartFxApp;
import validators.ContactValidator;

import java.awt.Label;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Imant on 17.11.16.
 */
public class EditContactController implements Initializable  {
    @FXML
    private Label labelName, labelPhoneNumber, labelAddress, labelGroup;
    @FXML
    private TextField tfName, tfPhoneNumber, tfAddress, tfGroup;
    @FXML
    private Button btSaveChanges, btGoBack;

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

    private void setButtonSaveChangesListener() {
        btSaveChanges.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    Parent mainParent = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/main_view.fxml"));
                    Scene mainScene = new Scene(mainParent);
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                Contact newContact = new Contact(tfName.getText(), tfPhoneNumber.getText(), tfAddress.getText(), tfGroup.getText());
//                if (contactValidator.checkAllTextField(newContact)){
//                    contactService.addContact(newContact);
//                }
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

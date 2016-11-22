package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Contact;
import service.ContactServiceImpl;
import ui.StartFxApp;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created by Imant on 17.11.16.
 */
public class MainViewController implements Initializable {
    @FXML
    private Button btAdd, btDelete, btEdit, btUpdateTable;
    @FXML
    private TextField tfFindContact;
    @FXML
    private TableView<Contact> tvContactList;
    @FXML
    private TableColumn tcId, tcName, tcNumber, tcAddress, tcGroup;
    @FXML
    private ChoiceBox choiceBox;
    private ObservableList<Contact> contactList;

    private ContactServiceImpl contactService = new ContactServiceImpl();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initContactListView();
        setButtonAddContactListener();
        setButtonDeleteContactListener();
        setButtonEditContactListener();
        setButtonUpdateContactListListener();
    }

    private void initContactListView() {
        try {
            contactList = FXCollections.observableArrayList(contactService.getAllContacts());
            tcId.setCellValueFactory(new PropertyValueFactory<Contact, Integer>("id"));
            tcName.setCellValueFactory(new PropertyValueFactory<Contact, String>("name"));
            tcNumber.setCellValueFactory(new PropertyValueFactory<Contact, String>("phoneNumber"));
            tcAddress.setCellValueFactory(new PropertyValueFactory<Contact, String>("address"));
            tcGroup.setCellValueFactory(new PropertyValueFactory<Contact, String>("group"));
            tvContactList.setItems(contactList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setButtonAddContactListener() {
        btAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage mainStage = StartFxApp.getInstance().getMainStage();
                Scene addContactScene = StartFxApp.getInstance().getAddContactScene();
                mainStage.setScene(addContactScene);
            }
        });
    }

    private void setButtonDeleteContactListener() {
        btDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int id = tvContactList.getSelectionModel().getSelectedIndex();
                if (id != -1) {
                    Contact contact = contactList.get(id);
                    contactService.deleteContactById(contact.getId());
                    contactList.remove(id);
                }
            }
        });
    }

    private void setButtonEditContactListener() {
        btEdit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int id = tvContactList.getSelectionModel().getSelectedIndex();
                if (id != -1) {
                    Contact contact = contactList.get(id);

                    EditContactController editContactController = new EditContactController();
                    editContactController.setContact(contact);

                    FXMLLoader fxmlEditContact = new FXMLLoader(getClass().getClassLoader().getResource("fxml/edit_contact_view.fxml"));
                    fxmlEditContact.setController(editContactController);
                    Parent parent = null;

                    try {
                        parent = fxmlEditContact.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Scene editContactScene = new Scene(parent);
                    Stage mainStage = StartFxApp.getInstance().getMainStage();
                    mainStage.setScene(editContactScene);
                }
            }
        });
    }

    private void setButtonUpdateContactListListener() {
        btUpdateTable.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    contactList = FXCollections.observableArrayList(contactService.getAllContacts());
                    tvContactList.setItems(contactList);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

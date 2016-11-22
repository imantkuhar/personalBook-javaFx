package ui;

import controllers.AddContactController;
import controllers.EditContactController;
import controllers.MainViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.PropertiesHolder;

/**
 * Created by Imant on 17.11.16.
 */
public class StartFxApp extends Application {

    private Stage mainStage;
    private Scene mainScene;
    private Scene addContactScene;
    private Scene editContactScene;
    private MainViewController mainViewController;
    private EditContactController editContactController;
    private AddContactController addContactController;

    private static StartFxApp instance;

    public static StartFxApp getInstance() {
        return instance;
    }

    public Stage getMainStage() {
        return mainStage;
    }

    public Scene getMainScene() {
        return mainScene;
    }

    public Scene getAddContactScene() {
        return addContactScene;
    }

    public Scene getEditContactScene() {
        return editContactScene;
    }

    public MainViewController getMainViewController() {
        return mainViewController;
    }

    public EditContactController getEditContactController() {
        return editContactController;
    }

    public void setEditContactController(EditContactController editContactController) {
        this.editContactController = editContactController;
    }

    public AddContactController getAddContactController() {
        return addContactController;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        instance = this;
//
//        FXMLLoader fxmlMainLoader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/main_view.fxml"));
//        mainViewController = fxmlMainLoader.<MainViewController>getController();
//
//        FXMLLoader fxmlAddContact = new FXMLLoader(getClass().getClassLoader().getResource("fxml/add_new_contact_view.fxml"));
//        addContactController = fxmlAddContact.<AddContactController>getController();

        String MAIN_VIEW_ROOT = PropertiesHolder.getProperty("MAIN_VIEW_ROOT");
        String ADD_NEW_CONTACT_VIEW_ROOT = PropertiesHolder.getProperty("ADD_NEW_CONTACT_VIEW_ROOT");

        Parent mainParent = FXMLLoader.load(getClass().getClassLoader().getResource(MAIN_VIEW_ROOT));
        Parent addContactParent = FXMLLoader.load(getClass().getClassLoader().getResource(ADD_NEW_CONTACT_VIEW_ROOT));

        mainScene = new Scene(mainParent);
        addContactScene = new Scene(addContactParent);

        mainStage = stage;
        String PERSONAL_BOOK_TITLE = PropertiesHolder.getProperty("PERSONAL_BOOK_TITLE");
        mainStage.setTitle(PERSONAL_BOOK_TITLE);
        mainStage.setScene(mainScene);
        mainStage.show();
    }
}
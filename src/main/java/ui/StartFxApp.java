package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Imant on 17.11.16.
 */
public class StartFxApp extends Application {

    private Stage mainStage;
    private Scene mainScene;
    private Scene addContactScene;
    private Scene editContactScene;

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

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        instance = this;
        Parent mainParent = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/main_view.fxml"));
        Parent addContactParent = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/add_new _contact_view.fxml"));
        Parent editContactParent = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/edit_contact_view.fxml"));

        mainScene = new Scene(mainParent);
        addContactScene = new Scene(addContactParent);
        editContactScene = new Scene(editContactParent);

        mainStage = stage;
        mainStage.setTitle("Personal Book");
        mainStage.setScene(mainScene);
        mainStage.show();
    }


}
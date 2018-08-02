//View: visual representation of the data contained in the Model
package loginapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;


public class LoginApp extends Application{

    //Start method
    public void start(Stage stage) throws Exception{
        Parent root = (Parent) FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/Admin/AdminStyle.css");
        stage.setScene(scene);
        stage.setTitle("School Management System");
        stage.show();
    }

    //Main method
    public static void main(String[] args){
        launch(args);
    }


}

// Controller: acts on both on LoginApp(View-updates view when data changes) and LoginModel(Model - controls data flow to model objects)
package loginapp;

// Initializable interface: used for automatic injection of properties into the controller.

import Admin.AdminController;
import students.StudentsController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Scene;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class LoginController implements Initializable {
    LoginModel loginModel = new LoginModel();

    @FXML
    private Label dbStatus;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private ComboBox<Options> comboBox;
    @FXML
    private Button loginButton;
    @FXML
    private Label loginStatus;

    // Initialize() is called to initialize the controller once the root element has been completely processed

    public void initialize(URL url, ResourceBundle rb){
        this.dbStatus.setText("Connected to Database");
        this.comboBox.setItems(FXCollections.observableArrayList(Options.values()));
    }

    @FXML
    public void Login(ActionEvent event){
        try{
            Options value = this.comboBox.getValue();
            if (value == null) {
                value = Options.Student;
            }

            if(this.loginModel.isLogin(this.username.getText(), this.password.getText(), value)) {
                Stage stage = (Stage)this.loginButton.getScene().getWindow();
                stage.close();

                switch (value){
                    case Admin:
                        adminLogin();
                        break;
                    case Student:
                        studentLogin();
                        break;
                }
            }else{
                this.loginStatus.setText("Wrong Credentials");
            }
        }catch(Exception localException){
            localException.printStackTrace();
        }

    }

    // Student login method
    public void studentLogin(){

        try{
            Stage studentStage = new Stage();
            FXMLLoader studentLoader = new FXMLLoader();
            Pane studentroot = (Pane)studentLoader.load(getClass().getResource("/students/StudentFXML.fxml").openStream());

            StudentsController studentsController = (StudentsController)studentLoader.getController();
            Scene scene = new Scene(studentroot);
            studentStage.setScene(scene);
            studentStage.setTitle("Student Dashboard");
            studentStage.setResizable(false);
            studentStage.show();


        }catch (IOException ex){
            ex.printStackTrace();
        }

    }

    // Admin login method
    public void adminLogin(){
        try{
            Stage adminStage = new Stage();
            FXMLLoader adminLoader = new FXMLLoader();
            Pane adminroot = (Pane)adminLoader.load(getClass().getResource("/Admin/AdminFXML.fxml").openStream());

            AdminController adminController = (AdminController)adminLoader.getController();
            Scene scene = new Scene(adminroot);
            adminStage.setScene(scene);
            adminStage.setTitle("Admin DashBoard");
            adminStage.setResizable(false);
            adminStage.show();


        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
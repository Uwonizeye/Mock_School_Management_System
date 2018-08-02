package Admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import dbUtil.DbConnection;
import javafx.scene.control.cell.PropertyValueFactory;


public class AdminController implements Initializable {
    @FXML
    private TextField id;
    @FXML
    private TextField fname;
    @FXML
    private TextField lname;
    @FXML
    private TextField email;
    @FXML
    private DatePicker dob;
    @FXML
    private TableView<StudentData> studentTable;
    @FXML
    private TableColumn<StudentData, String> idColumn;
    @FXML
    private TableColumn<StudentData, String> fnameColumn;
    @FXML
    private TableColumn<StudentData, String> lnameColumn;
    @FXML
    private TableColumn<StudentData, String> emailColumn;
    @FXML
    private TableColumn<StudentData, String> dobColumn;


    //Global variables
    private DbConnection dc;
    private ObservableList<StudentData> data;
    private String sqlQuery = "SELECT * FROM students";

    // Initialize method
    public void initialize(URL url, ResourceBundle rb){
        this.dc = new DbConnection();
    }

    // Load student data method
    @FXML
    private void loadStudentData(ActionEvent event) throws SQLException{

        // Load the data
        try{
            Connection conn = DbConnection.getConnection();
            this.data = FXCollections.observableArrayList();
            ResultSet rs = conn.createStatement().executeQuery(sqlQuery);

            // Loop to read till the last row in the db
            while(rs.next()){
                this.data.add(new StudentData(rs.getString(1), rs.getString(2), rs.getString(3),rs.getString(4), rs.getString(5)));
            }
        }catch (SQLException e){
            System.err.println("Error:" + e);
        }

        // Display the data in a table
        this.idColumn.setCellValueFactory(new PropertyValueFactory<StudentData, String>("ID"));
        this.fnameColumn.setCellValueFactory(new PropertyValueFactory<StudentData, String>("fname"));
        this.lnameColumn.setCellValueFactory(new PropertyValueFactory<StudentData, String>("lname"));
        this.emailColumn.setCellValueFactory(new PropertyValueFactory<StudentData, String>("email"));
        this.dobColumn.setCellValueFactory(new PropertyValueFactory<StudentData, String>("DOB"));

        this.studentTable.setItems(null);
        this.studentTable.setItems(this.data);
    }

    // Add student method
    @FXML
    private void addStudent(ActionEvent event){
        String sqlInsert = "INSERT INTO students(ID,FirstName,LastName,Email,DOB) VALUES(?,?,?,?,?)";

        try{
            Connection conn = DbConnection.getConnection();
            PreparedStatement stm = conn.prepareStatement(sqlInsert);

            stm.setString(1,this.id.getText());
            stm.setString(2,this.fname.getText());
            stm.setString(3,this.lname.getText());
            stm.setString(4,this.email.getText());
            stm.setString(5,this.dob.getEditor().getText());

            stm.execute();
            conn.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    //Delete method: deletes selected table row from student table and db

    @FXML
    private void deleteStudent(ActionEvent event){

        // Get the row that is currently selected
        StudentData selectedRow = studentTable.getSelectionModel().getSelectedItem();

        // Prepare SQL query
        String sqlDelete = "DELETE FROM students WHERE ID ="+selectedRow.getID();

        // Remove the selected row from tableview
        studentTable.getItems().removeAll(selectedRow);

        try{
            Connection conn = DbConnection.getConnection();
            PreparedStatement stm = conn.prepareStatement(sqlDelete);
            //Remove selected row from db
            stm.execute();
            conn.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    // Clear field method: clears the fields once new data has been entered
    @FXML
    private void clearFields(ActionEvent event){
        this.id.setText("");
        this.fname.setText("");
        this.lname.setText("");
        this.email.setText("");
        this.dob.setValue(null);

    }

}

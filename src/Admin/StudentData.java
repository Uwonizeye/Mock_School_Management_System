// SimpleStringProperty: This class provides a full implementation of a Property wrapping a String value.
//Property: Generic interface that defines the methods common to all (writable) properties independent of their type.

package Admin;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StudentData {

    private final StringProperty ID;
    private final StringProperty fname;
    private final StringProperty lname;
    private final StringProperty email;
    private final StringProperty DOB;

    // Constructor
    public StudentData (String id, String fname, String lname, String email, String dob){
        this.ID = new SimpleStringProperty(id);
        this.fname = new SimpleStringProperty(fname);
        this.lname = new SimpleStringProperty(lname);
        this.email = new SimpleStringProperty(email);
        this.DOB = new SimpleStringProperty(dob);
    }

    // Getters
    public String getID() {
        return ID.get();
    }

    public StringProperty IDProperty() {
        return ID;
    }

    public void setID(String ID) {
        this.ID.set(ID);
    }

    public String getFname() {
        return fname.get();
    }

    public StringProperty fnameProperty() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname.set(fname);
    }

    public String getLname() {
        return lname.get();
    }

    public StringProperty lnameProperty() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname.set(lname);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getDOB() {
        return DOB.get();
    }

    public StringProperty DOBProperty() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB.set(DOB);
    }
}




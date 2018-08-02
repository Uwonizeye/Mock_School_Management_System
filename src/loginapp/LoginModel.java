// Model: contains login of the program
package loginapp;


import dbUtil.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class LoginModel {
    Connection connection;

    // Constructor
    public LoginModel() {
        try{
            this.connection = DbConnection.getConnection();
        } catch(SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    // Method to check if anyone is logged into the db
    public boolean isLogin(String user, String pwd, Options opt) throws Exception{
        String sql = "SELECT * FROM login WHERE username = ? and password = ? and division = ?";
        try (PreparedStatement pr = this.connection.prepareStatement(sql)) {
            pr.setString(1, user);
            pr.setString(2, pwd);
            pr.setString(3, opt.toString());
            try (ResultSet rs = pr.executeQuery()) {
                if(rs.next()){
                    return true;
                }
                return false;
            }
        }  catch (SQLException ex){
            throw new RuntimeException("Could not connect to the database", ex);
        }
    }
}

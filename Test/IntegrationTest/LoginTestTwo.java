package IntegrationTest;

import static org.junit.jupiter.api.Assertions.*;


import com.Login;
import com.TestUtils;
import javax.swing.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;

public class LoginTestTwo {
    @Test
    void jButton1ActionPerformed() {
        Connection con;
        PreparedStatement pst;

        Login loginTest;
        JTextField userTest;
        JPasswordField passTest;
        String expResult;

        loginTest = new Login();

        userTest = (JTextField) TestUtils.getChildNamed(loginTest, "txtuser");
        passTest = (JPasswordField) TestUtils.getChildNamed(loginTest, "txtpass");
        userTest.setText("joHn");
        passTest.setText("123");

        String username =  userTest.getText();
        String password = passTest.getText();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/airline", "root", "");
            pst = con.prepareStatement("select * from user where username = ? and password = ?");
            pst.setString(1, username);
            pst.setString(2, password);

            ResultSet rs;
            rs = pst.executeQuery();
            assertTrue(rs.next());

        }catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }

        //assertEquals("" , userTest.getText());

    }

}


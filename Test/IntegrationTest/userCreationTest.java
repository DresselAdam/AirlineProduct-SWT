package IntegrationTest;

import com.TestUtils;
import com.userCreation;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import javax.swing.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class userCreationTest {
    /*
     * This test checks that new user information can be added to the database by inserting mock information into
     * the database before selecting what should be the same set of data and comparing it to the original dataset.
     * */
    @Test
    public void jButton1ActionPerformed() {
        Connection con = null;
        PreparedStatement pst;

        userCreation userCreationTest = new userCreation();
        JLabel userIDTest = (JLabel) TestUtils.getChildNamed(userCreationTest, "txtuserid");
        JTextField userFNameTest = (JTextField) TestUtils.getChildNamed(userCreationTest, "txtfirstname");
        JTextField userLNameTest = (JTextField) TestUtils.getChildNamed(userCreationTest, "txtlastname");
        JTextField userUsernameTest = (JTextField) TestUtils.getChildNamed(userCreationTest, "txtusername");
        JTextField userPasswordTest = (JPasswordField) TestUtils.getChildNamed(userCreationTest, "txtpassword");

        userIDTest.setText("testID");
        userFNameTest.setText("testFName");
        userLNameTest.setText("testLName");
        userUsernameTest.setText("testUsername");
        userPasswordTest.setText("testPassword");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/airline","root","");
            pst = con.prepareStatement("DELETE FROM user WHERE id='testID'");
            pst.executeUpdate();
            pst = con.prepareStatement("insert into user(id,firstname,lastname,username,password)values(?,?,?,?,?)");

            pst.setString(1, userIDTest.getText());
            pst.setString(2, userFNameTest.getText());
            pst.setString(3, userLNameTest.getText());
            pst.setString(4, userUsernameTest.getText());
            pst.setString(5, userPasswordTest.getText());

            pst.executeUpdate();

            pst = con.prepareStatement("select firstname,lastname,username,password from user where id = ?");
            pst.setString(1, userIDTest.getText());
            ResultSet rs = pst.executeQuery();
            rs.next();

            String compare[] = {"testFName","testLName","testUsername","testPassword"};
            for (int i=1;i<=4;i++) {
                //System.out.println(compare[i-1]);
                //System.out.println(rs.getString(i));
                Assert.assertEquals(compare[i-1], rs.getString(i));
            }

            pst = con.prepareStatement("DELETE FROM user WHERE id='testID'");
            pst.executeUpdate();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(userCreation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(userCreation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

import javax.swing.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class addCustomerTest {

    @Mock
    Blob testBlob;

    @BeforeMethod
    public void beforeMethod() {
        MockitoAnnotations.initMocks(this);
    }

    /*
     * This test checks that new customer information can be added to the database by inserting mock information into
     * the database before selecting what should be the same set of data and comparing it to the original dataset.
     * */
    @Test
    public void jButton1ActionPerformed() {
        Connection con = null;
        PreparedStatement pst;

        addCustomer addCustomerTest = new addCustomer();
        JLabel customerIDTest = (JLabel) TestUtils.getChildNamed(addCustomerTest, "txtid");
        JTextField customerFNameTest = (JTextField) TestUtils.getChildNamed(addCustomerTest, "txtfirstname");
        JTextField customerLNameTest = (JTextField) TestUtils.getChildNamed(addCustomerTest, "txtlastname");
        JTextField customerNicTest = (JTextField) TestUtils.getChildNamed(addCustomerTest, "txtnic");
        JTextField customerPassportTest = (JTextField) TestUtils.getChildNamed(addCustomerTest, "txtpassport");
        JTextArea customerAddressTest = (JTextArea) TestUtils.getChildNamed(addCustomerTest, "txtaddress");
        JTextField customerContactTest = (JTextField) TestUtils.getChildNamed(addCustomerTest, "txtcontact");

        customerIDTest.setText("testID");
        customerFNameTest.setText("testFName");
        customerLNameTest.setText("testLName");
        customerNicTest.setText("testNic");
        customerPassportTest.setText("testPassport");
        customerAddressTest.setText("testAddress");
        customerContactTest.setText("1111");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/airline","root","");
            pst = con.prepareStatement("DELETE FROM customer WHERE id='testID'");
            pst.executeUpdate();
            pst = con.prepareStatement("insert into customer(id,firstname,lastname,nic,passport,address,dob,gender,contact,photo)values(?,?,?,?,?,?,?,?,?,?)");

            pst.setString(1, customerIDTest.getText());
            pst.setString(2, customerFNameTest.getText());
            pst.setString(3, customerLNameTest.getText());
            pst.setString(4, customerNicTest.getText());
            pst.setString(5, customerPassportTest.getText());
            pst.setString(6, customerAddressTest.getText());
            pst.setString(7, "2011-11-11");
            pst.setString(8, "Male");
            pst.setString(9, customerContactTest.getText());
            pst.setBlob(10, testBlob);
            pst.executeUpdate();

            pst = con.prepareStatement("select firstname,lastname,nic,passport,address,dob,gender,contact from customer where id = ?");
            pst.setString(1, customerIDTest.getText());
            ResultSet rs = pst.executeQuery();
            rs.next();

            String compare[] = {"testFName","testLName","testNic","testPassport","testAddress","2011-11-11","Male","1111"};
            for (int i=1;i<=8;i++) {
                //System.out.println(compare[i-1]);
                //System.out.println(rs.getString(i));
                Assert.assertEquals(compare[i-1], rs.getString(i));
            }

            pst = con.prepareStatement("DELETE FROM customer WHERE id='testID'");
            pst.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(addflight.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(addflight.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

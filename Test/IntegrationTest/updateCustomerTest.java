package IntegrationTest;

import com.TestUtils;
import com.addflight;
import com.searchCustomer;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import javax.swing.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.mockito.*;
import org.testng.annotations.BeforeMethod;

public class updateCustomerTest {

    @Mock Blob testBlob;

    @BeforeMethod
    public void beforeMethod() {
        MockitoAnnotations.initMocks(this);
    }

    /*
     * This test checks that customer information can be edited/updated by a user by inserting a test dataset,
     * updating this dataset, then retrieving the data using the original ID to verify that the data was updated.
     * */
    @Test
    public void jButton4ActionPerformed() {
        Connection con;
        PreparedStatement pst;

        searchCustomer updateCustomerTest = new searchCustomer();;
        JTextField customerIDTest = (JTextField) TestUtils
            .getChildNamed(updateCustomerTest, "txtcustid");
        JTextField customerFNameTest = (JTextField) TestUtils.getChildNamed(updateCustomerTest, "txtfirstname");
        JTextField customerLNameTest = (JTextField) TestUtils.getChildNamed(updateCustomerTest, "txtlastname");
        JTextField customerNicTest = (JTextField) TestUtils.getChildNamed(updateCustomerTest, "txtnic");
        JTextField customerPassportTest = (JTextField) TestUtils.getChildNamed(updateCustomerTest, "txtpassport");
        JTextArea customerAddressTest = (JTextArea) TestUtils.getChildNamed(updateCustomerTest, "txtaddress");
        JTextField customerContactTest = (JTextField) TestUtils.getChildNamed(updateCustomerTest, "txtcontact");

        customerIDTest.setText("testID");
        customerFNameTest.setText("testFName");
        customerLNameTest.setText("testLName");
        customerNicTest.setText("testNic");
        customerPassportTest.setText("testPassport");
        customerAddressTest.setText("testAddress");
        customerContactTest.setText("1111");

        String testID = customerIDTest.getText();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/airline","root","");
            pst = con.prepareStatement("insert into customer(id,firstname,lastname,nic,passport,address,dob,gender,contact,photo)values(?,?,?,?,?,?,?,?,?,?)");

            pst.setString(1, testID);
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

            pst = con.prepareStatement("update customer set firstname = ?,lastname = ?,nic = ?,passport = ?,address = ?,dob = ?, gender = ?,contact = ? where id = ?");

            pst.setString(1, "testFNameTwo");
            pst.setString(2, "testLNameTwo");
            pst.setString(3, "testNicTwo");
            pst.setString(4, "testPassportTwo");
            pst.setString(5, "testAddressTwo");
            pst.setString(6, "2012-12-12");
            pst.setString(7, "Female");
            pst.setString(8, "2222");
            pst.setString(9, testID);
            pst.executeUpdate();

            pst = con.prepareStatement("select firstname,lastname,nic,passport,address,dob,gender,contact from customer where id = ?");
            pst.setString(1, testID);
            ResultSet rs = pst.executeQuery();
            rs.next();

            String compare[] = {"testFNameTwo","testLNameTwo","testNicTwo","testPassportTwo","testAddressTwo","2012-12-12","Female","2222"};
            for (int i=1;i<=8;i++) {
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

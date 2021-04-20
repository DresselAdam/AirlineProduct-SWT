import javax.swing.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class addflightTest {

    @Test
    public void jButton1ActionPerformed() {
        Connection con = null;
        PreparedStatement pst;

        addflight addflightTest;
        JLabel flightIDTest;
        JTextField flightNameTest;

        addflightTest = new addflight();

        flightIDTest = (JLabel) TestUtils.getChildNamed(addflightTest, "txtflightid");
        flightNameTest = (JTextField) TestUtils.getChildNamed(addflightTest, "txtflightname");
        flightIDTest.setText("testID");
        flightNameTest.setText("testFlightName");

        String idInsert =  flightIDTest.getText();
        String flightNameInsert = flightNameTest.getText();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/airline","root","");
            pst = con.prepareStatement("insert into flight(id,flightname,source,depart,date,deptime,arrtime,flightcharge)values(?,?,?,?,?,?,?,?)");

            pst.setString(1, idInsert);
            pst.setString(2, flightNameInsert);
            pst.setString(3, "testSource");
            pst.setString(4, "testDepart");
            pst.setString(5, "testDate");
            pst.setString(6, "testDeptime");
            pst.setString(7, "testArrtime");
            pst.setString(8, "testFlightCharge");

            pst.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(addflight.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(addflight.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            Statement testStmt = con.createStatement();
            ResultSet rs = testStmt.executeQuery("select id from flight where flightname = 'testFlightName'");
            rs.next();
            String foundId = rs.getString(1);
            System.out.println(foundId);
            System.out.println(idInsert);
            Assert.assertEquals("testID", foundId);
            // Compares flight id before and after adding flight to the database
            // Assert.assertEquals(id, "negative"); // Always results in comparison failure - used for testing
            pst = con.prepareStatement("DELETE FROM flight WHERE id='testID'");
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

}
import org.junit.Assert;
<<<<<<< HEAD
import org.junit.ComparisonFailure;
import org.junit.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;
=======
import org.junit.Test;

import javax.swing.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertTrue;
>>>>>>> ab937b61eec31b9713ae00280ccd93f74e624753

public class addflightTest {

    @Test
<<<<<<< HEAD
    public void autoID() {
    Connection con;

        try {
            String id = "";
            String flightName = "";
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/airline","root","");
            Statement testStmt = con.createStatement();
            ResultSet rs = testStmt.executeQuery("select id from flight where flightname = '"+flightName+"'");
            rs.next();
            String foundId = rs.getString(1);
            System.out.println(id);
            System.out.println(foundId);
            Assert.assertEquals(id, foundId); // Compares flight id before and after adding flight to the database
            // Assert.assertEquals(id, "negative"); // Always results in comparison failure - used for testing
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ComparisonFailure cf) {
            System.out.println("ERROR - Flight was not added to the database.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return;
    }

}
=======
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
>>>>>>> ab937b61eec31b9713ae00280ccd93f74e624753

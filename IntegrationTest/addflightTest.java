import javax.swing.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.toedter.calendar.JDateChooser;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


public class addflightTest {

    /*
     * This test ensures that the active database connection works properly and can be used to add flight data.
     * */
    @Test
    public void jButton1ActionPerformed() {
        Connection con = null;
        PreparedStatement pst;

        addflight addflightTest = new addflight();
        JLabel flightIDTest = (JLabel) TestUtils.getChildNamed(addflightTest, "txtflightid");
        JTextField flightNameTest = (JTextField) TestUtils.getChildNamed(addflightTest, "txtflightname");
        JComboBox flightSourceTest = (JComboBox) TestUtils.getChildNamed(addflightTest, "txtsource");
        JComboBox flightDepartTest = (JComboBox) TestUtils.getChildNamed(addflightTest, "txtdepart");
        JTextField flightDepTimeTest = (JTextField) TestUtils.getChildNamed(addflightTest, "txtdtime");
        JTextField flightArrTimeTest = (JTextField) TestUtils.getChildNamed(addflightTest, "txtarrtime");
        JTextField flightChargeTest = (JTextField) TestUtils.getChildNamed(addflightTest, "txtflightcharge");


        flightIDTest.setText("testID");
        flightNameTest.setText("testFlightName");
        flightSourceTest.getEditor().setItem("testSource");
        flightDepartTest.getEditor().setItem("testDepart");
        flightDepTimeTest.setText("testDepTime");
        flightArrTimeTest.setText("testArrTime");
        flightChargeTest.setText("testFlightCharge");


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/airline","root","");
            pst = con.prepareStatement("DELETE FROM flight WHERE id='testID'");
            pst.executeUpdate();
            pst = con.prepareStatement("insert into flight(id,flightname,source,depart,date,deptime,arrtime,flightcharge)values(?,?,?,?,?,?,?,?)");

            pst.setString(1, flightIDTest.getText());
            pst.setString(2, flightNameTest.getText());
            pst.setString(3, (String)flightSourceTest.getEditor().getItem());
            pst.setString(4, (String)flightDepartTest.getEditor().getItem());
            pst.setString(5, "testDate");
            pst.setString(6, flightDepTimeTest.getText());
            pst.setString(7, flightArrTimeTest.getText());
            pst.setString(8, flightChargeTest.getText());

            pst.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(addflight.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(addflight.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            Statement testStmt = con.createStatement();
            ResultSet rs = testStmt.executeQuery("select * from flight where flightname = 'testFlightName'");
            rs.next();
            String compare[] = {"testID","testFlightName","testSource","testDepart","testDate","testDepTime","testArrTime","testFlightCharge"};
            for (int i=1;i<=8;i++) {
                //System.out.println(compare[i-1]);
                //System.out.println(rs.getString(i));
                Assert.assertEquals(compare[i-1], rs.getString(i));
            }

            pst = con.prepareStatement("DELETE FROM flight WHERE id='testID'");
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

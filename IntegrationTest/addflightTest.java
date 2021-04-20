import javax.swing.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class addflightTest {

    @BeforeMethod
    public void beforeMethod() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void verifyNewFlight() {

        try {
            String testID = "testID";
            String testFlight = "testFlight";

            Connection mockConnection = Mockito.mock(Connection.class);
            Statement mockStatement = Mockito.mock(Statement.class);
            PreparedStatement mockPStatement = Mockito.mock(PreparedStatement.class);
            ResultSet mockResultSet = Mockito.mock(ResultSet.class);

            when(mockResultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
            when(mockResultSet.getString(1)).thenReturn(testID);

            when(mockConnection.prepareStatement(any(String.class))).thenReturn(mockPStatement);
            when(mockConnection.createStatement()).thenReturn(mockStatement);
            when(mockStatement.executeQuery("select id from flight where flightname = 'testFlight'")).thenReturn(mockResultSet);

            mockPStatement = mockConnection.prepareStatement("insert into flight(id,flightname,source,depart,date,deptime,arrtime,flightcharge)values(?,?,?,?,?,?,?,?)");

            mockPStatement.setString(1, testID);
            mockPStatement.setString(2, testFlight);
            mockPStatement.setString(3, "testSource");
            mockPStatement.setString(4, "testDepart");
            mockPStatement.setString(5, "testDate");
            mockPStatement.setString(6, "testDeptime");
            mockPStatement.setString(7, "testArrtime");
            mockPStatement.setString(8, "testFlightCharge");

            mockPStatement.executeUpdate();

            mockStatement = mockConnection.createStatement();
            ResultSet rs = mockStatement.executeQuery("select id from flight where flightname = '"+testFlight+"'");
            rs.next();
            String foundId = rs.getString(1);
            //System.out.println(foundId);
            Assert.assertEquals("testID", foundId); // Compares flight id before and after adding flight to the database
            // Assert.assertEquals(id, "negative"); // Always results in comparison failure - used for testing
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception cf) {
            cf.printStackTrace();
        }
        return;
    }

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
            //System.out.println(foundId);
            //System.out.println(idInsert);
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

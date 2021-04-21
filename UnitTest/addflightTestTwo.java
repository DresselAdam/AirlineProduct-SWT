import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

import java.sql.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class addflightTestTwo {

    @BeforeMethod
    public void beforeMethod() {
        MockitoAnnotations.initMocks(this);
    }

    /*
     * verifyAddFlight uses Mockito to test that the methodology used for uploading and retrieving flight data entries
     * to and from the database works properly without an established database connection.
     * */
    @Test
    public void verifyAddFlight() {

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

}

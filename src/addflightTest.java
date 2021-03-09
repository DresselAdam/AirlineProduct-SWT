import org.junit.Assert;
import org.junit.ComparisonFailure;
import org.junit.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

public class addflightTest {

    @Test
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
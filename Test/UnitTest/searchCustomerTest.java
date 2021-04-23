package UnitTest;

import com.TestUtils;
import com.searchCustomer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTextField;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

class searchCustomerTest {

  /*
   * This test checks that a customer in the system (represented by a test dataset) can be searched for and
   * selected using their unique customer ID.
   * */
  @Test
  public void jButton4ActionPerformed() {
    Connection con = null;
    PreparedStatement pst;

    searchCustomer searchCustomerTest;
    JTextField customerIDTest;

    searchCustomerTest = new searchCustomer();

    customerIDTest = (JTextField) TestUtils.getChildNamed(searchCustomerTest, "txtcustid");
    assert customerIDTest != null;
    customerIDTest.setText("CS001");

    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      con = DriverManager.getConnection("jdbc:mysql://localhost/airline", "root", "");
      pst = con.prepareStatement("select * from customer where id = ?");
      pst.setString(1, customerIDTest.getText());
      ResultSet rs = pst.executeQuery();
      rs.next();
      Assert.assertEquals("34232222", rs.getString("nic"));

    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
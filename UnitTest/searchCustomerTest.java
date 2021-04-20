import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.JTextField;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

class searchCustomerTest {

  /*
   * This test for the customer ID being correct and displaying the correct information.
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
      Class.forName("com.mysql.jdbc.Driver");
      con = DriverManager.getConnection("jdbc:mysql://localhost/airline", "root", "");
      pst = con.prepareStatement("select * from customer where id = ?");
      pst.setString(1, String.valueOf(customerIDTest));
      ResultSet rs = pst.executeQuery();

      Assert.assertEquals("34232222", rs.getString("nic"));

    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
package UnitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.userCreation;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserCreationTest {
  // Declare and Create user object
  private userCreation add;

  // Initialize the user creation object
  @BeforeEach
  public void userBuild() {
    add = new userCreation();
  }

  @Test
  public void userButtonTest() {

    add.addButtonActionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, ""));
    add.cancelButtonActionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, ""));
  }

  @Test
  public void validUserTest() {
    // Sample inputs
    String firstname = "Zach";
    String lastname = "Maroney";
    String username = "testuser";
    String password = "testpass";
    Connection con = null;

    String output = add.add(firstname, lastname, username, password, con, "com.mysql.jdbc.Driver");
    assertEquals("true", output);
  }

  @Test
  public void invalidUserTest() {
    // Sample inputs
    String firstname = "Zach";
    String lastname = "Maroney";
    String username = "testuser";
    String password = "testpass";
    Connection con = null;

    String output = add.add(firstname, lastname, username, password, con, "");
    assertEquals("error", output);
  }

  @Test
  public void emptyAutoIDTest() {

    String id = add.autoID("com.mysql.jdbc.Driver", true);
    assertEquals("UO001", id);

  }

  @Test
  public void validAutoIDTest() {

    String id = add.autoID("com.mysql.jdbc.Driver", false);
    assertTrue(id.substring(0,2).equals("UO"));
    assertTrue(id.length() == 5);
  }

  @Test
  public void invalidAutoIDTest() {

    String id = add.autoID("", false);
    assertEquals("error", id);

  }

  /**
   * Purpose: An AfterEach method that handles the removal of users from user table in the database in order to prevent
   * the table from being filled unnecessarily from the earlier test cases.
   * @throws ClassNotFoundException - an exception to be thrown in the case a proper connection to the database isn't made.
   * @throws SQLException - an exception thrown in the case that a proper connection to the database isn't made.
   */
  @AfterEach
  public void removeUser() throws ClassNotFoundException, SQLException {

    Connection con = null;
    PreparedStatement pst;
    String idOne = "UO006";

    Class.forName("com.mysql.jdbc.Driver");
    con = DriverManager.getConnection("jdbc:mysql://localhost/airline", "root", "");
    pst = con.prepareStatement("DELETE FROM user WHERE id = ?");

    pst.setString(1, idOne);

    pst.executeUpdate();
  }

  // Tear down the user creation object
  @AfterEach
  public void userTear() {
    add = null;
  }
}
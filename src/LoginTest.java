import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.function.Supplier;

class LoginTest {

    @Test
    void jButton1ActionPerformed() {

        Login loginTest;
        JTextField userTest;
        JPasswordField passTest;
        String expResult;

        loginTest = new Login();

        userTest = (JTextField) TestUtils.getChildNamed(loginTest, "txtuser");
        passTest = (JPasswordField) TestUtils.getChildNamed(loginTest, "txtpass");
        userTest.setText("john");
        passTest.setText("123");

        assertEquals("john", userTest.getText());
        //assertEquals("" , userTest.getText());

    }
}
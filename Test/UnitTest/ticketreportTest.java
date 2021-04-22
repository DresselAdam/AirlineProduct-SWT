package UnitTest;

import static org.junit.jupiter.api.Assertions.*;

import javax.swing.JButton;

import com.TestUtils;
import com.ticketreport;
import org.junit.jupiter.api.Test;

class ticketreportTest {

    @Test
    void loadData() {
        ticketreport tr = new ticketreport();

        assertDoesNotThrow(() -> tr.LoadData());
    }


    @Test
    void successCancel() {
        ticketreport tr = new ticketreport();
        JButton cancelButton = (JButton) TestUtils.getChildNamed(tr, "cancel");

        assertDoesNotThrow(() -> cancelButton.doClick());
    }
}
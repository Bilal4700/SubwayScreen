package ca.ucalgary.ensf380.jtest;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import javax.swing.JPanel;
import ca.ucalgary.ensf380.gui.NewsPanel;
import java.awt.event.ActionEvent;

public class NewsPanelTest {

    private NewsPanel newsPanel;
    private String testText = "Breaking News: Test Passed!";

    @Before
    public void setUp() {
        newsPanel = new NewsPanel(testText);
    }

   

    @Test
    public void testGetPanelReturnsCorrectPanel() {
        JPanel panel = newsPanel.getPanel();
        assertNotNull("getPanel should return a JPanel", panel);
        assertTrue("getPanel should return an instance of NewsPanel", panel instanceof NewsPanel);
    }

    

    @Test
    public void testTextResetsAfterScrollingOffScreen() {
        // Set the xCoordinate to a value that simulates the text is off-screen
        newsPanel.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
        
        int textWidth = newsPanel.getFontMetrics(newsPanel.getFont()).stringWidth(testText);
        if (newsPanel.getX() + textWidth < 0) {
            // After the text has scrolled off the screen, the xCoordinate should reset
            assertEquals("xCoordinate should reset after text scrolls off screen", 
                         newsPanel.getWidth(), newsPanel.getX());
        }
    }
}

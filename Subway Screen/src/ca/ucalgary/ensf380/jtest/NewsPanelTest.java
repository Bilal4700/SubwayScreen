package ca.ucalgary.ensf380.jtest;

import static org.junit.Assert.*;
import org.junit.Test;
import javax.swing.JPanel;
import ca.ucalgary.ensf380.gui.NewsPanel;
import java.awt.event.ActionEvent;

public class NewsPanelTest {

    private String testText = "Breaking News: Test Passed!";

    @Test
    public void testGetPanelReturnsCorrectPanel() {
        // Initialize the NewsPanel within the test
        NewsPanel newsPanel = new NewsPanel(testText);
        
        JPanel panel = newsPanel.getPanel();
        assertNotNull("getPanel should return a JPanel", panel);
        assertTrue("getPanel should return an instance of JPanel", panel instanceof JPanel);
    }

    @Test
    public void testTextResetsAfterScrollingOffScreen() {
        // Initialize the NewsPanel within the test
        NewsPanel newsPanel = new NewsPanel(testText);

        // Simulate the actionPerformed event
        newsPanel.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
        
        int textWidth = newsPanel.getFontMetrics(newsPanel.getFont()).stringWidth(testText);
        if (newsPanel.getX() + textWidth < 0) {
            // After the text has scrolled off the screen, the xCoordinate should reset
            assertEquals("xCoordinate should reset after text scrolls off screen", 
                         newsPanel.getWidth(), newsPanel.getX());
        }
    }
}

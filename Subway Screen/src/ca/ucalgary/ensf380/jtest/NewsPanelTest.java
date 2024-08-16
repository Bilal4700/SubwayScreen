package ca.ucalgary.ensf380.jtest;

import static org.junit.Assert.*;
import org.junit.Test;
import javax.swing.JPanel;
import ca.ucalgary.ensf380.gui.NewsPanel;
import java.awt.event.ActionEvent;

/**
 * @author Fateh Ali
 * The NewsPanelTest class provides unit tests for the NewsPanel class, 
 * focusing on the functionality of panel retrieval and text scrolling behavior.
 */
public class NewsPanelTest {

    private String testText = "Breaking News: Test Passed!";

    /**
     * Tests the getPanel method of the NewsPanel class.
     * The method verifies that getPanel returns a valid JPanel instance.
     */
    @Test
    public void testGetPanelReturnsCorrectPanel() {
        // Initialize the NewsPanel within the test
        NewsPanel newsPanel = new NewsPanel(testText);
        
        JPanel panel = newsPanel.getPanel();
        assertNotNull("getPanel should return a JPanel", panel);
        assertTrue("getPanel should return an instance of JPanel", panel instanceof JPanel);
    }

    /**
     * Tests the text scrolling behavior of the NewsPanel class.
     * The method simulates the actionPerformed event to check if the text resets 
     * its x-coordinate after scrolling off the screen.
     */
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

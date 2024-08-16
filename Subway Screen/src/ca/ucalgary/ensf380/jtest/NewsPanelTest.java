package ca.ucalgary.ensf380.jtest;

import static org.junit.Assert.*;
import org.junit.Test;
import javax.swing.JPanel;
import ca.ucalgary.ensf380.gui.NewsPanel;
import ca.ucalgary.ensf380.components.News;
import java.awt.event.ActionEvent;

/**
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
        // Mock the News object with the test text
        News mockNews = new News() {
            @Override
            public String getNews() {
                return testText;
            }
        };

        // Initialize the NewsPanel with the mocked News object
        NewsPanel newsPanel = new NewsPanel(mockNews);
        
        JPanel panel = newsPanel.getPanel();
        assertNotNull("getPanel should return a JPanel", panel);
        assertTrue("getPanel should return an instance of JPanel", panel instanceof JPanel);
    }

    
}

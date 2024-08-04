package ca.ucalgary.ensf380.gui;

import javax.swing.*;
import ca.ucalgary.ensf380.components.News;
import java.awt.*;

public class SubwayScreen {
    private JFrame frame;
    private WeatherAndTimePanel weatherAndTimePanel;
    private AdvertisementPanel advertisementPanel;
    private JPanel topPanel;
    private JPanel newsPanelContainer; // Container for news panel

    public SubwayScreen() {
        // Create a new JFrame
        frame = new JFrame("Subway Screen");
        frame.setSize(900, 600); // Increased height to accommodate news panel
        //frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize the main content pane with vertical BoxLayout
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        // Initialize topPanel with FlowLayout
        topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));

        try {
            advertisementPanel = new AdvertisementPanel();
            advertisementPanel.getPanel().setPreferredSize(new Dimension(600, 450)); // Increase size of advertisement panel
            topPanel.add(advertisementPanel.getPanel());
        } catch (Exception e) {
            System.out.println("Error initializing AdvertisementPanel: " + e.getMessage());
        }

        try {
            weatherAndTimePanel = new WeatherAndTimePanel();
            weatherAndTimePanel.getPanel().setPreferredSize(new Dimension(250, 450)); // Decrease size of weather panel
            topPanel.add(weatherAndTimePanel.getPanel());
        } catch (Exception e) {
            System.out.println("Error initializing WeatherAndTimePanel: " + e.getMessage());
        }

        // Add topPanel to the content pane
        contentPane.add(topPanel);

        // Initialize the news panel container
        newsPanelContainer = new JPanel();
        newsPanelContainer.setLayout(new BoxLayout(newsPanelContainer, BoxLayout.Y_AXIS));
        newsPanelContainer.setBorder(BorderFactory.createTitledBorder("News"));

        try {
            News news = new News();
            news.fetch();
            String myNews = news.getNews();
            NewsPanel newsPanel = new NewsPanel(myNews);
            newsPanelContainer.add(newsPanel.getPanel());
        } catch (Exception e) {
            System.out.println("Error initializing NewsPanel: " + e.getMessage());
        }

        // Add news panel container to the content pane
        contentPane.add(newsPanelContainer);

        // Make the frame visible
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // Create an instance of SubwayScreen to display the frame
        SwingUtilities.invokeLater(() -> new SubwayScreen());
    }
    
  
    	
    
}










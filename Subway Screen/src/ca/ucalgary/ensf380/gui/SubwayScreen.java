package ca.ucalgary.ensf380.gui;

import javax.swing.*;
import ca.ucalgary.ensf380.components.News;
import java.awt.*;

public class SubwayScreen {
    private JFrame frame;
    private WeatherAndTimePanel weatherAndTimePanel;
    private AdvertisementPanel advertisementPanel;
    private JPanel topPanel;
    private JPanel newsPanelContainer; 

    public SubwayScreen(String city, String countrycode) {
        // Create a new JFrame
        frame = new JFrame("Subway Screen");
        frame.setSize(900, 600); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        
        // Initialize the main content pane with vertical BoxLayout
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        // Initialize topPanel with FlowLayout
        topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));

        try {
            advertisementPanel = new AdvertisementPanel();
            advertisementPanel.getPanel().setPreferredSize(new Dimension(600, 450)); // Increase size of advertisement panel
            // Add border to advertisement panel
            advertisementPanel.getPanel();
            topPanel.add(advertisementPanel.getPanel());
            topPanel.setBackground(Color.DARK_GRAY);
        } catch (Exception e) {
            System.out.println("Error initializing AdvertisementPanel: " + e.getMessage());
        }

        try {
            weatherAndTimePanel = new WeatherAndTimePanel(city);
            weatherAndTimePanel.getPanel().setPreferredSize(new Dimension(250, 450)); // Decrease size of weather panel
            // Add border to weather and time panel
            weatherAndTimePanel.getPanel();
            topPanel.add(weatherAndTimePanel.getPanel());
        } catch (Exception e) {
            System.out.println("Error initializing WeatherAndTimePanel: " + e.getMessage());
        }

        // Add topPanel to the content pane
        contentPane.add(topPanel);

        // Initialize the news panel container
        newsPanelContainer = new JPanel();
        newsPanelContainer.setLayout(new BoxLayout(newsPanelContainer, BoxLayout.Y_AXIS));
        newsPanelContainer.setBorder(BorderFactory.createTitledBorder("NEWS"));

        try {
            News news;
            if (countrycode != null && !countrycode.isEmpty()) {
                news = new News(countrycode);
            } else {
                news = new News(); // Default constructor with "ca"
            }
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
        if (args.length < 1 || args.length > 2) {
            System.out.println("Error: Please provide one or two command-line arguments.");
            System.out.println("Usage: java ca.ucalgary.ensf380.gui.SubwayScreen <city> [countrycode]");
            return;
        }

        String city = args[0];
        String countrycode = (args.length == 2) ? args[1] : null;

        SwingUtilities.invokeLater(() -> new SubwayScreen(city, countrycode));
    }
}










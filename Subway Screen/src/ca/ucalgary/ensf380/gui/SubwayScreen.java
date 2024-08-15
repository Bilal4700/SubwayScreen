package ca.ucalgary.ensf380.gui;

import javax.swing.*;
import ca.ucalgary.ensf380.components.News;
import ca.ucalgary.ensf380.maps.*;
import ca.ucalgary.ensf380.data.DataProvider;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The SubwayScreen class creates a GUI for displaying subway information, including advertisements, weather, time, news,
 * Station Map, Small Map. It makes a frame by combining all the panels.
 */
public class SubwayScreen {
    private JFrame frame;
    private WeatherAndTimePanel weatherAndTimePanel;
    private AdvertisementPanel advertisementPanel;
    private StationMapPanel stationMapPanel;
    private JPanel topPanel;
    private JPanel newsPanelContainer;
    private boolean showMapPanel = false;
    private Timer timer;
    private SmallMapPanel smallMapPanel;
    private DataProvider dataProvider;
    private String trainNumb;

    /**
     * Constructs a SubwayScreen with the specified city, train number, and country code.
     * Initializes various panels and starts the panel switcher timer.
     * These are command line arguments.
     * 
     * @param city        the name of the city you want to know Weather and Time data
     * @param trainNumb   the train number you want to follow
     * @param countrycode the country code for fetching news
     * @throws Exception
     */
    public SubwayScreen(String city, String trainNumb, String countrycode) throws Exception {
        this.trainNumb = trainNumb;

        // Create a new JFrame
        frame = new JFrame("Subway Screen");
        frame.setSize(1000, 850);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        // Initialize topPanel with FlowLayout
        topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));

        try {
            // Initialize DataProvider
            dataProvider = new DataProvider();

            // Initialize and add the advertisement panel
            advertisementPanel = new AdvertisementPanel();
            advertisementPanel.getPanel().setPreferredSize(new Dimension(600, 450)); // Increase size of advertisement panel
            topPanel.add(advertisementPanel.getPanel());
            topPanel.setBackground(Color.DARK_GRAY);

            // Initialize the station map panel with DataProvider
            stationMapPanel = new StationMapPanel(dataProvider, trainNumb);
            stationMapPanel.setPreferredSize(new Dimension(600, 450)); // Adjust size as necessary

            // Initialize the small map panel with DataProvider
            smallMapPanel = new SmallMapPanel(dataProvider, trainNumb);
            smallMapPanel.setPreferredSize(new Dimension(600, 200)); // Adjust size as necessary

        } catch (Exception e) {
            System.out.println("Error initializing panels: " + e.getMessage());
        }

        try {
            // Initialize and add the weather and time panel
            weatherAndTimePanel = new WeatherAndTimePanel(city);
            weatherAndTimePanel.getPanel().setPreferredSize(new Dimension(300, 450)); // Decrease size of weather panel
            topPanel.add(weatherAndTimePanel.getPanel());
        } catch (Exception e) {
            System.out.println("Error initializing WeatherAndTimePanel: " + e.getMessage());
        }

        // Add topPanel to the frame
        frame.add(topPanel);

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

        // Add news panel container to the frame
        frame.add(newsPanelContainer);
        frame.add(smallMapPanel);

        // Start the panel switcher timer
        startPanelSwitcher();

        // Make the frame visible
        frame.setVisible(true);
    }

    private void startPanelSwitcher() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> {
                    // Switch panels
                    switchPanels();
                });
            }
        }, 0, 10000); // Schedule the task to run every 10 seconds
    }

    private void switchPanels() {
        topPanel.removeAll();
        if (showMapPanel) {
            topPanel.add(stationMapPanel);
            showMapPanel = false;
        } else {
            topPanel.add(advertisementPanel.getPanel());
            showMapPanel = true;
        }
        topPanel.add(weatherAndTimePanel.getPanel());
        topPanel.revalidate();
        topPanel.repaint();
    }
}

package ca.ucalgary.ensf380.gui;

import javax.swing.*;
import ca.ucalgary.ensf380.components.News;
import ca.ucalgary.ensf380.maps.LatestOutputReader;
import ca.ucalgary.ensf380.maps.StationMapPanel;
import ca.ucalgary.ensf380.maps.SmallMapPanel;
import ca.ucalgary.ensf380.maps.ReadSubwayFile;
import ca.ucalgary.ensf380.maps.Station;

import java.awt.*;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class SubwayScreen {
    private JFrame frame;
    private WeatherAndTimePanel weatherAndTimePanel;
    private AdvertisementPanel advertisementPanel;
    private StationMapPanel stationMapPanel;
    private JPanel topPanel;
    private JPanel newsPanelContainer;
    private boolean showMapPanel = true;
    private Timer timer;
    private SmallMapPanel smallMapPanel;
    

    public SubwayScreen(String city, String trainNumb, String countrycode) throws Exception {
        // Create a new JFrame
        frame = new JFrame("Subway Screen");
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        // Initialize topPanel with FlowLayout
        topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));

        try {
            // Initialize and add the advertisement panel
            advertisementPanel = new AdvertisementPanel();
            advertisementPanel.getPanel().setPreferredSize(new Dimension(600, 450)); // Increase size of advertisement panel
            topPanel.add(advertisementPanel.getPanel());
            topPanel.setBackground(Color.DARK_GRAY);

            // Initialize the station map panel
            List<Station> stations = ReadSubwayFile.readStations();
            smallMapPanel = new SmallMapPanel(stations, trainNumb);
            stationMapPanel = new StationMapPanel(stations, trainNumb);
            stationMapPanel.setPreferredSize(new Dimension(600, 450));

        } catch (Exception e) {
            System.out.println("Error initializing panels: " + e.getMessage());
        }

        try {
            // Initialize and add the weather and time panel
            weatherAndTimePanel = new WeatherAndTimePanel(city);
            weatherAndTimePanel.getPanel().setPreferredSize(new Dimension(250, 450)); // Decrease size of weather panel
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
            	// Step 4: Initialize LatestOutputReader and start updating train locations
                LatestOutputReader latestOutputReader = new LatestOutputReader(stationMapPanel, smallMapPanel);
                Thread readerThread = new Thread(latestOutputReader);
                readerThread.start();
                SwingUtilities.invokeLater(() -> switchPanels());
            }
        }, 0, 10000); // Schedule the task to run every 15 seconds
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

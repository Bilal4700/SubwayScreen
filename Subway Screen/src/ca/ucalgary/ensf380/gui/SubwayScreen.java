package ca.ucalgary.ensf380.gui;

import javax.swing.*;
import ca.ucalgary.ensf380.components.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class SubwayScreen {
    private JFrame frame;
    private AdvertisementPanel advertisementPanel;
    private WeatherAndTimePanel weatherAndTimePanel;
    private JPanel mapPanel;
    private JPanel smallMapPanel;

    private JPanel newsPanelContainer; // Container for news panel
    private JPanel topPanel; // Top panel that contains advertisement and weather panels
    private JPanel adPanelContainer; // Container for advertisement panel
    private JPanel weatherPanelContainer; // Container for weather panel

    public SubwayScreen() throws Exception {
        // Create a new JFrame
        frame = new JFrame("Subway Screen");
        frame.setSize(900, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Initialize topPanel with BoxLayout
        topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.setPreferredSize(new Dimension(900, 500)); // Set preferred size to make it larger than the news panel
        topPanel.setBackground(Color.ORANGE); // Set background color to orange

        advertisementPanel = new AdvertisementPanel();
        weatherAndTimePanel = new WeatherAndTimePanel();
        mapPanel = createMapPanel();

        adPanelContainer = new JPanel();
        adPanelContainer.setLayout(new GridBagLayout()); // Center the advertisement panel
        adPanelContainer.setPreferredSize(new Dimension(600, 500));
        adPanelContainer.add(advertisementPanel.getPanel(), new GridBagConstraints());

        weatherPanelContainer = new JPanel();
        weatherPanelContainer.setLayout(new GridBagLayout()); // Center the weather panel
        weatherPanelContainer.setPreferredSize(new Dimension(300, 500));
        weatherPanelContainer.add(weatherAndTimePanel.getPanel(), new GridBagConstraints());

        topPanel.add(adPanelContainer);
        topPanel.add(weatherPanelContainer);

        // Add topPanel to the content pane
        frame.getContentPane().add(topPanel, BorderLayout.NORTH);

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
        frame.getContentPane().add(newsPanelContainer, BorderLayout.CENTER);

        // Add Small Map panel
        smallMapPanel = createSmallMapPanel();
        frame.getContentPane().add(smallMapPanel, BorderLayout.SOUTH);

        // Start timer to alternate panels
        startPanelAlternation();

        // Make the frame visible
        frame.setVisible(true);
    }

    private JPanel createMapPanel() {
        JPanel mapPanel = new JPanel();
        mapPanel.setLayout(new BorderLayout());
        JLabel mapLabel = new JLabel("MAP", SwingConstants.CENTER);
        mapLabel.setFont(new Font("Arial", Font.BOLD, 24));
        mapPanel.add(mapLabel, BorderLayout.CENTER);
        mapPanel.setPreferredSize(new Dimension(600, 500));
        return mapPanel;
    }

    private JPanel createSmallMapPanel() {
        JPanel smallMapPanel = new JPanel();
        smallMapPanel.setLayout(new BorderLayout());
        JLabel smallMapLabel = new JLabel("Small Map", SwingConstants.CENTER);
        smallMapLabel.setFont(new Font("Arial", Font.BOLD, 24));
        smallMapPanel.add(smallMapLabel, BorderLayout.CENTER);
        smallMapPanel.setPreferredSize(new Dimension(900, 100));
        return smallMapPanel;
    }

    private void startPanelAlternation() {
        Timer timer = new Timer(true);
        timer.schedule(new TimerTask() {
            private boolean showingMap = false;

            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> {
                    adPanelContainer.removeAll();
                    if (showingMap) {
                        adPanelContainer.add(advertisementPanel.getPanel(), new GridBagConstraints());
                    } else {
                        adPanelContainer.add(mapPanel, new GridBagConstraints());
                        TimerTask switchBackTask = new TimerTask() {
                            @Override
                            public void run() {
                                SwingUtilities.invokeLater(() -> {
                                    adPanelContainer.removeAll();
                                    adPanelContainer.add(advertisementPanel.getPanel(), new GridBagConstraints());
                                    adPanelContainer.revalidate();
                                    adPanelContainer.repaint();
                                });
                            }
                        };
                        new Timer(true).schedule(switchBackTask, 5000); // Show map panel for 5 seconds
                    }
                    showingMap = !showingMap;
                    adPanelContainer.revalidate();
                    adPanelContainer.repaint();
                });
            }
        }, 0, 10000); // Switch every 10 seconds
    }

    public static void main(String[] args) {
        // Create an instance of SubwayScreen to display the frame
        SwingUtilities.invokeLater(() -> {
            try {
                new SubwayScreen();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}








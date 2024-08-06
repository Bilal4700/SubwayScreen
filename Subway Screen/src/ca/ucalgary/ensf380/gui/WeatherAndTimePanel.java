package ca.ucalgary.ensf380.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;
import ca.ucalgary.ensf380.components.WeatherAndTime;

/**
 * The WeatherAndTimePanel class creates a JPanel that displays weather information and the current time for a specified city.
 */
public class WeatherAndTimePanel extends JPanel {

    private JLabel locationLabel;
    private JLabel windLabel;
    private JLabel humidityLabel;
    private JLabel timeLabel;
    private JLabel tempLabel;
    private JPanel contentPanel;
    private WeatherAndTime weather;
    private LocalTime currentTime;
    public String city;
    
    /**
     * Constructs a WeatherAndTimePanel with the specified city.
     * initialize gui
     * 
     * @param city the name of the city for which to display weather information
     * @throws Exception if error 
     */

    public WeatherAndTimePanel(String city) throws Exception {
        this.city = city;
        initializeUI();
    }
    
    /**
     * Initializes the UI components and fetches the weather data.
     * Sets up the panel layout and starts the timer for updating the time.
     * 
     * @throws Exception if an error occurs during initialization
     */

    public void initializeUI() throws Exception {
        // Fetching Weather Data
        weather = new WeatherAndTime(city);
        weather.fetch();
        // Changed the initial time from the fetched weather data to LocalTime format so we can later run it
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
        currentTime = LocalTime.parse(weather.getTime(), timeFormat);

        // Making panel
        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        locationLabel = new JLabel();
        windLabel = new JLabel();
        humidityLabel = new JLabel();
        timeLabel = new JLabel();
        tempLabel = new JLabel(); 

        
        
        Font font1 = new Font("Helvetica", Font.BOLD, 55);  
        Font font = new Font("Helvetica", Font.BOLD, 25);
        locationLabel.setFont(font1);
        locationLabel.setForeground(Color.DARK_GRAY);
        tempLabel.setFont(font);
        tempLabel.setForeground(Color.DARK_GRAY);
        windLabel.setFont(font);
        windLabel.setForeground(Color.DARK_GRAY);
        humidityLabel.setFont(font);
        humidityLabel.setForeground(Color.DARK_GRAY);
        timeLabel.setFont(font);
        timeLabel.setForeground(Color.LIGHT_GRAY);
        
        locationLabel.setBorder(new EmptyBorder(10, 0, 10, 0));
        tempLabel.setBorder(new EmptyBorder(10, 0, 10, 0));
        windLabel.setBorder(new EmptyBorder(10, 0, 10, 0));
        humidityLabel.setBorder(new EmptyBorder(10, 0, 10, 0));
        timeLabel.setBorder(new EmptyBorder(10, 0, 10, 0));

        updateWeatherAndTime();
        updateWeatherAndTime();

        contentPanel.add(locationLabel);
        contentPanel.add(tempLabel);
        contentPanel.add(windLabel);
        contentPanel.add(humidityLabel);
        contentPanel.add(timeLabel);

        add(contentPanel, BorderLayout.CENTER);

        // Start a timer to update the time every second
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> updateTimeLabel());
            }
        }, 0, 1000);
    }
    
    /**
     * Updates the weather information labels.
     */

    public void updateWeatherAndTime() {
        locationLabel.setText(" "+weather.getLocation());
        tempLabel.setText("      "+weather.getConditionIcon() + " " + weather.getTemperature());
        windLabel.setText("    Wind  " + weather.getWind());
        humidityLabel.setText("     Humidity: " + weather.getHumidity());

        updateTimeLabel();
    }
    
    /**
     * We got the time of location in string form and we changed it into LocalTime Format so we can add 1 secind
     * after we call it again
     * Check the timer code above it calls this method every second
     */

    public void updateTimeLabel() {
        // Get the current system time
    	String time = weather.getTime();
        currentTime = currentTime.plusSeconds(1);
        // Format the new time and update the label
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
        timeLabel.setText("     Time: " + timeFormat.format(currentTime));
    }
    
    /**
     * @return the JPanel containing the weather and time display
     */

    public JPanel getPanel() {
        return contentPanel;
    }
}





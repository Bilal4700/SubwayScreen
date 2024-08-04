package ca.ucalgary.ensf380.gui;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import ca.ucalgary.ensf380.components.WeatherAndTime;

public class WeatherAndTimePanel extends JPanel {

    private JLabel locationLabel;
    private JLabel windLabel;
    private JLabel humidityLabel;
    private JLabel timeLabel;
    private JPanel contentPanel;
    private WeatherAndTime weather;
    private Date currentTime;

    public WeatherAndTimePanel() throws Exception {
        initializeUI();
    }

    public void initializeUI() throws Exception {
        // Fetching Weather Data
        weather = new WeatherAndTime("Calgary,CA");
        weather.fetch();
        // Parse the initial time from the fetched weather data
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        currentTime = timeFormat.parse(weather.getTime());

        // Making panel
        setLayout(new BorderLayout());
        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        locationLabel = new JLabel();
        windLabel = new JLabel();
        humidityLabel = new JLabel();
        timeLabel = new JLabel();

        Font font = new Font("Arial", Font.PLAIN, 18);
        locationLabel.setFont(font);
        locationLabel.setForeground(Color.DARK_GRAY);
        windLabel.setFont(font);
        windLabel.setForeground(Color.RED);
        humidityLabel.setFont(font);
        humidityLabel.setForeground(Color.RED);
        timeLabel.setFont(font);
        timeLabel.setForeground(Color.DARK_GRAY);

        updateWeatherAndTime();

        contentPanel.add(locationLabel);
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

    public void updateWeatherAndTime() {
        locationLabel.setText(weather.getLocation() + " " + weather.getConditionIcon() + " " + weather.getTemperature());
        windLabel.setText("Wind: " + weather.getWind());
        humidityLabel.setText("Humidity: " + weather.getHumidity());
        updateTimeLabel();
    }

    public void updateTimeLabel() {
        // Increment the time by one second
        currentTime.setTime(currentTime.getTime() + 1000);
        // Format the new time and update the label
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        timeLabel.setText("Local Time: " + timeFormat.format(currentTime));
    }

    public JPanel getPanel() {
        return contentPanel;
    }
}





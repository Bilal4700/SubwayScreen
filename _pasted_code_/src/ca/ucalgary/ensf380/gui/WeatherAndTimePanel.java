package ca.ucalgary.ensf380.gui;

import javax.swing.*;
import java.awt.*;
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

    public WeatherAndTimePanel() throws Exception {
        initializeUI();
    }

    public void initializeUI() throws Exception {
        // Fetching Weather Data
        weather = new WeatherAndTime("Calgary,CA");
        weather.fetch();

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
        try {
            weather.fetch();
            timeLabel.setText("Local Time: " + weather.getTime());
        } catch (Exception e) {
            timeLabel.setText("Failed to update time");
        }
    }

    public JPanel getPanel() {
        return contentPanel;
    }
}

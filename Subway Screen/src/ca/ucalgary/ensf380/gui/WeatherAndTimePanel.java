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

    public WeatherAndTimePanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        locationLabel = new JLabel();
        windLabel = new JLabel();
        humidityLabel = new JLabel();
        timeLabel = new JLabel();

        add(locationLabel);
        add(windLabel);
        add(humidityLabel);
        add(timeLabel);

        Font font = new Font("Arial", Font.PLAIN, 18);
        locationLabel.setFont(font);
        locationLabel.setForeground(Color.DARK_GRAY); // Setting color for location label
        windLabel.setFont(font);
        windLabel.setForeground(Color.RED); // Setting color for wind label
        humidityLabel.setFont(font);
        humidityLabel.setForeground(Color.RED); // Setting color for humidity label
        timeLabel.setFont(font);
        timeLabel.setForeground(Color.DARK_GRAY); // Setting color for time label
    }

    public void updateWeatherAndTime(WeatherAndTime weatherFetcher) {
        locationLabel.setText(weatherFetcher.getLocation() + " " + weatherFetcher.getConditionIcon() + " " + weatherFetcher.getTemperature());
        windLabel.setText("Wind: " + weatherFetcher.getWind());
        humidityLabel.setText("Humidity: " + weatherFetcher.getHumidity());
    }

    public void LiveClock() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
                String currentTime = timeFormat.format(new Date());
                timeLabel.setText("Current Time: " + currentTime);
            }
        };
        timer.scheduleAtFixedRate(task, 0, 1000);
    }

}


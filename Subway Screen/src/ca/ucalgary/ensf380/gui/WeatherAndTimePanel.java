package ca.ucalgary.ensf380.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import ca.ucalgary.ensf380.components.WeatherAndTime;

public class WeatherAndTimePanel extends JPanel {

    private JLabel locationLabel;
    private JLabel windLabel;
    private JLabel humidityLabel;
    private JLabel timeLabel;
    private String updatedTime;

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
        locationLabel.setForeground(Color.DARK_GRAY);
        windLabel.setFont(font);
        windLabel.setForeground(Color.RED);
        humidityLabel.setFont(font);
        humidityLabel.setForeground(Color.RED);
        timeLabel.setFont(font);
        timeLabel.setForeground(Color.DARK_GRAY);
    }

    public void updateWeatherAndTime(WeatherAndTime location) throws Exception {
        locationLabel.setText(location.getLocation() + " " + location.getConditionIcon() + " " + location.getTemperature());
        windLabel.setText("Wind: " + location.getWind());
        humidityLabel.setText("Humidity: " + location.getHumidity());
        timeLabel.setText("Local Time: " + location.getTime());
    }

    public void liveUpdate(WeatherAndTime location) throws Exception {

 

    	
    }
}
    





package ca.ucalgary.ensf380.test;

import ca.ucalgary.ensf380.components.WeatherAndTime;
import ca.ucalgary.ensf380.gui.WeatherAndTimePanel;

import javax.swing.*;

public class TestWeatherAndTimePanel {

    public static void createAndShowGUI() {
        JFrame frame = new JFrame("Weather and Time");
        WeatherAndTimePanel weatherAndTimePanel = new WeatherAndTimePanel();
        WeatherAndTime weatherFetcher = new WeatherAndTime("Toronto");

        try {
            weatherFetcher.fetch();

            weatherAndTimePanel.updateWeatherAndTime(weatherFetcher);

        } catch (Exception e) {
            e.printStackTrace();
        }

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(weatherAndTimePanel);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}


package ca.ucalgary.ensf380.test;

import javax.swing.*;
import ca.ucalgary.ensf380.gui.WeatherAndTimePanel;

public class TestWeatherAndTimePanel {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Weather and Time Test");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);

            WeatherAndTimePanel weatherAndTimePanel;
            try {
            	
            	//This is how to retreive Panel
                weatherAndTimePanel = new WeatherAndTimePanel();
                frame.add(weatherAndTimePanel.getPanel());
            } catch (Exception e) {
                System.out.println("Error initializing WeatherAndTimePanel: " + e.getMessage());
                return;
            }

            frame.setVisible(true);
        });
    }
}




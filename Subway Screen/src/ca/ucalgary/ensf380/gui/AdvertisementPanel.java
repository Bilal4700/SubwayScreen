package ca.ucalgary.ensf380.gui;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import ca.ucalgary.ensf380.components.Advertisement;

public class AdvertisementPanel extends JFrame {
    private ArrayList<String> gifPaths;
    private JLabel label;
    private int currentIndex = 0;

    public AdvertisementPanel() throws SQLException {
        Advertisement adManager = new Advertisement();
        adManager.createConnection();  // Ensure connection is established
        adManager.fetch();           // Fetch ads from the database
        setTitle("Advertisement Panel");
        setSize(300, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        label = new JLabel();
        label.setHorizontalAlignment(JLabel.CENTER);
        getContentPane().add(label, BorderLayout.CENTER);

        // Parse ad paths into a list
        String adPaths = adManager.getAdPaths();
        if (adPaths != null && !adPaths.isEmpty()) {
            gifPaths = new ArrayList<>(Arrays.asList(adPaths.split("\n")));
        } else {
            gifPaths = new ArrayList<>();
        }

        updateLabel();

        // Set up a timer to cycle through gifs
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                updateLabel();
            }
        }, 0, 10000); // Change image every 10 seconds
    }

    private void updateLabel() {
        if (gifPaths.isEmpty()) {
            label.setIcon(null);
            label.setText("No advertisements available");
            return;
        }
        // Ensure the path is valid and file exists before trying to create an ImageIcon
        String currentPath = gifPaths.get(currentIndex);
        File imgFile = new File(currentPath);
        if (imgFile.exists()) {
            ImageIcon icon = new ImageIcon(currentPath);
            label.setIcon(icon);
            label.setText(""); // Clear any text
        } else {
            label.setIcon(null);
            label.setText("File not found: " + currentPath);
        }
        currentIndex = (currentIndex + 1) % gifPaths.size(); // Cycle through the list
    }


}


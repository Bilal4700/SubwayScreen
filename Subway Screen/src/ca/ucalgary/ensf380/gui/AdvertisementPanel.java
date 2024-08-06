package ca.ucalgary.ensf380.gui;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import ca.ucalgary.ensf380.components.Advertisement;

public class AdvertisementPanel {
    private JPanel panel;
    private JLabel label;
    private ArrayList<String> gifPaths;
    private int currentIndex = 0;

    public AdvertisementPanel() throws SQLException {
        panel = new JPanel(new BorderLayout());
        label = new JLabel();
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        
        panel.add(label, BorderLayout.CENTER);

        Advertisement adManager = new Advertisement();
        adManager.createConnection();  // Ensure connection is established
        adManager.fetch();             // Fetch ads from the database

        // Parse ad paths into a list
        String adPaths = adManager.getAdPaths();
        if (adPaths != null && !adPaths.isEmpty()) {
            gifPaths = new ArrayList<>(Arrays.asList(adPaths.split("\n")));
        } else {
            gifPaths = new ArrayList<>();
        }

        // Set up a timer to cycle through Gifs
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> updateLabel());
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
        File file = new File(currentPath);
        if (file.exists()) {
            ImageIcon icon = new ImageIcon(currentPath);
            label.setIcon(icon);
            label.setText(""); // Clear any text
        } else {
            label.setIcon(null);
            label.setText("File not found: " + currentPath);
        }
        currentIndex = (currentIndex + 1) % gifPaths.size(); // Cycle through the list
    }

    public JPanel getPanel() {
        return panel;
    }
}



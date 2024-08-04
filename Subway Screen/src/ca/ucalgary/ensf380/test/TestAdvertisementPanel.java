package ca.ucalgary.ensf380.test;

import javax.swing.*;
import ca.ucalgary.ensf380.gui.AdvertisementPanel;
import java.sql.SQLException;

public class TestAdvertisementPanel {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                JFrame frame = new JFrame("Advertisement Panel Test");
                frame.setSize(400, 300);
                frame.setResizable(false);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                // This is how to retrieve the panel
                AdvertisementPanel adPanel = new AdvertisementPanel();
                frame.add(adPanel.getPanel());
                frame.setVisible(true);
                
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}

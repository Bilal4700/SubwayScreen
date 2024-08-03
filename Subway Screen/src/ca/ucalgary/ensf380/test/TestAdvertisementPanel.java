package ca.ucalgary.ensf380.test;

import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import ca.ucalgary.ensf380.gui.AdvertisementPanel;

public class TestAdvertisementPanel {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new AdvertisementPanel().setVisible(true);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Failed to load advertisements: " + e.getMessage());
            }
        });
    }
}


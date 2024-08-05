package ca.ucalgary.ensf380.maps;


import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList; // Ensure this import is present

public class StationMapPanel extends JPanel {
    private List<Station> stations;
    private List<Train> trains;

    public StationMapPanel(List<Station> stations) {
        this.stations = stations;
        this.trains = new ArrayList<>(); // Initialize the ArrayList
        setPreferredSize(new Dimension(800, 600)); // Set preferred size for the panel
    }

    public synchronized void updateTrains(List<Train> newTrains) {
        this.trains = newTrains;
        repaint(); // Repaint the panel to reflect the updated train data
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
     
        // Draw stations
        for (Station station : stations) {
            int x = (int) station.getX();
            int y = (int) station.getY();
            String stationCode = station.getStationCode();

            if (stationCode.startsWith("R")) {
                g.setColor(Color.RED);
            } else if (stationCode.startsWith("B")) {
                g.setColor(Color.BLUE);
            } else if (stationCode.startsWith("G")) {
                g.setColor(Color.GREEN);
            } else {
                g.setColor(Color.BLACK);
            }

            g.fillOval(x, y, 10, 10); // Draw the dot
        }
        // Draw trains
        for (Train train : trains) {
            for (Station station : stations) {
                String i = station.getStationCode();
                String atStation = train.getAtStation();

                if (i.equals(atStation)) {
                    int X = (int) station.getX();
                    int Y = (int) station.getY();
                    g.setColor(Color.BLACK);
                    g.fillRect(X - 15, Y - 10, 30, 20); // Draw the rectangle
                }
            }
        }

        
    }
}

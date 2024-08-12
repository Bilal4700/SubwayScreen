package ca.ucalgary.ensf380.maps;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The StationMapPanel class creates a JPanel that displays a map of stations and highlights trains.
 */
public class StationMapPanel extends JPanel {
    private List<Station> stations;
    private List<Train> trains;
    private String trainNumb;
    private LatestOutputReader latestOutputReader; 

    /**
     * Constructs a StationMapPanel with the specified list of stations and train number to highlight.
     * 
     * @param stations the list of stations
     * @param trainNumb the train number to highlight
     */
    public StationMapPanel(List<Station> stations, String trainNumb) {
        this.stations = stations;
        this.trainNumb = trainNumb;
        this.latestOutputReader = new LatestOutputReader(); // Initialize LatestOutputReader

        // Start the LatestOutputReader in a separate thread
        Thread readerThread = new Thread(latestOutputReader);
        readerThread.start(); 

        // Set up a timer to update the train data periodically
        Timer timer = new Timer(5000, new ActionListener() { // Update every 5 seconds
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTrains(latestOutputReader.getNewTrains()); // Update trains from the latest output
                
            }
        });
        timer.start(); // Start the timer
    }


    /**
     * Updates the list of trains and repaints the panel to reflect the updated train data.
     * 
     * @param newTrains the new list of trains
     */
    public synchronized void updateTrains(List<Train> newTrains) {
        this.trains = newTrains;
        repaint(); // Repaint the panel to reflect the updated train data
    }


    /**
     * 
     * @param g the Graphics object used for painting
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Paints every station with an oval of the respective colors of the line
        for (Station station : stations) {
            int x = (int) station.getX();
            int y = (int) station.getY();
            double widthScale = 600.0 / 1200.0;
            double heightScale = 450.0 / 700.0;

            int X = (int) (x * widthScale);
            int Y = (int) (y * heightScale);
            String stationCode = station.getStationCode();

            if (stationCode.startsWith("R")) {
                g.setColor(Color.RED);
            } else if (stationCode.startsWith("B")) {
                g.setColor(Color.BLUE);
            } else if (stationCode.startsWith("G")) {
                g.setColor(Color.GREEN);
            }
            g.fillOval(X, Y, 6, 6);
        }

        // Paints the station with a rectangle where there is a train. Selected train is orange; others are black
        for (Train train : trains) {
            for (Station station : stations) {
                String i = station.getStationCode();
                String atStation = train.getAtStation();
                if (i.equals(atStation)) {
                    int x = (int) station.getX();
                    int y = (int) station.getY();
                    double widthScale = 600.0 / 1200.0;
                    double heightScale = 450.0 / 700.0;
                    int X = (int) (x * widthScale);
                    int Y = (int) (y * heightScale);
                    if (trainNumb.equals(train.getTrainNum())) {
                        g.setColor(Color.ORANGE);
                        g.fillRect(X - 10, Y - 5, 20, 10);
                    } else {
                        g.setColor(Color.BLACK);
                        g.fillRect(X - 10, Y - 5, 20, 10);
                    }
                }
            }
        }
    }
    public LatestOutputReader getLatestOutputReader() {
        return latestOutputReader;
    }
}

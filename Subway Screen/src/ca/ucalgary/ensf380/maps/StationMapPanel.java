package ca.ucalgary.ensf380.maps;

import javax.swing.*;

import ca.ucalgary.ensf380.data.DataProvider;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

/**
 * The StationMapPanel class creates a JPanel that displays a map of stations and highlights trains.
 */
public class StationMapPanel extends JPanel {
    private List<Station> stationslist;
    private List<Train> trainslist;
    private String trainNumb;
    private DataProvider dataProvider;

    /**
     * Constructs a StationMapPanel with the specified DataProvider and train number to highlight.
     * 
     * @param dataProvider the DataProvider instance to fetch stations and trains
     * @param trainNumb the train number to highlight
     */
    public StationMapPanel(DataProvider dataProvider, String trainNumb) {
        this.dataProvider = dataProvider;
        this.stationslist = dataProvider.getStations();
        this.trainNumb = trainNumb;
        this.trainslist = new ArrayList<>(); // Initialize to an empty list

        // Start reading trains in a separate thread
        dataProvider.startReadingTrains();
     // Set up a timer to update the train data periodically
        Timer timer = new Timer(5000, new ActionListener() { // Update every 5 seconds
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTrains(dataProvider.getTrains()); // Update trains from the latest output
            }
        });
        timer.start(); // Start the timer
    }
    /**
     * Paints the stations and trains on the panel.
     * 
     * @param g the Graphics object used for painting
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Paints every station with an oval of the respective colors of the line
        for (Station station : stationslist) {
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
        for (Train train : trainslist) {
            for (Station station : stationslist) {
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

    public synchronized void updateTrains(List<Train> newTrains) {
        this.trainslist = newTrains;
        repaint(); // Repaint the panel to reflect the updated train data
    }
    
	public List<Train> getTrains() {
		return trainslist;
	}
}

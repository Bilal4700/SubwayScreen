package ca.ucalgary.ensf380.maps;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class SmallMapPanel extends JPanel {
    private List<Station> stations;
    private List<Train> trains;
    private String trainNumb;

    public SmallMapPanel(List<Station> stations, String trainNumb ) {
        this.stations = stations;
        this.trains = new ArrayList<>();
        this.trainNumb = trainNumb;
    }

    public synchronized void updateTrain(List<Train> newTrains) {
        this.trains = newTrains;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Train train : trains) {
            if (trainNumb.equals(train.getTrainNum())) {
                List<Station> stationList = new ArrayList<>();

                for (Station station : stations) {
                    if (train.getAtStation().equals(station.getStationCode())) {
                        // Current station found
                        String currentStationName = station.getStationName();
                        String trainDirection = train.getTrainDirection();

                        // Find the index of the current station in the stations list
                        int currentIndex = stations.indexOf(station);

                        // Calculate previous two and next three stations
                        List<Station> previousStations = new ArrayList<>();
                        List<Station> nextStations = new ArrayList<>();

                        // Add previous two stations (if available)
                        for (int i = 1; i <= 2; i++) {
                            if (currentIndex - i >= 0) {
                                previousStations.add(stations.get(currentIndex - i));
                            }
                        }

                        // Add next three stations (if available)
                        for (int i = 1; i <= 3; i++) {
                            if (currentIndex + i < stations.size()) {
                                nextStations.add(stations.get(currentIndex + i));
                            }
                        }

                        // Print or use the station information as needed
                        System.out.println("Current Station: " + currentStationName);
                        System.out.println("Train Direction: " + trainDirection);
                        System.out.println("Previous Stations: " + previousStations);
                        System.out.println("Next Stations: " + nextStations);

                        // Break out of the loop since we found the current station
                        break;
                    }
                }
            }
        }
    }
}

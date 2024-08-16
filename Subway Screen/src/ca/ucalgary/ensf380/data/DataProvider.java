package ca.ucalgary.ensf380.data;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import ca.ucalgary.ensf380.maps.*;

/**
 * @author Fateh Ali
 * The DataProvider class is responsible for managing and providing data 
 * related to stations and trains. It interacts with a LatestOutputReader 
 * to fetch real-time train data and provides various methods to access 
 * station coordinates, train coordinates, station names, and other information.
 */
public class DataProvider {
    private LatestOutputReader latestOutputReader;
    private List<Station> stations;

    /**
     * Constructs a DataProvider object. This constructor initializes the 
     * LatestOutputReader and retrieves the list of stations from the 
     * ReadSubwayFile class.
     */
    public DataProvider() {
        this.latestOutputReader = new LatestOutputReader();
     // Create an instance of ReadSubwayFile
        ReadSubwayFile reader = new ReadSubwayFile();
        this.stations = reader.readStations();
    }

    /**
     * Retrieves the latest list of trains from the LatestOutputReader.
     *
     * @return a list of Train objects representing the current train data.
     */
    public List<Train> getTrains() {
        return latestOutputReader.getNewTrains();
    }

    /**
     * Retrieves the list of stations.
     *
     * @return a list of Station objects representing the available stations.
     */
    public List<Station> getStations() {
        return stations;
    }

    /**
     * Calculates and returns the coordinates for each station on the map.
     * The coordinates are scaled based on the panel size.
     *
     * @return a list of integer arrays where each array represents the x and y 
     *         coordinates of a station on the map.
     */
    public List<int[]> getStationCoordinates() {
        List<int[]> coordinates = new ArrayList<>();
        for (Station station : stations) {
            int x = (int) (station.getX() * 600.0 / 1200.0);
            int y = (int) (station.getY() * 450.0 / 700.0);
            coordinates.add(new int[]{x, y});
        }
        return coordinates;
    }

    /**
     * Calculates and returns the coordinates for each train based on its current 
     * station. The coordinates are scaled and returned as a list of integer arrays.
     *
     * @return a list of integer arrays where each array represents the x and y 
     *         coordinates of a train based on its current station.
     */
    public List<int[]> getTrainCoordinates() {
        List<int[]> coordinates = new ArrayList<>();
        for (Train train : latestOutputReader.getNewTrains()) {
            for (Station station : stations) {
                if (station.getStationCode().equals(train.getAtStation())) {
                    int x = (int) (station.getX() * 600.0 / 1200.0);
                    int y = (int) (station.getY() * 450.0 / 700.0);
                    coordinates.add(new int[]{x, y});
                    break;
                }
            }
        }
        return coordinates;
    }

    /**
     * Calculates and returns the coordinates for a specific train based on its 
     * train number. The coordinates are scaled and returned as a list of integer arrays.
     *
     * @param trainNumb the number of the train for which coordinates are to be fetched.
     * @return a list of integer arrays representing the x and y coordinates of the specified train.
     */
    public List<int[]> getTrainCoordinatesForTrainNumber(String trainNumb) {
        List<int[]> coordinates = new ArrayList<>();
        for (Train train : latestOutputReader.getNewTrains()) {
            if (train.getTrainNum().equals(trainNumb)) {
                for (Station station : stations) {
                    if (station.getStationCode().equals(train.getAtStation())) {
                        int x = (int) (station.getX() * 600.0 / 1200.0);
                        int y = (int) (station.getY() * 450.0 / 700.0);
                        coordinates.add(new int[]{x, y});
                        break;
                    }
                }
            }
        }
        return coordinates;
    }

    /**
     * Retrieves and returns a list of station names for a specific train, 
     * including the current, previous, and next stations.
     *
     * @param trainNum the number of the train for which station names are to be fetched.
     * @return a list of strings where each string represents a station name.
     */
    public List<String> getStationNamesForTrain(String trainNum) {
        List<String> stationNames = new ArrayList<>();
        for (Train train : getTrains()) {
            if (train.getTrainNum().equals(trainNum)) {
                for (Station station : stations) {
                    if (train.getAtStation().equals(station.getStationCode())) {
                        String currentStation = station.getStationName();
                        stationNames.add("Current: " + currentStation);
                        int currentIndex = stations.indexOf(station);
                        String trainDirection = train.getTrainDirection();
                        if (trainDirection.equals("forward")) {
                            stationNames.add("Previous: " + stations.get(currentIndex - 1).getStationName());
                            stationNames.add("Next 1: " + stations.get(currentIndex + 1).getStationName());
                            stationNames.add("Next 2: " + stations.get(currentIndex + 2).getStationName());
                            stationNames.add("Next 3: " + stations.get(currentIndex + 3).getStationName());
                        } else {
                            stationNames.add("Previous: " + stations.get(currentIndex + 1).getStationName());
                            stationNames.add("Next 1: " + stations.get(currentIndex - 1).getStationName());
                            stationNames.add("Next 2: " + stations.get(currentIndex - 2).getStationName());
                            stationNames.add("Next 3: " + stations.get(currentIndex - 3).getStationName());
                        }
                        break;
                    }
                }
            }
        }
        return stationNames;
    }

    /**
     * Returns the color corresponding to the station code's prefix.
     * 
     * @param stationIndex the index of the station in the list.
     * @return the color associated with the station (e.g., RED for 'R', BLUE for 'B', 
     *         GREEN for 'G', and BLACK if none left).
     */
    public Color getColorForStation(int stationIndex) {
        Station station = stations.get(stationIndex);
        if (station.getStationCode().startsWith("R")) {
            return Color.RED;
        } else if (station.getStationCode().startsWith("B")) {
            return Color.BLUE;
        } else if (station.getStationCode().startsWith("G")) {
            return Color.GREEN;
        }
        return Color.BLACK;
    }

    /**
     * Starts a new thread to continuously read and update train data using the 
     * LatestOutputReader.
     */
    public void startReadingTrains() {
        new Thread(latestOutputReader).start();
    }
}

package ca.ucalgary.ensf380.data;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import ca.ucalgary.ensf380.maps.*;

public class DataProvider {
    private LatestOutputReader latestOutputReader;
    private List<Station> stations;

    public DataProvider() {
        this.latestOutputReader = new LatestOutputReader();
        this.stations = ReadSubwayFile.readStations();
    }

    public List<Train> getTrains() {
        return latestOutputReader.getNewTrains();
    }

    public List<Station> getStations() {
        return stations;
    }

    public List<int[]> getStationCoordinates() {
        List<int[]> coordinates = new ArrayList<>();
        for (Station station : stations) {
            int x = (int) (station.getX() * 600.0 / 1200.0);
            int y = (int) (station.getY() * 450.0 / 700.0);
            coordinates.add(new int[]{x, y});
        }
        return coordinates;
    }

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
            			 	
            			 		stationNames.add("Previous: " + (stations.get(currentIndex - 1)).getStationName());
            			 		stationNames.add("Next 1: " + stations.get(currentIndex + 1).getStationName());
            			 		stationNames.add("Next 2: " + stations.get(currentIndex + 2).getStationName());
            			 		stationNames.add("Next 3: " + stations.get(currentIndex + 3).getStationName());
                            } else {
                            	stationNames.add("Previous: " + (stations.get(currentIndex + 1)).getStationName());
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
    

    public void startReadingTrains() {
        new Thread(latestOutputReader).start();
    }
}

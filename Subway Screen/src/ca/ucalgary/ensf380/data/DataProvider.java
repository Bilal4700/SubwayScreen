package ca.ucalgary.ensf380.data;

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

    public void startReadingTrains() {
        new Thread(latestOutputReader).start();
    }
}

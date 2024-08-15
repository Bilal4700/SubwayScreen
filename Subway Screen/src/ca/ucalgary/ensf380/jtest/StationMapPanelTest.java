package ca.ucalgary.ensf380.jtest;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import ca.ucalgary.ensf380.maps.Station;
import ca.ucalgary.ensf380.maps.StationMapPanel;
import ca.ucalgary.ensf380.maps.Train;
import ca.ucalgary.ensf380.data.DataProvider;

public class StationMapPanelTest {

    @Test
    public void testInitialSetup() {
        // Initialize a mock DataProvider
        DataProvider dataProvider = new DataProvider() {
            @Override
            public List<Station> getStations() {
                List<Station> stations = new ArrayList<>();
                stations.add(new Station(1, "R1", 100, 100, "Red Line 1"));
                stations.add(new Station(2, "B1", 200, 200, "Blue Line 1"));
                return stations;
            }

            @Override
            public List<Train> getTrains() {
                List<Train> trains = new ArrayList<>();
                trains.add(new Train("Train1", "R1", "forward"));
                return trains;
            }

            @Override
            public void startReadingTrains() {
                // Mock implementation, no real thread will be started
            }
        };

        // Initialize the StationMapPanel with the mock DataProvider
        StationMapPanel stationMapPanel = new StationMapPanel(dataProvider, "Train1");

        // Ensure that the stations are loaded correctly
        List<Station> stations = dataProvider.getStations();
        assertNotNull("Stations list should not be null", stations);
        assertEquals("Stations list should have 2 stations", 2, stations.size());

        // Ensure that the initial train list in the panel is empty
        assertEquals("Initial train list should be empty", 0, stationMapPanel.getTrains().size());
    }

    @Test
    public void testUpdateTrains() {
        // Initialize a mock DataProvider
        DataProvider dataProvider = new DataProvider() {
            @Override
            public List<Station> getStations() {
                List<Station> stations = new ArrayList<>();
                stations.add(new Station(1, "R1", 100, 100, "Red Line 1"));
                stations.add(new Station(2, "B1", 200, 200, "Blue Line 1"));
                return stations;
            }

            @Override
            public List<Train> getTrains() {
                List<Train> trains = new ArrayList<>();
                trains.add(new Train("Train1", "R1", "forward"));
                return trains;
            }

            @Override
            public void startReadingTrains() {
                // Mock implementation, no real thread will be started
            }
        };

        // Initialize the StationMapPanel with the mock DataProvider
        StationMapPanel stationMapPanel = new StationMapPanel(dataProvider, "Train1");

        // Create a new list of trains
        List<Train> newTrains = new ArrayList<>();
        newTrains.add(new Train("Train2", "B1", "forward"));

        // Update the trains in the StationMapPanel
        stationMapPanel.updateTrains(newTrains);

        // Verify that the trains were updated correctly
        assertEquals("Trains list should have 1 train", 1, stationMapPanel.getTrains().size());
        assertEquals("First train should be Train2", "Train2", stationMapPanel.getTrains().get(0).getTrainNum());
    }

    @Test
    public void testPaintComponent() {
        // Initialize a mock DataProvider
        DataProvider dataProvider = new DataProvider() {
            @Override
            public List<Station> getStations() {
                List<Station> stations = new ArrayList<>();
                stations.add(new Station(1, "R1", 100, 100, "Red Line 1"));
                stations.add(new Station(2, "B1", 200, 200, "Blue Line 1"));
                return stations;
            }

            @Override
            public List<Train> getTrains() {
                List<Train> trains = new ArrayList<>();
                trains.add(new Train("Train1", "R1", "forward"));
                return trains;
            }

            @Override
            public void startReadingTrains() {
                // Mock implementation, no real thread will be started
            }
        };

        // Initialize the StationMapPanel with the mock DataProvider
        StationMapPanel stationMapPanel = new StationMapPanel(dataProvider, "Train1");

        // Simulate the call to updateTrains and repaint
        stationMapPanel.updateTrains(dataProvider.getTrains());
        stationMapPanel.repaint();

        // Further validation would be needed to assert the correct drawing.
        // This might involve more complex setup or a custom Graphics object.
    }
}

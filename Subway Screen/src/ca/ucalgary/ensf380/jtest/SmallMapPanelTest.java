package ca.ucalgary.ensf380.jtest;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import ca.ucalgary.ensf380.maps.SmallMapPanel;
import ca.ucalgary.ensf380.maps.Station;
import ca.ucalgary.ensf380.maps.Train;
import ca.ucalgary.ensf380.data.DataProvider;

public class SmallMapPanelTest {

    @Test
    public void testInitialSetup() {
        // Initialize a mock DataProvider
        DataProvider dataProvider = new DataProvider() {
            @Override
            public List<Station> getStations() {
                List<Station> stations = new ArrayList<>();
                stations.add(new Station(1, "R1", 100, 100, "Red Line 1"));
                stations.add(new Station(2, "B1", 200, 200, "Blue Line 1"));
                stations.add(new Station(3, "G1", 300, 300, "Green Line 1"));
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

            @Override
            public List<String> getStationNamesForTrain(String trainNum) {
                List<String> stationNames = new ArrayList<>();
                stationNames.add("Current: Red Line 1");
                stationNames.add("Previous: Blue Line 1");
                stationNames.add("Next 1: Green Line 1");
                stationNames.add("Next 2: Station 4");
                stationNames.add("Next 3: Station 5");
                return stationNames;
            }
        };

        // Initialize the SmallMapPanel with the mock DataProvider
        SmallMapPanel smallMapPanel = new SmallMapPanel(dataProvider, "Train1");

        // Check that the JLabel components were initialized with the correct default text
        assertEquals("Previous: N/A", smallMapPanel.getComponent(0).getAccessibleContext().getAccessibleName());
        assertEquals("Current: N/A", smallMapPanel.getComponent(1).getAccessibleContext().getAccessibleName());
        assertEquals("Next 1: N/A", smallMapPanel.getComponent(2).getAccessibleContext().getAccessibleName());
        assertEquals("Next 2: N/A", smallMapPanel.getComponent(3).getAccessibleContext().getAccessibleName());
        assertEquals("Next 3: N/A", smallMapPanel.getComponent(4).getAccessibleContext().getAccessibleName());
    }

    @Test
    public void testUpdateStationInfo() {
        // Initialize a mock DataProvider
        DataProvider dataProvider = new DataProvider() {
            @Override
            public List<Station> getStations() {
                List<Station> stations = new ArrayList<>();
                stations.add(new Station(1, "R1", 100, 100, "Red Line 1"));
                stations.add(new Station(2, "B1", 200, 200, "Blue Line 1"));
                stations.add(new Station(3, "G1", 300, 300, "Green Line 1"));
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

            @Override
            public List<String> getStationNamesForTrain(String trainNum) {
                List<String> stationNames = new ArrayList<>();
                stationNames.add("Current: Red Line 1");
                stationNames.add("Previous: Blue Line 1");
                stationNames.add("Next 1: Green Line 1");
                stationNames.add("Next 2: Station 4");
                stationNames.add("Next 3: Station 5");
                return stationNames;
            }
        };

        // Initialize the SmallMapPanel with the mock DataProvider
        SmallMapPanel smallMapPanel = new SmallMapPanel(dataProvider, "Train1");

        // Simulate updating the station info
        smallMapPanel.repaint();

        // Check that the JLabel components were updated with the correct station names
        assertEquals("Previous: Blue Line 1", ((JLabel) smallMapPanel.getComponent(0)).getText());
        assertEquals("Current: Red Line 1", ((JLabel) smallMapPanel.getComponent(1)).getText());
        assertEquals("Next 1: Green Line 1", ((JLabel) smallMapPanel.getComponent(2)).getText());
        assertEquals("Next 2: Station 4", ((JLabel) smallMapPanel.getComponent(3)).getText());
        assertEquals("Next 3: Station 5", ((JLabel) smallMapPanel.getComponent(4)).getText());
    }
}

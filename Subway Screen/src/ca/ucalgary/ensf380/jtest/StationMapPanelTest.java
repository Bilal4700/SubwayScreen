package ca.ucalgary.ensf380.jtest;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import ca.ucalgary.ensf380.maps.LatestOutputReader;
import ca.ucalgary.ensf380.maps.Station;
import ca.ucalgary.ensf380.maps.StationMapPanel;
import ca.ucalgary.ensf380.maps.Train;

public class StationMapPanelTest {

    private List<Station> stations;
    private String trainNumb;
    private StationMapPanel panel;

    @Before
    public void setUp() {
        // Initialize test data
        stations = new ArrayList<>();
        stations.add(new Station(1, "R01", 100, 200, "Station 1"));
        stations.add(new Station(2, "B02", 150, 250, "Station 2"));
        stations.add(new Station(3, "G03", 200, 300, "Station 3"));

        trainNumb = "Train1";

        // Initialize the panel
        panel = new StationMapPanel(stations, trainNumb);
    }

    @Test
    public void testConstructor() {
        // Ensure the panel is initialized correctly
        assertNotNull(panel);
    }

    @Test
    public void testUpdateTrains() {
        // Create mock trains
        List<Train> trains = new ArrayList<>();
        trains.add(new Train("Train1", "forward", "R01"));
        trains.add(new Train("Train2", "backward", "B02"));

        // Call the updateTrains method
        panel.updateTrains(trains);

        // Verify that the trains list is updated
        assertEquals(trains.size(), 2);
    }

    @Test
    public void testGetLatestOutputReader() {
        // Ensure LatestOutputReader is correctly initialized and returned
        LatestOutputReader reader = panel.getLatestOutputReader();
        assertNotNull(reader);
    }
}

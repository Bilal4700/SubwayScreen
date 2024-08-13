package ca.ucalgary.ensf380.jtest;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import ca.ucalgary.ensf380.maps.SmallMapPanel;
import ca.ucalgary.ensf380.maps.Station;
import ca.ucalgary.ensf380.maps.Train;
import ca.ucalgary.ensf380.data.DataProvider;

public class SmallMapPanelTest {

    private SmallMapPanel smallMapPanel;
    private DataProvider dataProvider;

    @Before
    public void setUp() {
        // Initialize a mock DataProvider
        dataProvider = new DataProvider() {
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
        };

        // Initialize the SmallMapPanel with the mock DataProvider
        smallMapPanel = new SmallMapPanel(dataProvider, "Train1");
    }

    @Test
    public void testInitialSetup() {
        // Ensure that the stations are loaded correctly
        List<Station> stations = dataProvider.getStations();
        assertNotNull("Stations list should not be null", stations);
        assertEquals("Stations list should have 3 stations", 3, stations.size());

        // Ensure that the initial train list in the panel is empty
        assertEquals("Initial train list should be empty", 0, smallMapPanel.getTrains().size());
    }

    @Test
    public void testUpdateTrain() {
        // Create a new list of trains
        List<Train> newTrains = new ArrayList<>();
        newTrains.add(new Train("Train2", "B1", "forward"));

        // Update the trains in the SmallMapPanel
        smallMapPanel.updateTrain(newTrains);

        // Verify that the trains were updated correctly
        assertEquals("Trains list should have 1 train", 1, smallMapPanel.getTrains().size());
        assertEquals("First train should be Train2", "Train2", smallMapPanel.getTrains().get(0).getTrainNum());
    }

    @Test
    public void testPaintComponent() {
        // This is a more complex test that would require verifying the drawing behavior
        // It would involve creating a custom Graphics object or mocking the Graphics
        // class to check that the correct drawing methods are called.

        // This part of the test is more advanced and typically requires a more
        // sophisticated setup. For now, you might simulate the call and observe
        // behavior by logging or using a framework like Mockito.

        // Example:
        smallMapPanel.updateTrain(dataProvider.getTrains());
        smallMapPanel.repaint();
        // Further validation would be needed to assert the correct drawing.
    }
}

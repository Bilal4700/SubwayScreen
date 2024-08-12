package ca.ucalgary.ensf380.jtest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import org.junit.Before;
import org.junit.Test;

import ca.ucalgary.ensf380.maps.SmallMapPanel;
import ca.ucalgary.ensf380.maps.Station;
import ca.ucalgary.ensf380.maps.Train;

public class SmallMapTest {

    private List<Station> stations;
    private String trainNumb;
    private SmallMapPanel panel;

    @Before
    public void setUp() throws Exception {
        // Ensure tests run on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeAndWait(() -> {
            // Initialize test data
            stations = new ArrayList<>();
            stations.add(new Station(1, "R01", 100, 200, "Station 1"));
            stations.add(new Station(2, "B02", 150, 250, "Station 2"));
            stations.add(new Station(3, "G03", 200, 300, "Station 3"));
            stations.add(new Station(4, "G04", 250, 350, "Station 4"));
            stations.add(new Station(5, "G05", 300, 400, "Station 5"));
            stations.add(new Station(6, "G06", 350, 450, "Station 6"));

            trainNumb = "Train1";

            // Initialize the panel
            panel = new SmallMapPanel(stations, trainNumb);
        });
    }

    @Test
    public void testConstructor() {
        assertNotNull(panel);
        JLabel label = (JLabel) panel.getComponent(0);
        assertNotNull(label);
    }

    @Test
    public void testUpdateTrainWithMultipleTrains() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            // Create mock trains
            List<Train> trains = new ArrayList<>();
            trains.add(new Train("Train1", "forward", "R01"));
            trains.add(new Train("Train2", "backward", "B02"));
            trains.add(new Train("Train3", "forward", "G03"));
            trains.add(new Train("Train4", "forward", "G04"));
            trains.add(new Train("Train5", "backward", "G05"));
            trains.add(new Train("Train6", "forward", "G06"));
            trains.add(new Train("Train7", "forward", "G01"));

            // Call the updateTrain method
            panel.updateTrain(trains);

            // Manually trigger a repaint to ensure the UI is updated
            panel.repaint();

            // Check if the panel has been updated with the correct train information
            assertEquals("Station 1", ((JLabel) panel.getComponent(1)).getText());
            assertEquals("Station 2", ((JLabel) panel.getComponent(2)).getText());
            assertEquals("Station 3", ((JLabel) panel.getComponent(3)).getText());
            assertEquals("Station 4", ((JLabel) panel.getComponent(4)).getText());
        });
    }

    @Test
    public void testTrainDirectionForwardWithMultipleTrains() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            // Create a train moving forward
            List<Train> trains = new ArrayList<>();
            trains.add(new Train("Train1", "forward", "R01"));
            trains.add(new Train("Train3", "forward", "G03"));
            trains.add(new Train("Train4", "forward", "G04"));

            panel.updateTrain(trains);
            panel.repaint();

            assertEquals("Station 1", ((JLabel) panel.getComponent(1)).getText()); // Current station for Train1
            assertEquals("Station 2", ((JLabel) panel.getComponent(2)).getText()); // Next station 1 for Train1
            assertEquals("Station 3", ((JLabel) panel.getComponent(3)).getText()); // Next station 2 for Train1
        });
    }

    @Test
    public void testTrainDirectionBackwardWithMultipleTrains() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            // Create trains moving backward
            List<Train> trains = new ArrayList<>();
            trains.add(new Train("Train1", "backward", "G05"));
            trains.add(new Train("Train5", "backward", "G05"));

            panel.updateTrain(trains);
            panel.repaint();

            assertEquals("Station 5", ((JLabel) panel.getComponent(1)).getText()); // Current station for Train1
            assertEquals("Station 4", ((JLabel) panel.getComponent(2)).getText()); // Previous station for Train1
            assertEquals("Station 3", ((JLabel) panel.getComponent(3)).getText()); // Previous station for Train1
        });
    }
}

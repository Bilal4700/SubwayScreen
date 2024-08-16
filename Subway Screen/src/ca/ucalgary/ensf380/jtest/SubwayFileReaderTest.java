package ca.ucalgary.ensf380.jtest;

import static org.junit.Assert.*;
import org.junit.Test;
import ca.ucalgary.ensf380.maps.SubwayFileReader;
import ca.ucalgary.ensf380.maps.Station;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

/**
 * @author Fateh Ali
 * Tests for the SubwayFileReader class.
 * Ensures that station data is correctly read from a CSV file.
 * 
 */
public class SubwayFileReaderTest {

    private static final String TEST_CSV_FILE_PATH = "./data/test_subway.csv";

    /**
     * Creates a temporary CSV file for testing purposes.
     */
    private void createTestCSVFile() throws Exception {
        BufferedWriter writer = new BufferedWriter(new FileWriter(TEST_CSV_FILE_PATH));
        writer.write("Row,Line,StationNumber,StationCode,StationName,X,Y\n");
        writer.write("14,R,14,R14,Cedar Point Station,394.1186295,176.2062416\n");
        writer.write("19,R,19,R19,Summerfield Station,545.2255783,238.2502327\n");
        writer.write("28,R,28,R28,Sunnybrook Station,703.5114441,442.7256165\n");
        writer.close();
    }

    /**
     * Tests the readStations method of the SubwayFileReader class.
     * Verifies that the method correctly reads station data from the CSV file
     * and returns a list of Station objects.
     */
    @Test
    public void testReadStations() throws Exception {
        // Create a temporary CSV file
        createTestCSVFile();

        // Initialize SubwayFileReader with the path to the test CSV file
        SubwayFileReader SubwayFileReader = new SubwayFileReader(TEST_CSV_FILE_PATH);

        // Read stations from the test CSV file
        List<Station> stations = SubwayFileReader.readStations();

        // Verify the number of stations read
        assertEquals("The number of stations read should be 3", 3, stations.size());

        // Verify the first station's details
        Station station1 = stations.get(0);
        assertEquals("Station number should be 14", 14, station1.getStationNumber());
        assertEquals("Station code should be R14", "R14", station1.getStationCode());
        assertEquals("Station name should be Cedar Point Station", "Cedar Point Station", station1.getStationName());
        assertEquals("Station X coordinate should be 394.1186295", 394.1186295, station1.getX(), 0.01);
        assertEquals("Station Y coordinate should be 176.2062416", 176.2062416, station1.getY(), 0.01);

        // Verify the second station's details
        Station station2 = stations.get(1);
        assertEquals("Station number should be 19", 19, station2.getStationNumber());
        assertEquals("Station code should be R19", "R19", station2.getStationCode());
        assertEquals("Station name should be Summerfield Station", "Summerfield Station", station2.getStationName());
        assertEquals("Station X coordinate should be 545.2255783", 545.2255783, station2.getX(), 0.01);
        assertEquals("Station Y coordinate should be 238.2502327", 238.2502327, station2.getY(), 0.01);

        // Verify the third station's details
        Station station3 = stations.get(2);
        assertEquals("Station number should be 28", 28, station3.getStationNumber());
        assertEquals("Station code should be R28", "R28", station3.getStationCode());
        assertEquals("Station name should be Sunnybrook Station", "Sunnybrook Station", station3.getStationName());
        assertEquals("Station X coordinate should be 703.5114441", 703.5114441, station3.getX(), 0.01);
        assertEquals("Station Y coordinate should be 442.7256165", 442.7256165, station3.getY(), 0.01);
    }
}

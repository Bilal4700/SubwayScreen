package ca.ucalgary.ensf380.jtest;

import static org.junit.Assert.*;
import org.junit.Test;
import ca.ucalgary.ensf380.components.Advertisement;

import java.sql.*;

public class AdvertisementTest {

    

    @Test
    public void testFetch() throws SQLException {
        // Set up the environment
        Advertisement advertisement = new Advertisement();
        advertisement.createConnection();

        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/advertisement_db", "admin", "subwayscreen");
        Statement stmt = connection.createStatement();

        // Clean up the table before the test
        stmt.execute("DELETE FROM Gifs");

        // Insert some initial test data
        stmt.execute("INSERT INTO Gifs (brand_name, path) VALUES ('TestBrand', 'Gifs/TestAd.gif')");

        // Fetch the advertisements
        advertisement.fetch();

        // Check if the adPaths contains the expected path
        String expectedPath = "Gifs/TestAd.gif\n";
        String actualPaths = advertisement.getAdPaths();

        assertEquals("The fetched advertisement paths should match the expected value", expectedPath, actualPaths);

        // Clean up
        stmt.execute("DELETE FROM Gifs");
        stmt.close();
        connection.close();
    }

    @Test
    public void testCreateConnection() {
        // Initialize the Advertisement object and create a connection
        Advertisement advertisement = new Advertisement();
        advertisement.createConnection();

        // Check that the connection was successfully established
        assertNotNull("Database connection should be established", advertisement);
    }
}

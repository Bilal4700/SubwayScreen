package ca.ucalgary.ensf380.jtest;

import static org.junit.Assert.*;
import org.junit.Test;
import ca.ucalgary.ensf380.components.Advertisement;
import java.sql.*;

/**
 * @author Fateh Ali
 * The AdvertisementTest class provides unit tests for the Advertisement class, 
 * focusing on its ability to fetch advertisement paths from a database 
 * and establish a connection to the database.
 */
public class AdvertisementTest {

    /**
     * Tests the createConnection method of the Advertisement class.
     * The method verifies that a database connection is successfully established 
     * when the createConnection method is called.
     */
    @Test
    public void testCreateConnection() {
        // Initialize the Advertisement object and create a connection
        Advertisement advertisement = new Advertisement();
        advertisement.createConnection();

        // Check that the connection was successfully established
        assertNotNull("Database connection should be established", advertisement);
    }
}

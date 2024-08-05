package ca.ucalgary.ensf380.components;

import java.sql.*;
import java.util.ArrayList;

/**
 * The Advertisement class is responsible for managing the connection to the database
 * and fetching the paths of GIF advertisements. It also allows for inserting new advertisements
 * into the database.
 * You can add gifs from TestAdvertisement class as I didnt implemented this in main class for you to add something SORRY
 */


public class Advertisement extends Fetcher{
    private Connection dbConnect;
    private ResultSet results;
    private String adPaths;

    /**
     * Default constructor for Advertisement class.
     */
    
    public Advertisement() {
    }

    public void createConnection(){
        try {
            // Corrected JDBC URL for syntax
            dbConnect = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/advertisement_db", "admin", "subwayscreen");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Inserts a new advertisement into the database.
     * Before calling this method, ensure the GIF file is placed in the "Gifs" folder
     * and provide the correct filename.
     *
     * @param brand_name the brand name associated with the advertisement
     * @param fileName   the name of the GIF file
     * @throws Exception if an error occurs during the database operation
     */

    public void insertNewAd(String brand_name,  String fileName)throws Exception {
    	
    	final String filePath = "Gifs/" + fileName;
    	try {

            String query = "INSERT INTO Gifs (brand_name, path) VALUES(?, ?)";
            PreparedStatement myStmt = dbConnect.prepareStatement(query);
            myStmt.setString(1, brand_name);
            myStmt.setString(2, filePath);
            myStmt.executeUpdate();  // Execute the update to insert data
            myStmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    /**
     * Fetches the paths of GIF advertisements from the database.
     * The paths are stored in the adPaths variable as a single string with new lines.
     */

    @Override
    public void fetch() {
        ArrayList<String> filepaths = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        
        try {
            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT path FROM Gifs");

            while (results.next()) {
                filepaths.add(results.getString("path"));
            }

            // Convert ArrayList to a single string with new lines
            for (String path : filepaths) {
                stringBuilder.append(path).append('\n');
            }
            this.adPaths = stringBuilder.toString(); 
            myStmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        


    }
    /**
     * @return the paths of GIF advertisements
     */
    public String getAdPaths() {
    	return adPaths;
    }
    
    
    


}

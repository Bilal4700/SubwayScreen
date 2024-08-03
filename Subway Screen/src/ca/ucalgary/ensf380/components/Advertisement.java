package ca.ucalgary.ensf380.components;

import java.sql.*;
import java.util.ArrayList;

public class Advertisement {
    private Connection dbConnect;
    private ResultSet results;

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

    public void insertNewAd(String brand_name, String gifname)throws Exception {
    	if (!gifname.startsWith("/Gifs/")) {
            throw new Exception("First you have to put the GIF into the Gif Folder");
        }
    	try {
            final String path = "/Gifs/" + gifname;  // Added a slash for correct path formatting
            String query = "INSERT INTO Gifs (brand_name, path) VALUES(?, ?)";
            PreparedStatement myStmt = dbConnect.prepareStatement(query);
            myStmt.setString(1, brand_name);
            myStmt.setString(2, path);
            myStmt.executeUpdate();  // Execute the update to insert data
            myStmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public String fetchAd() {
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

            myStmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return stringBuilder.toString();
    }
    


}

package ca.ucalgary.ensf380.components;

import java.sql.*;
import java.util.ArrayList;

public class Advertisement extends Fetcher{
    private Connection dbConnect;
    private ResultSet results;
    private String adPaths;

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
    public String getAdPaths() {
    	return adPaths;
    }
    
    
    


}

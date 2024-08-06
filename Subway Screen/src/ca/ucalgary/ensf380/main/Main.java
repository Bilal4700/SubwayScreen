package ca.ucalgary.ensf380.main;

import ca.ucalgary.ensf380.maps.*;
import ca.ucalgary.ensf380.gui.*;
import javax.swing.SwingUtilities;


/**
 * The Main class initializes and runs the SubwayScreen application.
 * It takes command-line arguments for the city, train number, and an optional country code for news.
 * The application displays weather, time, train locations, and news in a GUI.
 *
 * <p>Authors:
 * <br>Muhammad Bilal UCID: 30188943
 * <br>Fateh Bukhari UCID: 30123431
 * </p>
 */
public class Main {
	

    public static void main(String[] args) {

    	
        if (args.length < 1 || args.length > 3) {
            System.out.println("Error: Please provide two or three command-line arguments.");
            System.out.println("Usage: java ca.ucalgary.ensf380.gui.SubwayScreen [city] [trainnums] [countrycode] ");
            return;
        }
        String city = args[0];
        String countrycode = (args.length == 3) ? args[2] : null;
        String trainNumb = args[1];
        // Step 1: Run MyApp3 and ensure it completes
            try {
                MyApp3.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        // Wait for MyApp3 to signal completion
        while (!GlobBool.isApp3Done) {
            System.out.print(""); 
        }
        // Step 2: Read stations from the subway file
            try {
            } catch (Exception e) {
                e.printStackTrace();
            }
        
         // Step 3: Create and show the station map frame
            SwingUtilities.invokeLater(() -> {
                try {
                    new SubwayScreen(city, trainNumb, countrycode);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

       
    }


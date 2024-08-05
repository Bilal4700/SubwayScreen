package ca.ucalgary.ensf380.main;

import ca.ucalgary.ensf380.maps.*;
import java.util.List;
import ca.ucalgary.ensf380.gui.*;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        boolean app3Completed = false;
        boolean readSubwayFileCompleted = false;
        List<Station> stations = null;

        // Step 1: Run MyApp3 and ensure it completes
        if (!app3Completed) {
            try {
                MyApp3.run();
                app3Completed = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Wait for MyApp3 to signal completion
        while (!GlobBool.isApp3Done) {
            System.out.print(""); // Busy-wait, consider improving with a more efficient wait strategy
        }

        // Step 2: Read stations from the subway file
        if (app3Completed && !readSubwayFileCompleted) {
            try {
                
                stations = ReadSubwayFile.readStations();
                readSubwayFileCompleted = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Step 3: Create and show the station map frame
        if (readSubwayFileCompleted) {
           

            if (args.length < 1 || args.length > 3) {
                System.out.println("Error: Please provide two or three command-line arguments.");
                System.out.println("Usage: java ca.ucalgary.ensf380.gui.SubwayScreen <city> [countrycode]");
                return;
            }

            String city = args[0];
            String countrycode = (args.length == 3) ? args[2] : null;
            String trainNumb = args[1];
            SwingUtilities.invokeLater(() -> {
                try {
                    new SubwayScreen(city, trainNumb, countrycode);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

       
    }
}

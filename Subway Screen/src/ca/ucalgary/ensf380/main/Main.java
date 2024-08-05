package ca.ucalgary.ensf380.main;


import javax.swing.SwingUtilities;

import ca.ucalgary.ensf380.components.*;
import ca.ucalgary.ensf380.gui.*;
import ca.ucalgary.ensf380.main.*;
import ca.ucalgary.ensf380.simulations.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Timer;
import java.util.TimerTask;



public class Main {

    public static void main(String[] args) {
        if (args.length < 1 || args.length > 2) {
            System.out.println("Error: Please provide one or two command-line arguments.");
            System.out.println("Usage: java ca.ucalgary.ensf380.gui.SubwayScreen <city> [countrycode]");
            return;
        }

        String city = args[0];
        String countrycode = (args.length == 2) ? args[1] : null;
        SwingUtilities.invokeLater(() -> new SubwayScreen(city, countrycode));

     // Runs the simulator 
     		Process process = null;        
             try {
             	String[] command = {"java", "-jar", "./exe/SubwaySimulator.jar", "--in", "./data/subway.csv", "--out", "./out"};
             	process = new ProcessBuilder(command).start();
             } catch (IOException e) {
             	System.err.println(e);
                 e.printStackTrace();
             }
             final Process finalProcess = process;
             
             // It will destroy the simulator process at the end 
             Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                 if (finalProcess != null) {
                     finalProcess.destroy();
                 }
             }));
             
             // Keep the application alive for 5 minutes 
             Timer timer = new Timer();
             timer.schedule(new TimerTask() {
                 @Override
                 public void run() {
                     if (finalProcess != null) {
                         finalProcess.destroy();
                     }
                     timer.cancel();
                 }
             }, 5 * 60 * 1000); // 5 minutes in milliseconds
                     
             // Prints simulator in the console. 
             // Just for test. Its while loop friezes the application.  
             InputStream inputStream = process.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
             String line;
             try {
                 while ((line = reader.readLine()) != null) {
                     System.out.println(line);
                 }
             } catch (IOException e) {
                 e.printStackTrace();
             }
        
        
    }

}

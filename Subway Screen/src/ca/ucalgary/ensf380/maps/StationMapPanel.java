package ca.ucalgary.ensf380.maps;


import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList; 


/**
 * The StationMapPanel class creates a JPanel that displays a map of stations and highlights trains.
 */
public class StationMapPanel extends JPanel {
    private List<Station> stations;
    private List<Train> trains;
    private String trainNumb;

    
    /**
     * Constructs a StationMapPanel with the specified list of stations and train number to highlight.
     * 
     * @param stations the list of stations
     * @param trainNumb the train number to highlight
     */
    public StationMapPanel(List<Station> stations, String trainNumb) {
        this.stations = stations;
        this.trains = new ArrayList<>(); // Initialize the ArrayList
        this.trainNumb = trainNumb;
    }

    
    /**
     * Updates the list of trains and repaints the panel to reflect the updated train data.
     * 
     * @param newTrains the new list of trains
     */
    public synchronized void updateTrains(List<Train> newTrains) {
        this.trains = newTrains;
        repaint(); // Repaint the panel to reflect the updated train data
    }

    /**
     * 
     * @param g the Graphics object used for painting
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
     
 
        //Paints every station with oval of respected colors of the line
        for (Station station : stations) {
        	int x = (int) station.getX();
        	int y = (int) station.getY();
        	double widthScale = 600.0 / 1200.0;
        	double heightScale = 450.0 / 700.0;

        	int X = (int) (x * widthScale);
        	int Y = (int) (y * heightScale);
            String stationCode = station.getStationCode();

            if (stationCode.startsWith("R")) {
                g.setColor(Color.RED);
            } else if (stationCode.startsWith("B")) {
                g.setColor(Color.BLUE);
            } else if (stationCode.startsWith("G")) {
                g.setColor(Color.GREEN);
            } 
            g.fillOval(X, Y, 6, 6);
        }

       
       // Paints the station with rectangle where there is train. Selected train is orange
        // Others are black
        
        for (Train train : trains) {
            for (Station station : stations) {
                String i = station.getStationCode();
                String atStation = train.getAtStation();
                if (i.equals(atStation)) {
                	int x = (int) station.getX();
                	int y = (int) station.getY();
                	double widthScale = 600.0 / 1200.0;
                	double heightScale = 450.0 / 700.0;
                	int X = (int) (x * widthScale);
                	int Y = (int) (y * heightScale);
                	if (trainNumb.equals(train.getTrainNum())) {
                    	g.setColor(Color.ORANGE);
                        g.fillRect(X - 10, Y - 5, 20, 10);
                	}else {
	                    g.setColor(Color.BLACK);
	                    g.fillRect(X - 10, Y - 5, 20, 10);
                	}  
                }
            }
        }

        
    }
}

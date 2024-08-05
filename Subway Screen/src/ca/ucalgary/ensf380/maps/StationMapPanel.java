package ca.ucalgary.ensf380.maps;


import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList; 

public class StationMapPanel extends JPanel {
    private List<Station> stations;
    private List<Train> trains;
    private String trainNumb;

    public StationMapPanel(List<Station> stations, String trainNumb) {
        this.stations = stations;
        this.trains = new ArrayList<>(); // Initialize the ArrayList
        this.trainNumb = trainNumb;
    }

    public synchronized void updateTrains(List<Train> newTrains) {
        this.trains = newTrains;
        repaint(); // Repaint the panel to reflect the updated train data
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
     
 
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

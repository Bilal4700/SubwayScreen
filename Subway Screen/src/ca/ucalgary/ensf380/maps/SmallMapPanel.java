package ca.ucalgary.ensf380.maps;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

/**
 * The SmallMapPanel class creates a JPanel that displays information about the current, previous, and next stations for a specified train.
 */
public class SmallMapPanel extends JPanel {
    private List<Station> stations;
    private List<Train> trains;
    private String trainNumb;
    private LatestOutputReader latestOutputReader; 
    private String previousStation = null;
    private String currentStationName;
    private String nextStation1;
    private String nextStation2;
    private String nextStation3;

    private JLabel labelPreviousStation;
    private JLabel labelCurrentStation;
    private JLabel labelNextStation1;
    private JLabel labelNextStation2;
    private JLabel labelNextStation3;
    private JLabel strPrevious;
    private JLabel strCurrent;
    private JLabel strNextSta;
    private JLabel strUpComing;
    private JLabel strNextStation1;

    
    /**
     * Constructs a SmallMapPanel with the specified stations and train number.
     * 
     * @param stations the list of stations
     * @param trainNumb the train number to highlight
     */
    public SmallMapPanel(List<Station> stations, String trainNumb ) {
        this.stations = stations;
        this.trains = new ArrayList<>();
        this.trainNumb = trainNumb;
        this.latestOutputReader = new LatestOutputReader(); // Initialize LatestOutputReader

        // Start the LatestOutputReader in a separate thread
        Thread readerThread = new Thread(latestOutputReader);
        readerThread.start(); 

        // Set up a timer to update the train data periodically
        Timer timer = new Timer(5000, new ActionListener() { // Update every 5 seconds
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTrain(latestOutputReader.getNewTrains()); // Update trains from the latest output
                
            }
        });
        timer.start(); // Start the timer
    

        setLayout(new GridLayout(5, 1)); 

        labelPreviousStation = new JLabel();
        labelCurrentStation = new JLabel();
        labelNextStation1 = new JLabel();
        labelNextStation2 = new JLabel();
        labelNextStation3 = new JLabel();
        strPrevious = new JLabel();
        strCurrent = new JLabel();
        strNextSta = new JLabel();
        strUpComing = new JLabel();
        strNextStation1 = new JLabel();
        
        add(labelPreviousStation);
        add(labelCurrentStation);
        add(labelNextStation1);
        add(labelNextStation2);
        add(labelNextStation3);
        add(strPrevious);
        add(strCurrent);
        add(strNextSta);
        add(strUpComing);
        add(strNextStation1);
    }
    /**
     * Updates the train information and repaints the panel.
     * 
     * @param newTrains the new list of trains
     */
    public synchronized void updateTrain(List<Train> newTrains) {
        this.trains = newTrains;
        repaint();
    }
    /**
     * Paints the component, including the station and train information.
     * 
     * @param g the Graphics object used for painting
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Train train : trains) {
            if (trainNumb.equals(train.getTrainNum())) {
                for (Station station : stations) {
                    if (train.getAtStation().equals(station.getStationCode())) {
                        currentStationName = station.getStationName();
                        String trainDirection = train.getTrainDirection();
                        int currentIndex = stations.indexOf(station);
                        if (trainDirection.equals("forward")) {
                            if (currentIndex - 1 >= 0) {
                                previousStation = stations.get(currentIndex - 1).getStationName();
                            }
                            nextStation1 = stations.get(currentIndex + 1).getStationName();
                            nextStation2 = stations.get(currentIndex + 2).getStationName();
                            nextStation3 = stations.get(currentIndex + 3).getStationName();
                        } else {
                            if (currentIndex + 1 < stations.size()) {
                                previousStation = stations.get(currentIndex + 1).getStationName();
                            }
                            nextStation1 = stations.get(currentIndex - 1).getStationName();
                            nextStation2 = stations.get(currentIndex - 2).getStationName();
                            nextStation3 = stations.get(currentIndex - 3).getStationName();
                        }

                        
                        labelPreviousStation.setText(previousStation);
                        labelPreviousStation.setBounds(170, 115, 300, 60);
                        labelCurrentStation.setText(currentStationName);
                        labelCurrentStation.setForeground(new Color(0xFF0000)); 
                        labelCurrentStation.setBounds(306, 90, 300, 60);
                        labelNextStation1.setText(nextStation1);
                        labelNextStation1.setBounds(442, 115, 300, 60);
                        labelNextStation2.setText(nextStation2);
                        labelNextStation2.setBounds(578, 90, 300, 60);
                        labelNextStation3.setText(nextStation3);
                        labelNextStation3.setBounds(714, 115, 300, 60);
                        strPrevious.setText("Previous: ");
                        strPrevious.setBounds(190, 5, 300, 60);
                        strCurrent.setText("Current: ");
                        strCurrent.setBounds(326, 5, 300, 60);
                        strNextSta.setText("UpComing....");
                        strNextSta.setBounds(462, 5, 300, 60);
                        strUpComing.setText("The Next Station is: ");
                        strUpComing.setBounds(408, 150, 300, 60);
                        strNextStation1.setText(nextStation1);
                        strNextStation1.setBounds(530, 150, 300, 60);
                        
                        break;
                    }
                }
            }

          
            for (int xpoint = 80; xpoint <= 900; xpoint++) {
                g.setColor(Color.DARK_GRAY);
                g.fillOval(xpoint, 70, 10, 10);
            }
            for (int xpoint = 216; xpoint <= 800; xpoint += 136) {
                g.setColor(Color.DARK_GRAY);
                g.fillOval(xpoint - 10, 60, 30, 30);
            }
            g.setColor(Color.RED);
            g.fillOval(342, 60, 30, 30);
            g.fillOval(332, 61, 50, 30);
        }
    }
}

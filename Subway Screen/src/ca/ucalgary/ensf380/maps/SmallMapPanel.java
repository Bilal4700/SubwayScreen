package ca.ucalgary.ensf380.maps;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class SmallMapPanel extends JPanel {
    private List<Station> stations;
    private List<Train> trains;
    private String trainNumb;
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
    private JLabel label6;
    private JLabel label7;
    private JLabel label8;
    private JLabel label9;
    private JLabel label10;

    public SmallMapPanel(List<Station> stations, String trainNumb ) {
        this.stations = stations;
        this.trains = new ArrayList<>();
        this.trainNumb = trainNumb;

        setLayout(new GridLayout(5, 1)); 

        labelPreviousStation = new JLabel();
        labelCurrentStation = new JLabel();
        labelNextStation1 = new JLabel();
        labelNextStation2 = new JLabel();
        labelNextStation3 = new JLabel();
        label6 = new JLabel();
        label7 = new JLabel();
        label8 = new JLabel();
        label9 = new JLabel();
        label10 = new JLabel();
        
        add(labelPreviousStation);
        add(labelCurrentStation);
        add(labelNextStation1);
        add(labelNextStation2);
        add(labelNextStation3);
        add(label6);
        add(label7);
        add(label8);
        add(label9);
        add(label10);
    }

    public synchronized void updateTrain(List<Train> newTrains) {
        this.trains = newTrains;
        repaint();
    }

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
                        label6.setText("Previous: ");
                        label6.setBounds(190, 5, 300, 60);
                        label7.setText("Current: ");
                        label7.setBounds(326, 5, 300, 60);
                        label8.setText("UpComing....");
                        label8.setBounds(462, 5, 300, 60);
                        label9.setText("The Next Station is: ");
                        label9.setBounds(408, 150, 300, 60);
                        label10.setText(nextStation1);
                        label10.setBounds(516, 150, 300, 60);
                        

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

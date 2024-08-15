package ca.ucalgary.ensf380.maps;

import javax.swing.*;
import ca.ucalgary.ensf380.data.DataProvider;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class StationMapPanel extends JPanel {
    private DataProvider dataProvider;
    private String trainNumb;

    public StationMapPanel(DataProvider dataProvider, String trainNumb) {
        this.dataProvider = dataProvider;
        this.trainNumb = trainNumb;

        // Start reading trains in a separate thread
        dataProvider.startReadingTrains();
        
        Timer timer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw stations
        List<int[]> stationCoords = dataProvider.getStationCoordinates();
        for (int i = 0; i < stationCoords.size(); i++) {
            int[] coord = stationCoords.get(i);
            g.setColor(dataProvider.getColorForStation(i));
            g.fillOval(coord[0], coord[1], 6, 6);
        }

        // Draw trains
        List<int[]> trainCoords = dataProvider.getTrainCoordinates();
        List<int[]> specificTrainCoords = dataProvider.getTrainCoordinatesForTrainNumber(trainNumb);

        
        for (int i = 0; i < trainCoords.size(); i++) {
            int[] coord = trainCoords.get(i);
            boolean isSpecificTrain = false;
            for (int[] specificCoord : specificTrainCoords) {
                if (specificCoord[0] == coord[0] && specificCoord[1] == coord[1]) {
                    isSpecificTrain = true;
                    break;
                }
            }
            if (isSpecificTrain) {
                g.setColor(Color.ORANGE);
            } else {
                g.setColor(Color.BLACK);
            }
            g.fillRect(coord[0] - 10, coord[1] - 5, 20, 10);
        }
    }


}

package ca.ucalgary.ensf380.maps;

import javax.swing.*;
import ca.ucalgary.ensf380.data.DataProvider;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * @author Fateh Ali
 * SmallMapPanel is a custom JPanel that displays a simplified map 
 * of a subway system and shows the previous, current, and next stations 
 * for a specific train. It automatically updates the station information 
 * at regular intervals.
 */
public class SmallMapPanel extends JPanel {
    private DataProvider dataProvider;
    private String trainNumb;
    private JLabel labelPreviousStation;
    private JLabel labelCurrentStation;
    private JLabel labelNextStation1;
    private JLabel labelNextStation2;
    private JLabel labelNextStation3;
    
    /**
     * Paints the subway map and updates the station information for the specified train.
     *
     * @param g the Graphics object used to draw on this panel.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        updateStationInfo();
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
    
    /**
     * Constructs a SmallMapPanel with the given DataProvider and train number.
     * This panel displays information about the train's current and upcoming stations.
     * 
     * @param dataProvider the DataProvider that supplies station and train information.
     * @param trainNumb the train number whose information will be displayed.
     */
    public SmallMapPanel(DataProvider dataProvider, String trainNumb) {
        this.dataProvider = dataProvider;
        this.trainNumb = trainNumb;

        labelPreviousStation = new JLabel("Previous: N/A");
        labelCurrentStation = new JLabel("Current: N/A");
        labelNextStation1 = new JLabel("Next 1: N/A");
        labelNextStation2 = new JLabel("Next 2: N/A");
        labelNextStation3 = new JLabel("Next 3: N/A");

        add(labelPreviousStation);
        add(labelCurrentStation);
        add(labelNextStation1);
        add(labelNextStation2);
        add(labelNextStation3);

        dataProvider.startReadingTrains();
        
        // Timer to periodically update the station information every 5 seconds.
        Timer timer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateStationInfo();
            }
        });
        timer.start();
    }

    /**
     * Updates the station information labels with the current, previous,
     * and next stations for the specified train.
     */
    private void updateStationInfo() {
        List<String> stationNames = dataProvider.getStationNamesForTrain(trainNumb);
        if (!stationNames.isEmpty()) {
            labelPreviousStation.setText(stationNames.get(1));
            labelPreviousStation.setBounds(165, 115, 300, 60);
            labelCurrentStation.setText(stationNames.get(0));
            labelCurrentStation.setForeground(new Color(0xFF0000)); 
            labelCurrentStation.setBounds(300, 90, 300, 60);
            labelNextStation1.setText(stationNames.get(2));
            labelNextStation1.setBounds(437, 115, 300, 60);
            labelNextStation2.setText(stationNames.get(3));
            labelNextStation2.setBounds(573, 90, 300, 60);
            labelNextStation3.setText(stationNames.get(4));
            labelNextStation3.setBounds(709, 115, 300, 60);
        }
    }
}

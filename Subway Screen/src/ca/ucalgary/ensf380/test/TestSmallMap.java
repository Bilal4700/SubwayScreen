package ca.ucalgary.ensf380.test;

import ca.ucalgary.ensf380.maps.*;

import javax.swing.*;
import java.util.List;
import java.util.ArrayList;

public class TestSmallMap {
    public static void main(String[] args) {
        // Create some sample stations
        List<Station> stations = new ArrayList<>();
        stations.add(new Station(1,"ST1", 1.0, 0.0, "Station1"));
        stations.add(new Station(1,"ST2",0.0,0.0, "Station2"));
        stations.add(new Station(1,"ST3", 1.0, 0.0,"Station3"));
        stations.add(new Station(1,"ST4", 1.0, 0.0,"Station4"));
        stations.add(new Station(1,"ST5", 1.0, 0.0,"Station5"));
        stations.add(new Station(1, "ST6", 1.0, 0.0,"Station6"));

        // Create a sample train
        List<Train> trains = new ArrayList<>();
        trains.add(new Train("T1", "ST3", "North"));
        trains.add(new Train("T2", "ST4", "South"));

        // Create the SmallMapPanel
        SmallMapPanel panel = new SmallMapPanel(stations, "T1");

        // Update the panel with the train list
        panel.updateTrain(trains);

        // Create a JFrame to display the panel
        JFrame frame = new JFrame("Test SmallMapPanel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(panel);
        frame.setVisible(true);
    }
}

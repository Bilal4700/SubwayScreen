package ca.ucalgary.ensf380.maps;


import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList; 

public class SmallMapPanel extends JPanel {
    private List<Station> stations;
    private List<Train> trains;
    private String trainNumb;

    public SmallMapPanel(List<Station> stations, String trainNumb) {
        this.stations = stations;
        this.trains = new ArrayList<>(); // Initialize the ArrayList
        this.trainNumb = trainNumb;
    }
    
    
    
    
}
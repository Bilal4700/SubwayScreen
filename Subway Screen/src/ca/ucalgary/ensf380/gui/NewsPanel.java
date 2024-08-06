package ca.ucalgary.ensf380.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The NewsPanel class creates a JPanel that displays a scrolling news ticker.
 * The text scrolls from right to left at a specified speed.
 */

public class NewsPanel extends JPanel implements ActionListener {
    private String newsText;
    private int xCoordinate;
    private Timer timer;

    /**
     * Constructs a NewsPanel with the specified news text.
     * 
     * @param Stronf of news text that is fetched in News class
     */
    
    public NewsPanel(String text) {
        this.newsText = text;
        this.xCoordinate = getWidth();
        this.timer = new Timer(30, this);  // Adjust the delay as needed
        this.timer.start();
    }

    /**
     * Paints the component with the scrolling news text.
     * 
     * @param g the Graphics object used for painting
     */
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString(newsText, xCoordinate, getHeight() / 2);
    }

    
    /**
     * Updates the x-coordinate of the text to create the scrolling effect.
     * 
     * @param e the action event triggered by the Timer
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        xCoordinate -= 5;  // Adjust the speed as needed
        if (xCoordinate + getFontMetrics(getFont()).stringWidth(newsText) < 0) {
            xCoordinate = getWidth();
        }
        repaint();
    }
    /**
     * Returns the JPanel managed by this class.
     * 
     * @return the JPanel containing the scrolling news ticker
     */


    public JPanel getPanel() {
        return this;
    }
    
    
    /**
     * You can see the impleementation of this in TestNewsClass
     */
}



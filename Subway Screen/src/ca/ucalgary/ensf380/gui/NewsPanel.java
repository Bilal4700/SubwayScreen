package ca.ucalgary.ensf380.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewsPanel extends JPanel implements ActionListener {
    private String newsText;
    private int xCoordinate;
    private Timer timer;

    public NewsPanel(String text) {
        this.newsText = text;
        this.xCoordinate = getWidth();
        this.timer = new Timer(30, this);  // Adjust the delay as needed
        this.timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString(newsText, xCoordinate, getHeight() / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        xCoordinate -= 5;  // Adjust the speed as needed
        if (xCoordinate + getFontMetrics(getFont()).stringWidth(newsText) < 0) {
            xCoordinate = getWidth();
        }
        repaint();
    }

    public JPanel getPanel() {
        return this;
    }
}



package ca.ucalgary.ensf380.test;

import javax.swing.JFrame;

import ca.ucalgary.ensf380.components.News;
import ca.ucalgary.ensf380.gui.NewsPanel;

public class TestNewsPanel {
	
    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame("News Ticker");
        News news = new News();
        try {
        	news.fetch();
        	String myNews = news.getNews();
            NewsPanel News = new NewsPanel(myNews);
            frame.add(News);
            frame.setSize(800, 100);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        } catch (Exception e) {
        	e.printStackTrace();
        }
    

    }

}

package ca.ucalgary.ensf380.test;

import ca.ucalgary.ensf380.components.News;

public class TestNews {

	public static void main(String[] args) {
		String countryCode;
		if (args.length > 0) {
		    countryCode = args[0];
		} else {
		    countryCode = "ca";
		}

        News news = new News(countryCode);
        try {
        	news.fetch();
        	String myNews = news.getNews();
        	System.out.println(myNews);
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }


}

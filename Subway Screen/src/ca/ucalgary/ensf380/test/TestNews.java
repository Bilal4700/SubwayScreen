package ca.ucalgary.ensf380.test;

import ca.ucalgary.ensf380.components.News;

public class TestNews {

	public static void main(String[] args) {
		String countryCode;
		if (args.length > 0) {
		    countryCode = args[0];
		} else {
		    countryCode = "";
		}

        News news = new News(countryCode);
        try {
           String myNews =  news.fetchAndParse();
           System.out.println(myNews);
        } catch (Exception e) {
            System.err.println("Failed to fetch and parse news");
        }
    }


}

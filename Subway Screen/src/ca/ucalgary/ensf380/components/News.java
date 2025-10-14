package ca.ucalgary.ensf380.components;

import java.net.URL;
import java.util.Scanner;
import javax.net.ssl.HttpsURLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * @author Muhammad Bilal
 * The News class is responsible for fetching the top news headlines
 * from a specified country using the News API.
 */
public class News implements Fetcher {
	
	private static final String REGEX = "(?:\"author\"\\s*:\\s*\"([^\"]*)\"\\s*,\\s*)?\"title\"\\s*:\\s*\"([^\"]*)\"";
	public  String countryCode ;
    private static final String API_KEY = "eb92e682e4994af1a0ce7f83e8c4ffbf";
	private String news = "";
	private static final Pattern PATTERN = Pattern.compile(REGEX);
	
	
	
	/*
	 * Lemme explain why i made two constructor
	 * One that is default is used when you dont provide the 3rd command line argument which is for news
	 * It sets the country code to "ca" by default
	 * 
	 * Other is used when you provide the country code, it changes the country code to 3rd argument
	 * 
	 * You will later see its implementation in SubwayScreen class
	 */
	
	
    /**
     * Default constructor for News class.
     * Initializes the country code to "ca" (Canada).
     */
	public News() {
		this.countryCode = "ca";
	}
	
	
    /**
     * Constructor for News class with a specified country code.
     *
     * @param countryCode the country code for fetching news headlines
     */
    public News(String countryCode) {
        this.countryCode = countryCode;
        
    }
    
    /**
     * Fetches the top news headlines from the specified country using the News API.
     * Extracts the author and title of each news article using regular expressions.
     *
     * @throws Exception if an error occurs during the fetch operation you can test it from TestNews class
     */
    
    
    @Override
    public void fetch() throws Exception {
        final String API_URL = "https://api.worldnewsapi.com/search-news?source-country=" + countryCode + "&api-key=" + API_KEY;
        URL urlObj = new URL(API_URL);
        HttpsURLConnection connection = (HttpsURLConnection) urlObj.openConnection();
        connection.setRequestMethod("GET");
        
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpsURLConnection.HTTP_OK) {
            StringBuilder sb = new StringBuilder();
            try (Scanner scanner = new Scanner(connection.getInputStream())) {
                while (scanner.hasNext()) {
                    sb.append(scanner.nextLine());
                    sb.append("\n");
                }
            }


            
            Matcher matcher = PATTERN.matcher(sb.toString());
            while (matcher.find()) {

                String author = matcher.group(1); 
                String title = matcher.group(2); 
                this.news +=  ((author == null || author.isEmpty()) ? "" : author + ": ") + title + "\n";


            }
        
        }

    }
    
    /**
     * @return the news headlines
     */
    public String getNews() {
    	return news;
    }
      

}

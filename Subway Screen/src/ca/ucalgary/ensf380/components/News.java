package ca.ucalgary.ensf380.components;

import java.net.URL;
import java.util.Scanner;
import javax.net.ssl.HttpsURLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class News extends Fetcher {
	
	private String REGEX = "\"author\":\\s*\"([^\"]*)\",\\s*\"title\":\\s*\"([^\"]*)\"";
	public  String countryCode ;
    private static final String API_KEY = "19705915eef84ed2af4ec71feda81a87";
	private String news;
	
	public News() {
		this.countryCode = "ca";
	}
	
    public News(String countryCode) {
        this.countryCode = countryCode;
        
    }
    
    
    @Override
    public void fetch() throws Exception {

        final String API_URL = "https://newsapi.org/v2/top-headlines?country=" + countryCode + "&apiKey=" + API_KEY;
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


            Pattern pattern = Pattern.compile(REGEX);
            Matcher matcher = pattern.matcher(sb.toString());
            while (matcher.find()) {

                String author = matcher.group(1); 
                String title = matcher.group(2); 
                this.news +=  author + ": " + title + "\n";


            }
        
        }

    }
    public String getNews() {
    	return news;
    }
      

}


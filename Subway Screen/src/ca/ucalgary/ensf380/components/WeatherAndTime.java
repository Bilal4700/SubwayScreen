package ca.ucalgary.ensf380.components;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import javax.net.ssl.HttpsURLConnection;

public class WeatherAndTime extends Fetcher {
    private String location;
    private String conditionIcon;
    private String temperature;
    private String wind;
    private String humidity;
    private String time;

    public WeatherAndTime(String location) {
        this.location = location;
    }
    
    @Override
    public void fetch() throws Exception {
        final String API_URL = "https://wttr.in/" + location + "?format=%l:%c:%t:%w:%h:%T";

        URL urlObj = new URL(API_URL);
        HttpsURLConnection connection = (HttpsURLConnection) urlObj.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpsURLConnection.HTTP_OK) {
            StringBuilder sb = new StringBuilder();
            try (Scanner scanner = new Scanner(connection.getInputStream())) {
                while (scanner.hasNext()) {
                    sb.append(scanner.nextLine());
                }
            }

            String[] weatherData = sb.toString().split(":");
            this.location = weatherData[0];
            this.conditionIcon = weatherData[1];
            this.temperature = weatherData[2];
            this.wind = weatherData[3];
            this.humidity = weatherData[4];
            String hours = weatherData[5];
            String min = weatherData[6];
            String sec = weatherData[7].split("-")[0];
            String time = hours + ":"+ min+ ":" + sec;
            this.time = time;

        } else {
            throw new Exception("Error Reading the response with response code: " + responseCode);
        }
        
    }
    


    public String getLocation() {
        return location;
    }

    public String getConditionIcon() {
        return conditionIcon;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getWind() {
        return wind;
    }

    public String getHumidity() {
        return humidity;
    }
    public String getTime() {
    	return time;
    }

}










package ca.ucalgary.ensf380.components;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import javax.net.ssl.HttpsURLConnection;
/**
 * The WeatherAndTime class is responsible for fetching weather conditions and current time
 * for a specified location using the wttr.in API.
 */
public class WeatherAndTime extends Fetcher {
    private String location;
    private String conditionIcon;
    private String temperature;
    private String wind;
    private String humidity;
    private String time;
    
    /**
     * Constructor for WeatherAndTime class.
     *
     * @param location the location for which weather and time information is to be fetched
     */
    public WeatherAndTime(String location) {
        this.location = location;
    }
    
    
    /**
     * Fetches the weather conditions and current time for the specified location using the wttr.in API.
     * The data includes location, weather condition icon, temperature, wind speed, humidity, and time.
     * You can see i am fetching these information for different end points of API
     * @throws Exception if an error occurs during the fetch operation
     */
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
    
    /**
     * Returns the location for which the weather and time information was fetched.
     *
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Returns the weather condition icon.
     *
     * @return the weather condition icon
     */
    public String getConditionIcon() {
        return conditionIcon;
    }

    /**
     * @return the temperature
     */
    public String getTemperature() {
        return temperature;
    }
    /**
     * @return the wind speed
     */
    public String getWind() {
        return wind;
    }
    /**
     * @return the humidity level
     */
    public String getHumidity() {
        return humidity;
    }
    /**
     * @return the current time
     * It fetches the current Time which is not runnable as it is String 
     * 
     * (SPOILER ALERT) 
     * we will run that later in WeaherAndTimePanel class 
     */
    public String getTime() {
    	return time;
    }

}










package ca.ucalgary.ensf380.test;
import ca.ucalgary.ensf380.components.WeatherAndTime;

public class TestWeatherAndTime {
    public static void main(String[] args) {
        // Create an instance of WeatherAndTime for a specific location
        WeatherAndTime weather = new WeatherAndTime("Beijing");

        // Fetch weather data
        try {
            weather.fetchWeather();

            // Print the fetched weather data
            System.out.println("Location: " + weather.getLocation());
            System.out.println("Condition Icon: " + weather.getConditionIcon());
            System.out.println("Temperature: " + weather.getTemperature());
            System.out.println("Wind: " + weather.getWind());
            System.out.println("Humidity: " + weather.getHumidity());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
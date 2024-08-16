package ca.ucalgary.ensf380.maps;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Fateh Ali
 * The ReadSubwayFile class provides functionality to read subway station data from a CSV file.
 */
public class ReadSubwayFile {
    private String csvFilePath;

    /**
     * Constructor that initializes the ReadSubwayFile with a specified file path.
     * 
     * @param csvFilePath the path to the CSV file containing subway station data.
     */
    public ReadSubwayFile() {
        this.csvFilePath = "./data/subway.csv";
    }
    
    /**
     * for Junit test
     * @param csvFilePath
     */
    public ReadSubwayFile(String csvFilePath) {
        this.csvFilePath = csvFilePath;
    }

    /**
     * Reads station data from the specified CSV file.
     * 
     * @return a list of Station objects read from the CSV file.
     */
    public List<Station> readStations() {
        List<Station> stations = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            int lineNumber = 0;

            while ((line = br.readLine()) != null) {
                // Ignore the first line (header)
                if (lineNumber == 0) {
                    lineNumber++;
                    continue;
                }

                String[] parts = line.split(",");
                if (parts.length >= 7) {
                    int stationNumber = Integer.parseInt(parts[2]);
                    String stationCode = parts[3];
                    double x = Double.parseDouble(parts[5]);
                    double y = Double.parseDouble(parts[6]);
                    String stationName = parts[4];

                    Station station = new Station(stationNumber, stationCode, x, y, stationName);
                    stations.add(station);
                }

                lineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stations;
    }
}

package ca.ucalgary.ensf380.maps;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadSubwayFile {
    private static final String CSV_FILE_PATH = "./data/subway.csv";

    public static List<Station> readStations() {
        List<Station> stations = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
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

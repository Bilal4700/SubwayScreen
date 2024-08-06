package ca.ucalgary.ensf380.maps;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LatestOutputReader implements Runnable {

    private static final String OUTPUT_DIRECTORY = "./out";  // Directory where output files are stored
    private static final int POLLING_INTERVAL = 15000;  // Polling interval in milliseconds
    private StationMapPanel stationMapPanel;
    private SmallMapPanel smallMapPanel;

    public LatestOutputReader(StationMapPanel stationMapPanel, SmallMapPanel smallMapPanel) {
        this.stationMapPanel = stationMapPanel;
        this.smallMapPanel = smallMapPanel;
    }

    @Override
    public void run() {
        startReading();
    }

    public void startReading() {
        File lastFile = null;

        while (true) {
            try {
                File latestFile = findLatestFile(OUTPUT_DIRECTORY);

                if (latestFile != null && !latestFile.equals(lastFile)) {
                    readAndStoreFile(latestFile);
                    lastFile = latestFile;
                } else {
                    System.out.println("No new file found.");
                }

                // Sleep for the specified polling interval before checking again
                Thread.sleep(POLLING_INTERVAL);

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static File findLatestFile(String dirPath) throws IOException {
        return Files.list(Paths.get(dirPath))
                .filter(Files::isRegularFile)
                .max(Comparator.comparingLong(path -> path.toFile().lastModified()))
                .map(Path::toFile)
                .orElse(null);
    }

    private synchronized void readAndStoreFile(File file) {
        List<Train> newTrains = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                if (lineNumber == 0) {
                    // Ignore the first line
                    lineNumber++;
                    continue;
                }

                String[] eachWord = line.split(",");
                if (eachWord.length >= 4) {
                    String trainNum = eachWord[1];
                    String atStation = eachWord[2];
                    String trainDirection = eachWord[3];

                    Train trainObj = new Train(trainNum, trainDirection, atStation);
                    newTrains.add(trainObj);

                    if (newTrains.size() >= 12) {
                        break;  // Stop after creating 12 trains
                    }
                }

                lineNumber++;
            }

            // Update the class-level trains list
            synchronized (this) {
            }

            // Notify the StationMapPanel about the updated train data
            stationMapPanel.updateTrains(newTrains);
            smallMapPanel.updateTrain(newTrains);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
}

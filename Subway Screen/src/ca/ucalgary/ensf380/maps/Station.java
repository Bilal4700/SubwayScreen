package ca.ucalgary.ensf380.maps;


/**
 * The Station class represents a subway station with a number, Station code, coordinates, and name.
 */
public class Station {
    private int stationNumber;
    private String stationCode;
    private double x;
    private double y;
    private String stationName;

    /**
     * Constructs a Station with the specified parameters.
     * 
     * @param stationNumber the number of the station
     * @param stationCode the code of the station
     * @param x the x-coordinate of the station
     * @param y the y-coordinate of the station
     * @param stationName the name of the station
     */
    public Station(int stationNumber, String stationCode, double x, double y,String stationName) {
        this.stationNumber = stationNumber;
        this.stationCode = stationCode;
        this.x = x;
        this.y = y;
        this.stationName = stationName;
    }


    public int getStationNumber() {
        return stationNumber;
    }

    public void setStationNumber(int stationNumber) {
        this.stationNumber = stationNumber;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
    
    public String getStationName() {
        return stationName;
    }

    // Setter method for stationName
    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    // toString method
    @Override
    public String toString() {
        return "Station{" +
                "stationNumber=" + stationNumber +
                ", stationCode=" + stationCode + '\'' +
                ", x=" + x +
                ", y=" + y +", stationName=" + stationName+
                '}';
    }
}

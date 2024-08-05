package ca.ucalgary.ensf380.maps;

public class Station {
    private int stationNumber;
    private String stationCode;
    private double x;
    private double y;
    private String stationName;

    // Constructor
    public Station(int stationNumber, String stationCode, double x, double y,String stationName) {
        this.stationNumber = stationNumber;
        this.stationCode = stationCode;
        this.x = x;
        this.y = y;
        this.stationName = stationName;
    }

    // Getters and Setters
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

package ca.ucalgary.ensf380.maps;

public class Station {
    private int stationNumber;
    private String stationCode;
    private double x;
    private double y;

    // Constructor
    public Station(int stationNumber, String stationCode, double x, double y) {
        this.stationNumber = stationNumber;
        this.stationCode = stationCode;
        this.x = x;
        this.y = y;
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

    // toString method
    @Override
    public String toString() {
        return "Station{" +
                "stationNumber=" + stationNumber +
                ", stationCode='" + stationCode + '\'' +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}

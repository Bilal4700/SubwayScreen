package ca.ucalgary.ensf380.maps;


public class Train {
    private String trainNum;
    private String trainDirection;
    private String atStation;

    public Train(String trainNum, String trainDirection, String atStation) {
        this.trainNum = trainNum;
        this.trainDirection = trainDirection;
        this.atStation = atStation;
    }

    public String getTrainNum() {
        return trainNum;
    }

    public void setTrainNum(String trainNum) {
        this.trainNum = trainNum;
    }

    public String getTrainDirection() {
        return trainDirection;
    }

    public void setTrainDirection(String trainDirection) {
        this.trainDirection = trainDirection;
    }

    public String getAtStation() {
        return atStation;
    }

    public void setAtStation(String atStation) {
        this.atStation = atStation;
    }

    @Override
    public String toString() {
        return "Train{" +
                "trainNum='" + trainNum + '\'' +
                ", trainDirection='" + trainDirection + '\'' +
                ", atStation='" + atStation + '\'' +
                '}';
    }
}

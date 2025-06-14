package Controller;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class AverageResults {
        private final SimpleStringProperty algorithmName;
        private final SimpleStringProperty averagetime;
        private final SimpleStringProperty totaltime;

        public AverageResults(String AlgorithmName, String averagetime, String totaltime) {
            this.algorithmName = new SimpleStringProperty(AlgorithmName);
            this.averagetime = new SimpleStringProperty(averagetime);
            this.totaltime = new SimpleStringProperty(totaltime);
        }

    public String getAlgorithmName() {
        return algorithmName.get();
    }

    public SimpleStringProperty algorithmNameProperty() {
        return algorithmName;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName.set(algorithmName);
    }

    public String getAveragetime() {
        return averagetime.get();
    }

    public SimpleStringProperty averagetimeProperty() {
        return averagetime;
    }

    public void setAveragetime(String averagetime) {
        this.averagetime.set(averagetime);
    }

    public String getTotaltime() {
        return totaltime.get();
    }

    public SimpleStringProperty totaltimeProperty() {
        return totaltime;
    }

    public void setTotaltime(String totaltime) {
        this.totaltime.set(totaltime);
    }
}

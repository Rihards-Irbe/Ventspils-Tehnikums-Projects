package Controller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class SortingResult {
    private final SimpleStringProperty method;
    private final SimpleStringProperty structure;
    private final SimpleIntegerProperty amount;
    private final SimpleLongProperty timeSec;
    private final SimpleLongProperty timeMs;
    private final SimpleLongProperty timeMsc;
    private final SimpleLongProperty timeNanSec;

    public SortingResult(String method, String structure, Integer amount, long time) {
        this.method = new SimpleStringProperty(method);
        this.structure = new SimpleStringProperty(structure);
        this.amount = new SimpleIntegerProperty(amount);
        this.timeSec = new SimpleLongProperty(time / 1_000_000_000);
        this.timeMs = new SimpleLongProperty(time / 1_000_000);
        this.timeMsc = new SimpleLongProperty(time / 1_000);
        this.timeNanSec = new SimpleLongProperty(time);
    }

    public String getMethod() {
        return method.get();
    }

    public SimpleStringProperty methodProperty() {
        return method;
    }

    public void setMethod(String method) {
        this.method.set(method);
    }

    public String getStructure() {
        return structure.get();
    }

    public SimpleStringProperty structureProperty() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure.set(structure);
    }

    public int getAmount() {
        return amount.get();
    }

    public SimpleIntegerProperty amountProperty() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount.set(amount);
    }

    public long getTimeSec() {
        return timeSec.get();
    }

    public SimpleLongProperty timeSecProperty() {
        return timeSec;
    }

    public void setTimeSec(long timeSec) {
        this.timeSec.set(timeSec);
    }

    public long getTimeMs() {
        return timeMs.get();
    }

    public SimpleLongProperty timeMsProperty() {
        return timeMs;
    }

    public void setTimeMs(long timeMs) {
        this.timeMs.set(timeMs);
    }

    public long getTimeMsc() {
        return timeMsc.get();
    }

    public SimpleLongProperty timeMscProperty() {
        return timeMsc;
    }

    public void setTimeMsc(long timeMsc) {
        this.timeMsc.set(timeMsc);
    }

    public long getTimeNanSec() {
        return timeNanSec.get();
    }

    public SimpleLongProperty timeNanSecProperty() {
        return timeNanSec;
    }

    public void setTimeNanSec(long timeNanSec) {
        this.timeNanSec.set(timeNanSec);
    }
}

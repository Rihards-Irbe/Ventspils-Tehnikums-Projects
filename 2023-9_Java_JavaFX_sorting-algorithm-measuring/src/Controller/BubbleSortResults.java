package Controller;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class BubbleSortResults {

    private final SimpleIntegerProperty ID;
    private final SimpleStringProperty BubbleSort_Amount;
    private final SimpleStringProperty BubbleSortResult_1;
    private final SimpleStringProperty BubbleSortResult_2;
    private final SimpleStringProperty BubbleSortDifference_1;
    private final SimpleStringProperty BubbleSortResult_3;
    private final SimpleStringProperty BubbleSortResult_4;
    private final SimpleStringProperty BubbleSortDifference_2;

    public BubbleSortResults(Integer ID, String BubbleSort_Amount, String BubbleSortResult_1, String BubbleSortResult_2, String BubbleSortDifference_1, String BubbleSortResult_3, String BubbleSortResult_4, String BubbleSortDifference_2) {
        this.ID = new SimpleIntegerProperty(ID);
        this.BubbleSort_Amount = new SimpleStringProperty(BubbleSort_Amount);
        this.BubbleSortResult_1 = new SimpleStringProperty(BubbleSortResult_1);
        this.BubbleSortResult_2 = new SimpleStringProperty(BubbleSortResult_2);
        this.BubbleSortDifference_1 = new SimpleStringProperty(BubbleSortDifference_1);
        this.BubbleSortResult_3 = new SimpleStringProperty(BubbleSortResult_3);
        this.BubbleSortResult_4 = new SimpleStringProperty(BubbleSortResult_4);
        this.BubbleSortDifference_2 = new SimpleStringProperty(BubbleSortDifference_2);
    }

    public int getID() {
        return ID.get();
    }

    public SimpleIntegerProperty IDProperty() {
        return ID;
    }

    public void setID(int ID) {
        this.ID.set(ID);
    }

    public String getBubbleSort_Amount() {
        return BubbleSort_Amount.get();
    }

    public SimpleStringProperty bubbleSort_AmountProperty() {
        return BubbleSort_Amount;
    }

    public void setBubbleSort_Amount(String bubbleSort_Amount) {
        this.BubbleSort_Amount.set(bubbleSort_Amount);
    }

    public String getBubbleSortResult_1() {
        return BubbleSortResult_1.get();
    }

    public SimpleStringProperty bubbleSortResult_1Property() {
        return BubbleSortResult_1;
    }

    public void setBubbleSortResult_1(String bubbleSortResult_1) {
        this.BubbleSortResult_1.set(bubbleSortResult_1);
    }

    public String getBubbleSortResult_2() {
        return BubbleSortResult_2.get();
    }

    public SimpleStringProperty bubbleSortResult_2Property() {
        return BubbleSortResult_2;
    }

    public void setBubbleSortResult_2(String bubbleSortResult_2) {
        this.BubbleSortResult_2.set(bubbleSortResult_2);
    }

    public String getBubbleSortDifference_1() {
        return BubbleSortDifference_1.get();
    }

    public SimpleStringProperty bubbleSortDifference_1Property() {
        return BubbleSortDifference_1;
    }

    public void setBubbleSortDifference_1(String bubbleSortDifference_1) {
        this.BubbleSortDifference_1.set(bubbleSortDifference_1);
    }

    public String getBubbleSortResult_3() {
        return BubbleSortResult_3.get();
    }

    public SimpleStringProperty bubbleSortResult_3Property() {
        return BubbleSortResult_3;
    }

    public void setBubbleSortResult_3(String bubbleSortResult_3) {
        this.BubbleSortResult_3.set(bubbleSortResult_3);
    }

    public String getBubbleSortResult_4() {
        return BubbleSortResult_4.get();
    }

    public SimpleStringProperty bubbleSortResult_4Property() {
        return BubbleSortResult_4;
    }

    public void setBubbleSortResult_4(String bubbleSortResult_4) {
        this.BubbleSortResult_4.set(bubbleSortResult_4);
    }

    public String getBubbleSortDifference_2() {
        return BubbleSortDifference_2.get();
    }

    public SimpleStringProperty bubbleSortDifference_2Property() {
        return BubbleSortDifference_2;
    }

    public void setBubbleSortDifference_2(String bubbleSortDifference_2) {
        this.BubbleSortDifference_2.set(bubbleSortDifference_2);
    }
}

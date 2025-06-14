package Controller;

import javafx.concurrent.Task;

public class SortingTask extends Task<Void> {
    private String algorithm;
    private String dataStructure;
    private int max;
    private Controller_Main controller;
    private long sortingTime; // Add this instance variable

    public SortingTask(String algorithm, String dataStructure, int max, long sortingTime, Controller_Main controller) {
        this.algorithm = algorithm;
        this.dataStructure = dataStructure;
        this.max = max;
        this.sortingTime = sortingTime; // Store sortingTime
        this.controller = controller;
    }

    @Override
    protected Void call() throws Exception {
        // Perform your sorting operations here

        // When sorting is complete, update UI with the results and sorting time
        controller.updateUIWithSortingResults(algorithm, dataStructure, max, sortingTime);

        return null;
    }

}

package Controller;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.scene.control.Button;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.*;
import java.text.DecimalFormat;

public class Controller_Main {

    @FXML
    private ToggleGroup Algorithm, DataStructure, Amount, JaunaDatuStruktura, Merijumi, NewAlgorithmStructure, NewDataStructure, AmountOfElements, BubbleSortAmount;

    @FXML
    private TableView<SortingResult> SortingTable;

    @FXML
    private TableColumn<SortingResult, String> TableMethod;

    @FXML
    private TableColumn<SortingResult, String> TableStructure;

    @FXML
    private TableColumn<SortingResult, Integer> TableAmount;

    @FXML
    private TableColumn<SortingResult, Long> TableTimeSec;

    @FXML
    private TableColumn<SortingResult, Long> TableTimeMs;

    @FXML
    private TableColumn<SortingResult, Long> TableTimeMsc;

    @FXML
    private TableColumn<SortingResult, Long> TableTimeNanSec;

    @FXML
    private TableView<AverageResults> AverageTimeTable;

    @FXML
    private TableColumn<AverageResults, String> SortingAlgorithmName;
    @FXML
    private TableColumn<AverageResults, String> AverageTime;
    @FXML
    private TableColumn<AverageResults, String> TotalTime;

    @FXML
    private TableView <BubbleSortResults> BubbleResults;
    @FXML
    private TableColumn<BubbleSortResults, Integer> id;
    @FXML
    private TableColumn<BubbleSortResults, String> BubbleAmount;
    @FXML
    private TableColumn<BubbleSortResults, Integer> Bubble_one;
    @FXML
    private TableColumn<BubbleSortResults, Integer> Bubble_two;
    @FXML
    private TableColumn<BubbleSortResults, Integer> Bubble_Difference_one;
    @FXML
    private TableColumn<BubbleSortResults, Integer> Bubble_three;
    @FXML
    private TableColumn<BubbleSortResults, Integer> Bubble_four;
    @FXML
    private TableColumn<BubbleSortResults, Integer> Bubble_Difference_two;


    @FXML
    private Text AlgorithmError, TenMeasurements, IzveletsKartosanas, IzveletaDatuStruktura, IzveletsElementuSkaits, IzveletaJaunaDatuStruk, IzveletsVeiktDesmit, IzveletDatuStrukturu, IzveletElementuSkait, IzveletVeidotJaunu, BubbleSortNavIzvele;
    @FXML
    Button RainbowButton;


    private ObservableList<SortingResult> sortingResults = FXCollections.observableArrayList();
    private ObservableList<AverageResults> AverageTimeResults = FXCollections.observableArrayList();
    private ObservableList<BubbleSortResults> BubbleSortResults = FXCollections.observableArrayList();
    @FXML
    Pane ChartPane, CreateNewDataStructurePane, BubbleSortTablePane;
    @FXML
    BarChart Chart;
    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;
    @FXML
    Button ChartClose, AverageButton;
    @FXML
    RadioButton VeidotJaunuJa, VeidotJaunuNe, viens, divi, tris, cetri, pieci, Masivs, ArrayList, onek, tenk, hundredk, onem, tenm;
    int repeat = 1;
    int goal = 0;

    public static boolean ArrayMade = false;
    public static boolean ArrayListMade = false;
    public static int[] array;
    public static int[] oldarray;
    public long Laiks2, Laiks4;
    private int colorIndex = 0;
    public int BubbleSortID = 0;

    public static ArrayList<Integer> arraylist = new ArrayList<Integer>();
    public static ArrayList<Integer> oldarraylist = new ArrayList<Integer>();

    public void initialize() {
        TableMethod.setCellValueFactory(new PropertyValueFactory<>("method"));
        TableStructure.setCellValueFactory(new PropertyValueFactory<>("structure"));
        TableAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        TableTimeSec.setCellValueFactory(new PropertyValueFactory<>("timeSec"));
        TableTimeMs.setCellValueFactory(new PropertyValueFactory<>("timeMs"));
        TableTimeMsc.setCellValueFactory(new PropertyValueFactory<>("timeMsc"));
        TableTimeNanSec.setCellValueFactory(new PropertyValueFactory<>("timeNanSec"));

        ArrayList<String> Colors = new ArrayList<String>();
        Colors.add("Lime");
        Colors.add("Orange");
        Colors.add("Green");
        Colors.add("Purple");
        Colors.add("Cyan");

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    RainbowButton.setStyle("-fx-background-color: " + (Colors.get(colorIndex)) + "; -fx-cursor: hand; -fx-border-radius: 7px; -fx-border-color: black; -fx-background-radius: 7px; -fx-font-weight: bold;");
                    colorIndex = (colorIndex + 1) % Colors.size();
                })
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        SortingTable.setItems(sortingResults);

    }

    public void SelectedMethod() {

        String SelectedAlgorithm = null;
        String SelectedDataStructure = null;
        String SelectedAmount = null;
        String SelectedNew = null;
        String Atkartot = null;

        if (Algorithm.getSelectedToggle() == null) {
            IzveletsKartosanas.setVisible(true);
        } else {
            IzveletsKartosanas.setVisible(false);
            RadioButton selectedRadioButton = (RadioButton) Algorithm.getSelectedToggle();
            SelectedAlgorithm = selectedRadioButton.getText();
        }

        if (DataStructure.getSelectedToggle() == null) {
            IzveletaDatuStruktura.setVisible(true);
        } else {
            IzveletaDatuStruktura.setVisible(false);
            RadioButton selectedRadioButton2 = (RadioButton) DataStructure.getSelectedToggle();
            SelectedDataStructure = selectedRadioButton2.getText();
        }

        if (Amount.getSelectedToggle() == null) {
            IzveletsElementuSkaits.setVisible(true);
        } else {
            IzveletsElementuSkaits.setVisible(false);
            RadioButton selectedRadioButton3 = (RadioButton) Amount.getSelectedToggle();
            SelectedAmount = selectedRadioButton3.getText();
        }

        if(JaunaDatuStruktura.getSelectedToggle() == null){
            IzveletaJaunaDatuStruk.setVisible(true);
        } else{
            IzveletaJaunaDatuStruk.setVisible(false);
            RadioButton selectedRadioButton4 = (RadioButton) JaunaDatuStruktura.getSelectedToggle();
            SelectedNew = selectedRadioButton4.getText();

        }

        if(Merijumi.getSelectedToggle() == null){
            IzveletsVeiktDesmit.setVisible(true);
        }else{
            IzveletsVeiktDesmit.setVisible(false);
            RadioButton selectedRadioButton5 = (RadioButton) Merijumi.getSelectedToggle();
            Atkartot = selectedRadioButton5.getText();
        }


        if (SelectedAlgorithm == null || SelectedDataStructure == null || SelectedAmount == null || SelectedNew == null || Atkartot == null) {

        } else {
            IzveletsKartosanas.setVisible(false);
            IzveletaDatuStruktura.setVisible(false);
            IzveletsElementuSkaits.setVisible(false);
            IzveletaJaunaDatuStruk.setVisible(false);
            IzveletsVeiktDesmit.setVisible(false);
            StartSorting(SelectedAlgorithm, SelectedDataStructure, SelectedAmount, SelectedNew, Atkartot);
        }

    }

    public void StartSorting(String algorithm, String datastructure, String amount, String jauns, String atkartot) {

        if (atkartot.equals("Jā")) {
            repeat = 10;
        } else if(atkartot.equals("Nē")){
            repeat = 1;
        }else if(atkartot.equals("30")){
            repeat = 30;
        }

        while (repeat != goal) {
            int max = 0;
            int min = 1;
            int range;

            if (jauns.equals("Jā")) {

                if (datastructure.equals("Masivs")) {
                    ArrayMade = false;
                } else {
                    ArrayListMade = false;
                }

            }

            switch (amount) {
                case "1,000":
                    max = 1000;
                    if (array != null && arraylist != null) {
                        if (datastructure.equals("Masivs") && array.length != max) {
                            ArrayMade = false;
                        } else if (datastructure.equals("ArrayList") && arraylist.size() != max) {
                            ArrayListMade = false;
                        }
                    }
                    break;
                case "10,000":
                    max = 10000;
                    if (array != null && arraylist != null) {
                        if (datastructure.equals("Masivs") && array.length != max) {
                            ArrayMade = false;
                        } else if (datastructure.equals("ArrayList") && arraylist.size() != max) {
                            ArrayListMade = false;
                        }
                    }
                    break;
                case "100,000":
                    max = 100000;
                    if (array != null && arraylist != null) {
                        if (datastructure.equals("Masivs") && array.length != max) {
                            ArrayMade = false;
                        } else if (datastructure.equals("ArrayList") && arraylist.size() != max) {
                            ArrayListMade = false;
                        }
                    }
                    break;
                case "1,000,000":
                    max = 1000000;
                    if (array != null && arraylist != null) {
                        if (datastructure.equals("Masivs") && array.length != max) {
                            ArrayMade = false;
                        } else if (datastructure.equals("ArrayList") && arraylist.size() != max) {
                            ArrayListMade = false;
                        }
                    }
                    break;
                case "10,000,000":
                    max = 10000000;
                    if (array != null && arraylist != null) {
                        if (datastructure.equals("Masivs") && array.length != max) {
                            ArrayMade = false;
                        } else if (datastructure.equals("ArrayList") && arraylist.size() != max) {
                            ArrayListMade = false;
                        }
                    }
                    break;
                default:
                    System.out.println("Something Went Wrong While Selecting The Amount");
                    break;
            }

            boolean full = false;
            int total = 0;

            if (!ArrayMade) {

                int[] createarray = new int[max];

                full = false;
                total = 0;

                while (!full) {
                    range = max - min + 1;
                    int rand = (int) (Math.random() * range) + min;
                    boolean duplicate = false;

                    for (int i = 0; i < total; i++) {
                        if (createarray[i] == rand) {
                            duplicate = true;
                            break;
                        }
                    }

                    if (!duplicate) {
                        createarray[total] = rand;
                        total++;

                        if (total == max) {
                            full = true;
                        }
                    }
                }
                oldarray = createarray;
                array = Arrays.copyOf(oldarray, oldarray.length);
                ArrayMade = true;

            } else {
                array = Arrays.copyOf(oldarray, oldarray.length);
            }

            if (!ArrayListMade) {

                ArrayList<Integer> createarraylist = new ArrayList<Integer>();

                range = max - min + 1;
                full = false;
                total = 0;

                while (!full) {
                    int rand = (int) (Math.random() * range) + min;

                    if (createarraylist.contains(rand)) {

                    } else {
                        createarraylist.add(rand);
                        total++;
                    }

                    if (total == max) {
                        full = true;
                    }
                }

                oldarraylist = createarraylist;
                arraylist = new ArrayList<>(oldarraylist);
                ArrayListMade = true;

            } else {
                arraylist = new ArrayList<>(oldarraylist);
            }


            switch (algorithm) {
                case "Neizmantojot":
                    if (datastructure.equals("Masivs")) {

                        //parbaudu pirms un pec - System.out.println("Pirms: " + Arrays.toString(array));

                        long startTime = System.nanoTime();

                        boolean sorted = false;

                        while (!sorted) {
                            sorted = true;
                            for (int i = 0; i < array.length - 1; i++) {
                                if (array[i] > array[i + 1]) { //ja arraya nakamais indexs ir mazaks
                                    int elemenets = array[i]; //elements ir konkretais index(i)
                                    array[i] = array[i + 1]; //samaina vietam, proti konkretais index ir vienads ar nakamo, jo tas ir mazaks, kjip, lai butu augosa seciba
                                    array[i + 1] = elemenets; //un nakamais indexs ir elements, jo tas ir lielaks
                                    sorted = false; //sorted ir false, jo izejoc cauri sim while ciklam tika runots if(konkretais ir lielaks par nakamo)
                                }
                            }
                        }

                        long endTime = System.nanoTime();
                        long Time = endTime - startTime;

                        SortingTask sortingTask = new SortingTask(algorithm, datastructure, max, Time, this);
                        new Thread(sortingTask).start();

                        SortingResult result = new SortingResult("Neizmantojot", "Array", max, Time);
                        sortingResults.add(result);

                        //parbaudu pirms un pec - System.out.println("Pec: " + Arrays.toString(array));
                        //parbaudu vai laiks strada - System.out.println("Laiks: " + Time);


                    } else {


                        long startTime = System.nanoTime();

                        boolean sorted = false;

                        while (!sorted) {

                            sorted = true;
                            for (int i = 0; i < arraylist.size() - 1; i++) {
                                if (arraylist.get(i) > arraylist.get(i + 1)) {
                                    int elements = arraylist.get(i);
                                    arraylist.set(i, arraylist.get(i + 1));
                                    arraylist.set(i + 1, elements);
                                    sorted = false;
                                }

                            }

                        }

                        long endTime = System.nanoTime();
                        long Time = endTime - startTime;


                        SortingTask sortingTask = new SortingTask(algorithm, datastructure, max, Time, this);
                        new Thread(sortingTask).start();

                        SortingResult result = new SortingResult("Neizmantojot", "ArrayList", max, Time);
                        sortingResults.add(result);
                    }
                    break;
                case "BubbleSort":
                    if (datastructure.equals("Masivs")) {


                        long Time = bubbleSort(array); //bubbleSort prieks masiva dabuju no interneta

                        SortingTask sortingTask = new SortingTask(algorithm, datastructure, max, Time, this);
                        new Thread(sortingTask).start();

                        SortingResult result = new SortingResult("BubbleSort", "Array", max, Time);
                        sortingResults.add(result);

                    } else {


                        long Time = bubbleSortArlist(arraylist); //bubbleSort prieks arraylist dabuju no chatgbt, promts bija, lai parveido doto bubbleSort, ta lai tas butu comatible ar arraylist

                        SortingTask sortingTask = new SortingTask(algorithm, datastructure, max, Time, this);
                        new Thread(sortingTask).start();

                        SortingResult result = new SortingResult("BubbleSort", "ArrayList", max, Time);
                        sortingResults.add(result);

                    }
                    break;
                case "Selection Sort":
                    if (datastructure.equals("Masivs")) {


                        long Time = selectionSort(array);

                        SortingTask sortingTask = new SortingTask(algorithm, datastructure, max, Time, this);
                        new Thread(sortingTask).start();

                        SortingResult result = new SortingResult("Selection Sort", "Array", max, Time);
                        sortingResults.add(result);

                    } else {

                        long Time = selectionSortArlist(arraylist);

                        SortingTask sortingTask = new SortingTask(algorithm, datastructure, max, Time, this);
                        new Thread(sortingTask).start();

                        SortingResult result = new SortingResult("Selection Sort", "ArrayList", max, Time);
                        sortingResults.add(result);
                    }
                    break;
                case "Merge Sort":

                    if (datastructure.equals("Masivs")) {


                        long startTime = System.nanoTime();
                        mergeSort(array);
                        long endTime = System.nanoTime();
                        long Time = endTime - startTime;

                        SortingTask sortingTask = new SortingTask(algorithm, datastructure, max, Time, this);
                        new Thread(sortingTask).start();

                        SortingResult result = new SortingResult("Merge Sort", "Array", max, Time);
                        sortingResults.add(result);

                    } else {

                        long startTime = System.nanoTime();
                        mergeSortArList(arraylist);
                        long endTime = System.nanoTime();
                        long Time = endTime - startTime;

                        SortingTask sortingTask = new SortingTask(algorithm, datastructure, max, Time, this);
                        new Thread(sortingTask).start();

                        SortingResult result = new SortingResult("Merge Sort", "ArrayList", max, Time);
                        sortingResults.add(result);

                    }

                    break;
                case "Quick Sort":
                    if (datastructure.equals("Masivs")) {

                        long startTime = System.nanoTime();
                        quickSort(array, 0, array.length - 1);
                        long endTime = System.nanoTime();
                        long Time = endTime - startTime;

                        SortingTask sortingTask = new SortingTask(algorithm, datastructure, max, Time, this);
                        new Thread(sortingTask).start();

                        SortingResult result = new SortingResult("Quick Sort", "Array", max, Time);
                        sortingResults.add(result);

                    } else {


                        long startTime = System.nanoTime();
                        quickSortArList(arraylist, 0, arraylist.size() - 1);
                        long endTime = System.nanoTime();
                        long Time = endTime - startTime;

                        SortingTask sortingTask = new SortingTask(algorithm, datastructure, max, Time, this);
                        new Thread(sortingTask).start();

                        SortingResult result = new SortingResult("Quick Sort", "ArrayList", max, Time);
                        sortingResults.add(result);
                    }
                    break;
                case "Shell Sort":
                    if (datastructure.equals("Masivs")) {


                        long startTime = System.nanoTime();
                        shellSort(array);
                        long endTime = System.nanoTime();
                        long Time = endTime - startTime;

                        SortingTask sortingTask = new SortingTask(algorithm, datastructure, max, Time, this);
                        new Thread(sortingTask).start();

                        SortingResult result = new SortingResult("Shell Sort", "Array", max, Time);
                        sortingResults.add(result);

                    } else {

                        long startTime = System.nanoTime();
                        shellSortArList(arraylist);
                        long endTime = System.nanoTime();
                        long Time = endTime - startTime;

                        SortingTask sortingTask = new SortingTask(algorithm, datastructure, max, Time, this);
                        new Thread(sortingTask).start();

                        SortingResult result = new SortingResult("Shell Sort", "ArrayList", max, Time);
                        sortingResults.add(result);
                    }
                    break;
                case "Javas iebūvētais":
                    if (datastructure.equals("Masivs")) {

                        long startTime = System.nanoTime();
                        Arrays.sort(array);
                        long endTime = System.nanoTime();
                        long Time = endTime - startTime;

                        SortingTask sortingTask = new SortingTask(algorithm, datastructure, max, Time, this);
                        new Thread(sortingTask).start();

                        SortingResult result = new SortingResult("Javas iebūvētais", "Array", max, Time);
                        sortingResults.add(result);

                    } else {


                        long startTime = System.nanoTime();
                        Collections.sort(arraylist);
                        long endTime = System.nanoTime();
                        long Time = endTime - startTime;

                        SortingTask sortingTask = new SortingTask(algorithm, datastructure, max, Time, this);
                        new Thread(sortingTask).start();

                        SortingResult result = new SortingResult("Javas iebūvētais", "ArrayList", max, Time);
                        sortingResults.add(result);
                    }
                    break;
                case "Special": //vnk izveidos array mana vieta, nerekinot un neievadot rezultatus tabula
                    break;
                default:
                    System.out.println("Something Went Wrong!");
                    break;
            }
            goal = goal + 1;
        }

        if (SortingTable.getItems().size() >= 10) {
            TenMeasurements.setVisible(false);
            AverageButton.setDisable(false);
        }

        goal = 0;
    }

    public void DeleteSelectedRow() {

        SortingResult selectedRow = SortingTable.getSelectionModel().getSelectedItem();
        SortingTable.getItems().remove(selectedRow);

    }

    static long bubbleSort(int[] arr) {

        long startTime = System.nanoTime();

        int n = arr.length;
        int temp = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (arr[j - 1] > arr[j]) {
                    //swap elements
                    temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                }

            }
        }

        long endTime = System.nanoTime();
        return endTime - startTime;

    }

    static long bubbleSortArlist(List<Integer> list) {

        long startTime = System.nanoTime();

        int n = list.size();
        int temp = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (list.get(j - 1) > list.get(j)) {
                    // Swap elements
                    temp = list.get(j - 1);
                    list.set(j - 1, list.get(j));
                    list.set(j, temp);
                }
            }
        }

        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    static long selectionSort(int[] arr) {

        long startTime = System.nanoTime();

        for (int i = 0; i < arr.length - 1; i++) {
            int index = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[index]) {
                    index = j;//searching for lowest index
                }
            }
            int smallerNumber = arr[index];
            arr[index] = arr[i];
            arr[i] = smallerNumber;
        }

        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    static long selectionSortArlist(List<Integer> list) {
        long startTime = System.nanoTime();

        int n = list.size();

        for (int i = 0; i < n - 1; i++) {
            int index = i;
            for (int j = i + 1; j < n; j++) {
                if (list.get(j) < list.get(index)) {
                    index = j; // searching for the lowest index
                }
            }
            int smallerNumber = list.get(index);
            list.set(index, list.get(i));
            list.set(i, smallerNumber);
        }

        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    public static void mergeSort(int[] arr) {

        if (arr == null || arr.length <= 1) {
            return; // Base case: already sorted or empty array
        }

        int mid = arr.length / 2;
        int[] left = new int[mid];
        int[] right = new int[arr.length - mid];

        // Copy data to temporary arrays left[] and right[]
        System.arraycopy(arr, 0, left, 0, mid);
        System.arraycopy(arr, mid, right, 0, arr.length - mid);

        // Recursively sort both sub-arrays
        mergeSort(left);
        mergeSort(right);

        // Merge the sorted left and right sub-arrays
        merge(arr, left, right);

    }

    private static void merge(int[] arr, int[] left, int[] right) {

        int i = 0, j = 0, k = 0;

        // Merge elements from left[] and right[] back into arr[]
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }

        // Copy remaining elements of left[] and right[], if any
        while (i < left.length) {
            arr[k++] = left[i++];
        }

        while (j < right.length) {
            arr[k++] = right[j++];
        }

    }

    public static void mergeSortArList(List<Integer> list) {

        if (list == null || list.size() <= 1) {
            return; // Base case: already sorted or empty list
        }

        int mid = list.size() / 2;
        List<Integer> left = new ArrayList<>(list.subList(0, mid));
        List<Integer> right = new ArrayList<>(list.subList(mid, list.size()));

        // Recursively sort both sub-lists
        mergeSortArList(left);
        mergeSortArList(right);

        // Merge the sorted left and right sub-lists
        mergeArList(list, left, right);
    }

    private static void mergeArList(List<Integer> list, List<Integer> left, List<Integer> right) {
        int i = 0, j = 0, k = 0;

        // Merge elements from left and right sub-lists back into the original list
        while (i < left.size() && j < right.size()) {
            if (left.get(i) <= right.get(j)) {
                list.set(k++, left.get(i++));
            } else {
                list.set(k++, right.get(j++));
            }
        }

        // Copy remaining elements of left and right sub-lists, if any
        while (i < left.size()) {
            list.set(k++, left.get(i++));
        }

        while (j < right.size()) {
            list.set(k++, right.get(j++));
        }

    }

    public static void quickSort(int[] arr, int low, int high) {

        if (low < high) {
            int pivotIndex = partition(arr, low, high);

            // Recursively sort the elements before and after the pivot
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    public static int partition(int[] arr, int low, int high) {
        // Choose the pivot element (in this case, we'll use the last element)
        int pivot = arr[high];

        // Index of the smaller element
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            // If the current element is smaller than or equal to the pivot
            if (arr[j] <= pivot) {
                i++;

                // Swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // Swap arr[i+1] and arr[high] (pivot)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;

    }

    public static void quickSortArList(List<Integer> arrayList, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arrayList, low, high);

            // Recursively sort the elements before and after the pivot
            quickSortArList(arrayList, low, pivotIndex - 1);
            quickSortArList(arrayList, pivotIndex + 1, high);
        }
    }

    public static int partition(List<Integer> arrayList, int low, int high) {
        int pivot = arrayList.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arrayList.get(j) <= pivot) {
                i++;

                // Swap arrayList[i] and arrayList[j]
                int temp = arrayList.get(i);
                arrayList.set(i, arrayList.get(j));
                arrayList.set(j, temp);
            }
        }

        // Swap arrayList[i+1] and arrayList[high] (pivot)
        int temp = arrayList.get(i + 1);
        arrayList.set(i + 1, arrayList.get(high));
        arrayList.set(high, temp);

        return i + 1;
    }

    public static void shellSort(int[] arr) {
        int n = arr.length;

        // Start with a large gap and reduce it in each iteration
        for (int gap = n / 2; gap > 0; gap /= 2) {
            // Perform an insertion sort for elements at each gap
            for (int i = gap; i < n; i++) {
                int temp = arr[i];
                int j;
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) {
                    arr[j] = arr[j - gap];
                }
                arr[j] = temp;
            }
        }
    }

    public static void shellSortArList(List<Integer> arrayList) {
        int n = arrayList.size();

        // Start with a large gap and reduce it in each iteration
        for (int gap = n / 2; gap > 0; gap /= 2) {
            // Perform an insertion sort for elements at each gap
            for (int i = gap; i < n; i++) {
                int temp = arrayList.get(i);
                int j;
                for (j = i; j >= gap && arrayList.get(j - gap) > temp; j -= gap) {
                    arrayList.set(j, arrayList.get(j - gap));
                }
                arrayList.set(j, temp);
            }
        }
    }

    public void updateUIWithSortingResults(String algorithm, String dataStructure, int max, long sortingTime) {
            //todo- remove
    }

    public void CloseChartPane() {

        ChartPane.setVisible(false);
        ChartClose.setVisible(false);

        Chart.getData().clear();

        AverageTimeTable.getItems().clear();

    }

    public void ShowChart() {

        if (SortingTable.getItems().size() < 10) {
            TenMeasurements.setVisible(true);
            AverageButton.setDisable(true);
        } else {

            int totalNeizmantojot = 0;
            int totalTimeNeizmantojot = 0;
            int Neizmantojotnanosec;
            int actualTotalTimeNeizmantojot = 0;
            boolean containsNeizmantojot = false;

            int totalBubbleSort = 0;
            int totalTimeBubbleSort = 0;
            int BubbleSortnanosec;
            int actualTotalTimeBubbleSort = 0;
            boolean containsBubbleSort = false;

            int totalSelectionSort = 0;
            int totalTimeSelectionSort = 0;
            int SelectionSortnanosec;
            int actualTotalTimeSelectionSort = 0;
            boolean containsSelectionSort = false;

            int totalMergeSort = 0;
            int totalTimeMergeSort = 0;
            int MergeSortnanosec;
            int actualTotalTimeMergeSort = 0;
            boolean containsMergeSort = false;

            int totalQuickSort = 0;
            int totalTimeQuickSort = 0;
            int QuickSortnanosec;
            int actualTotalTimeQuickSort = 0;
            boolean containsQuickSort = false;

            int totalShellSort = 0;
            int totalTimeShellSort = 0;
            int ShellSortnanosec;
            int actualTotalTimeShellSort = 0;
            boolean containsShellSort = false;

            int totalJavaBuiltIn = 0;
            int totalTimeJavaBuiltIn = 0;
            int JavaBuiltInnanosec;
            int actualTotalTimeJavaBuiltIn = 0;
            boolean containsJavaBuiltIn = false;

            ChartPane.setVisible(true);
            ChartClose.setVisible(true);

        /* XYChart.Series set1 = new XYChart.Series<>();
        set1.getData().add(new XYChart.Data(TableMethod.getCellData(i), TableTimeNanSec.getCellData(i)));
        Chart.getData().addAll(set1);
        */


            SortingAlgorithmName.setCellValueFactory(new PropertyValueFactory<>("algorithmName"));
            AverageTime.setCellValueFactory(new PropertyValueFactory<>("averagetime"));
            TotalTime.setCellValueFactory(new PropertyValueFactory<>("totaltime"));

            AverageTimeTable.setItems(AverageTimeResults);

            for (int i = 0; i < SortingTable.getItems().size(); i++) {
                if (TableMethod.getCellData(i).equals("Neizmantojot")) {
                    if (TableAmount.getCellData(i).equals(10000)) {
                        Neizmantojotnanosec = Math.toIntExact(TableTimeNanSec.getCellData(i) / 10); // for 10,000
                        totalTimeNeizmantojot = (totalTimeNeizmantojot + Neizmantojotnanosec);
                    } else if (TableAmount.getCellData(i).equals(100000)) {
                        Neizmantojotnanosec = Math.toIntExact(TableTimeNanSec.getCellData(i) / 100); // for 100,000
                        totalTimeNeizmantojot = (totalTimeNeizmantojot + Neizmantojotnanosec);
                    } else if (TableAmount.getCellData(i).equals(1000000)) {
                        Neizmantojotnanosec = Math.toIntExact(TableTimeNanSec.getCellData(i) / 1000);// for 1,000,000
                        totalTimeNeizmantojot = (totalTimeNeizmantojot + Neizmantojotnanosec);
                    } else {
                        Neizmantojotnanosec = Math.toIntExact(TableTimeNanSec.getCellData(i)); // for 1000
                        totalTimeNeizmantojot = (totalTimeNeizmantojot + Neizmantojotnanosec);
                    }
                    actualTotalTimeNeizmantojot = (int) (actualTotalTimeNeizmantojot + TableTimeNanSec.getCellData(i));
                    totalNeizmantojot = totalNeizmantojot + 1;
                    containsNeizmantojot = true;
                }

                if (TableMethod.getCellData(i).equals("BubbleSort")) {
                    if (TableAmount.getCellData(i).equals(10000)) {
                        BubbleSortnanosec = Math.toIntExact(TableTimeNanSec.getCellData(i) / 10); // for 10,000
                        totalTimeBubbleSort = (totalTimeBubbleSort + BubbleSortnanosec);
                    } else if (TableAmount.getCellData(i).equals(100000)) {
                        BubbleSortnanosec = Math.toIntExact(TableTimeNanSec.getCellData(i) / 100); // for 100,000
                        totalTimeBubbleSort = (totalTimeBubbleSort + BubbleSortnanosec);
                    } else if (TableAmount.getCellData(i).equals(1000000)) {
                        BubbleSortnanosec = Math.toIntExact(TableTimeNanSec.getCellData(i) / 1000);// for 1,000,000
                        totalTimeBubbleSort = (totalTimeBubbleSort + BubbleSortnanosec);
                    } else {
                        BubbleSortnanosec = Math.toIntExact(TableTimeNanSec.getCellData(i)); // for 1000
                        totalTimeBubbleSort = (totalTimeBubbleSort + BubbleSortnanosec);
                    }
                    actualTotalTimeBubbleSort = (int) (actualTotalTimeBubbleSort + TableTimeNanSec.getCellData(i));
                    totalBubbleSort = totalBubbleSort + 1;
                    containsBubbleSort = true;
                }

                if (TableMethod.getCellData(i).equals("Selection Sort")) {
                    if (TableAmount.getCellData(i).equals(10000)) {
                        SelectionSortnanosec = Math.toIntExact(TableTimeNanSec.getCellData(i) / 10); // for 10,000
                        totalTimeSelectionSort = (totalTimeSelectionSort + SelectionSortnanosec);
                    } else if (TableAmount.getCellData(i).equals(100000)) {
                        SelectionSortnanosec = Math.toIntExact(TableTimeNanSec.getCellData(i) / 100); // for 100,000
                        totalTimeSelectionSort = (totalTimeSelectionSort + SelectionSortnanosec);
                    } else if (TableAmount.getCellData(i).equals(1000000)) {
                        SelectionSortnanosec = Math.toIntExact(TableTimeNanSec.getCellData(i) / 1000);// for 1,000,000
                        totalTimeSelectionSort = (totalTimeSelectionSort + SelectionSortnanosec);
                    } else {
                        SelectionSortnanosec = Math.toIntExact(TableTimeNanSec.getCellData(i)); // for 1000
                        totalTimeSelectionSort = (totalTimeSelectionSort + SelectionSortnanosec);
                    }
                    actualTotalTimeSelectionSort = (int) (actualTotalTimeSelectionSort + TableTimeNanSec.getCellData(i));
                    totalSelectionSort = totalSelectionSort + 1;
                    containsSelectionSort = true;
                }

                if (TableMethod.getCellData(i).equals("Merge Sort")) {
                    if (TableAmount.getCellData(i).equals(10000)) {
                        MergeSortnanosec = Math.toIntExact(TableTimeNanSec.getCellData(i) / 10); // for 10,000
                        totalTimeMergeSort = (totalTimeMergeSort + MergeSortnanosec);
                    } else if (TableAmount.getCellData(i).equals(100000)) {
                        MergeSortnanosec = Math.toIntExact(TableTimeNanSec.getCellData(i) / 100); // for 100,000
                        totalTimeMergeSort = (totalTimeMergeSort + MergeSortnanosec);
                    } else if (TableAmount.getCellData(i).equals(1000000)) {
                        MergeSortnanosec = Math.toIntExact(TableTimeNanSec.getCellData(i) / 1000);// for 1,000,000
                        totalTimeMergeSort = (totalTimeMergeSort + MergeSortnanosec);
                    } else {
                        MergeSortnanosec = Math.toIntExact(TableTimeNanSec.getCellData(i)); // for 1000
                        totalTimeMergeSort = (totalTimeMergeSort + MergeSortnanosec);
                    }
                    actualTotalTimeMergeSort = (int) (actualTotalTimeMergeSort + TableTimeNanSec.getCellData(i));
                    totalMergeSort = totalMergeSort + 1;
                    containsMergeSort = true;
                }

                if (TableMethod.getCellData(i).equals("Quick Sort")) {
                    if (TableAmount.getCellData(i).equals(10000)) {
                        QuickSortnanosec = Math.toIntExact(TableTimeNanSec.getCellData(i) / 10); // for 10,000
                        totalTimeQuickSort = (totalTimeQuickSort + QuickSortnanosec);
                    } else if (TableAmount.getCellData(i).equals(100000)) {
                        QuickSortnanosec = Math.toIntExact(TableTimeNanSec.getCellData(i) / 100); // for 100,000
                        totalTimeQuickSort = (totalTimeQuickSort + QuickSortnanosec);
                    } else if (TableAmount.getCellData(i).equals(1000000)) {
                        QuickSortnanosec = Math.toIntExact(TableTimeNanSec.getCellData(i) / 1000);// for 1,000,000
                        totalTimeQuickSort = (totalTimeQuickSort + QuickSortnanosec);
                    } else {
                        QuickSortnanosec = Math.toIntExact(TableTimeNanSec.getCellData(i)); // for 1000
                        totalTimeQuickSort = (totalTimeQuickSort + QuickSortnanosec);
                    }
                    actualTotalTimeQuickSort = (int) (actualTotalTimeQuickSort + TableTimeNanSec.getCellData(i));
                    totalQuickSort = totalQuickSort + 1;
                    containsQuickSort = true;
                }

                if (TableMethod.getCellData(i).equals("Shell Sort")) {
                    if (TableAmount.getCellData(i).equals(10000)) {
                        ShellSortnanosec = Math.toIntExact(TableTimeNanSec.getCellData(i) / 10); // for 10,000
                        totalTimeShellSort = (totalTimeShellSort + ShellSortnanosec);
                    } else if (TableAmount.getCellData(i).equals(100000)) {
                        ShellSortnanosec = Math.toIntExact(TableTimeNanSec.getCellData(i) / 100); // for 100,000
                        totalTimeShellSort = (totalTimeShellSort + ShellSortnanosec);
                    } else if (TableAmount.getCellData(i).equals(1000000)) {
                        ShellSortnanosec = Math.toIntExact(TableTimeNanSec.getCellData(i) / 1000);// for 1,000,000
                        totalTimeShellSort = (totalTimeShellSort + ShellSortnanosec);
                    } else {
                        ShellSortnanosec = Math.toIntExact(TableTimeNanSec.getCellData(i)); // for 1000
                        totalTimeShellSort = (totalTimeShellSort + ShellSortnanosec);
                    }
                    actualTotalTimeShellSort = (int) (actualTotalTimeShellSort + TableTimeNanSec.getCellData(i));
                    totalShellSort = totalShellSort + 1;
                    containsShellSort = true;
                }

                if (TableMethod.getCellData(i).equals("Javas iebūvētais")) {
                    if (TableAmount.getCellData(i).equals(10000)) {
                        JavaBuiltInnanosec = Math.toIntExact(TableTimeNanSec.getCellData(i) / 10); // for 10,000
                        totalTimeJavaBuiltIn = (totalTimeJavaBuiltIn + JavaBuiltInnanosec);
                    } else if (TableAmount.getCellData(i).equals(100000)) {
                        JavaBuiltInnanosec = Math.toIntExact(TableTimeNanSec.getCellData(i) / 100); // for 100,000
                        totalTimeJavaBuiltIn = (totalTimeJavaBuiltIn + JavaBuiltInnanosec);
                    } else if (TableAmount.getCellData(i).equals(1000000)) {
                        JavaBuiltInnanosec = Math.toIntExact(TableTimeNanSec.getCellData(i) / 1000);// for 1,000,000
                        totalTimeJavaBuiltIn = (totalTimeJavaBuiltIn + JavaBuiltInnanosec);
                    } else {
                        JavaBuiltInnanosec = Math.toIntExact(TableTimeNanSec.getCellData(i)); // for 1000
                        totalTimeJavaBuiltIn = (totalTimeJavaBuiltIn + JavaBuiltInnanosec);
                    }
                    actualTotalTimeJavaBuiltIn = (int) (actualTotalTimeJavaBuiltIn + TableTimeNanSec.getCellData(i));
                    totalJavaBuiltIn = totalJavaBuiltIn + 1;
                    containsJavaBuiltIn = true;
                }
            }

            XYChart.Series<String, Number> set1 = new XYChart.Series<>();


            if (containsNeizmantojot == true) {
                long AverageTimeOfNeizmantojot = (totalTimeNeizmantojot / totalNeizmantojot);
                AverageResults result = new AverageResults("Neizmantojot", FormatDecimal(AverageTimeOfNeizmantojot), FormatDecimal(actualTotalTimeNeizmantojot));
                AverageTimeResults.add(result);

                set1.getData().add(new XYChart.Data("Neizmantojot", AverageTimeOfNeizmantojot / 10000));
            }
            if (containsBubbleSort == true) {
                long AverageTimeOfBubbleSort = (totalTimeBubbleSort / totalBubbleSort);
                AverageResults result = new AverageResults("BubbleSort", FormatDecimal(AverageTimeOfBubbleSort), FormatDecimal(actualTotalTimeBubbleSort));
                AverageTimeResults.add(result);

                set1.getData().add(new XYChart.Data("BubbleSort", AverageTimeOfBubbleSort / 10000));

            }
            if (containsSelectionSort == true) {
                long AverageTimeOfSelectionSort = (totalTimeSelectionSort / totalSelectionSort);
                AverageResults result = new AverageResults("Selection Sort", FormatDecimal(AverageTimeOfSelectionSort), FormatDecimal(actualTotalTimeSelectionSort));
                AverageTimeResults.add(result);

                set1.getData().add(new XYChart.Data("Selection Sort", AverageTimeOfSelectionSort / 10000));

            }
            if (containsMergeSort == true) {
                long AverageTimeOfMergeSort = (totalTimeMergeSort / totalMergeSort);
                AverageResults result = new AverageResults("Merge Sort", FormatDecimal(AverageTimeOfMergeSort), FormatDecimal(actualTotalTimeMergeSort));
                AverageTimeResults.add(result);

                set1.getData().add(new XYChart.Data("Merge Sort", AverageTimeOfMergeSort / 10000));

            }
            if (containsQuickSort == true) {
                long AverageTimeOfQuickSort = (totalTimeQuickSort / totalQuickSort);
                AverageResults result = new AverageResults("Quick Sort", FormatDecimal(AverageTimeOfQuickSort), FormatDecimal(actualTotalTimeQuickSort));
                AverageTimeResults.add(result);

                set1.getData().add(new XYChart.Data("Quick Sort", AverageTimeOfQuickSort / 10000));

            }
            if (containsShellSort == true) {
                long AverageTimeOfShellSort = (totalTimeShellSort / totalShellSort);
                AverageResults result = new AverageResults("Shell Sort", FormatDecimal(AverageTimeOfShellSort), FormatDecimal(actualTotalTimeShellSort));
                AverageTimeResults.add(result);

                set1.getData().add(new XYChart.Data("Shell Sort", AverageTimeOfShellSort / 10000));

            }
            if (containsJavaBuiltIn == true) {
                long AverageTimeOfJavaBuiltIn = (totalTimeJavaBuiltIn / totalJavaBuiltIn);
                AverageResults result = new AverageResults("Javas iebūvētais", FormatDecimal(AverageTimeOfJavaBuiltIn), FormatDecimal(actualTotalTimeJavaBuiltIn));
                AverageTimeResults.add(result);

                set1.getData().add(new XYChart.Data("Javas iebūvētais", AverageTimeOfJavaBuiltIn / 10000));

            }

            Chart.getData().add(set1);

            if (AverageTimeResults.size() == 1) {
                Chart.setVisible(false);
                AlgorithmError.setVisible(true);
            } else {
                Chart.setVisible(true);
                AlgorithmError.setVisible(false);
            }
        }
    }

    public static String FormatDecimal(long nanoseconds) {
        double milliseconds = (double) nanoseconds / 1_000_000;
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(milliseconds);
    }

    public void ShowNewDataStructurePane(){
        CreateNewDataStructurePane.setVisible(true);
    }
    public void DoEachAlgorithm30times(){
        String izvel = null;
        String daudzums = null;
        String datustruktura = null;
        if(NewAlgorithmStructure.getSelectedToggle() == null){
            IzveletDatuStrukturu.setVisible(true);
        }else {
            IzveletDatuStrukturu.setVisible(false);
            if (VeidotJaunuJa.isSelected()) {
                izvel = "Jā";
            } else {
                izvel = "Nē";
            }
        }
            if (AmountOfElements.getSelectedToggle() == null) {
                IzveletElementuSkait.setVisible(true);
            } else {
                IzveletElementuSkait.setVisible(false);
                if (viens.isSelected()) {
                    daudzums = "1,000";
                } else if (divi.isSelected()) {
                    daudzums = "10,000";
                } else if (tris.isSelected()) {
                    daudzums = "100,000";
                } else if (cetri.isSelected()) {
                    daudzums = "1,000,000";
                } else if (pieci.isSelected()) {
                    daudzums = "10,000,000";
                }
            }
            if (NewDataStructure.getSelectedToggle() == null) {
                IzveletVeidotJaunu.setVisible(true);
            } else {
                IzveletVeidotJaunu.setVisible(false);
                    if (Masivs.isSelected()) {
                    datustruktura = "Masivs";
                } else if (ArrayList.isSelected()) {
                    datustruktura = "ArrayList";
                }
            }
            if (NewAlgorithmStructure.getSelectedToggle() == null || AmountOfElements.getSelectedToggle() == null || NewDataStructure.getSelectedToggle() == null) {

            }else{
                CreateNewDataStructurePane.setVisible(false);
                StartSorting("Neizmantojot", datustruktura, daudzums, izvel, "30");
                StartSorting("BubbleSort", datustruktura, daudzums, izvel, "30");
                StartSorting("Selection Sort", datustruktura, daudzums, izvel, "30");
                StartSorting("Merge Sort", datustruktura, daudzums, izvel, "30");
                StartSorting("Quick Sort", datustruktura, daudzums, izvel, "30");
                StartSorting("Shell Sort", datustruktura, daudzums, izvel, "30");
                StartSorting("Javas iebūvētais", datustruktura, daudzums, izvel, "30");

            }
        }

    public void CloseNewDataStructurePane(){
        CreateNewDataStructurePane.setVisible(false);
    }

    public void BubbleSortComparing(){

        id.setCellValueFactory(new PropertyValueFactory<>("ID"));
        BubbleAmount.setCellValueFactory(new PropertyValueFactory<>("BubbleSort_Amount"));
        Bubble_one.setCellValueFactory(new PropertyValueFactory<>("BubbleSortResult_1"));
        Bubble_two.setCellValueFactory(new PropertyValueFactory<>("BubbleSortResult_2"));
        Bubble_Difference_one.setCellValueFactory(new PropertyValueFactory<>("BubbleSortDifference_1"));
        Bubble_three.setCellValueFactory(new PropertyValueFactory<>("BubbleSortResult_3"));
        Bubble_four.setCellValueFactory(new PropertyValueFactory<>("BubbleSortResult_4"));
        Bubble_Difference_two.setCellValueFactory(new PropertyValueFactory<>("BubbleSortDifference_2"));

        BubbleResults.setItems(BubbleSortResults);

        if(BubbleSortAmount.getSelectedToggle() == null){
            BubbleSortNavIzvele.setVisible(true);
        }else{
            BubbleSortNavIzvele.setVisible(false);
            String Bubble_Amount = null;
            int max = 0;

            if(onek.isSelected()){
                Bubble_Amount = "1,000";
                max = 1000;
            }else if(tenk.isSelected()){
                Bubble_Amount = "10,000";
                max = 10000;
            }else if(hundredk.isSelected()){
                Bubble_Amount = "100,000";
                max = 100000;
            }else if(onem.isSelected()){
                Bubble_Amount = "1,000,000";
                max = 1000000;
            }else if(tenm.isSelected()){
                Bubble_Amount = "10,000,000";
                max = 10000000;
            }

            StartSorting("Special", "Masivs", Bubble_Amount, "Jā", "Nē");

            long startTime = System.nanoTime();

            bubbleSort(oldarray);

            long endTime = System.nanoTime();
            String Time_1 = String.valueOf(endTime - startTime);

            ExecutorService executor = Executors.newSingleThreadExecutor();

            CountDownLatch latch = new CountDownLatch(1);
            Runnable BubbleSortArray = () -> {
                long startTime2 = System.nanoTime();
                bubbleSort(oldarray);
                long endTime2 = System.nanoTime();
                Laiks2 = endTime2 - startTime2;
                latch.countDown();
            };

            executor.submit(BubbleSortArray);

            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            executor.shutdown();
            String Time_2 = String.valueOf(Laiks2);

            String Difference_1 = String.valueOf((Integer.valueOf(Time_1) - Integer.valueOf(Time_2)));

            LinkedList<Integer> LinkedList = new LinkedList<>();


            int min = 1;
            int range = max - min + 1;
            boolean full = false;
            int total = 0;

            while (!full) {
                int rand = (int) (Math.random() * range) + min;

                if (LinkedList.contains(rand)) {

                } else {
                    LinkedList.add(rand);
                    total++;
                }

                if (total == max) {
                    full = true;
                }
            }

            long startTime3 = System.nanoTime();

            bubbleSortArlist(LinkedList);

            long endTime3 = System.nanoTime();
            String Time_3 = String.valueOf(endTime3 - startTime3);

            ExecutorService executor2 = Executors.newSingleThreadExecutor();

            CountDownLatch latch2 = new CountDownLatch(1);
            Runnable BubbleSortLinkedList = () -> {
                long startTime4 = System.nanoTime();
                bubbleSortArlist(LinkedList);
                long endTime4 = System.nanoTime();
                Laiks4 = endTime4 - startTime4;
                latch2.countDown();
            };

            executor2.submit(BubbleSortLinkedList);

            try {
                latch2.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            executor2.shutdown();
            String Time_4 = String.valueOf(Laiks4);

            String Difference_2 = String.valueOf((Integer.valueOf(Time_3) - Integer.valueOf(Time_4)));


            Time_1 = FormatDecimal(endTime - startTime);
            Time_2 = FormatDecimal(Laiks2);
            Time_3 = FormatDecimal(endTime3 - startTime3);
            Time_4 = FormatDecimal(Laiks4);

            double difference1 = Double.parseDouble(Difference_1) / 1000000;
            double difference2 = Double.parseDouble(Difference_2) / 1000000;

            String formattedDifference1 = String.format("%.2f", difference1);
            String formattedDifference2 = String.format("%.2f", difference2);

            Difference_1 = formattedDifference1;
            Difference_2 = formattedDifference2;

            BubbleSortID = BubbleSortID + 1;

            BubbleSortResults result = new BubbleSortResults(BubbleSortID, Bubble_Amount, Time_1, Time_2, Difference_1, Time_3, Time_4, Difference_2);
            BubbleSortResults.add(result);

        }
    }

    public void CloseBubbleSortTable(){
        BubbleSortTablePane.setVisible(false);
    }

    public void ShowBubbleSortTable(){
        BubbleSortTablePane.setVisible(true);
    }

}


    //https://www.youtube.com/watch?v=shB0AHNNLOw&ab_channel=GenuineCoder 3:57
package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Controller3 implements Initializable {

    String defaultpath = "src/data/info.txt";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {

            File file = new File(defaultpath);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(":", 2);
                String itemname = parts[0].trim();
                String[] parts2 = parts[1].trim().split(",", 2);
                String itemsize = parts2[0];
                String itemvalue = parts2[1];

                name.setText(itemname);
                size.setText(itemsize);
                value.setText(itemvalue);
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    @FXML
    Button back, open;
    @FXML
    Text name, size, value;

    private Stage stage;
    private Parent root;

    public void backtowindows1(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/windows1.fxml"));
        stage = (Stage) back.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }

    public void choose(){

        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog((Stage)open.getScene().getWindow());

        if (selectedFile != null) {

           String filepath = selectedFile.toPath().toString();

            try {

                File file = new File(filepath);
                Scanner scanner = new Scanner(file);

                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] parts = line.split(":", 2);
                    String itemname = parts[0].trim();
                    String[] parts2 = parts[1].trim().split(",", 2);
                    String itemsize = parts2[0];
                    String itemvalue = parts2[1];

                    name.setText(itemname);
                    size.setText(itemsize);
                    value.setText(itemvalue);
                }
                scanner.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

        }
    }

}

package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import model.Item;

import java.io.*;

public class Controller2 {

    @FXML
    Button check;

    @FXML
    ToggleGroup Size;

    @FXML
    TextField name, value;

    @FXML
    RadioButton big, small;

    private Stage stage;
    private Parent root;

    String select;

    public void switchtowindows3() throws IOException {

        root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/windows3.fxml"));
        stage = (Stage) check.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }

    public void save(){

        try {
            File file = new File("src/data/info.txt");
            FileWriter fileWriter = new FileWriter(file, false);

            if (big.isSelected() == true) {

                select = "big";

            } else {

                select = "small";

            }

            int i=Integer.parseInt(value.getText());

            Item h = new Item(name.getText(), select, i);
            h.setName(name.getText());
            h.setSize(select);
            h.setValue(i);

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(h.getName() + ":" + h.getSize() + "," + h.getValue());
            bufferedWriter.close();
            System.out.println("Dati saglabāti!");

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
}

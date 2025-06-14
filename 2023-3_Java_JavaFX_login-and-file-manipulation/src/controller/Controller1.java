package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller1 {

    @FXML
    Button ok;

    @FXML
    TextField password;

    private Stage stage;
    private Parent root;

    public void switchtowindows2(ActionEvent event) throws IOException {

        if (password.getText().equals("pt2020")) {

            root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/windows2.fxml"));
            stage = (Stage) ok.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } else {

            System.out.println("ievadi paroli: pt2020");

        }

    }
}

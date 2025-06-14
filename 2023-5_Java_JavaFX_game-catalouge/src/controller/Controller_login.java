package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.*;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.util.Scanner;

public class Controller_login {

 private Stage stage;
 private Scene scene;
 private Parent root;

 public static String username;
 @FXML
 PasswordField LoginPassword;

 @FXML
 TextField LoginUsername;

 @FXML
 Text Error;

 public void SwitchToRegister(ActionEvent event) throws IOException { //Pārslēdzās uz scene "Scene_register", kad poga Scene_login ar nosaukumu "Register"ir uzspiesta (On Action : #SwitchToRegister)
  root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/Scene_register.fxml"));
  stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
  System.out.println("User novests uz Scene_register");
  stage.setTitle("Register");
  Error.setText(null);

 }

 public void Login(ActionEvent event) throws IOException {

  if (LoginUsername.getText().isEmpty() || LoginPassword.getText().isEmpty()) { //Ja LoginUsername vai LoginPassword nav aizpildīti, tad parādās Error.

   Error.setText("Lietotajs vai parole ir tukša");

  } else {

   if(LoginUsername.getText().equals("admin") && LoginPassword.getText().equals("admin")) {
    username = "admin";
    System.out.println("Lietotājs: " + LoginUsername.getText() + " ielogojas!");
    root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/Scene_catalogue.fxml"));
    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    System.out.println("User novests uz Catalogue_login");
    stage.setTitle("Catalogue");
    return;
   }

   try {

    File file = new File("src/data/users.xml");
    Scanner scanner = new Scanner(file);

    while (scanner.hasNextLine()) {
     String line = scanner.nextLine();
     String[] parts = line.split(":", 2);
     String Exsistingusername = parts[0].trim();
     String[] passwordParts = parts[1].trim().split(",", 2);
     String Exsistingpassword = passwordParts[0];
     if (Exsistingusername.equals(LoginUsername.getText()) && Exsistingpassword.equals(LoginPassword.getText())) { // ja no users.xml username: parole, admin, jeb parts[0].trim()/username: parts[1].trim()/password sakrīt ar textfield ievadito, tad lietotajs ielogojas sava kontā.
      username = Exsistingusername;
      System.out.println("Lietotājs: " + LoginUsername.getText() + " ielogojas!");
      root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/Scene_catalogue.fxml"));
      stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
      System.out.println("User novests uz Catalogue_login");
      stage.setTitle("Catalogue");
      return;
     }else {
      Error.setText("Lietotajs vai parole ir ievadita nepareizi");
     }
    }
    scanner.close();
   } catch (IOException e) {
    System.out.println("An error occurred.");
    e.printStackTrace();
   }

  }
 }

}
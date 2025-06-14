package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.*;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.util.Scanner;

public class Controller_register {

    private Stage stage;
    private Scene scene;
    private Parent root;
    String username;
    String password;

    @FXML
    TextField RegisterUsername, RegisterPassword;

    @FXML
    Text ErrorUserExsists, Saved;

    @FXML
    Text Register_Error_Username, Register_Error_Password;

    @FXML
    CheckBox AdminCB;

    public void SwitchToLogin(ActionEvent event) throws IOException { //Pārslēdzās uz scene "Scene_login", kad poga Scene_register ar nosaukumu "Done" ir uzspiesta (On Action : #SwitchToLogin)
        root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/Scene_login.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("User novests uz Scene_login");
        stage.setTitle("Login");

    }

    public void Save(){

        if (RegisterUsername.getText().isEmpty()) {
            Register_Error_Username.setVisible(true);
        }
        if (RegisterPassword.getText().isEmpty()) {
            Register_Error_Password.setVisible(true);
        }
        if (RegisterUsername.getText().isEmpty() && RegisterPassword.getText().isEmpty()){
            Register_Error_Username.setVisible(true);
            Register_Error_Password.setVisible(true);
        }else{
            if(RegisterUsername.getText().equals("") || RegisterPassword.getText().equals("")){
                //nezinu kāpēc, bet ir vajadzība pārbaudīt, vai ievaditie lauki nav tukši ar .equals palīdzību, savādāk var saglabāt lietotāju ar tukšumu.
            }else {
                username = RegisterUsername.getText();
                password = RegisterPassword.getText();
                saveUser(username, password);
            }
        }
    }

    public void saveUser(String username, String password) {

        boolean admin = false;

        if (RegisterUsername.getText().equals("admin")) { //admin ir noklusētais admin, tāpēc lai pārbaudītu vai ierakstītais vards ir admin, tad vnk jāparbauda vai ievadītais vards nesakrīt ar admin.
            ErrorUserExsists.setVisible(true);
            Saved.setVisible(false);
            return;
        }

        try {
            File file = new File("src/data/users.xml");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(":"); // Skaneris parbauda failu users.xml un mekle sakritibas, kas sini gadijuma ir :.
                String existingUsername = parts[0].trim(); // Faila users.xml dati saglabajas sadi: username: password, admin, jeb ka si funkcija saprot sos datus parts[0]: parts[1]

                if(RegisterUsername.getText().isEmpty() && RegisterPassword.getText().isEmpty()){
                    Register_Error_Username.setVisible(true);
                    Register_Error_Password.setVisible(true);
                    return;
                }
                else if(RegisterUsername.getText().isEmpty()){
                    Register_Error_Username.setVisible(true);
                    return;
                }else if(RegisterPassword.getText().isEmpty()){
                    Register_Error_Password.setVisible(true);
                    return;
                } else if (existingUsername.equals(username)) { //Ja exsistingUsername, jeb jau eksistējoš lietotajs sakrīt ar RegisterUsername.getText() ievadi, tad izvadās "Lietotājs jau eskistē".
                    ErrorUserExsists.setVisible(true);
                    Saved.setVisible(false);
                    return;
                }
            }
            scanner.close();

            if(AdminCB.isSelected()){
                admin = true;
            }

            FileWriter fileWriter = new FileWriter(file, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println(username + ": " + password + ", " + admin); //Ja abi teksta logi ir aizpildīti un ierakstītas lietotajs jau neeksistē failā, tad saglabājais jaunais lietotājs un viņa parole formātā: username: password.
            printWriter.close();
            System.out.println("Lietotajs saglabāts.");
            ErrorUserExsists.setVisible(false);
            Saved.setVisible(true);

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void Remove_Error(){ //Ja pele atrodās virsū uz error tekstu, tad errors pazūd, jeb tiek vina visibility ir setots uz false.

        Register_Error_Username.setVisible(false);
        Register_Error_Password.setVisible(false);

    }

}
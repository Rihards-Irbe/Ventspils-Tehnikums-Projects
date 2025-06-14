package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import model.Admin;
import model.GameList;
import model.User;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;


public class Controller_catalogue implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    ArrayList<String> Adminlist = new ArrayList<String>();
    ArrayList<String> Userlist = new ArrayList<String>();


    @FXML
    Button AdminButton, ButtonPopUP, FCButton, MCButton, CPButton, LogoutButton, ShowGameList, ShowAdmins, ShowUsers, ChoosePreset, SavePreset, ShowClose;

    @FXML
    Text AdminPermision, Username, TextPopUP, ShowText, ShowTitle, Nofile;

    @FXML
    Pane PanePopUP, Show;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if(Controller_login.username.equals("admin")){ //pārbauda vai ir ielogojies noklusētais admin
            AdminPermision.setVisible(true);
            AdminButton.setVisible(true);
        }

        Username.setText("User: " + Controller_login.username);

        if (Controller_Minecraft.mcedited == true) { //saliek noklusētās vērtības, ja nav editots

            MCButton.setText(Controller_Minecraft.minecrafteditedlabel);

        }

        if (Controller_Cyberpunk2077.cpedited == true) {

            CPButton.setText(Controller_Cyberpunk2077.cyberpunkeditedlabel);

        }

        if (Controller_FarCry3.fcedited == true) {

            FCButton.setText(Controller_FarCry3.farcryeditedlabel);

        }

        try {
            File file = new File("src/data/users.xml");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(","); // Skaneris parbauda failu users.xml un mekle sakritibas, kas sini gadijuma ir ,.
                String isadmin = parts[1].trim(); // Faila users.xml dati saglabajas sadi: username: password, admin jeb ka si funkcija saprot sos datus parts[0], parts[1]. (password, admin)
                String[] adminuser = line.split(":");
                String user = adminuser[0].trim(); // Faila users.xml dati saglabajas sadi: username: password, admin jeb ka si funkcija saprot sos datus parts[0]: parts[1]. (username: password)
                if (isadmin.equals("true") && user.equals(Controller_login.username)) { //vnk pārbauda vai vārdam ar ko ielogojas ir admin privileģijas un ja tadas ir tad tas ir visible.
                    AdminPermision.setVisible(true);
                    AdminButton.setVisible(true);
                }
            }
            scanner.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void SwitchToFarCry3(ActionEvent event) throws IOException { //Pārslēdzās uz scene "Scene_FarCry3", kad poga Scene_catalogue ar nosaukumu "FarCry3"ir uzspiesta (On Action : #SwitchToFarCry3)
        root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/Scene_FarCry3.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("User novests uz Scene_FarCry3");
        stage.setTitle(Controller_FarCry3.farcryeditedlabel);

    }

    public void SwitchToCyberpunk2077(ActionEvent event) throws IOException { //Pārslēdzās uz scene "Scene_Cyberpunk2077", kad poga Scene_catalogue ar nosaukumu "Cyberpunk2077"ir uzspiesta (On Action : #SwitchToCyberpunk2077)

        root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/Scene_Cyberpunk2077.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("User novests uz Scene_Cyberpunk2077");
        stage.setTitle("Cyberpunk2077");
        stage.setTitle(Controller_Cyberpunk2077.cyberpunkeditedlabel);

    }

    public void SwitchToMinecraft(ActionEvent event) throws IOException { //Pārslēdzās uz scene "Scene_Minecraft", kad poga Scene_catalogue ar nosaukumu "Minecraft"ir uzspiesta (On Action : #SwitchToMinecraft)
        root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/Scene_Minecraft.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("User novests uz Scene_Minecraft");
        stage.setTitle(Controller_Minecraft.minecrafteditedlabel);

    }

    public void SwitchToLogin(ActionEvent event) throws IOException { //Pārslēdzās uz scene "Scene_login", kad poga Scene_catalogue ar nosaukumu "Logout" ir uzspiest (On Action : #SwitchToLogin)
        root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/Scene_login.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("User novests uz Scene_login");
        stage.setTitle("Login");

    }

    public void PopUP() { //vienkāršs popups kad tiek uzspiesta poga works.

        PanePopUP.setVisible(true);
        TextPopUP.setVisible(true);
        ButtonPopUP.setVisible(true);
        FCButton.setDisable(true);
        MCButton.setDisable(true);
        CPButton.setDisable(true);
        LogoutButton.setDisable(true);
        ShowGameList.setVisible(true);
        ShowAdmins.setVisible(true);
        ShowUsers.setVisible(true);
        ChoosePreset.setVisible(true);
        SavePreset.setVisible(true);
    }

    public void Close() { // ar x pogu aizveras popup

        PanePopUP.setVisible(false);
        TextPopUP.setVisible(false);
        ButtonPopUP.setVisible(false);
        FCButton.setDisable(false);
        MCButton.setDisable(false);
        CPButton.setDisable(false);
        LogoutButton.setDisable(false);
        ShowGameList.setVisible(false);
        ShowAdmins.setVisible(false);
        ShowUsers.setVisible(false);
        ChoosePreset.setVisible(false);
        SavePreset.setVisible(false);
        Nofile.setVisible(false);

    }

    public void ShowGameList() {

        GameList list = new GameList(Controller_Minecraft.minecrafteditedlabel, Controller_FarCry3.farcryeditedlabel, Controller_Cyberpunk2077.cyberpunkeditedlabel); //ievada konstruktora vērtības.


        if(Controller_FarCry3.fcedited == false){ //parbauda vai ir editoti sples label/virsraksti, ja nē, tad ievietotas ir noklusetās vērtības.
            list.setGame1("FarCry 3");
        }else{
            list.setGame1(Controller_FarCry3.farcryeditedlabel);
        }
        if(Controller_Cyberpunk2077.cpedited == false){
            list.setGame2("Cyberpunk 2077");
        }else{
            list.setGame2(Controller_Cyberpunk2077.cyberpunkeditedlabel);
        }
        if(Controller_Minecraft.mcedited == false){
            list.setGame3("Minecraft");
        }else{
            list.setGame3(Controller_Minecraft.minecrafteditedlabel);
        }

        ShowTitle.setText("Game list");
        PanePopUP.setVisible(false);
        TextPopUP.setVisible(false);
        ButtonPopUP.setVisible(false);
        ShowGameList.setVisible(false);
        ShowAdmins.setVisible(false);
        ShowUsers.setVisible(false);
        ChoosePreset.setVisible(false);
        SavePreset.setVisible(false);
        ShowTitle.setVisible(true);
        Show.setVisible(true);
        ShowText.setVisible(true);
        ShowText.setText("Game1: " + list.getGame1() + " Game2: " + list.getGame2() + " Game3: " + list.getGame3());
        ShowClose.setVisible(true);
    }
    public void ShowAdmins() throws FileNotFoundException {

        File file = new File("src/data/users.xml");
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(","); // Skaneris parbauda failu users.xml un mekle sakritibas, kas sini gadijuma ir ,.
            String isadmin = parts[1].trim(); // Faila users.xml dati saglabajas sadi: username: password, admin jeb ka si funkcija saprot sos datus parts[0], parts[1]. (password, admin)
            String[] adminuser = line.split(":");
            String user = adminuser[0].trim(); // Faila users.xml dati saglabajas sadi: username: password, admin jeb ka si funkcija saprot sos datus parts[0]: parts[1]. (username: password)
            if (isadmin.equals("true")) {
                if(Adminlist.contains(user)) {
                }else{
                    Adminlist.add(user);
                }
            }
        }

        Admin adminslist = new Admin(Adminlist);
        adminslist.setAdminList(Adminlist);

        ShowTitle.setText("Admin list");
        PanePopUP.setVisible(false);
        TextPopUP.setVisible(false);
        ButtonPopUP.setVisible(false);
        ShowGameList.setVisible(false);
        ShowAdmins.setVisible(false);
        ShowUsers.setVisible(false);
        ChoosePreset.setVisible(false);
        SavePreset.setVisible(false);
        ShowTitle.setVisible(true);
        Show.setVisible(true);
        ShowText.setVisible(true);
        ShowText.setText("" + adminslist.getAdminList());
        ShowClose.setVisible(true);

    }

    public void ShowUsers() throws FileNotFoundException {

        File file = new File("src/data/users.xml");
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] adminuser = line.split(":");
            String user = adminuser[0].trim(); // Faila users.xml dati saglabajas sadi: username: password, admin jeb ka si funkcija saprot sos datus parts[0]: parts[1]. (username: password)
            if(Userlist.contains(user)) {
            }else{
                Userlist.add(user);
            }

        }

        User userlist = new User(Userlist);
        userlist.setUserlist(Userlist);

        ShowTitle.setText("User list");
        PanePopUP.setVisible(false);
        TextPopUP.setVisible(false);
        ButtonPopUP.setVisible(false);
        ShowGameList.setVisible(false);
        ShowAdmins.setVisible(false);
        ShowUsers.setVisible(false);
        ChoosePreset.setVisible(false);
        SavePreset.setVisible(false);
        ShowTitle.setVisible(true);
        Show.setVisible(true);
        ShowText.setVisible(true);
        ShowText.setText("" + userlist.getUserlist());
        ShowClose.setVisible(true);

    }

    public void SavePreset() throws FileNotFoundException {

        if(Controller_Minecraft.mcedited == false && Controller_Minecraft.minecrafteditedlabel == null){ //Šī koda daļa pārbauda vai ir mainīti spēles nosaukumi vai teksts ir editoti un ja nē, tad ir vienkārši atstātas noklusējuma vērtības
            Controller_Minecraft.minecrafteditedlabel = "Minecraft";
        }
        if(Controller_FarCry3.fcedited == false && Controller_FarCry3.farcryeditedlabel == null){
            Controller_FarCry3.farcryeditedlabel = "FarCry 3";
        }
        if(Controller_Cyberpunk2077.cpedited == false && Controller_Cyberpunk2077.cyberpunkeditedlabel == null){
            Controller_Cyberpunk2077.cyberpunkeditedlabel = "Cyberpunk2077";
        }

        if(Controller_Minecraft.minecrafteditedtext == null){
            Controller_Minecraft.minecrafteditedtext = "Minecraft is a video game in which players create and break apart various kinds of blocks in three-dimensional worlds. The game’s two main modes are Survival and Creative. In Survival, players must find their own building supplies and food. They also interact with blocklike mobs, or moving creatures. (Creepers and zombies are some of the dangerous ones.)";
        }
        if(Controller_FarCry3.farcryeditedtext == null){
            Controller_FarCry3.farcryeditedtext = "Far Cry 3 is an open world first-person shooter set on an island unlike any other. A place where heavily armed warlords traffic in slaves. Where outsiders are hunted for ransom. And as you embark on a desperate quest to rescue your friends, you realize that the only way to escape this darkness… is to embrace it";
        }
        if(Controller_Cyberpunk2077.cyberpunkeditedtext == null){
            Controller_Cyberpunk2077.cyberpunkeditedtext = "Cyberpunk 2077 is an open-world, action-adventure RPG set in the megalopolis of Night City, where you play as a cyberpunk mercenary wrapped up in a do-or-die fight for survival. Improved and featuring all-new free additional content, customize your character and playstyle as you take on jobs, build a reputation, and unlock upgrades.";
        }

        if(Controller_Minecraft.mcsongedited == false){
            Controller_Minecraft.mcfilepath = null;
        }
        if(Controller_Cyberpunk2077.cpsongedited == false){
            Controller_Cyberpunk2077.cpfilepath = null;
        }
        if(Controller_FarCry3.fcsongedited == false){
            Controller_FarCry3.fcfilepath = null;
        }

        if(Controller_Minecraft.mcimgedited == false){
            Controller_Minecraft.mcimgpath = null;
        }
        if(Controller_Cyberpunk2077.cpimgedited == false){
            Controller_Cyberpunk2077.cpimgpath = null;
        }
        if(Controller_FarCry3.fcimgedited == false){
            Controller_FarCry3.fcimgpath = null;
        }

        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            Element presets = document.createElement("presets");
            document.appendChild(presets);

            Element minecraft = document.createElement("minecraft");
            minecraft.setAttribute("label", Controller_Minecraft.minecrafteditedlabel);
            minecraft.setTextContent(Controller_Minecraft.minecrafteditedtext);
            presets.appendChild(minecraft);

            Element farcry = document.createElement("farcry3");
            farcry.setAttribute("label", Controller_FarCry3.farcryeditedlabel);
            farcry.setTextContent(Controller_FarCry3.farcryeditedtext);
            presets.appendChild(farcry);

            Element cyberpunk = document.createElement("cyberpunk2077");
            cyberpunk.setAttribute("label", Controller_Cyberpunk2077.cyberpunkeditedlabel);
            cyberpunk.setTextContent(Controller_Cyberpunk2077.cyberpunkeditedtext);
            presets.appendChild(cyberpunk);

            Element minecraftfilepath = document.createElement("minecraftfilepath");
            minecraftfilepath.setAttribute("label", Controller_Minecraft.mcfilepath);
            minecraftfilepath.setTextContent(Controller_Minecraft.mcimgpath);
            presets.appendChild(minecraftfilepath);

            Element farcryfilepath = document.createElement("farcryfilepath");
            farcryfilepath.setAttribute("label", Controller_FarCry3.fcfilepath);
            farcryfilepath.setTextContent(Controller_FarCry3.fcimgpath);
            presets.appendChild(farcryfilepath);

            Element cyberpunkfilepath = document.createElement("cyberpunkfilepath");
            cyberpunkfilepath.setAttribute("label", Controller_Cyberpunk2077.cpfilepath);
            cyberpunkfilepath.setTextContent(Controller_Cyberpunk2077.cpimgpath);
            presets.appendChild(cyberpunkfilepath);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File("src/data/presets.xml"));
            transformer.transform(domSource, streamResult);

            System.out.println("Preset saved.");

        } catch (ParserConfigurationException | TransformerException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


    }


    public void LoadPreset(ActionEvent event) throws FileNotFoundException, IOException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a Preset File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Xml Files", "*.xml")
        );
        File presetfilepath = fileChooser.showOpenDialog(ChoosePreset.getScene().getWindow());

        if (presetfilepath != null) {
            try {
                DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
                Document document = documentBuilder.parse(presetfilepath);

                Element presets = document.getDocumentElement();

                String minecraftlabel = presets.getElementsByTagName("minecraft").item(0).getAttributes().getNamedItem("label").getNodeValue();
                String minecrafttext = presets.getElementsByTagName("minecraft").item(0).getTextContent();
                Controller_Minecraft.mcfilepath = presets.getElementsByTagName("minecraftfilepath").item(0).getAttributes().getNamedItem("label").getNodeValue();
                Controller_Minecraft.mcimgpath = presets.getElementsByTagName("minecraftfilepath").item(0).getTextContent();
                String farcrylabel = presets.getElementsByTagName("farcry3").item(0).getAttributes().getNamedItem("label").getNodeValue();
                String farcrytext = presets.getElementsByTagName("farcry3").item(0).getTextContent();
                Controller_FarCry3.fcfilepath = presets.getElementsByTagName("farcryfilepath").item(0).getAttributes().getNamedItem("label").getNodeValue();
                Controller_FarCry3.fcimgpath = presets.getElementsByTagName("farcryfilepath").item(0).getTextContent();
                String cyberpunklabel = presets.getElementsByTagName("cyberpunk2077").item(0).getAttributes().getNamedItem("label").getNodeValue();
                String cyberpunktext = presets.getElementsByTagName("cyberpunk2077").item(0).getTextContent();
                Controller_Cyberpunk2077.cpfilepath = presets.getElementsByTagName("cyberpunkfilepath").item(0).getAttributes().getNamedItem("label").getNodeValue();
                Controller_Cyberpunk2077.cpimgpath = presets.getElementsByTagName("cyberpunkfilepath").item(0).getTextContent();

                Controller_Minecraft.minecrafteditedlabel = minecraftlabel;
                Controller_Minecraft.minecrafteditedtext = minecrafttext;
                Controller_Minecraft.mcedited = true;
                Controller_Cyberpunk2077.cyberpunkeditedlabel = cyberpunklabel;
                Controller_Cyberpunk2077.cyberpunkeditedtext = cyberpunktext;
                Controller_Cyberpunk2077.cpedited = true;
                Controller_FarCry3.farcryeditedlabel = farcrylabel;
                Controller_FarCry3.farcryeditedtext = farcrytext;
                Controller_FarCry3.fcedited = true;

                if(Controller_Minecraft.mcfilepath.isEmpty()){
                    Controller_Minecraft.mcsongedited = false;
                }else{
                    Controller_Minecraft.mcsongedited = true;
                }
                if(Controller_Cyberpunk2077.cpfilepath.isEmpty()){
                    Controller_Cyberpunk2077.cpsongedited = false;
                }else{
                    Controller_Cyberpunk2077.cpsongedited = true;
                }
                if(Controller_FarCry3.fcfilepath.isEmpty()){
                    Controller_FarCry3.fcsongedited = false;
                }else{
                    Controller_FarCry3.fcsongedited = true;
                }

                if(Controller_Minecraft.mcimgpath.isEmpty()){
                    Controller_Minecraft.mcimgedited = false;
                }else{
                    Controller_Minecraft.mcimgedited = true;
                }
                if(Controller_Cyberpunk2077.cpimgpath.isEmpty()){
                    Controller_Cyberpunk2077.cpimgedited = false;
                }else{
                    Controller_Cyberpunk2077.cpimgedited = true;
                }
                if(Controller_FarCry3.fcimgpath.isEmpty()){
                    Controller_FarCry3.fcimgedited = false;
                }else{
                    Controller_FarCry3.fcimgedited = true;
                }


            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            }
            root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/Scene_login.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            System.out.println("User novests uz Scene_login");
            stage.setTitle("Login");

        }else{
            Nofile.setVisible(true);
        }

    }

    public void ShowClose(){

        Show.setVisible(false);
        ShowText.setVisible(false);
        ShowClose.setVisible(false);
        PanePopUP.setVisible(true);
        TextPopUP.setVisible(true);
        ButtonPopUP.setVisible(true);
        ShowGameList.setVisible(true);
        ShowAdmins.setVisible(true);
        ShowUsers.setVisible(true);
        ChoosePreset.setVisible(true);
        SavePreset.setVisible(true);
        ShowTitle.setVisible(false);

    }
}
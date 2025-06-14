package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.*;
import java.io.IOException;
import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class Controller_Cyberpunk2077 implements Initializable{

    private Stage stage;
    private Scene scene;
    private Parent root;
    private Image cpimage;
    private ImageView cpimageview;


    private File directory;
    private File[] files;

    private ArrayList<File> songs;

    private Media media;
    private MediaPlayer mediaPlayer;

    @FXML
    ImageView CyberpunkImage;

    @FXML
    Button StopCP, PlayCP, CyberpunkEdIt, CyberpunkEditMP3Select, CyberpunkEditDone, CyberpunkBack, ImgSelect;

    @FXML
    Text CyberpunkAdminTxt, Cyberpunk2077label, CPText, NoSound, NoImg;

    @FXML
    TextField CyberpunkEditLabel;

    @FXML
    TextArea CyberpunkEditTextArea;

    public static String cyberpunkeditedlabel;
    public static String cyberpunkeditedtext;
    public static boolean cpedited;
    public static boolean cpsongedited;
    public static boolean cpimgedited;

    public static String cpfilepath;
    public static String cpimgpath;

    int songnumber = 1;
    boolean cpsongisplaying;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { //kods paņemts no Bro Code video: https://www.youtube.com/watch?v=-D2OIekCKes&t=919s

        if(Controller_login.username.equals("admin")){ //pārbauda vai ir ielogojies noklusētais admin

            CyberpunkAdminTxt.setVisible(true);
            CyberpunkEdIt.setVisible(true);

        }

        if(cpedited == true){

            Cyberpunk2077label.setText(cyberpunkeditedlabel);
            CPText.setText(cyberpunkeditedtext);

        }
        if(cpimgedited == true){

            cpimage = new Image(cpimgpath);
            CyberpunkImage.setImage(cpimage);

        }

        songs = new ArrayList<File>();
        directory = new File("Music");
        files = directory.listFiles();
        if (files != null) {
            for (File file : files) { //ja Music directory atrodas faili, tad for each cikls iziet cauri katram failam un pievieno viņu pie songs.
                songs.add(file);
            }

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

                    CyberpunkAdminTxt.setVisible(true);
                    CyberpunkEdIt.setVisible(true);

                }
            }
            scanner.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public void SwitchToCatalogue(ActionEvent event) throws IOException { //Pārslēdzās uz scene "Scene_catalogue", kad poga Scene_FarCry3, Scene_Cyberpunk2077 vai Scene_Minecraft ar nosaukumu "Back" ir uzspiesta (On Action : #SwitchToCatalogue)
        root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/Scene_catalogue.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("User novests uz Scene_catalogue");
        stage.setTitle("Catalogue");

        if(cpsongisplaying == true){

            mediaPlayer.stop();

        }

    }

    public void PlayCyberpunk2077Soundtrack() { //Kad poga Scene_FarCry3 ar nosaukumu "Play" ir uzspiesta izpildās funkcija PlayCyberpunk2077Soundtrack()

        if(cpsongedited == false) {

            cpsongisplaying = true;

            media = new Media(songs.get(songnumber).toURI().toString()); //Spēlējas pēc song ArrayLista 1 vērtība, jeb Cyberpunk 2077 music.mp3
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
            PlayCP.setVisible(false); //Šī ir dziesmas izslēgšanas poga, kad poga Play ir uzspiesta, Play pazūd un Stop parādās
            StopCP.setVisible(true);
        }else if(cpsongedited = true){

            cpsongisplaying = true;

            media = new Media(new File(cpfilepath).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
            PlayCP.setVisible(false); //Šī ir dziesmas izslēgšanas poga, kad poga Play ir uzspiesta, Play pazūd un Stop parādās
            StopCP.setVisible(true);

        }

    }

    public void StopCyperpunk2077Soundtrack(){ //Kad poga Stop parādās, pēc Play ir uzspiests, uzspiežot izpildās StopCyperpunk2077Soundtrack(), kas izslēdz šo dziesmu un parāda Play pogu.

        cpsongisplaying = false;
        mediaPlayer.stop();
        mediaPlayer.seek(Duration.ZERO);
        mediaPlayer.dispose();
        PlayCP.setVisible(true); //Parādās Play poga un pazūd Stop.
        StopCP.setVisible(false);
    }

    public void EditCP(){ //kad edit ir uzspiests paslepjas text un paradas textfield

        CyberpunkEditMP3Select.setVisible(true);
        CyberpunkEditTextArea.setVisible(true);
        CyberpunkEditTextArea.setTextFormatter(new TextFormatter<String>(change -> //setoju text limit, ta lai teksts nebutu zem bildem
                change.getControlNewText().length() <= 370 ? change : null));
        CyberpunkEditLabel.setVisible(true);
        CyberpunkEditDone.setVisible(true);
        CyberpunkEditLabel.setVisible(true);
        PlayCP.setVisible(false);
        CyberpunkEdIt.setVisible(false);
        CyberpunkBack.setDisable(true);
        Cyberpunk2077label.setVisible(false);
        CPText.setVisible(false);
        CyberpunkImage.setVisible(false);
        ImgSelect.setVisible(true);

        if(cpsongisplaying == true){

            mediaPlayer.stop();
            StopCP.setVisible(false);

        }

    }

    public void EditDoneCP(){

        System.out.println("Done!");
        CyberpunkBack.setDisable(false);

        cpedited = true;

        if(CyberpunkEditLabel.getText().isEmpty()){ //updato textu uz jauno

            cyberpunkeditedlabel = Cyberpunk2077label.getText();

        }else{

            cyberpunkeditedlabel = CyberpunkEditLabel.getText();
            Cyberpunk2077label.setText(cyberpunkeditedlabel);
            cyberpunkeditedlabel = Cyberpunk2077label.getText();

        }

        if(CyberpunkEditTextArea.getText().isEmpty()){

            cyberpunkeditedtext = CyberpunkEditTextArea.getText();

        }else{

            cyberpunkeditedtext = CyberpunkEditTextArea.getText();
            CPText.setText(cyberpunkeditedtext);
            cyberpunkeditedtext = CPText.getText();

        }
        if(cpimgedited == true){ //ja bilde ir mainita

            cpimage = new Image(cpimgpath); //nomaina bildi uz jauno path/atrasanas vietu
            cpimageview = (ImageView) CyberpunkImage.getScene().lookup("#CyberpunkImage"); //CyberpunkImage.getScene().lookup("#CyberpunkImage") atrod CyberpunkImage ar id
            cpimageview.setImage(cpimage);//parmaina cpimageview/CyberpunkImage uz cpimage jeb path/atrasanas vietu uz jauno bildi.

        }

        CyberpunkEditLabel.setVisible(false);
        Cyberpunk2077label.setVisible(true);
        CyberpunkEditTextArea.setVisible(false);
        CPText.setVisible(true);
        PlayCP.setVisible(true);
        CyberpunkEditMP3Select.setVisible(false);
        CyberpunkEdIt.setVisible(true);
        CyberpunkEditDone.setVisible(false);
        CyberpunkImage.setVisible(true);
        ImgSelect.setVisible(false);
        NoSound.setVisible(false);
        NoImg.setVisible(false);
    }

    public void EditSelectMP3(){

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose an audio file");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Audio Files", "*.mp3", "*.wav", "*.aac") //atļauts ir izvelēties tikai .mp3, .wav, .aac faila tipus/veidus.
        );
        File file = fileChooser.showOpenDialog(CyberpunkEditMP3Select.getScene().getWindow());
        if (file != null) {
            cpfilepath = file.toPath().toString();//saglabā muzikas filepath.
            cpsongedited = true;
            NoSound.setVisible(true);
            NoSound.setText("Sound saved!");
        }else{
            NoSound.setVisible(true);
            NoSound.setText("No Sound Selected");
            cpsongedited = false;

        }

    }

    public void EditSelectImg(){

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose an image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.img", "*.png") //atļauts ir izvelēties tikai .img, .png faila tipus/veidus.
        );
        File file = fileChooser.showOpenDialog(ImgSelect.getScene().getWindow());
        if (file != null) {
            cpimgpath = file.toPath().toString();//saglabā muzikas filepath.
            cpimgedited = true;
            NoImg.setVisible(true);
            NoImg.setText("Image saved!");
        }else{
            NoImg.setVisible(true);
            NoImg.setText("No Image Selected");
            cpimgedited = false;
        }

    }


}
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

public class Controller_FarCry3 implements Initializable{


    private Stage stage;
    private Scene scene;
    private Parent root;
    private Image fcimage;
    private ImageView fcimageview;

    private File directory;
    private File[] files;

    private ArrayList<File> songs;

    private Media media;
    private MediaPlayer mediaPlayer;

    int songnumber = 0;
    boolean fcsongisplaying;

    @FXML
    ImageView FarCryImage;

    @FXML
    Button StopFC, PlayFC, FarCry3EdIt, FarCry3EditMP3Select, FarCry3EditDone, FarCry3Back, ImgSelect;

    @FXML
    Text FarCry3AdminTxt, FarCry3Label, FCText, NoSound, NoImg;

    @FXML
    TextField FarCry3EditLabel;

    @FXML
    TextArea FarCry3EditTextArea;

    public static String farcryeditedlabel;
    public static String farcryeditedtext;
    public static boolean fcedited;
    public static boolean fcsongedited;
    public static boolean fcimgedited;

    public static String fcfilepath;
    public static String fcimgpath;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { //kods paņemts no Bro Code video: https://www.youtube.com/watch?v=-D2OIekCKes&t=919s

        if(Controller_login.username.equals("admin")){ //pārbauda vai ir ielogojies noklusētais admin

            FarCry3AdminTxt.setVisible(true);
            FarCry3EdIt.setVisible(true);

        }

        if(fcedited == true){

            FarCry3Label.setText(farcryeditedlabel);
            FCText.setText(farcryeditedtext);

            System.out.println("jeb sis");

        }
        if(fcimgedited == true){

            fcimage = new Image(fcimgpath);
            FarCryImage.setImage(fcimage);

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

                    FarCry3AdminTxt.setVisible(true);
                    FarCry3EdIt.setVisible(true);

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

        if(fcsongisplaying == true){

            mediaPlayer.stop();

        }

    }

    public void PlayFarCry3Soundtrack() { //Kad poga Scene_Minecraft ar nosaukumu "Play" ir uzspiesta izpildās funkcija PlayFarCry3Soundtrack()

        if(fcsongedited == false) {

            fcsongisplaying = true;

            media = new Media(songs.get(songnumber).toURI().toString()); //Spēlējas pēc song ArrayLista 0 vērtība, jeb Music/Brian Tyler - Im Sorry (Far Cry 3).mp3
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
            PlayFC.setVisible(false); //Šī ir dziesmas izslēgšanas poga, kad poga Play ir uzspiesta, Play pazūd un Stop parādās
            StopFC.setVisible(true);
        }else{

            fcsongisplaying = true;

            media = new Media(new File(fcfilepath).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
            PlayFC.setVisible(false); //Šī ir dziesmas izslēgšanas poga, kad poga Play ir uzspiesta, Play pazūd un Stop parādās
            StopFC.setVisible(true);

        }

    }

    public void StopFarCry3Soundtrack(){ //Kad poga Stop parādās, pēc Play ir uzspiests, uzspiežot izpildās StopFarCry3Soundtrack(), kas izslēdz šo dziesmu un parāda Play pogu.

        fcsongisplaying = false;
        mediaPlayer.stop();
        mediaPlayer.seek(Duration.ZERO);
        mediaPlayer.dispose();
        PlayFC.setVisible(true); //Parādās Play poga un pazūd Stop.
        StopFC.setVisible(false);
    }

    public void EditFC(){ //kad edit ir uzspiests paslepjas text un paradas textfield

        FarCry3EditMP3Select.setVisible(true);
        FarCry3EditTextArea.setVisible(true);
        FarCry3EditTextArea.setTextFormatter(new TextFormatter<String>(change -> //setoju text limit, ta lai teksts nebutu zem bildem
                change.getControlNewText().length() <= 370 ? change : null));
        FarCry3EditLabel.setVisible(true);
        FarCry3EditDone.setVisible(true);
        FarCry3EditLabel.setVisible(true);
        PlayFC.setVisible(false);
        FarCry3EdIt.setVisible(false);
        FarCry3Back.setDisable(true);
        FarCry3Label.setVisible(false);
        FCText.setVisible(false);
        FarCryImage.setVisible(false);
        ImgSelect.setVisible(true);

        if(fcsongisplaying == true){

            mediaPlayer.stop();
            StopFC.setVisible(false);

        }

    }

    public void EditDoneFC(){ //updato textu uz jauno

        System.out.println("Done!");
        FarCry3Back.setDisable(false);

        fcedited = true;

        if(FarCry3EditLabel.getText().isEmpty()){

            farcryeditedlabel = FarCry3Label.getText();

        }else{

            farcryeditedlabel = FarCry3EditLabel.getText();
            FarCry3Label.setText(farcryeditedlabel);
            farcryeditedlabel = FarCry3Label.getText();

        }

        if(FarCry3EditTextArea.getText().isEmpty()){

            farcryeditedtext = FarCry3EditTextArea.getText();

        }else{

            farcryeditedtext = FarCry3EditTextArea.getText();
            FCText.setText(farcryeditedtext);
            farcryeditedtext = FCText.getText();

        }
        if(fcimgedited == true){ //ja bilde ir mainita

            fcimage = new Image(fcimgpath); //nomaina bildi uz jauno path/atrasanas vietu
            fcimageview = (ImageView) FarCryImage.getScene().lookup("#FarCryImage"); //FarCryImage.getScene().lookup("#FarCryImage") atrod FarCryImage ar id
            fcimageview.setImage(fcimage);//parmaina fcimageview/FarCryImage uz fcimage jeb path/atrasanas vietu uz jauno bildi.

        }

        FarCry3EditLabel.setVisible(false);
        FarCry3Label.setVisible(true);
        FarCry3EditTextArea.setVisible(false);
        FCText.setVisible(true);
        PlayFC.setVisible(true);
        FarCry3EditMP3Select.setVisible(false);
        FarCry3EdIt.setVisible(true);
        FarCry3EditDone.setVisible(false);
        FarCryImage.setVisible(true);
        ImgSelect.setVisible(false);
        NoSound.setVisible(false);
        NoImg.setVisible(false);
    }

    public void EditSelectMP3(){

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose an audio file");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Audio Files", "*.mp3", "*.wav", "*.aac")  //atļauts ir izvelēties tikai .mp3, .wav, .aac faila tipus/veidus.
        );
        File file = fileChooser.showOpenDialog(FarCry3EditMP3Select.getScene().getWindow());
        if (file != null) {
            fcfilepath = file.toPath().toString(); //saglabā muzikas filepath.
            fcsongedited = true;
            NoSound.setVisible(true);
            NoSound.setText("Sound saved!");
        }else{
            NoSound.setVisible(true);
            NoSound.setText("No Sound Selected");
            fcsongedited = false;
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
            fcimgpath = file.toPath().toString();//saglabā bildes filepath.
            fcimgedited = true;
            NoImg.setVisible(true);
            NoImg.setText("Image saved!");
        }else{
            NoImg.setVisible(true);
            NoImg.setText("No Image Selected");
            fcimgedited = false;
        }

    }


}
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

import java.awt.*;
import java.net.URL;
import java.util.*;
import java.io.IOException;
import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class Controller_Minecraft implements Initializable{

    private Stage stage;
    private Scene scene;
    private Parent root;
    private Image mcimage;
    private ImageView mcimageview;

    private File directory;
    private File[] files;

    private ArrayList<File> songs;

    private Media media;
    private MediaPlayer mediaPlayer;

    int songnumber = 2;
    boolean mcsongisplaying;

    @FXML
    ImageView MinecraftImage;

    @FXML
    Button StopMC, PlayMC, MinecraftEdIt, MinecraftEditMP3Select, MinecraftEditDone, MinecraftBack, ImgSelect;

    @FXML
    Text MinecraftAdminTxt, MinecraftLabel, MCText, NoSound, NoImg;

    @FXML
    TextField MinecraftEditLabel;

    @FXML
    TextArea MinecraftEditTextArea;

    public static String minecrafteditedlabel;
    public static String minecrafteditedtext;
    public static boolean mcedited;
    public static boolean mcsongedited;
    public static boolean mcimgedited;

    public static String mcfilepath;
    public static String mcimgpath;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { //kods paņemts no Bro Code video: https://www.youtube.com/watch?v=-D2OIekCKes&t=919s

        if(Controller_login.username.equals("admin")){ //pārbauda vai ir ielogojies noklusētais admin

            MinecraftAdminTxt.setVisible(true);
            MinecraftEdIt.setVisible(true);

        }

        if(mcedited == true){

            MinecraftLabel.setText(minecrafteditedlabel);
            MCText.setText(minecrafteditedtext);

            System.out.println("jeb sis");

        }
        if(mcimgedited == true){

            mcimage = new Image(mcimgpath);
            MinecraftImage.setImage(mcimage);

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

                    MinecraftAdminTxt.setVisible(true);
                    MinecraftEdIt.setVisible(true);

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

        if(mcsongisplaying == true){

            mediaPlayer.stop();

        }

    }

    public void PlayMinecraftSoundtrack() { //Kad poga Scene_Minecraft ar nosaukumu "Play" ir uzspiesta izpildās funkcija PlayMinecraftSoundtrack()

        if(mcsongedited == false) {

            mcsongisplaying = true;

            media = new Media(songs.get(songnumber).toURI().toString()); //Spēlējas pēc song ArrayLista 2 vērtība, jeb Minecraft.mp3
            mediaPlayer = new MediaPlayer(media);

            mediaPlayer.play();
            PlayMC.setVisible(false);
            StopMC.setVisible(true);//Šī ir dziesmas izslēgšanas poga, kad poga Play ir uzspiesta, Play pazūd un Stop parādās
        }else{

            mcsongisplaying = true;

            media = new Media(new File(mcfilepath).toURI().toString());
            mediaPlayer = new MediaPlayer(media);

            mediaPlayer.play();
            PlayMC.setVisible(false);
            StopMC.setVisible(true);//Šī ir dziesmas izslēgšanas poga, kad poga Play ir uzspiesta, Play pazūd un Stop parādās

        }

    }

    public void StopMinecraftSoundtrack(){ //Kad poga Stop parādās, pēc Play ir uzspiests, uzspiežot izpildās StopMinecraftSoundtrack(), kas izslēdz šo dziesmu un parāda Play pogu.

        mcsongisplaying = false;

        mediaPlayer.stop();
        mediaPlayer.seek(Duration.ZERO);
        mediaPlayer.dispose();//Parādās Play poga un pazūd Stop.
        PlayMC.setVisible(true);
        StopMC.setVisible(false);
    }

    public void EditMC(){ //kad edit ir uzspiests paslepjas text un paradas textfield

        MinecraftEditMP3Select.setVisible(true);
        MinecraftEditTextArea.setVisible(true);
        MinecraftEditTextArea.setTextFormatter(new TextFormatter<String>(change -> //setoju text limit, ta lai teksts nebutu zem bildem
                change.getControlNewText().length() <= 370 ? change : null));
        MinecraftEditLabel.setVisible(true);
        MinecraftEditDone.setVisible(true);
        MinecraftEditLabel.setVisible(true);
        PlayMC.setVisible(false);
        MinecraftEdIt.setVisible(false);
        MinecraftBack.setDisable(true);
        MinecraftLabel.setVisible(false);
        MCText.setVisible(false);
        MinecraftImage.setVisible(false);
        ImgSelect.setVisible(true);

        if(mcsongisplaying == true){

            mediaPlayer.stop();
            StopMC.setVisible(false);

        }

    }

    public void EditDoneMC(){ //updato textu uz jauno

        System.out.println("Done!");
        MinecraftBack.setDisable(false);

        mcedited = true;

        if(MinecraftEditLabel.getText().isEmpty()){

            minecrafteditedlabel = MinecraftLabel.getText();

        }else{

            minecrafteditedlabel = MinecraftEditLabel.getText();
            MinecraftLabel.setText(minecrafteditedlabel);
            minecrafteditedlabel = MinecraftLabel.getText();

        }

        if(MinecraftEditTextArea.getText().isEmpty()){

            minecrafteditedtext = MinecraftEditTextArea.getText();

        }else{

            minecrafteditedtext = MinecraftEditTextArea.getText();
            MCText.setText(minecrafteditedtext);
            minecrafteditedtext = MCText.getText();

        }
        if(mcimgedited == true){ //ja bilde ir mainita

            mcimage = new Image(mcimgpath); //nomaina bildi uz jauno path/atrasanas vietu
            mcimageview = (ImageView) MinecraftImage.getScene().lookup("#MinecraftImage"); //MinecraftImage.getScene().lookup("#MinecraftImage") atrod MinecraftImage ar id
            mcimageview.setImage(mcimage); //parmaina mcimageview/MinecraftImage uz mcimage jeb path/atrasanas vietu uz jauno bildi.

        }

        MinecraftEditLabel.setVisible(false);
        MinecraftLabel.setVisible(true);
        MinecraftEditTextArea.setVisible(false);
        MCText.setVisible(true);
        PlayMC.setVisible(true);
        MinecraftEditMP3Select.setVisible(false);
        MinecraftEdIt.setVisible(true);
        MinecraftEditDone.setVisible(false);
        MinecraftImage.setVisible(true);
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
        File file = fileChooser.showOpenDialog(MinecraftEditMP3Select.getScene().getWindow());
        if (file != null) {
            mcfilepath = file.toPath().toString(); //saglabā muzikas filepath.
            mcsongedited = true;
            NoSound.setVisible(true);
            NoSound.setText("Sound saved!");
        }else{
            NoSound.setVisible(true);
            NoSound.setText("No Sound Selected");
            mcsongedited = false;

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
            mcimgpath = file.toPath().toString();//saglabā bildes filepath.
            mcimgedited = true;
            NoImg.setVisible(true);
            NoImg.setText("Image saved!");
        }else{
            mcimgedited = false;
            NoImg.setVisible(true);
            NoImg.setText("No Image Selected");
        }

    }

}
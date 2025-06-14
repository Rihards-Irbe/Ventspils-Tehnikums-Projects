package Sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage LoginStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../FXML/Main.fxml"));
        LoginStage.setTitle("Sorting Window");
        LoginStage.setScene(new Scene(root));
        LoginStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

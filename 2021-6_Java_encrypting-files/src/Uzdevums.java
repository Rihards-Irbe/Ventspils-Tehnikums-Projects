import java.io.File;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.net.URL;
import java.util.Scanner;
import java.nio.file.Paths;
import java.sql.Array;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.Arrays;

public class Uzdevums {
    public static void main(String[] args) throws URISyntaxException, IOException {
        int atslēga = 69;
        URL pirmaisURL = Uzdevums.class.getClassLoader().getResource("pirmais.txt");
        URL otraisURL = Uzdevums.class.getClassLoader().getResource("otrais.txt");
        URL rezultatsURL = Uzdevums.class.getClassLoader().getResource("rezultats.txt");
        URL otraisrezultats = Uzdevums.class.getClassLoader().getResource("otraisrezultats.txt");

        File pirmaisFile = new File(pirmaisURL.toURI());
        File otraisFile = new File(otraisURL.toURI());
        File rezultatsFile = new File(rezultatsURL.toURI());
        File otraisrezultatsFile = new File(otraisrezultats.toURI());

        Scanner otraisScanner = new Scanner(otraisFile);
        Scanner pirmaisScanner = new Scanner(pirmaisFile);
        Scanner otraisrezultatsScanner = new Scanner(otraisrezultatsFile);

        FileWriter rezultatsWriter = new FileWriter(rezultatsFile);
        FileWriter otraisrezultatsWriter = new FileWriter(otraisrezultatsFile);

        while(pirmaisScanner.hasNext()){
            String line = pirmaisScanner.nextLine();
            String output = "";
            for(char ch : line.toCharArray()){
                output = output + (char)(ch - atslēga);
            }
            rezultatsWriter.write(output);
        }
        while(otraisScanner.hasNext()){
            String line = otraisScanner.nextLine();
            String output = "";
            for(char ch : line.toCharArray()){
                output = output + (char)(ch + atslēga);
            }
            otraisrezultatsWriter.write(output);
        }

        pirmaisScanner.close();
        otraisScanner.close();
        otraisrezultatsScanner.close();
        rezultatsWriter.close();
        otraisrezultatsWriter.close();
    }
}

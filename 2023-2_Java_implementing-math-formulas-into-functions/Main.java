import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class Cilindrs {
    private double radius;
    private double height;

    public Cilindrs(double radius, double height) {
        this.radius = radius;
        this.height = height;
    }

    public double getRadius() {
        return radius;
    }

    public double getHeight() {
        return height;
    }

    public double getVolume() {
        return Math.PI * radius * radius * height;
    }

    @Override
    public String toString() {
        return "Cilindra: radius = " + radius + " height = " + height + " volume = " + getVolume();
    }
}

public class Main {
    private static ArrayList<Cilindrs> cilindri = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Cilindrs c1 = new Cilindrs(6, 10);
        Cilindrs c2 = new Cilindrs(13, 15);
        Cilindrs c3 = new Cilindrs(5, 6);
        Cilindrs c4 = new Cilindrs(8, 9);
        Cilindrs c5 = new Cilindrs(6, 7);

        cilindri.add(c1);
        cilindri.add(c2);
        cilindri.add(c3);
        cilindri.add(c4);
        cilindri.add(c5);

        izvelne();
    }

    private static void izvelne() {
        while (true) {
            System.out.println("Izvēlēties opciju:");
            System.out.println("1. Izvadīt informāciju par cilindriem");
            System.out.println("2. Saglabāt failā informāciju");
            System.out.println("3. Apskatīties vidējo aritmētisko tilpumu");
            System.out.println("4. Iziet");

            int opcija = scanner.nextInt();
            switch (opcija) {
                case 1:
                    izvaditInformaciju();
                    break;
                case 2:
                    saglabatInformaciju();
                    break;
                case 3:
                    System.out.println("Vidējais aritmētiskais tilpums ir " + vidTilpums());
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Nepareiza izvēle, mēģiniet vēlreiz");
            }
        }
    }

    private static void izvaditInformaciju() {
        for (Cilindrs c : cilindri) {
            System.out.println(c);
        }
    }

    private static void saglabatInformaciju() {
        try (FileWriter fw = new FileWriter("cilindri.txt")) {
            for (Cilindrs c : cilindri) {
                fw.write(c.toString() + "\n");
            }
            System.out.println("Informācija saglabāta failā cilindri.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static double vidTilpums() {
        double sum = 0;
        for (Cilindrs c : cilindri) {
            sum += c.getVolume();
        }
        return sum / cilindri.size();
    }
}
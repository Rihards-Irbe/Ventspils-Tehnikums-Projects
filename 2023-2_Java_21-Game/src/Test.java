import java.util.Scanner;
public class Test {

    public static void main(String[] args) {

        boolean Game = true;

        Dators Dators_1 = new Dators(0, 0, 0);

        Speletajs Speletajs_1 = new Speletajs("Rihards", 0, 0, 0);

        System.out.println("Spēle sākās!");

        while (Game == true) {

            Speletajs_1.setPlayer_points(0);
            Dators_1.setComputer_points(0);

            int min = 1;
            int max = 11;
            boolean Speletaja_gajiens = true;
            boolean Speletajs_Stop = false;
            boolean Stop = false;
            boolean Datora_gajiens = false;
            boolean Answer_true = false;

            Scanner izvele = new Scanner(System.in);

            do {

                do {

                    if (Speletajs_Stop == true) {

                        Datora_gajiens = true;
                        break;

                    }

                    if (Speletajs_1.getPlayer_points() == 0) {

                        System.out.println("============================================================");
                        int numurs = (int) (Math.random() * (max - min + 1) + min);

                        System.out.println("Spēlētājs: " + Speletajs_1.getPlayer_Name() + " ieguva skaitli: " + numurs);
                        Speletajs_1.setPlayer_points(Speletajs_1.getPlayer_points() + numurs);

                        Speletaja_gajiens = false;
                        Datora_gajiens = true;
                    } else if (Speletajs_1.getPlayer_points() <= 21) {
                        System.out.println("============================================================");
                        System.out.println("Spēlētāja: " + Speletajs_1.getPlayer_Name() + " punkti: " + Speletajs_1.getPlayer_points() + " || Datora punkti: " + Dators_1.getComputer_points());

                        Answer_true = false;

                        do {

                            System.out.print("Vai vēlies turpināt (Y/N)");
                            String YvaiN = izvele.nextLine();

                            if (YvaiN.equalsIgnoreCase("Y")) {

                                int numurs = (int) (Math.random() * (max - min + 1) + min);


                                System.out.println("Spēlētājs: " + Speletajs_1.getPlayer_Name() + " ieguva skaitli: " + numurs);

                                Speletajs_1.setPlayer_points(Speletajs_1.getPlayer_points() + numurs);
                                Speletaja_gajiens = false;
                                Datora_gajiens = true;
                                Answer_true = true;

                                if(Speletajs_1.getPlayer_points() > 21){

                                    Datora_gajiens = false;
                                    Stop = true;

                                }

                            } else if (YvaiN.equalsIgnoreCase("N")) {

                                Answer_true = true;
                                Speletajs_Stop = true;
                                Datora_gajiens = true;
                                Speletaja_gajiens = false;
                                break;

                            } else {

                                System.out.println("Nepareiza ievade!");

                            }
                        } while (Answer_true == false);
                    } else {

                        Stop = true;
                        Datora_gajiens = false;
                    }

                } while (Speletaja_gajiens == true);

                while (Datora_gajiens == true) {

                    if (Dators_1.getComputer_points() > 21) {

                        Stop = true;

                    } else if (Dators_1.getComputer_points() == 21) {

                        Stop = true;

                    } else if (Dators_1.getComputer_points() > Speletajs_1.getPlayer_points() && Speletajs_Stop == true) {

                        Stop = true;

                    } else if (Dators_1.getComputer_points() < Speletajs_1.getPlayer_points()) {

                        int numurs = (int) (Math.random() * (max - min + 1) + min);

                        System.out.println("============================================================");
                        System.out.println("Dators ieguva: " + numurs);
                        Dators_1.setComputer_points(Dators_1.getComputer_points() + numurs);

                        if (Dators_1.getComputer_points() > 21) {

                            Stop = true;

                        }

                    } else if (Dators_1.getComputer_points() > Speletajs_1.getPlayer_points() && Dators_1.getComputer_points() < 21) {

                        int numurs = (int) (Math.random() * (max - min + 1) + min);
                        System.out.println("Dators ieguva: " + numurs);
                        Dators_1.setComputer_points(Dators_1.getComputer_points() + numurs);

                        if (Dators_1.getComputer_points() > 21) {

                            Stop = true;

                        }


                    } else if (Dators_1.getComputer_points() == Speletajs_1.getPlayer_points()) {

                        int numurs = (int) (Math.random() * (max - min + 1) + min);
                        System.out.println("Dators ieguva: " + numurs);
                        Dators_1.setComputer_points(Dators_1.getComputer_points() + numurs);

                        if (Dators_1.getComputer_points() > 21) {

                            Stop = true;

                        }

                    }

                    Datora_gajiens = false;

                }

            } while (Stop == false);

            System.out.println("============================================================");
            System.out.println("Spēle beigusies!");

            if (Speletajs_1.getPlayer_points() > 21) {
                System.out.println("--------------------->" + Speletajs_1.getPlayer_Name() + " zaudēja!<---------------------");
                Speletajs_1.setPlayer_loses(Speletajs_1.getPlayer_loses() + 1);
                Dators_1.setComputer_wins(Dators_1.getComputer_wins() + 1);
            } else if (Dators_1.getComputer_points() > 21) {
                System.out.println("--------------------->Dators zaudēja!<---------------------");
                Speletajs_1.setPlayer_wins(Speletajs_1.getPlayer_wins() + 1);
                Dators_1.setComputer_loses(Dators_1.getComputer_loses() + 1);
            } else if (Speletajs_1.getPlayer_points() > Dators_1.getComputer_points()) {
                System.out.println("--------------------->" + Speletajs_1.getPlayer_Name() + " uzvarēja!<---------------------");
                Speletajs_1.setPlayer_wins(Speletajs_1.getPlayer_wins() + 1);
                Dators_1.setComputer_loses(Dators_1.getComputer_loses() + 1);
            } else {
                System.out.println("--------------------->Dators uzvarēja!<---------------------");
                Speletajs_1.setPlayer_loses(Speletajs_1.getPlayer_loses() + 1);
                Dators_1.setComputer_wins(Dators_1.getComputer_wins() + 1);
            }
            System.out.println(Speletajs_1.toString());
            System.out.println(Dators_1.toString());

            System.out.println("============================================================");

            Scanner Continue = new Scanner(System.in);

            Answer_true = false;

            do {

                System.out.print("Mēģināt vēlreiz? Y/N");
                String YvaiN_2 = Continue.nextLine();

                if (YvaiN_2.equalsIgnoreCase("Y")) {

                    System.out.println("Spēle turpinās!");
                    Answer_true = true;

                } else if (YvaiN_2.equalsIgnoreCase("N")) {

                    System.exit(0);

                } else {

                    System.out.println("Nepareiza ievade!");
                    Answer_true = false;

                }
            } while(Answer_true == false);
        }
    }
}

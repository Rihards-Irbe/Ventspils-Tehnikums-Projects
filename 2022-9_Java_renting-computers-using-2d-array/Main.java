import java.util.*;

public class Main {
    public static void main(String[] args) {
        int telpusk = 5;
        int telpudat = 8;
        int i = 0;
        String atbilde = " ";
        boolean darbiba = true;
        int pareizs = 0;

        int telpizv;
        int datizv;

        String[][] telpa_dators = new String[telpusk][telpudat];

        while (darbiba == true) {
            Scanner input = new Scanner(System.in);

            System.out.println("Izvēlies vienu no izvēlēm!");
            System.out.println("Reģistrēties pie vienu no telpām/datoriem. (1)");
            System.out.println("Atbrīvot telpu. (2)");
            System.out.println("Apskatīties, kuri datori ir aizņemti. (3)");
            System.out.println("Apskatīties, kuri datori ir brīvi. (4)");

            int izvele = input.nextInt();

            switch (izvele) {
                case 1:
                    System.out.println("==============================================");
                    System.out.println("Izvēlēts reģistrēties, tava izvēle (" + izvele + ")");

                    Scanner input2 = new Scanner(System.in);
                    System.out.print("Ievadi savu vārdu un uzvārdu: ");
                    String vards = input2.nextLine();
                    System.out.print("Sveiks " + vards + ", Izvēlies brīvu telpu no 1-5: ");
                    telpizv = input.nextInt();
                    telpizv = telpizv - 1;
                    System.out.print("Tagad izvēlies vienu no datoriem: ");
                    datizv = input.nextInt();
                    datizv = datizv - 1;
                    Date datums = new Date();

                    if (telpa_dators[telpizv][datizv] == null) {
                        telpa_dators[telpizv][datizv] = vards;
                        telpizv = telpizv + 1;
                        datizv = datizv + 1;
                        System.out.println(vards + " Tiki piereģistrēts pie " + telpizv + " telpas " + datizv + " datoru! " + datums);
                        System.out.println("==============================================");
                    } else {
                        System.out.println("Vieta aizņemta!");
                        System.out.println("==============================================");
                    }

                    do {
                        System.out.println("==============================================");
                        System.out.println("Vai vēlies turpināt? Y/N");
                        pareizs = 0;
                        Scanner input3 = new Scanner(System.in);
                        atbilde = input3.nextLine();

                        if (atbilde.equalsIgnoreCase("Y")) {
                            darbiba = true;
                            pareizs = 1;
                        } else if (atbilde.equalsIgnoreCase("N")) {
                            darbiba = false;
                            pareizs = 1;
                        } else {
                            System.out.println("Izvēle ir nepareiza, ievadi Y vai N!");
                        }
                    } while (pareizs < 1);
                    break;

                case 2:
                    System.out.println("==============================================");
                    System.out.println("Izvēlēts atbrīvot vietu, tava izvēle (" + izvele + ")");

                    System.out.print("Izvēlēies, kuru istabu atbrīvot: ");
                    telpizv = input.nextInt();
                    telpizv = telpizv - 1;
                    System.out.print("Izvēlēies, kuru datoru atbrīvot: ");
                    datizv = input.nextInt();
                    datizv = datizv - 1;

                    if (telpa_dators[telpizv][datizv] != null) {
                        telpa_dators[telpizv][datizv] = null;
                        telpizv = telpizv + 1;
                        datizv = datizv + 1;
                        System.out.println(telpizv + " telpas " + datizv + " dators tika atbrīvots!");
                        System.out.println("==============================================");
                    } else {
                        System.out.println("Vieta jau ir brīva");
                        System.out.println("==============================================");
                    }

                    do {
                        System.out.println("==============================================");
                        System.out.println("Vai vēlies turpināt? Y/N");
                        pareizs = 0;
                        Scanner input4 = new Scanner(System.in);
                        atbilde = input4.nextLine();

                        if (atbilde.equalsIgnoreCase("Y")) {
                            darbiba = true;
                            pareizs = 1;
                        } else if (atbilde.equalsIgnoreCase("N")) {
                            darbiba = false;
                            pareizs = 1;
                        } else {
                            System.out.println("Izvēle ir nepareiza, ievadi Y vai N!");
                        }
                    } while (pareizs < 1);
                    break;

                case 3:
                    System.out.println("==============================================");
                    System.out.println("Izvēlēts apskatīties, kuri datori ir aizņemti, tava izvēle (" + izvele + ")");

                    do {
                        int x;
                        int y = 0;
                        int k = 0;

                        for (x = 0; x < telpudat; x++) {
                            if (telpa_dators[i][x] != null) {
                                if (x == 8 && i == 5) {
                                    y = x - 1;
                                    k = i - 1;
                                    System.out.println(k + " telpas " + y + " dators ir aizņemts");
                                } else if (x == 8) {
                                    y = x - 1;
                                    System.out.println(i + " telpas " + y + " dators ir aizņemts");
                                } else if (i == 5) {
                                    k = i - 1;
                                    System.out.println(k + " telpas " + x + " dators ir aizņemts");
                                } else {
                                    y = x + 1;
                                    k = i + 1;
                                    System.out.println(k + " telpas " + y + " dators ir aizņemts");
                                }
                            }
                        }
                        i = i + 1;
                    } while (i < telpusk);
                    i = 0;

                    do {
                        System.out.println("==============================================");
                        System.out.println("Vai vēlies turpināt? Y/N");
                        pareizs = 0;
                        Scanner input5 = new Scanner(System.in);
                        atbilde = input5.nextLine();

                        if (atbilde.equalsIgnoreCase("Y")) {
                            darbiba = true;
                            pareizs = 1;
                        } else if (atbilde.equalsIgnoreCase("N")) {
                            darbiba = false;
                            pareizs = 1;
                        } else {
                            System.out.println("Izvēle ir nepareiza, ievadi Y vai N!");
                        }
                    } while (pareizs < 1);
                    break;

                case 4:
                    System.out.println("==============================================");
                    System.out.println("Izvēlēts apskatīties, kuri datori ir brīvi, tava izvēle (" + izvele + ")");

                    do {
                        int x;
                        int y = 0;
                        int k = 0;

                        for (x = 0; x < telpudat; x++) {
                            if (telpa_dators[i][x] == null) {
                                if (x == 8 && i == 5) {
                                    y = x - 1;
                                    k = i - 1;
                                    System.out.println(k + " telpas " + y + " dators ir brīvs");
                                } else if (x == 8) {
                                    y = x - 1;
                                    System.out.println(i + " telpas " + y + " dators ir brīvs");
                                } else if (i == 5) {
                                    k = i - 1;
                                    System.out.println(k + " telpas " + x + " dators ir brīvs");
                                } else {
                                    y = x + 1;
                                    k = i + 1;
                                    System.out.println(k + " telpas " + y + " dators ir brīvs");
                                }
                            }
                        }
                        i = i + 1;
                    } while (i < telpusk);
                    i = 0;

                    do {
                        System.out.println("==============================================");
                        System.out.println("Vai vēlies turpināt? Y/N");
                        pareizs = 0;
                        Scanner input6 = new Scanner(System.in);
                        atbilde = input6.nextLine();

                        if (atbilde.equalsIgnoreCase("Y")) {
                            darbiba = true;
                            pareizs = 1;
                        } else if (atbilde.equalsIgnoreCase("N")) {
                            darbiba = false;
                            pareizs = 1;
                        } else {
                            System.out.println("Izvēle ir nepareiza, ievadi Y vai N!");
                        }
                    } while (pareizs < 1);
                    break;

                default:
                    System.out.println("==============================================");
                    System.out.println("Nav izvēlēts no 1 - 4, tava izvēle: (" + izvele + ")");
            }
        }
    }
}

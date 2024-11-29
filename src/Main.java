import java.util.Scanner;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Klub klub = new Klub("Svømmeklub Delfinen");

        klub.laesMedlemmerFraFil("medlemmer.txt");
        klub.laesTræningsResultaterFraFil("træningsresultater.txt");

        boolean running = true;

        while (running) {
            System.out.println("Velkommen til " + klub.getNavn() + "!");
            System.out.println("Log ind som:");
            System.out.println("1. Formand");
            System.out.println("2. Kasserer");
            System.out.println("3. Træner");
            System.out.println("4. Afslut program");
            System.out.print("Vælg din rolle: ");

            int rolleValg = scanner.nextInt();
            scanner.nextLine();

            switch (rolleValg) {
                case 1: { // Formand
                    boolean formandRunning = true;
                    while (formandRunning) {
                        System.out.println("\nFormandens menu:");
                        System.out.println("1. Opret nyt medlem");
                        System.out.println("2. Vis alle medlemmer");
                        System.out.println("3. Afslut til hovedmenu");
                        System.out.print("Vælg en mulighed: ");

                        int formandValg = scanner.nextInt();
                        scanner.nextLine();

                        switch (formandValg) {
                            case 1:
                                System.out.print("Indtast navn: ");
                                String navn = scanner.nextLine();

                                System.out.print("Indtast alder: ");
                                int alder = scanner.nextInt();
                                scanner.nextLine();

                                System.out.print("Indtast email: ");
                                String email = scanner.nextLine();

                                System.out.println("Indtast adresse: ");
                                String adresse = scanner.nextLine();

                                System.out.println("Indtast telefonnummer: ");
                                int telefonnummer = scanner.nextInt();

                                System.out.print("Er medlemmet aktiv? (true/false): ");
                                boolean erAktivtMedlem = scanner.nextBoolean();
                                scanner.nextLine(); // Ryd scanner-bufferen.

                                System.out.print("Er medlemmet motionist eller konkurrencesvømmer? ");
                                String medlemsType = scanner.nextLine();

                                Medlem nytMedlem = new Medlem(navn, alder, email, adresse, telefonnummer, erAktivtMedlem, medlemsType);
                                klub.registrerMedlem(nytMedlem);
                                klub.gemMedlemmerTilFil("medlemmer.txt");

                                System.out.println("Medlemmet er oprettet");
                                System.out.println();
                                break;

                            case 2:
                                if (klub.getMedlemmer().isEmpty()) {
                                    System.out.println("Der er ingen registrerede medlemmer.");
                                } else {
                                    System.out.println("Medlemmer i klubben:");
                                    for (Medlem medlem : klub.getMedlemmer()) {
                                        System.out.println(medlem);
                                    }
                                }
                                System.out.println();
                                break;

                            case 3:
                                formandRunning = false;
                                break;

                            default:
                                System.out.println("Ugyldigt valg. Prøv igen.");
                        }
                    }
                    break;
                }
                case 2: { // Kasserer
                    boolean kassererRunning = true;
                    while (kassererRunning) {
                        System.out.println("\nKassererens menu:");
                        System.out.println("1. Beregn total kontingent");
                        System.out.println("2. Marker medlem som i restance");
                        System.out.println("3. Vis medlemmer i restance");
                        System.out.println("4. Afslut til hovedmenu");
                        System.out.print("Vælg en mulighed: ");

                        int kassererValg = scanner.nextInt();
                        scanner.nextLine();

                        switch (kassererValg) {
                            case 1:
                                System.out.println("Total kontingent: " + klub.beregnSamletIndbetaltKontingent());
                                System.out.println();
                                break;

                            case 2:
                                System.out.println("Indtast navnet på medlemmet, der skal markeres i restance:");
                                String navn = scanner.nextLine();
                                Medlem fundetMedlem = klub.findMedlemByNavn(navn);
                                if (fundetMedlem != null) {
                                    fundetMedlem.setErIRestance(true);
                                    System.out.println(navn + " er nu markeret som i restance.");
                                } else {
                                    System.out.println("Medlem ikke fundet.");
                                }
                                System.out.println();
                                break;

                            case 3:
                                System.out.println("Medlemmer i restance:");
                                for (Medlem medlem : klub.findMedlemmerIRestance()) {
                                    System.out.println(medlem.getNavn());
                                }
                                System.out.println();
                                break;

                            case 4:
                                kassererRunning = false;
                                break;

                            default:
                                System.out.println("Ugyldigt valg. Prøv igen.");
                        }
                    }
                    break;
                }

                case 3: { // Træner
                    boolean trænerRunning = true;
                    while (trænerRunning) {
                        System.out.println("\nTrænerens menu:");
                        System.out.println("1. Registrer træningsresultat");
                        System.out.println("2. Vis bedste resultat for et medlem");
                        System.out.println("3. Afslut til hovedmenu");
                        System.out.print("Vælg en mulighed: ");

                        int trænerValg = scanner.nextInt();
                        scanner.nextLine(); // Ryd scanner-bufferen

                        switch (trænerValg) {
                            case 1 -> {
                                System.out.print("Indtast medlemmets navn: ");
                                String navn = scanner.nextLine();
                                Medlem medlem = klub.findMedlemByNavn(navn);

                                if (medlem != null) {
                                    System.out.print("Indtast disciplin: ");
                                    String disciplin = scanner.nextLine();
                                    System.out.print("Indtast tid (sek): ");
                                    double tid = scanner.nextDouble();
                                    scanner.nextLine(); // Ryd scanner-bufferen

                                    TræningsResultat resultat = new TræningsResultat(disciplin, tid, LocalDate.now());
                                    boolean erRekord = medlem.tilføjOgTjekRekord(resultat);

                                    if (erRekord) {
                                        System.out.println("Resultat registreret som personlig rekord!");
                                    } else {
                                        System.out.println("Resultat registreret.");
                                    }



                                    klub.gemTræningsResultaterTilFil("træningsresultater.txt");
                                } else {
                                    System.out.println("Medlem ikke fundet.");
                                }
                            }

                            case 2 -> {
                                System.out.print("Indtast medlemmets navn: ");
                                String navn = scanner.nextLine();
                                Medlem medlem = klub.findMedlemByNavn(navn);

                                if (medlem != null) {
                                    System.out.print("Indtast disciplin: ");
                                    String disciplin = scanner.nextLine();
                                    TræningsResultat bedsteResultat = medlem.findBedsteResultat(disciplin);

                                    if (bedsteResultat != null) {
                                        System.out.println("Bedste resultat: " + bedsteResultat);
                                    } else {
                                        System.out.println("Ingen resultater fundet for disciplinen.");
                                    }
                                } else {
                                    System.out.println("Medlem ikke fundet.");
                                }
                            }
                            case 3 -> trænerRunning = false;
                            default -> System.out.println("Ugyldigt valg. Prøv igen.");

                        }
                    }
                    System.out.println();
                    break;
                }

                case 4: { // Afslut
                    klub.gemTræningsResultaterTilFil("træningsresultater.txt");
                    running = false;
                    System.out.println("Programmet afsluttes. Farvel!");
                    break;

                }
                default:
                    System.out.println("Ugyldigt valg. Prøv igen.");
            }
        }

        scanner.close();
    }
}
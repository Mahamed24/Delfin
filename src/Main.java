import java.util.InputMismatchException;
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
            try {
                System.out.println("Velkommen til " + klub.getNavn() + "!");
                System.out.println("Log ind som:");
                System.out.println("1. Formand");
                System.out.println("2. Kasserer");
                System.out.println("3. Træner");
                System.out.println("4. Afslut program");
                System.out.print("Vælg din rolle: ");

                int rolleValg = scanner.nextInt();
                scanner.nextLine(); // Clear scanner buffer

                switch (rolleValg) {
                    case 1: { // Formand
                        boolean formandRunning = true;
                        while (formandRunning) {
                            try {
                                System.out.println("\nFormandens menu:");
                                System.out.println("1. Opret nyt medlem");
                                System.out.println("2. Slet medlem");
                                System.out.println("3. Vis alle medlemmer");
                                System.out.println("4. Afslut til hovedmenu");
                                System.out.print("Vælg en mulighed: ");

                                int formandValg = scanner.nextInt();
                                scanner.nextLine(); // Clear buffer

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

                                        // Variabler til forældreinformation
                                        String forældreNavn = null;
                                        String forældreTelefonnummer = null;


                                        // Hvis medlemmet er under 18 år, bed om forældrefelter
                                        if (alder < 18) {
                                            System.out.print("Indtast forældres/værges navn: ");
                                            forældreNavn = scanner.nextLine();

                                            System.out.print("Indtast forældres/værges telefonnummer: ");
                                            forældreTelefonnummer = scanner.nextLine();
                                        }



                                        // Opret nyt medlem med alle nødvendige parametre
                                        Medlem nytMedlem = new Medlem(navn, alder, email, adresse, telefonnummer, erAktivtMedlem, medlemsType, forældreNavn, forældreTelefonnummer);

                                        klub.registrerMedlem(nytMedlem);
                                        klub.gemMedlemmerTilFil("medlemmer.txt");

                                        System.out.println("Medlemmet er oprettet.");
                                        break;

                                    case 2:
                                        System.out.print("Indtast navnet på medlemmet, der skal slettes: ");
                                        String sletNavn = scanner.nextLine();

                                        Medlem medlemAtSlette = klub.findMedlemByNavn(sletNavn);
                                        if (medlemAtSlette != null) {
                                            klub.sletMedlem(medlemAtSlette);
                                            klub.gemMedlemmerTilFil("medlemmer.txt");
                                            System.out.println("Medlemmet " + sletNavn + " er blevet slettet.");
                                        } else {
                                            System.out.println("Medlem ikke fundet.");
                                        }
                                        break;

                                    case 3:
                                        if (klub.getMedlemmer().isEmpty()) {
                                            System.out.println("Der er ingen registrerede medlemmer.");
                                        } else {
                                            System.out.println("Medlemmer i klubben:");
                                            for (Medlem medlem : klub.getMedlemmer()) {
                                                System.out.println(medlem);
                                            }
                                        }
                                        break;

                                    case 4:
                                        formandRunning = false;
                                        break;

                                    default:
                                        System.out.println("Ugyldigt valg. Prøv igen.");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Ugyldigt input. Prøv igen.");
                                scanner.nextLine(); // Clear scanner-bufferen
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
                            scanner.nextLine(); // Clear buffer

                            switch (kassererValg) {
                                case 1:
                                    System.out.println("Total kontingent: " + klub.beregnSamletIndbetaltKontingent());
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
                                    break;

                                case 3:
                                    System.out.println("Medlemmer i restance:");
                                    for (Medlem medlem : klub.findMedlemmerIRestance()) {
                                        System.out.println(medlem.getNavn());
                                    }
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
                            System.out.println("3. Vis top 5 oversigt");
                            System.out.println("4. Afslut til hovedmenu");
                            System.out.print("Vælg en mulighed: ");

                            int trænerValg = scanner.nextInt();
                            scanner.nextLine(); // Clear buffer

                            switch (trænerValg) {
                                case 1:
                                    System.out.print("Indtast medlemmets navn: ");
                                    String navn = scanner.nextLine();
                                    Medlem medlem = klub.findMedlemByNavn(navn);

                                    if (medlem != null) {
                                        System.out.print("Indtast disciplin: ");
                                        String disciplin = scanner.nextLine();
                                        System.out.print("Indtast tid (sek): ");
                                        double tid = scanner.nextDouble();
                                        scanner.nextLine(); // Clear buffer

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
                                    break;

                                case 2:
                                    System.out.print("Indtast medlemmets navn: ");
                                    String navn2 = scanner.nextLine();
                                    Medlem medlem2 = klub.findMedlemByNavn(navn2);

                                    if (medlem2 != null) {
                                        System.out.print("Indtast disciplin: ");
                                        String disciplin = scanner.nextLine();
                                        TræningsResultat bedsteResultat = medlem2.findBedsteResultat(disciplin);

                                        if (bedsteResultat != null) {
                                            System.out.println("Bedste resultat: " + bedsteResultat);
                                        } else {
                                            System.out.println("Ingen resultater fundet for disciplinen.");
                                        }
                                    } else {
                                        System.out.println("Medlem ikke fundet.");
                                    }
                                    break;

                                case 3:
                                    Svømmedisciplin svømmedisciplin = new Svømmedisciplin(klub.getMedlemmer());
                                    svømmedisciplin.genererTop5();
                                    break;

                                case 4: { // Afslut til hovedmenu
                                    trænerRunning = false;
                                    break;
                                }

                                default:
                                    System.out.println("Ugyldigt valg. Prøv igen.");
                            }
                        }
                        break;
                    }

                    case 4: { // Afslut program
                        klub.gemTræningsResultaterTilFil("træningsresultater.txt");
                        running = false;
                        System.out.println("Programmet afsluttes. Farvel!");
                        break;
                    }

                    default:
                        System.out.println("Ugyldigt valg. Prøv igen.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Ugyldigt input. Prøv igen.");
                scanner.nextLine(); // Clear scanner buffer
            }
        }

        scanner.close();
    }
}

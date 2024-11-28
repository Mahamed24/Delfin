import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.*;


public class Klub {
    private String navn;
    private List<Medlem> medlemmer;

    public Klub(String navn) {
        this.navn = navn;
        this.medlemmer = new ArrayList<>();

    }

    public void registrerMedlem(Medlem medlem) {
        medlemmer.add(medlem);

    }
    public String getNavn() {
        return navn;
    }

    public List<Medlem> getMedlemmer() {
        return medlemmer;
    }

    public double beregnSamletIndbetaltKontingent() {
        double samletKontingent = 0;

        for (Medlem medlem : medlemmer) {
            // Tæl kun aktive medlemmers kontingent
            if (medlem.erAktivtMedlem() && !medlem.erIRestance()) {
                samletKontingent += medlem.beregnKontigent();
            }
        }

        return samletKontingent;
    }


    public List<Medlem> findMedlemmerIRestance() {
        List<Medlem> restanter = new ArrayList<>();
        for (Medlem medlem : medlemmer) {
            if (medlem.erIRestance()) {
                restanter.add(medlem); // Tilføj medlemmet til listen, hvis de er i restance
            }
        }
        return restanter;
    }

    public Medlem findMedlemByNavn(String navn) {
        for (Medlem medlem : medlemmer) {
            if (medlem.getNavn().equalsIgnoreCase(navn)) {
                return medlem;
            }
        }
        return null; // Returnér null, hvis medlemmet ikke findes
    }

    // Persistens: Gem medlemmer til en fil
    public void gemMedlemmerTilFil(String filnavn) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filnavn))) {
            for (Medlem medlem : medlemmer) {
                writer.write(medlem.toCSV());
                writer.newLine();
            }
            System.out.println("Medlemmer gemt til fil: " + filnavn);
        } catch (IOException e) {
            System.out.println("Fejl ved lagring: " + e.getMessage());
        }
    }

    // Persistens: Læs medlemmer fra en fil
    public void laesMedlemmerFraFil(String filnavn) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filnavn))) {
            String linje;
            while ((linje = reader.readLine()) != null) {
                Medlem medlem = Medlem.fraCSV(linje);
                medlemmer.add(medlem);
            }
            System.out.println("Medlemmer indlæst fra fil: " + filnavn);
        } catch (IOException e) {
            System.out.println("Fejl ved indlæsning: " + e.getMessage());
        }
    }
}
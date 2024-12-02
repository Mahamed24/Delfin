import java.util.*;

public class Svømmedisciplin {

    private final List<Medlem> medlemmer;

    public Svømmedisciplin(List<Medlem> medlemmer) {
        this.medlemmer = medlemmer;
    }

    // Metode til at generere top 5 for hver disciplin
    public void genererTop5() {
        // Svømmediscipliner
        String[] discipliner = {"butterfly", "crawl", "rygcrawl", "brystsvømning"};

        // Gennemgår alle discipliner
        for (String disciplin : discipliner) {
            System.out.println("Disciplin: " + disciplin);

            List<TræningsResultat> juniorResultater = new ArrayList<>();
            List<TræningsResultat> seniorResultater = new ArrayList<>();

            // Gennemgår medlemmerne og tjekker deres resultater
            for (Medlem medlem : medlemmer) {
                List<TræningsResultat> resultater = medlem.getResultater();

                for (TræningsResultat resultat : resultater) {
                    if (resultat.getDisciplin().equalsIgnoreCase(disciplin)) {
                        if (medlem.erJunior()) {
                            juniorResultater.add(resultat);
                        } else if (medlem.erSenior()) {
                            seniorResultater.add(resultat);
                        }
                    }
                }
            }

            // Sorter resultaterne for junior og senior baseret på tid (stigende)
            sortResultater(juniorResultater);
            sortResultater(seniorResultater);

            // Vis top 5 junior
            System.out.println("  Junior:");
            visTop5Resultater(juniorResultater);

            // Vis top 5 senior
            System.out.println("  Senior:");
            visTop5Resultater(seniorResultater);
        }
    }

    // Metode til at sortere resultaterne efter tid
    private void sortResultater(List<TræningsResultat> resultater) {
        resultater.sort(new Comparator<TræningsResultat>() {
            @Override
            public int compare(TræningsResultat r1, TræningsResultat r2) {
                return Double.compare(r1.getTid(), r2.getTid());
            }
        });
    }

    // Metode til at vise top 5 resultater
    private void visTop5Resultater(List<TræningsResultat> resultater) {
        if (resultater.isEmpty()) {
            System.out.println("    Ingen resultater");
        } else {
            int count = 1;
            for (TræningsResultat resultat : resultater) {
                if (count > 5) break;
                System.out.printf("    %d. Tid: %.2f sek - Dato: %s\n",
                        count++, resultat.getTid(), resultat.getDato());
            }
        }
    }
}


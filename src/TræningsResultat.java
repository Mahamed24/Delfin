import java.time.LocalDate;
public class TræningsResultat {

    private final String disciplin;
    private final double tid;
    private final LocalDate dato;


    public TræningsResultat(String disciplin, double tid, LocalDate dato) {
        this.disciplin = disciplin;
        this.tid = tid;
        this.dato = dato;

    }

    public String getDisciplin() {
        return disciplin;
    }

    public double getTid() {
        return tid;
    }

    public LocalDate getDato() {
        return dato;
    }

    @Override
    public String toString() {
        return "Disciplin: " + disciplin + ", Tid: " + tid + " sek, Dato: " + dato;
    }

    // Konverter til CSV-format
    public String toCSV() {
        return disciplin + "," + tid + "," + dato;
    }

    // Opret fra CSV
    public static TræningsResultat fraCSV(String csv) {
        String[] dele = csv.split(",");
        String disciplin = dele[0];
        double tid = Double.parseDouble(dele[1]);
        LocalDate dato = LocalDate.parse(dele[2]);
        return new TræningsResultat(disciplin, tid, dato);
    }

}

import java.time.LocalDate;
public class TræningsResultat {

    private String disciplin;
    private double tid;
    private LocalDate dato;


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
}

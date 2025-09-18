import java.util.HashMap;

public class Formation {
    private String idFormation;
    public HashMap<Matiere,Double> matieres;

    public Formation(String idFormation) {
        this.matieres = new HashMap<>();
        this.idFormation = idFormation;

    }

}

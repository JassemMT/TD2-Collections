import java.util.HashMap;
import java.util.Objects;

public class Formation {
    private String idFormation;
    public HashMap<Matiere,Double> matieres;

    public Formation(String idFormation) {
        this.matieres = new HashMap<>();
        this.idFormation = idFormation;
    }

    public String getIdFormation() {
        return this.idFormation;
    }

    public void setIdFormation(String idFormation) {
        this.idFormation = idFormation;
    }

    public void ajouterMatiere(Matiere matiere,Double note) {
        this.matieres.put(matiere,note);
    }

    public void afficherMatieres() {
        System.out.println(this.matieres);
    }
    @Override
    public String toString() {
        return "Formation{" + "idFormation='" + idFormation + '\'' + ", matieres=" + matieres + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Formation formation)) return false;
        return Objects.equals(idFormation, formation.idFormation) && Objects.equals(matieres, formation.matieres);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFormation, matieres);
    }
}

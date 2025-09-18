import java.util.HashMap;
import java.util.Objects;

public class Formation {
    private String idFormation;
    public HashMap<Matiere,Double> matieres;
    // changer ca
    public Formation(String idFormation) {
        this.matieres = new HashMap<>();
        this.idFormation = idFormation;
    }

    public String getIdFormation() {
        return this.idFormation;
    }

    public HashMap<Matiere,Double> getMatieres() {
        return this.matieres;
    }

    public void setIdFormation(String idFormation) {
        this.idFormation = idFormation;
    }



    public void ajouterMatiere(Matiere matiere,Double coef) {
        this.matieres.put(matiere,coef);
    }

    public void afficherMatieres() {
        for (Matiere key : this.matieres.keySet()) {
            System.out.println(key + " -> " + this.matieres.get(key));
        }
    }

    public void supprimerMatiere(Matiere matiere) {
        this.matieres.remove(matiere);
    }

    public double getCoefficient(Matiere matiere) {
        if (this.matieres.containsKey(matiere)) {
            return this.matieres.get(matiere);
        }
        else {
            System.out.println("La matière n'est pas présente dans cette formation");
            return -1;
        }
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

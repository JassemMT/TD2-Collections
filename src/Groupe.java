import java.util.ArrayList;
import java.util.List;

public class Groupe {
    private List<Etudiant> etudiants;
    private Formation formation;

    public Groupe(Formation formation) {
        this.etudiants = new ArrayList<>();
        this.formation = formation;
    }

    public void ajouterEtudiant(Etudiant etudiant) {
        if (etudiant.getFormation().equals(formation)) {
            etudiants.add(etudiant);
        } else {
            System.out.println("étudiant non présent dans la formation");
        }
    }

    public void supprimerEtudiant(Etudiant etudiant) {
        etudiants.remove(etudiant);
    }

    public Formation getFormation() {
        return this.formation;
    }

    public List<Etudiant> getEtudiants() {
        return this.etudiants;
    }

    public double moyenneMatiere(Matiere m) throws MatiereInexistanteException {
        double total = 0;
        int count = 0;

        for (Etudiant etu : etudiants) {
            total += etu.moyenne(m);
            count++;
        }

        return (count == 0) ? 0 : total / count;
    }
/*
    public double moyenneGenerale() {
        double total = 0;
        double total_coef = 0;
        int count = 0;

        for (mo : formation.getMatieres()) {
            //key est la Matiere  bigT:   key = mo.getKey()
            //val est le coef     bigT:   val = mo.getValue()

            dcp pas besoin de mettre mo.attr juste la variable
            if(mo.val != 0){
                total += etu.moyenneMatiere(mo.key)*mo.val;
                total_coef += mo.val
            }
            count++;
        }

        return (count == 0) ? 0 : total / count;
    }
    // Méthode pour trier les étudiants A-Z
    public void triAlpha() {
        etudiants.sort(Comparator.comparing(Etudiant::getNom));
    }

    // Méthode pour trier les étudiants Z-A
    public void triAntiAlpha() {
        etudiants.sort(Comparator.comparing(Etudiant::getNom, Comparator.reverseOrder()));
    }


*/
}

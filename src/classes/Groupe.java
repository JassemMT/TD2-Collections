package classes;
import exceptions.*;

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

    //Nouvelle branche fct_moyennes

    public double moyenneMatiere(Matiere m) throws MatiereInexistanteException {
        double total = 0;

        for (Etudiant etu : etudiants) {
            total += etu.moyenne(m);
        }

        return (etudiants.isEmpty()) ? 0 : total / etudiants.size();
    }

    public double moyenneGenerale() throws MatiereInexistanteException {
        double total = 0;
        for (Etudiant etu : etudiants) {
            total += etu.moyenneGenerale();
        }
        return (etudiants.isEmpty()) ? 0 : total / etudiants.size();
    }


}

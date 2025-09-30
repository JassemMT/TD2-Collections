package classes;
import exceptions.*;


import java.util.ArrayList;
import java.util.Collections;
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
    /** Tri alphabétique A vers Z */
    public void triAlpha() {
        Collections.sort(etudiants, new ComparateurAlpha());
    }

    /** Tri alphabétique inverse Z bers A */
    public void triAntiAlpha() {
        Collections.sort(etudiants, new ComparateurAntiAlpha());
    }
}

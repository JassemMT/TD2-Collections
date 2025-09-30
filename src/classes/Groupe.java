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
//
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
        int count = 0;

        for (Etudiant etu : etudiants) {
            total += etu.moyenne(m);
            count++;
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

    /** Tri alphabétique A vers Z */
    public void triAlpha() {
        Collections.sort(etudiants, new ComparateurAlpha());
    }

    /** Tri alphabétique inverse Z bers A */
    public void triAntiAlpha() {
        Collections.sort(etudiants, new ComparateurAntiAlpha());
    }

    public List<Etudiant> triMerite() {
        List<Etudiant> listeTrie = new ArrayList<>(etudiants); // Copie de la liste

        if (etudiants.isEmpty()) {
            System.out.println("Aucun etudiant");
            return listeTrie;
        }

        if (etudiants.size() == 1) {
            System.out.println("un seul étudiant dans le groupe : " + listeTrie);
            return listeTrie;
        }

        try {
            listeTrie.sort(new ComparateurMerite()); // Trie avec le Comparator personnalisé
        } catch (Exception e) {
            System.out.println("Erreur lors du tri : " + e.getMessage());
        }
        return listeTrie; // Retourne la liste triée par moyenne décroissante
    }
}

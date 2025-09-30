package classes;
import classes.Etudiant;
import exceptions.MatiereInexistanteException;

import java.util.Comparator;


public class ComparateurMerite implements Comparator<Etudiant> {
    public int compare(Etudiant e1, Etudiant e2) {
        try {
            // Comparaison des moyennes générales en ordre décroissant
            double moyenneE1 = e1.moyenneGenerale();
            double moyenneE2 = e2.moyenneGenerale();
            return Double.compare(moyenneE2, moyenneE1); // Tri décroissant
        } catch (MatiereInexistanteException e) {
            System.out.println("Matiere inexistante pour un étudiant : " + e.getMessage());
            return 0; // Retourne 0 en cas d'erreur, laissant l'ordre inchangé
        }
    }
}

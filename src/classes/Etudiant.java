package classes;
import exceptions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Etudiant implements Comparable<Etudiant> {
    Identite identite;
    Formation formation;
    HashMap<Matiere, ArrayList<Double>> resultat;

    public Etudiant(Identite identite, Formation formation) {
        this.identite = identite;
        this.formation = formation;
        this.resultat = new HashMap<>();
    }

    public Formation getFormation() {
        return formation;
    }

    public Identite getIdentite() {
        return identite;
    }

    public HashMap<Matiere, ArrayList<Double>> getResultat() {
        return resultat;
    }

    public void ajouterNote (Matiere m, Double note) throws NoteInvalideException, MatiereInexistanteException{
        if (!resultat.keySet().contains(m)) {
            throw new MatiereInexistanteException("Le matiere n'existe pas");
        } if (note <0 || note > 20){
            throw new NoteInvalideException("la note est invalide");
        }
        resultat.get(m).add(note);
    }

    public double moyenne (Matiere m) throws MatiereInexistanteException{
        if (!resultat.containsKey(m)){
            throw new MatiereInexistanteException("Le matiere n'est pas dans la formation de l'etudiant");
        }
        int len = resultat.get(m).size();
        if (len == 0){
            return 0.0;
        }
        Double somme = 0.0;
        for(int i=0; i<len; i++){
           somme+= resultat.get(m).get(i);
        }
        return somme/len;
    }

    /**
     * Calcule la moyenne générale pondérée par les coefficients des matières
     */
    public double moyenneGenerale() throws MatiereInexistanteException{
        double sommeNotes = 0.0;
        double sommeCoef = 0.0;

        for (Matiere m : resultat.keySet()) { // seulement les matières où l'étudiant a eu une note
            double moy = moyenne(m);
            double coef = formation.getCoefficient(m);

            if (coef == -1) {
                throw new MatiereInexistanteException("La matière " + m.getNom() + " n'existe pas dans la formation");
            }

            sommeNotes += moy * coef;
            sommeCoef += coef;
        }

        if (sommeCoef == 0) {
            return 0.0;
        }
        return sommeNotes / sommeCoef;
    }

    public int compareTo(Etudiant autre) {
        // Exemple : trier par nom alphabétique
        return this.identite.getNom().compareTo(autre.identite.getNom());
    }


}

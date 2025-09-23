import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Etudiant {
    Identite identite;
    Formation formation;
    HashMap<Matiere, List<Double>> resultat;

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

    public HashMap<Matiere, List<Double>> getResultat() {
        return resultat;
    }

    public void AjouterNote (Matiere m, Double note) throws NoteInvalideException, MatiereInexistanteException{
        if (!formation.getMatiereNamesSet().contains(m)) {
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
            Double coef = m.getCoeff();
            sommeNotes += moy * coef;
            sommeCoef += coef;
        }

        if (sommeCoef == 0) {
            return 0.0;
        }
        return sommeNotes / sommeCoef;
    }



}

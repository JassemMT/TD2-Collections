package classes;

import classes.Etudiant;
import exceptions.*;

import java.util.Comparator;


public class ComparateurAlpha implements Comparator<Etudiant> {
    @Override
    public int compare(Etudiant e1, Etudiant e2) {
        return e1.getIdentite().getNom().compareTo(e2.getIdentite().getNom());
    }
}

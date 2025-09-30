package classes;

import classes.Etudiant;
import exceptions.*;

import java.util.Comparator;

public class ComparateurAntiAlpha implements Comparator<Etudiant> {
    @Override
    public int compare(Etudiant e1, Etudiant e2) {
        return e2.getIdentite().getNom().compareTo(e1.getIdentite().getNom());
    }
}

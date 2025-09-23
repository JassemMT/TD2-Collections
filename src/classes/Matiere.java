package classes;
import exceptions.*;
import java.util.Objects;

public class Matiere {
    private String nom;
    private double coeff;

    public Matiere(String nom,
                   //double coeff
                  ) {
        this.nom = nom;
        //this.coeff = coeff;
    }
    public String getNom() {
        return this.nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    
    /*
    public double getCoeff() {
        return this.coeff;
    }
    public void setCoeff(double coeff) {
        this.coeff = coeff;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof classes.Matiere matiere)) return false;
        return Double.compare(coeff, matiere.coeff) == 0 && Objects.equals(nom, matiere.nom);
    }

        @Override
    public int hashCode() {
        return Objects.hash(nom, coeff);
    }

        @Override
    public String toString() {
        return "classes.Matiere{" +
                "nom='" + nom + '\'' +
                ", coeff=" + coeff +
                '}';
    }
    
    */
    

     @Override
    public boolean equals(Object o) {
        if (!(o instanceof Matiere matiere)) return false;
        return matiere.nom.equals(nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom);
    }

    @Override
    public String toString() {
        return "classes.Matiere{" +
                "nom='" + nom + '\'' +
                '}';
    }
}

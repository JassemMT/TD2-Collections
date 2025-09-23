package classes;
import exceptions.*;

import java.util.Objects;

public class Identite {
    private String nip;
    private String nom;
    private String prenom;

    public Identite(String nip, String nom, String prenom) {
        this.nip = nip;
        this.nom = nom;
        this.prenom = prenom;
    }

    public String getNip() {
        return this.nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Identite identite)) return false;
        return Objects.equals(nip, identite.nip) && Objects.equals(nom, identite.nom) && Objects.equals(prenom, identite.prenom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nip, nom, prenom);
    }

    @Override
    public String toString() {
        return "classes.Identite{" +
                "nip='" + nip + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                '}';
    }
}

package stream;

import java.util.Objects;

public class Personne {
    Genre sexe;
    String nom;
    String prenom;
    int taille;

    Personne()
    {
        this(Genre.FEMME, "SALAI","Isma",156);
    }
    public Personne(Genre sexe, String nom, String prenom, int taille) {
        this.sexe = sexe;
        this.nom = nom;
        this.prenom = prenom;
        this.taille = taille;
    }

    public Genre getSexe() {
        return sexe;
    }

    public void setSexe(Genre sexe) {
        this.sexe = sexe;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Personne personne = (Personne) o;
        return taille == personne.taille && sexe == personne.sexe && Objects.equals(nom, personne.nom) && Objects.equals(prenom, personne.prenom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sexe, nom, prenom, taille);
    }

    @Override
    public String toString() {
        return "Personne{" +
                "sexe=" + sexe +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", taille=" + taille +
                '}';
    }
}

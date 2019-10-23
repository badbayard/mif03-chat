package fr.univlyon1.m1if.m1if03.bean;

public class GroupeBean {
    private String nom;
    private String description;
    private String proprietaire;

    public GroupeBean(String nom, String description, String proprietaire) {
        this.description = description;
        this.nom = nom;
        this.proprietaire = proprietaire;
    }
    public String getProprietaire() {
        return proprietaire;
    }

    public String getDescription() {
        return description;
    }

    public void setProprietaire(String proprietaire) {
        this.proprietaire = proprietaire;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNom() {
        return nom;
    }


}

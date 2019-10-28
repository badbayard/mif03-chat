package fr.univlyon1.m1if.m1if03.classes;


import java.util.ArrayList;

public class Groupe {
    private String nom;
    private String description;
    private String proprietaire;
    private ArrayList<String> participants;
    private GestionBillets gestion;

    public Groupe(){
         this.participants = new ArrayList<>();
         this.gestion = new GestionBillets();
    }

    public Groupe(String nom,String description, String proprietaire){
        this.participants = new ArrayList<>();
        this.gestion = new GestionBillets();
        this.nom = nom ;
        this.description = description;
        this.proprietaire = proprietaire;
    }

    public Groupe (String nom) {
        this.participants = new ArrayList<>();
        this.gestion = new GestionBillets();
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public String getProprietaire() {
        return proprietaire;
    }

    public ArrayList<String> getParticipants() {
        return participants;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setParticipants(ArrayList<String> participants) {
        this.participants = participants;
    }

    public void setProprietaire(String proprietaire) {
        this.proprietaire = proprietaire;
    }

    public GestionBillets getGestion() {
        return gestion;
    }

    public void setGestion(GestionBillets gestion) {
        this.gestion = gestion;
    }


}

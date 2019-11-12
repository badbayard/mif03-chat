package fr.univlyon1.m1if.m1if03.classes;

import java.util.ArrayList;

public class Billet {
    private String titre, contenu, auteur;
    private ArrayList<Message> commentaires;

    public Billet() {
        this.titre = "Rien";
        this.contenu = "Vide";
        this.auteur = "Personne";
        commentaires = new ArrayList<>();

    }

    public Billet(String titre, String contenu, String auteur , ArrayList<Message> commentaires) {
        this.titre = titre;
        this.contenu = contenu;
        this.auteur = auteur;
        this.commentaires = commentaires;
    }

    public ArrayList<Message> getCommentaires() {
        return commentaires;
    }

    public void addComments(String pseudo ,String message){
        Message m = new Message(pseudo , message);
        commentaires.add(m);
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public boolean equals (Billet b2){
        return(this.getAuteur() ==b2.getAuteur() && this.getContenu()==b2.getContenu() && this.getTitre()==b2.getTitre());
    }

    public String toString(){
        return ("titre = "+titre+"contenu = " + contenu+"auteur = " + auteur+ "commentaire = " + commentaires);
    }
}

package fr.univlyon1.m1if.m1if03.classes;

import java.util.ArrayList;

public class Billet {
    private String titre, contenu, auteur;
    private ArrayList<String> commentaires;

    public Billet() {
        this.titre = "Rien";
        this.contenu = "Vide";
        this.auteur = "Personne";
        commentaires = new ArrayList<>();

    }

    public Billet(String titre, String contenu, String auteur , ArrayList<String> commentaires) {
        this.titre = titre;
        this.contenu = contenu;
        this.auteur = auteur;
        this.commentaires = commentaires;
    }

    public ArrayList<String> getCommentaires() {
        return commentaires;
    }

    public void addcomments(String str){
        commentaires.add(str);
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
}

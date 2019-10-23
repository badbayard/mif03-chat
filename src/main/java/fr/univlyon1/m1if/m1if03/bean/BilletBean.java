package fr.univlyon1.m1if.m1if03.bean;

public class BilletBean {
    private String titre, contenu, auteur;


    public BilletBean(String titre, String contenu, String auteur){
        this.titre = titre;
        this.contenu = contenu;
        this.auteur = auteur;
    }

    public String getAuteur() {
        return auteur;
    }

    public String getContenu() {
        return contenu;
    }

    public String getTitre() {
        return titre;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

}

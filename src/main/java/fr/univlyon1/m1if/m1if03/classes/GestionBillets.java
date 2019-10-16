package fr.univlyon1.m1if.m1if03.classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GestionBillets {
    private HashMap<String, ArrayList<Billet>> billets;
    public GestionBillets() {
        billets = new HashMap<>();
    }

    public Billet getBillet(String groupe,int indice) {
        return billets.get(groupe).get(indice);
    }

    public ArrayList<Billet> getBillets(String groupe) {
        return billets.get(groupe);
    }

    public void addgroupe(String groupe){
        if(!billets.containsKey((groupe))){
            ArrayList<Billet> listegroupe = new ArrayList<>();
            billets.put(groupe,listegroupe);
        }
    }

    public void addbillet(Billet billet , String groupe){
        Billet newBillet = new Billet(billet.getTitre(),billet.getContenu(),billet.getAuteur(),billet.getCommentaires());
        billets.get(groupe).add(newBillet);
    }

}

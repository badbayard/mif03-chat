package fr.univlyon1.m1if.m1if03.classes;

import java.util.ArrayList;

public class Groupes {
    ArrayList <Groupe> groupes;

    public  Groupes(){
        groupes = new ArrayList<>();
    }

    public void addGroupe(Groupe g){
        groupes.add(g);
    }

    public  Groupe getGroupes (int indice){
        return groupes.get(indice);
    }

    public  ArrayList<Groupe> getGroupes (){
        return groupes;
    }

    public boolean contains(String n) {
        for(Groupe g : groupes) {
            if (g.getNom().equals(n)) {
                return true;
            }
        }
        return false;
    }


    public int position (Groupe g) {
        for (int i = 0 ; i < groupes.size() ; i++) {
            Groupe g2 = groupes.get(i);
            if(g.getNom() == g2.getNom()){
                return i;
            }
        }
        return -1;

    }


    public Groupe find (String name) {
        for (Groupe g : groupes) {
            if(g.getNom() == name) {
                return g;
            }
        }
        return new Groupe();
    }
}

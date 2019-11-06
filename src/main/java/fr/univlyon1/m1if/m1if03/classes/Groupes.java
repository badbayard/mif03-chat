package fr.univlyon1.m1if.m1if03.classes;

import java.util.ArrayList;

public class Groupes {
    ArrayList <Groupe> groupes;

    public  Groupes(){
        groupes = new ArrayList<>();
    }

    public void addGroupes(Groupe g){
        groupes.add(g);
    }

    public  Groupe getGroupes (int indice){
        return groupes.get(indice);
    }

    public  ArrayList<Groupe> getGroupes (){
        return groupes;
    }
}

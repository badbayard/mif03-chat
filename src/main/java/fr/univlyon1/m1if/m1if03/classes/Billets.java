package fr.univlyon1.m1if.m1if03.classes;

import java.util.ArrayList;
import java.util.List;

public class Billets {
    private List<Billet> billets;

    public Billets(){
        billets = new ArrayList<>();
    }

    public  Billets (List<Billet> billets) {
        this.billets = new ArrayList<>();
        for(Billet b : billets) {
            this.billets.add(b);
        }
    }


    public void add(Billet b) {
        billets.add(b);
    }


    public List<Billet> getBillets() {
        return billets;
    }

    public Billet getBillet(int i ) {
        return billets.get(i);
    }

    public int position (Billet b) {
        for (int i = 0 ; i < billets.size() ; i++) {
            Billet b2 = billets.get(i);
            if(b.equals(b2)){
               return i;
            }
        }
        return -1;

    }



}

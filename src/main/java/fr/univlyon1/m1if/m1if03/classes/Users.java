package fr.univlyon1.m1if.m1if03.classes;

import java.util.ArrayList;

public class Users {
    private ArrayList<String> users;

    public Users() {
        users = new ArrayList<>();
    }

    public ArrayList<String> getUsers() {
        return users;
    }

    public void addUser(String u) {
        users.add(u);
    }

    public boolean containUser(String u) {
        return users.contains(u);
    }
}

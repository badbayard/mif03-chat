package fr.univlyon1.m1if.m1if03.classes;

public class Message {
    private String pseudo;
    private String message;


    public Message(String pseudo , String message) {
        this.pseudo = pseudo;
        this.message = message;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPseudo() {
        return pseudo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return pseudo + " : " + message ;
    }


}

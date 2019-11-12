package fr.univlyon1.m1if.m1if03.servlets;

import fr.univlyon1.m1if.m1if03.classes.Billet;
import fr.univlyon1.m1if.m1if03.classes.Billets;
import fr.univlyon1.m1if.m1if03.classes.Groupe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


@WebServlet(name = "NewBillet", urlPatterns = "/NewBillet")
public class NewBillet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



        System.out.println("pseudo : " + request.getAttribute("pseudo"));
        System.out.println("pseudo : " + request.getAttribute("groupe"));




        //http header
        response.addDateHeader("Last-Modified" , (new Date().getTime()));


        //HttpSession session = request.getSession(true);
        String contenu  = request.getParameter("contenu");
        String titre  = request.getParameter("titre");

        String pseudo = (String)request.getAttribute("pseudo");
        String groupe = (String)request.getAttribute("groupe");

        boolean empty = false;
        HashMap<String, Groupe> g =(HashMap<String, Groupe>) request.getServletContext().getAttribute("g");

        Billet billet = new Billet();

        if(billet.getAuteur().equals("Personne")) {
            billet.setAuteur(pseudo);
        }
        if (titre != null && !titre.equals("") || contenu != null && !contenu.equals("")) {
            if(billet.getTitre().equals("Rien")) {
                billet.setTitre(titre);
            }
            if(billet.getContenu().equals("Vide")){
                billet.setContenu(contenu);
            }

            // ajout du billet a son groupe
            if(g.get(pseudo).getGestion() != null) {
                Billet b2 = new Billet(billet.getTitre(), billet.getContenu() , billet.getAuteur() , new ArrayList<>());
                g.get(pseudo).getGestion().addbillet(b2,groupe);
            }

        } else {
            //l'utilisateur a saisi un billet vide
            if(g.get(pseudo).getGestion().nbBillet(groupe) == 0) {
                //l'utilisateur n'a pas de billet -> redirection background


                //TODO
                //request.getRequestDispatcher("WEB-INF/jsp/background.jsp").forward(request, response);
                request.setAttribute("view" , "background");
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            } else  {
                // billet vide et l'utilisateur avais deja des billets
                empty = true ;
            }
        }
        //menu billet
        Billets billets = new Billets(g.get(pseudo).getGestion().getBillets(groupe));
        request.setAttribute("billets",billets);

        int indice;
        if(empty) {
            indice = (int)request.getServletContext().getAttribute("indice");
            billet = g.get(pseudo).getGestion().getBillet(groupe, indice);
        } else {
            indice = g.get(pseudo).getGestion().getBillets(groupe).size() - 1;
        }

        request.getServletContext().setAttribute("indice" ,indice);

        request.setAttribute("billet",billet);

        response.setStatus(HttpServletResponse.SC_CREATED);
        request.setAttribute("view" , "billet");

    }




}

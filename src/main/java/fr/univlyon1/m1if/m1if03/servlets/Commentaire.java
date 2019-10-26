package fr.univlyon1.m1if.m1if03.servlets;
import fr.univlyon1.m1if.m1if03.classes.Groupe;
import fr.univlyon1.m1if.m1if03.classes.Billet;
import fr.univlyon1.m1if.m1if03.classes.Message;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import java.util.HashMap;

@WebServlet(name = "Commentaire", urlPatterns = "/Commentaire")
public class Commentaire extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(true);

        String pseudo = (String)session.getAttribute("pseudo");
        String groupe = (String)session.getAttribute("groupe");
        String commentaire = request.getParameter("commentaire");

        HashMap<String, Groupe> g =(HashMap<String, Groupe>) request.getServletContext().getAttribute("g");


        int indiceMax = g.get(pseudo).getGestion().getBillets(groupe).size() - 1;
        Billet billet = g.get(pseudo).getGestion().getBillet(groupe, indiceMax);

        System.out.println("pseudo"  + pseudo);

        if(!commentaire.isEmpty()) {
            billet.getCommentaires().add(new Message(pseudo , commentaire));
            request.setAttribute("billet", billet);

            request.getRequestDispatcher("billet.jsp").forward(request, response);
        } else {
            request.setAttribute("billet", billet);
            request.getRequestDispatcher("billet.jsp").forward(request, response);
        }
    }


}

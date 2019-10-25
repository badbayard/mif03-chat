package fr.univlyon1.m1if.m1if03.servlets;
import fr.univlyon1.m1if.m1if03.classes.Groupe;
import fr.univlyon1.m1if.m1if03.classes.Billet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.http.HttpRequest;
import java.util.HashMap;

@WebServlet(name = "Commentaire", urlPatterns = "/Commentaire")
public class Commentaire extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        HttpSession session = request.getSession(true);
        ServletContext context = getServletContext();
        String pseudo = request.getParameter("pseudo");
        String groupe = request.getParameter("groupe");
        String commentaire = request.getParameter("commentaire");
        HashMap<String, Groupe> g =(HashMap<String, Groupe>) request.getServletContext().getAttribute("g");
        request.getRequestDispatcher("billet.jsp").forward(request, response);
        System.out.println(commentaire);
        System.out.println(g.get(pseudo));
        if(!commentaire.isEmpty()) {
            System.out.println("yoloo");
            session.setAttribute("commentaire", commentaire);
           // g.get(pseudo).getGestion().getBillets(groupe).addComments(pseudo,commentaire);
        }
    }
}

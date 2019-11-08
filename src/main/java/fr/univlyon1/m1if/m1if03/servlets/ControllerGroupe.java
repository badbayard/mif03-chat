package fr.univlyon1.m1if.m1if03.servlets;


import fr.univlyon1.m1if.m1if03.classes.Groupe;
import fr.univlyon1.m1if.m1if03.classes.Groupes;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "Groupes", urlPatterns = "/Groupes")
public class ControllerGroupe extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        Groupes groupes = new Groupes();
        ServletContext context = config.getServletContext();
        context.setAttribute("groupes", groupes);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("dans le groupes");

        HashMap<String, Groupe> g = (HashMap<String, Groupe>) request.getServletContext().getAttribute("g");

        Groupes groupes = new Groupes();
        for (String k : g.keySet()) {
            groupes.addGroupe(g.get(k));
        }

        request.setAttribute("groupes", groupes);

        //request.setAttribute("vuejsp", "groupes");

        //System.out.println("url " + request.getRequestURI());
        System.out.println("Groupes : ");
        for(Groupe grp : groupes.getGroupes()){
            System.out.println(grp.getNom());
        }
    }
}

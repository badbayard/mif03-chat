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


@WebServlet(name = "Groupes", urlPatterns = "/Groupes")
public class ControllerGroupe extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        Groupes groupes = new Groupes();
        ServletContext context = config.getServletContext();
        context.setAttribute("groupes", groupes);
        context.setAttribute("view" , "groupes");
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //HttpSession session = request.getSession(true);

        Groupes groupes = (Groupes)request.getServletContext().getAttribute("groupes");

        String groupe = request.getParameter("groupe");
        //session.setAttribute("groupe",groupe);

        if(!groupes.contains(groupe)) {
            groupes.addGroupe(new Groupe(groupe));
        }
        request.setAttribute("groupes", groupes);
        request.setAttribute("view" , "groupes");


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("dans le groupes");


        Groupes groupes = (Groupes)request.getServletContext().getAttribute("groupes");


        //request.setAttribute("view", "groupes");

        //System.out.println("url " + request.getRequestURI());
        System.out.println("Groupes : ");
        for(Groupe grp : groupes.getGroupes()){
            System.out.println(grp.getNom());
        }

        request.getRequestDispatcher("WEB-INF/jsp/groupes.jsp").forward(request, response);
    }
}

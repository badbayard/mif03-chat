package fr.univlyon1.m1if.m1if03.servlets;



import fr.univlyon1.m1if.m1if03.classes.Groupe;
import fr.univlyon1.m1if.m1if03.classes.Groupes;
import fr.univlyon1.m1if.m1if03.classes.Users;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "Users", urlPatterns = "/Users")
public class UsersController extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        Users users = new Users();
        Groupe groupe = new Groupe();
        String pseudo  = "";

        ServletContext context = config.getServletContext();
        context.setAttribute("users", users);
        context.setAttribute("groupe " ,groupe);
        context.setAttribute("pseudo " ,pseudo);

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //HttpSession session = request.getSession(true);
        Users users = (Users)request.getServletContext().getAttribute("users");
        Groupes grp = (Groupes)request.getServletContext().getAttribute("groupes");

        String pseudo = request.getParameter("pseudo");
        int index  = Integer.parseInt(request.getParameter("menuGroupe"));

        //session.setAttribute("pseudo" , pseudo);

        if(!users.containUser(pseudo)) {
            users.addUser(pseudo);
        }

        System.out.println("groupe : " + grp.getGroupes(index).getNom());
        System.out.println("pseudo : " + request.getParameter("pseudo"));



        request.setAttribute("index" , index);
        request.setAttribute("pseudo" , pseudo);
        request.setAttribute("users", users);


        Groupe gr = grp.getGroupes(index);
        request.getServletContext().setAttribute("groupe" ,gr);
        request.getServletContext().setAttribute("pseudo" ,pseudo);

        request.setAttribute("view" , "background");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Users users = (Users)request.getServletContext().getAttribute("users");
        request.setAttribute("users", users);

        HashMap<String, Groupe> g =(HashMap<String, Groupe>) request.getServletContext().getAttribute("g");
        String pseudo = (String)getServletContext().getAttribute("pseudo");
        Groupe groupe = (Groupe)getServletContext().getAttribute("groupe");


        if(!g.get(pseudo).getGestion().getBillets(groupe.getNom()).isEmpty()) {
            request.setAttribute("view" , "billet");
        } else  {
            request.setAttribute("view" , "background");
        }


    }
}

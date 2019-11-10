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

        ServletContext sc = config.getServletContext();
        HashMap<String, Groupe> g = new HashMap<>();
        Users u = new Users();
        sc.setAttribute("users", u);
        sc.setAttribute("g",g);
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
        request.setAttribute("groupe" ,gr);
        request.setAttribute("pseudo" ,pseudo);

        HashMap<String, Groupe> g =(HashMap<String, Groupe>) request.getServletContext().getAttribute("g");


        if(g.get(pseudo) == null) {
            g.put(pseudo , gr);
        }
        if(g.get(pseudo).getGestion().getBillets(gr.getNom()) == null) {
            // objet bien ajouté
            g.get(pseudo).getGestion().addgroupe(gr.getNom());

            response.setStatus(HttpServletResponse.SC_CREATED);
        } else {
            //utilisateur deja présent dans le groupe
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }


        request.setAttribute("view" , "background");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Users users = (Users)request.getServletContext().getAttribute("users");
        Groupes grps = (Groupes)request.getServletContext().getAttribute("groupes");
        request.setAttribute("users", users);

        HashMap<String, Groupe> g =(HashMap<String, Groupe>) request.getServletContext().getAttribute("g");
        String pseudo = (String)request.getAttribute("pseudo");
        String groupe = (String)request.getAttribute("groupe");


        Groupe gr = new Groupe();
        for(String k : g.keySet()) {
            System.out.println(g.get(k).getNom());
            if(g.get(k).getNom().equals(groupe)) {
                gr = g.get(k);
            }
        }

        if(g.containsKey(pseudo) && g.containsValue(gr)) {
            if(g.get(pseudo).getGestion().getBillets(groupe).isEmpty()) {
                request.setAttribute("view" , "background");
            } else {
                request.setAttribute("view" , "billet");
            }

        } else {
            // l'objet n'existe pas
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }


    }
}

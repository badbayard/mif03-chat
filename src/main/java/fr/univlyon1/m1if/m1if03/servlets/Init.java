package fr.univlyon1.m1if.m1if03.servlets;



import fr.univlyon1.m1if.m1if03.classes.Billet;
import fr.univlyon1.m1if.m1if03.classes.Billets;
import fr.univlyon1.m1if.m1if03.classes.Groupe;

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


@WebServlet(name = "Init", urlPatterns = "/Init")
public class Init extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext sc = config.getServletContext();
        HashMap<String, Groupe> g = new HashMap<>();
        sc.setAttribute("g",g);
    }




    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        String pseudo = request.getParameter("pseudo");
        String groupe = request.getParameter("groupe");

        HashMap<String, Groupe> g =(HashMap<String, Groupe>) request.getServletContext().getAttribute("g");

        if(pseudo != null && !pseudo.equals("")) {
            session.setAttribute("pseudo", pseudo);
            session.setAttribute("groupe",groupe); // <----- groupe



            System.out.println("liste des groupes : ");
            Groupe grp = new Groupe(groupe);
            for(String k : g.keySet()) {
                System.out.println(g.get(k).getNom());
                if(g.get(k).getNom().equals(groupe)) {
                    grp = g.get(k);
                }
            }

            if(g.get(pseudo) == null) {
                g.put(pseudo , grp);
            }
            if(g.get(pseudo).getGestion().getBillets(groupe) == null) {
                //ajout du groupe
                //System.out.println("g.get(pseudo).getGestion().getBillets(groupe) == null");
                g.get(pseudo).getGestion().addgroupe(groupe);
            }


            if (g.get(pseudo).getGestion().getBillets(groupe).isEmpty()) {
                //pas de billets pour l'utilisateur
                request.getRequestDispatcher("WEB-INF/background.jsp").forward(request, response);

            } else {
                //il existe un billet pour l'utilisateur on le récupere et on l'affiche (on recup le dernier billet par defaut)

                int indiceMax = g.get(pseudo).getGestion().getBillets(groupe).size() - 1;
                Billet billet = g.get(pseudo).getGestion().getBillet(groupe, indiceMax);

                //menu billet
                Billets billets = new Billets(g.get(pseudo).getGestion().getBillets(groupe));
                request.setAttribute("billets",billets);

                request.setAttribute("billet", billet);
                request.getRequestDispatcher("WEB-INF/billet.jsp").forward(request, response);
            }


        } else {
            response.sendRedirect("index.html");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //request.getRequestDispatcher("billet.jsp").forward(request, response);
        response.sendRedirect("index.html");
    }


    
}

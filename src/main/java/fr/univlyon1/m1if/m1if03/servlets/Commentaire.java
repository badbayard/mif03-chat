package fr.univlyon1.m1if.m1if03.servlets;
import fr.univlyon1.m1if.m1if03.classes.Billets;
import fr.univlyon1.m1if.m1if03.classes.Groupe;
import fr.univlyon1.m1if.m1if03.classes.Billet;
import fr.univlyon1.m1if.m1if03.classes.Message;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Path;
import java.io.IOException;

import java.util.Date;
import java.util.HashMap;

@WebServlet(name = "Commentaire", urlPatterns = "/Commentaire")
public class Commentaire extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("dans le DOPOST") ;
        System.out.println("groupe : " +(String)request.getAttribute("groupe") + "pseudo : " +(String)request.getAttribute("pseudo")) ;
        //http header
        response.addDateHeader("Last-Modified" , (new Date().getTime()));
        //HttpSession session = request.getSession(true);

        String pseudo = (String)request.getAttribute("pseudo");
        String groupe = (String)request.getAttribute("groupe");
        String commentaire = request.getParameter("commentaire");

        HashMap<String, Groupe> g =(HashMap<String, Groupe>) request.getServletContext().getAttribute("g");


        int indice = (int)request.getServletContext().getAttribute("indice");
        Billet billet = g.get(pseudo).getGestion().getBillet(groupe, indice);


        String ind = indice+"";
        //cookies
        Cookie c = new Cookie("indice" , ind);
        response.addCookie(c);


        //menu billet
        Billets billets = new Billets(g.get(pseudo).getGestion().getBillets(groupe));
        request.setAttribute("billets",billets);


        if(!commentaire.isEmpty()) {
            billet.getCommentaires().add(new Message(pseudo , commentaire));
            request.setAttribute("billet", billet);

            //request.getRequestDispatcher("WEB-INF/jsp/billet.jsp").forward(request, response);
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            request.setAttribute("view" , "billet");

        } else {
            request.setAttribute("billet", billet);
            //request.getRequestDispatcher("WEB-INF/jsp/billet.jsp").forward(request, response);
            response.setStatus(HttpServletResponse.SC_CREATED);
            request.setAttribute("view" , "billet");
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        System.out.println("dans le DOGET") ;
        System.out.println("groupe : " +(String)req.getAttribute("groupe") + "pseudo : " +(String)req.getAttribute("pseudo")) ;

        String groupe = (String)req.getAttribute("groupe");
        String pseudo = (String)req.getAttribute("pseudo");
        HashMap<String, Groupe> g =(HashMap<String, Groupe>) req.getServletContext().getAttribute("g");


        Cookie [] cookies = req.getCookies();
        if(cookies != null) {
            for(Cookie c : cookies) {
                if(c.getName().equals("indice")) {
                    int ind = Integer.parseInt(c.getValue());
                    System.out.println(req.getServletContext().getAttribute("indice").equals(ind));
                    if(req.getServletContext().getAttribute("indice").equals(ind)) {
                        resp.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
                        return;
                    }
                }
            }
        }

        //menu billet
        Billets billets = new Billets(g.get(pseudo).getGestion().getBillets(groupe));
        req.setAttribute("billets",billets);


        //req.getRequestDispatcher("WEB-INF/jsp/billet.jsp").forward(req, resp);
        req.setAttribute("view" , "billet");

    }
}

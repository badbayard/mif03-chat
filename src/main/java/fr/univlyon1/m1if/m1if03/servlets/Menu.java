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
import java.util.Date;
import java.util.HashMap;


@WebServlet(name = "Menu", urlPatterns = "/Menu")
public class Menu extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //http header
        response.addDateHeader("Last-Modified" , (new Date().getTime()));


        String rep = request.getParameter("menu");
        int indice = Integer.parseInt(request.getParameter("menu"));
        request.getServletContext().setAttribute("indice" ,indice);

        HttpSession session = request.getSession(true);
        String pseudo = (String)session.getAttribute("pseudo");
        String groupe = (String)session.getAttribute("groupe");

        HashMap<String, Groupe> g =(HashMap<String, Groupe>) request.getServletContext().getAttribute("g");

        //menu billet
        Billets billets = new Billets(g.get(pseudo).getGestion().getBillets(groupe));
        request.setAttribute("billets",billets);


        if(rep != null && indice != -1) {
            Billet billet  = g.get(pseudo).getGestion().getBillet(groupe,indice);
            request.setAttribute("billet", billet);
        }

        request.getRequestDispatcher("WEB-INF/jsp/billet.jsp").forward(request, response);
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String groupe = (String)request.getAttribute("groupe");
        String pseudo = (String)request.getAttribute("pseudo");
        int index = Integer.parseInt((String )request.getAttribute("index"));
        HashMap<String, Groupe> g =(HashMap<String, Groupe>) request.getServletContext().getAttribute("g");


        //menu billet
        Billets billets = new Billets(g.get(pseudo).getGestion().getBillets(groupe));
        request.setAttribute("billets",billets);

        if(index != -1) {
            Billet billet  = g.get(pseudo).getGestion().getBillet(groupe,index);
            request.setAttribute("billet", billet);
        }

        request.setAttribute("view" , "billet");
    }
}

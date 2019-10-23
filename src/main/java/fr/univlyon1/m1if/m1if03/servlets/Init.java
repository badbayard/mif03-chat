package fr.univlyon1.m1if.m1if03.servlets;




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
import java.io.PrintWriter;
import java.util.HashMap;


@WebServlet(name = "Init", urlPatterns = "/Init")
public class Init extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext sc = config.getServletContext();
        HashMap<String, Groupe> g = new HashMap<>();
        sc.setAttribute("g",g);
        g.put("test",new Groupe("test" , "test" , "test"));
    }




    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pseudo = request.getParameter("pseudo");
        String groupe = request.getParameter("groupe");
        if(pseudo != null && !pseudo.equals("")) {
            HttpSession session = request.getSession(true);
            session.setAttribute("pseudo", pseudo);
            session.setAttribute("groupe",groupe); // <----- groupe
            request.getRequestDispatcher("billet.jsp").forward(request, response);
        } else {
            response.sendRedirect("index.html");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        request.getRequestDispatcher("billet.jsp").forward(request, response);
        HashMap<String, Groupe> g =(HashMap<String, Groupe>) getServletContext().getAttribute("g");
        Groupe p = g.get(0);
        out.println("<h1>"+p.getNom()+"</h1>");
    }


    
}

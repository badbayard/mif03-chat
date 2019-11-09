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

@WebServlet(name = "Users", urlPatterns = "/Users")
public class UsersController extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        Users users = new Users();
        ServletContext context = config.getServletContext();
        context.setAttribute("users", users);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("je suis dans users");
        HttpSession session = request.getSession(true);

        Users users = (Users)request.getServletContext().getAttribute("users");
        Groupes grp = (Groupes)request.getServletContext().getAttribute("groupes");

        String pseudo = request.getParameter("pseudo");
        session.setAttribute("pseudo",pseudo);

        if(!users.containUser(pseudo)) {
            users.addUser(pseudo);
        }
        request.setAttribute("users", users);
        //response.sendRedirect("WEB-INF/jsp/groupes.jsp");

        request.getRequestDispatcher("WEB-INF/jsp/groupes.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Users users = (Users)request.getServletContext().getAttribute("users");
        request.setAttribute("users", users);
        request.getRequestDispatcher("WEB-INF/jsp/groupes.jsp").forward(request, response);
    }
}

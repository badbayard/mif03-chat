package fr.univlyon1.m1if.m1if03.servlets;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "Routeur", urlPatterns = "/")
public class Routeur extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getRequestURI();
        String [] params = url.split("/");
        String last = params[params.length - 1];


        System.out.println("method : " + request.getMethod() + " last : " + last);
        if (last.equals("Init")) {
            request.getRequestDispatcher("/Init").forward(request, response);
        }

        }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getRequestURI();
        String[] params = url.split("/");
        String last = params[params.length - 1];

        if (request.getParameter("pseudo") != null && !request.getParameter("pseudo").equals("")) {

            String pseudo = request.getParameter("pseudo");
            url += "/" + pseudo;
            System.out.println("new URL : " + url);

            if (last.equals("Init")) {
                request.getRequestDispatcher(url).forward(request, response);
            }

        }

        System.out.println("method : " + request.getMethod() + " last : " + last);

    }
}

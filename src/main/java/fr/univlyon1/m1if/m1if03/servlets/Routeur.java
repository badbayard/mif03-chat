package fr.univlyon1.m1if.m1if03.servlets;
import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "Routeur", urlPatterns = "/Routeur")
public class Routeur extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext context = config.getServletContext();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String url = request.getRequestURI();
        String [] params = url.split("/");
        //String last = params[params.length - 1];

        System.out.println("status : " + response.getStatus());

        //System.out.println("method : " + request.getMethod() +" l'url :" +url );
        if (url.equals("/Init")) {
            request.getServletContext().getNamedDispatcher("Init").forward(request, response);
        } else {
            System.out.println("URL : " + url + " method : " +request.getMethod());
        }


    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getRequestURI();
        String[] params = url.split("/");
        //String last = params[params.length - 1];

        if (request.getParameter("pseudo") != null && !request.getParameter("pseudo").equals("")) {


            if (url.equals("/index.html")) {
                request.getRequestDispatcher(url).forward(request, response);
            }else if (url.equals("/Init")) {
                request.getServletContext().getNamedDispatcher("Init").forward(request, response);
            }
            else {
                System.out.println("status : " + response.getStatus());
                System.out.println("URL : " + url + " method : " +request.getMethod());

            }


        }
        //System.out.println("method : " + request.getMethod() + " last : " + last);

    }

}

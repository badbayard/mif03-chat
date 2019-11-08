package fr.univlyon1.m1if.m1if03.servlets;
import javax.servlet.Filter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "Routeur", urlPatterns = "/*")
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
        String last = "/";
        if(params.length > 0) {
            last = params[params.length - 1];
        }



/*
        RequestDispatcher dispatcher;
        String path [] = request.getRequestURI().split("/");
        if(path.length > 1) { // l'URL est complète
            dispatcher = request.getServletContext().getNamedDispatcher(path[1]);
            if(dispatcher != null) { // la servlet est référencée dans le contexte par son nom
                if (url.equals("/Init")) {
                    request.getServletContext().getNamedDispatcher("Init").forward(request, response);
                } else if (url.equals("/Groupes")) {
                    request.getServletContext().getNamedDispatcher("Groupes").forward(request, response);
                } else {
                    System.out.println("URL : " + url + " method : " + request.getMethod() + " status : " + response.getStatus());
                }
                dispatcher.forward(request, response);
            } else { // renvoi de fichiers statiques
                // cf. https://stackoverflow.com/questions/132052/servlet-for-serving-static-content
                dispatcher = getServletContext().getNamedDispatcher("default");

                HttpServletRequest wrapped = new HttpServletRequestWrapper(request) {
                    public String getServletPath() { return ""; }
                };

                dispatcher.forward(wrapped, response);
            }
        } else { // Page d'accueil
            dispatcher = request.getRequestDispatcher("/index.html");
            dispatcher.forward(request, response);
        }

 */
    dispatch(request,response);
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

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dispatch(request, response);
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dispatch(request, response);
    }


    private void dispatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher;

        // On décompose l'URL
        String path [] = request.getRequestURI().split("/");
        // Les 2 premières parties sont serveur et chemin de l'application -> on s'intéresse à la suite
        if(path.length > 1) { // l'URL est complète
            dispatcher = request.getServletContext().getNamedDispatcher(path[1]);
            if(dispatcher != null) { // la servlet est référencée dans le contexte par son nom
                dispatcher.forward(request, response);
            } else { // renvoi de fichiers statiques
                // cf. https://stackoverflow.com/questions/132052/servlet-for-serving-static-content
                dispatcher = getServletContext().getNamedDispatcher("default");

                HttpServletRequest wrapped = new HttpServletRequestWrapper(request) {
                    public String getServletPath() { return ""; }
                };

                dispatcher.forward(wrapped, response);
            }
        } else { // Page d'accueil
            dispatcher = request.getRequestDispatcher("/index.html");
            dispatcher.forward(request, response);
        }
    }

}

package fr.univlyon1.m1if.m1if03.servlets;
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
                if (url.equals("/Init")) {
                    request.getServletContext().getNamedDispatcher("Init").forward(request, response);
                } else if (url.equals("/Groupes")) {
                    request.getServletContext().getNamedDispatcher("Groupes").forward(request, response);
                } else {
                    System.out.println("URL : " + url + " method : " + request.getMethod() + " status : " + response.getStatus());
                }
 */
    dispatch(request,response);
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dispatch(request, response);
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dispatch(request, response);
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dispatch(request, response);
    }


    private void dispatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher;

/*
        if(getServletContext().getAttribute("pseudo") != null || !getServletContext().getAttribute("pseudo").equals("")) {
            String url = request.getRequestURI() + "?pseudo=" + getServletContext().getAttribute("pseudo");
            System.out.println(url);

        }
*/
        // On décompose l'URL
        String path [] = request.getRequestURI().split("/");
        // Les 2 premières parties sont serveur et chemin de l'application -> on s'intéresse à la suite




        if(path.length > 1) { // l'URL est complète
            dispatcher = request.getServletContext().getNamedDispatcher(path[1]);
            if(path.length == 4 ) {
                // url de la forme localhost:8080/Groupes/titi/nomgrp
                for (String s : path) {
                    System.out.println(s);
                }
                request.setAttribute("pseudo" ,path[2]);
                request.setAttribute("groupe" ,path[3]);
                dispatcher = getServletContext().getNamedDispatcher("Users");
            }
            if(dispatcher != null) { // la servlet est référencée dans le contexte par son nom
                dispatcher.include(request, response);
            } else { // renvoi de fichiers statiques
                // cf. https://stackoverflow.com/questions/132052/servlet-for-serving-static-content
                dispatcher = getServletContext().getNamedDispatcher("default");
                //System.out.println(dispatcher);
                HttpServletRequest wrapped = new HttpServletRequestWrapper(request) {
                    public String getServletPath() {
                        return "";
                    }
                };

                dispatcher.forward(wrapped, response);
            }
        } else { // Page d'accueil
            dispatcher = request.getRequestDispatcher("/index.html");
            dispatcher.forward(request, response);
        }
    }

}

/**
 *     <servlet>
 *   <servlet-name>ControllerGroupe</servlet-name>
 *   <jsp-file>/groupes.jsp</jsp-file>
 * </servlet>
 *   <servlet-mapping>
 *     <servlet-name>ControllerGroupe</servlet-name>
 *     <url-pattern>/groupes.jsp</url-pattern>
 *   </servlet-mapping>

 */
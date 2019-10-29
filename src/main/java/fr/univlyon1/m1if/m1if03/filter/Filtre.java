package fr.univlyon1.m1if.m1if03.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/Filtre")
public class Filtre extends HttpServlet implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(true);

        //bloc innutile la servlet Init n'est pas prise en compte dans le filtre
        /*
        String pseu = request.getParameter("pseudo");
        if(pseu != null && !pseu.equals("")){
            session.setAttribute(pseu,"pseudo");
            System.out.println("affectation");
        }
        */


        String pseudo = (String)session.getAttribute("pseudo");
        if(pseudo == null || pseudo.equals("")) {
            //System.out.println("redirection");
            response.sendRedirect("index.html");
            return;
        }

        //System.out.println("ok");

        filterChain.doFilter(request,response);

    }

}

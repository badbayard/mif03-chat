package fr.univlyon1.m1if.m1if03.servlets;

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
        String pseudo = (String)session.getAttribute("pseudo");
        //System.out.println("pseudo : " + pseudo);

        if(pseudo == null || pseudo.equals("")) {
            response.sendRedirect("index.html");
            return;
        }

        System.out.println("je suis laaaaaaaaaaaaaaaaaaaaaaaaaa");
        filterChain.doFilter(request,response);

    }

}

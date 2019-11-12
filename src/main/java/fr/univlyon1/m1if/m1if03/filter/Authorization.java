package fr.univlyon1.m1if.m1if03.filter;


import fr.univlyon1.m1if.m1if03.classes.Groupe;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

@WebFilter("/Filtre2")
public class Authorization extends HttpServlet implements Filter{

    private FilterConfig fg;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        fg = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        ServletContext sc = fg.getServletContext();

        HttpSession session = request.getSession(true);
        String groupe = (String)request.getAttribute("groupe");
        String pseudo = (String)request.getAttribute("pseudo");
        HashMap<String, Groupe> g = (HashMap<String, Groupe>) sc.getAttribute("g");


        if(g.get(pseudo).getGestion().getBillets(groupe) == null) {
            response.sendRedirect("index.html");
            return;
        }

        filterChain.doFilter(request,response);


    }

}
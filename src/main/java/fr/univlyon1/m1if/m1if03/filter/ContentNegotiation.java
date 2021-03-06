package fr.univlyon1.m1if.m1if03.filter;

import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(filterName = "ContentNegotiation")
public class ContentNegotiation implements Filter {
    FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        chain.doFilter(req, resp);
        String view = (String) ((HttpServletRequest) req).getAttribute("view");
        //System.out.println("view : " + view);
        // S'il y a un contenu à renvoyer
        if (view != null) {
            // TODO implémenter ici la négociation de contenus
            /*
            resp.setContentType("application/json");
            PrintWriter writer = resp.getWriter();
            //JsonObject model = Json.createObjectBuilder();
            //writer.println(model);
            writer.flush();
            writer.close();
            */


            if(((HttpServletRequest) req).getHeader("Accept").startsWith("text/html")) {
                // Cas des JSP (nommées dans le web.xml)
                RequestDispatcher dispatcher = filterConfig.getServletContext().getNamedDispatcher(view + "-vue");
                HttpServletRequest wrapped = new HttpServletRequestWrapper((HttpServletRequest) req) {
                    public String getServletPath() {
                        return "";
                    }
                };
                dispatcher.forward(wrapped, resp);
            }
        }
    }
}
package fr.univlyon1.m1if.m1if03.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.io.IOException;

@WebFilter("/Filtre")
public class Authentication extends HttpServlet implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //HttpSession session = request.getSession(true);

        //bloc innutile la servlet Init n'est pas prise en compte dans le filtre
        /*
        String pseu = request.getParameter("pseudo");
        if(pseu != null && !pseu.equals("")){
            session.setAttribute(pseu,"pseudo");
            System.out.println("affectation");
        }
        */


        /*String pseudo = (String)session.getAttribute("pseudo");
        if(pseudo == null || pseudo.equals("")) {
            //System.out.println("redirection");
            response.sendRedirect("index.html");
            return;
        }*/


        //String token = request.getParameter("token");
        String token = (String)request.getAttribute("token");
        //System.out.println("token dans auth = " + token);
        if(token != null) {
            //System.out.println("token dans auth = " + token);
            DecodedJWT jwt = JWT.decode(token);
            String contentType = jwt.getContentType();
            //System.out.println("token_after = " + contentType);
            Algorithm algorithm = Algorithm.HMAC256("secret");
            JWTVerifier verifier = JWT.require(algorithm).build();
        }

        filterChain.doFilter(request,response);

    }

}

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="fr.univlyon1.m1if.m1if03.classes.Billet" %>
<%@ page import="fr.univlyon1.m1if.m1if03.classes.GestionBillets" %>
<%@ page import="fr.univlyon1.m1if.m1if03.classes.Message" %>
<%! private Billet billet = new Billet();
    private static GestionBillets gestion = new GestionBillets();
%>
<%

    if(session.getAttribute("pseudo") == null) {
        session.invalidate();
        response.sendRedirect("index.html");
        return;
    } else if (session.getAttribute("pseudo") != billet.getAuteur()) {
        billet = new Billet();
    }

    if (request.getMethod().equals("POST")) {
    String contenu  = request.getParameter("contenu");
    String titre  = request.getParameter("titre");
    String commentaire = request.getParameter("commentaire");
    boolean add = false;
    if (titre != null && !titre.equals("") || contenu != null && !contenu.equals("")) {
        billet = new Billet();
        billet.setTitre(titre);
        billet.setContenu(contenu);
        add = true;
    }


    billet.setAuteur((String) session.getAttribute("pseudo"));

    if(commentaire != null  && !commentaire.equals("")) {
            billet.addComments((String) session.getAttribute("pseudo") , commentaire);
    }

    if (add) {
        Billet b2 = new Billet(billet.getTitre(), billet.getContenu() , billet.getAuteur() , billet.getCommentaires());
        gestion.add(b2);
    }


} %>
<!doctype html>
<html>
<head>
    <meta http-equiv="refresh" content="5"/>
    <title>Billet</title>
</head>
<body>
<h2>Hello <%= session.getAttribute("pseudo")%> !</h2>
<p>Ceci est un billet de <%= billet.getAuteur() %></p>
<h1><c:out value="<%= billet.getTitre()%>"/></h1>
<div class="contenu"><%= billet.getContenu()%></div>
<hr>
<form method="post" action="billet.jsp">
    <p>
        Commentaire :
        <input type="text" name="commentaire">
        <input type="submit" value="Envoyer">
    </p>
</form>
<%

for(Message m : billet.getCommentaires()) {
   out.println("<p>" + m.toString() + "</p>");
}

%>


<form action="billet.jsp">
    <select name="menu">
<%
        for (int i = 0 ; i < gestion.getBillets().size() ; i++) {
            Billet b = gestion.getBillet(i);
            out.println("<option value=" + i + ">" + b.getTitre() + " </option>");
        }
%>

        <!--option value="Java">Java</option>
        <option value="PHP">PHP</option>
        <option value="Python">Python</option!-->
    </select>
    <input type="submit" Value="Get"/>
</form>

<%
    if(request.getParameter("menu") != null) {
        out.println("<p>" + request.getParameter("menu") + "<p>" );
    }
%>



<p><a href="saisie.html">Saisir un nouveau billet</a></p>
<p><a href="Deco">Se déconnecter</a></p>

</body>
</html>



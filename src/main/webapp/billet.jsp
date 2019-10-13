<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="fr.univlyon1.m1if.m1if03.classes.Billet" %>
<%@ page import="fr.univlyon1.m1if.m1if03.classes.GestionBillets" %>
<%! private Billet billet = new Billet();
    private static GestionBillets gestion = new GestionBillets();

%>
<% if (request.getMethod().equals("POST")) {
    String contenu  = request.getParameter("contenu");
    String titre  = request.getParameter("titre");
    boolean add = false;
    if(contenu != null && !contenu.equals("")) {
        session.setAttribute("contenu", contenu);
        add = true;
    }
    if (titre != null && !titre.equals("")) {
        session.setAttribute("titre", titre);
        add = true;
    }


    billet.setContenu((String) session.getAttribute("contenu"));
    billet.setTitre((String) session.getAttribute("titre"));
    billet.setAuteur((String) session.getAttribute("pseudo"));
    billet.addcomments(request.getParameter("commentaire"));

    if (add) {
        gestion.add(billet);
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

    for(String m : billet.getCommentaires()) {
        out.println("<p>" + m + "</p>");
    }

out.println("<h3> print gestion billet </h3>");
for (Billet b : gestion.getBillets()){
    out.println("<p>" +b.getAuteur()+ " : " +b.getTitre() +" : " + b.getContenu() + " taille : " + gestion.getBillets().size()+ " </p>");

}



%>

<p><a href="saisie.html">Saisir un nouveau billet</a></p>
<p><a href="Deco">Se déconnecter</a></p>

</body>
</html>

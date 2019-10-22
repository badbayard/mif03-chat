<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="fr.univlyon1.m1if.m1if03.classes.Billet" %>
<%@ page import="fr.univlyon1.m1if.m1if03.classes.GestionBillets" %>
<%@ page import="fr.univlyon1.m1if.m1if03.classes.Message" %>
<%! private Billet billet = new Billet();
    private static GestionBillets gestion = new GestionBillets();
    private  String groupe ;
%>

<%
        groupe = (String) session.getAttribute("groupe");
        gestion.addgroupe(groupe);

    //response.setIntHeader("Refresh", 5);
    if(session.getAttribute("pseudo") == null) {
        session.invalidate();
        response.sendRedirect("index.html");
        return;
    }

    if (request.getMethod().equals("POST")) {
    String contenu  = request.getParameter("contenu");
    String titre  = request.getParameter("titre");
    String commentaire = request.getParameter("commentaire");
    boolean add = false;
    if (titre != null && !titre.equals("") || contenu != null && !contenu.equals("")) {
        billet = new Billet();
        if(billet.getTitre().equals("Rien")) {
            billet.setTitre(titre);
        }
        if(billet.getContenu().equals("Vide")){
            billet.setContenu(contenu);
        }
        add = true;
    }

    if(billet.getAuteur().equals("Personne")) {
        billet.setAuteur((String) session.getAttribute("pseudo"));
    }


    if(commentaire != null  && !commentaire.equals("")) {
            billet.addComments((String) session.getAttribute("pseudo") , commentaire);
    }

    if (add) {
        Billet b2 = new Billet(billet.getTitre(), billet.getContenu() , billet.getAuteur() , billet.getCommentaires());
        gestion.addbillet(b2,groupe);
    }


} else if(request.getParameter("menu") != null) {
        int indice = Integer.parseInt(request.getParameter("menu"));
        billet = gestion.getBillet(groupe, indice);
    }
%>
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

<%
if(gestion.getBillets(groupe).size() > 0) {
    out.println("<form action='billet.jsp'>");
    out.println("<select name='menu'>");

    for (int i = 0; i < gestion.getBillets(groupe).size(); i++) {
        Billet b = gestion.getBillet(groupe, i);
        out.println("<option value=" + i + ">" + b.getTitre() + " </option>");
    }

    out.println("</select>");
    out.println("<input type='submit' Value='Get'/>");
    out.println("</form>");
}
%>

<% out.println("<p>" + groupe + "</p>"); %>

<p><a href="saisie.html">Saisir un nouveau billet</a></p>
<p><a href="Deco">Se déconnecter</a></p>

</body>
</html>



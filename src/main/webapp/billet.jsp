<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="fr.univlyon1.m1if.m1if03.classes.Billet" %>
<%@ page import="fr.univlyon1.m1if.m1if03.classes.GestionBillets" %>
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
    if (titre != null && !titre.equals("")) {
        billet.setTitre(titre);
        add = true;
    }
    if(contenu != null && !contenu.equals("")) {
        billet.setContenu(contenu);
        add = true;
    }

    billet.setAuteur((String) session.getAttribute("pseudo"));

    if(commentaire != null  && !commentaire.equals("")) {
            billet.addcomments(commentaire);
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

for(String m : billet.getCommentaires()) {
   out.println("<p>" + m + "</p>");
}

out.println("<div  class= 'vertical-menu' >");
for (int i = 0 ; i < gestion.getBillets().size() ; i++){
    Billet b = gestion.getBillet(i);
    if(b.getTitre().equals(billet.getTitre())) {
        out.println(" <a href='billet.jsp' value =" + i +" class='active'>" + b.getTitre() +"</a>");
    } else {
        out.println(" <a href='billet.jsp' value =" + i +">" + b.getTitre() +"</a>");
    }

}
out.println("</div>");

%>






<p><a href="saisie.html">Saisir un nouveau billet</a></p>
<p><a href="Deco">Se déconnecter</a></p>

</body>
</html>


<style>
    .vertical-menu {
        width: 200px;
        height: 150px;
        overflow-y: auto;
    }

    .vertical-menu a {
        background-color: #eee; /* Grey background color */
        color: black; /* Black text color */
        display: block; /* Make the links appear below each other */
        padding: 12px; /* Add some padding */
        text-decoration: none; /* Remove underline from links */
    }

    .vertical-menu a:hover {
        background-color: #ccc; /* Dark grey background on mouse-over */
    }

    .vertical-menu a.active {
        background-color: #4CAF50; /* Add a green color to the "active/current" link */
        color: white;
    }
</style>

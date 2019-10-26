<jsp:useBean id="billet" scope="request" class="fr.univlyon1.m1if.m1if03.classes.Billet"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="fr.univlyon1.m1if.m1if03.classes.Billet" %>
<%! //private Billet billet = new Billet(); %>
<% if (request.getMethod().equals("POST")) {
    //billet.setContenu(request.getParameter("contenu"));
    //billet.setTitre(request.getParameter("titre"));
    //billet.setAuteur((String) session.getAttribute("pseudo"));
} %>
<!doctype html>
<html>
<jsp:include page="WEB-INF/header.jsp" ></jsp:include>
<body>
<p>Ceci est un billet de ${billet.auteur}</p>
<h1>${billet.titre}</h1>
<div class="contenu">${billet.contenu}</div>
<hr>
<form method="post" action="Commentaire">
    <p>
        Commentaire :
        <input type="text" name="commentaire">
        <input type="submit" value="Envoyer">
    </p>
</form>

<c:forEach items = "${billet.commentaires}" var = "com" >
    <b><c:out value="${com.pseudo}" /></b> : <c:out value="${com.message}"></c:out>
        <br/><br/>
</c:forEach>


<p><a href="saisie.html">Saisir un nouveau billet</a></p>
<p><a href="Deco">Se déconnecter</a></p>

</body>
</html>

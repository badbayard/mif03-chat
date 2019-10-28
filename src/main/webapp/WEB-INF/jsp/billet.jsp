<jsp:useBean id="billet" scope="request" class="fr.univlyon1.m1if.m1if03.classes.Billet"/>
<jsp:useBean id="billets" scope="request" class="fr.univlyon1.m1if.m1if03.classes.Billets"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<!doctype html>
<html>
<jsp:include page="header.jsp" ></jsp:include>
<body>
<p>Ceci est un billet de ${billet.auteur}</p>
<h1>${billet.titre}</h1>
<div class="contenu">${billet.contenu}</div>
<hr>
<form method="post" action="Commentaire.do">
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


<form method="post" action = "Menu.do" >
    <select id="idMenu" name="menu">
        <c:forEach items = "${billets.billets}" var = "b" >
            <option value="${billets.position(b)}">${b.titre}</option>
        </c:forEach>
    </select>
    <input type = "submit" value="Get"/>
</form>

<%out.println("<p>"+(String) session.getAttribute("groupe")+"</p>");%> <!-- juste pour le test -->

<p><a href="saisie.html">Saisir un nouveau billet</a></p>
<p><a href="Deco.do">Se déconnecter</a></p>

</body>
</html>

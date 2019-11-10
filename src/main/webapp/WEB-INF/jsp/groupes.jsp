<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<jsp:useBean id="groupes" scope="request" class="fr.univlyon1.m1if.m1if03.classes.Groupes"/>
<!doctype html>
<html>
<body>

<form method="post" action="Users">
    <p>
        Entrez votre pseudo :
        <input type="text" name="pseudo">
    </p>

    <select id="idMenuGroupe" name="menuGroupe">
        <c:forEach items = "${groupes.groupes}" var = "b" >
            <option value="${groupes.position(b)}">${ b.nom }</option>
        </c:forEach>
    </select>

    <input type="submit" value="Connexion">
</form>

<p><a href="Deco.do">Se déconnecter</a></p>

</body>
</html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>


<!doctype html>
<html>
<jsp:include page="header.jsp" ></jsp:include>
<body>
<h2>Hello <%= session.getAttribute("pseudo") %> !</h2>

<p><a href="saisie.html">Saisir un nouveau billet</a></p>
<p><a href="Deco">Se déconnecter</a></p>

</body>
</html>
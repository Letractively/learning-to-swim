<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>

<jsp:include page="header.jsp">
	<jsp:param name="title" value="Gestione abilit&agrave;"/>
</jsp:include>


<p>In questa pagina puoi modificare l'elenco delle tue abilità!</p>

<form action="gestiscicompetenze" method="post">
 
Which buildings do you want access to?<br />
<input type="checkbox" name="ability" value="1" checked="true"/>Acorn Building<br />
<input type="checkbox" name="ability" value="2" />Brown Hall<br />
<input type="checkbox" name="ability" value="3" />Carnegie Complex<br />
<input type="checkbox" name="ability" value="4" />Drake Commons<br />
<input type="checkbox" name="ability" value="5" />Elliot House
 
<input type="submit" value="Invia" />
 
</form>

<jsp:include page="footer.jsp" />
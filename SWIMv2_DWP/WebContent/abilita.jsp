<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<jsp:include page="header.jsp">
	<jsp:param name="title" value="Gestione Abilit&agrave;"/>
</jsp:include>

  <div class="content">
  <div style="width:80%;">
	<% 
		String alert = (String)session.getAttribute("alert");
		if(alert!=null){
	%>
		<p>
		<%=alert %>
		</p>
	<%
		session.removeAttribute("alert");} 
	%>
	<h1>Modifica delle abilit&agrave;</h1>
	<p>
		In questa pagina potrai inserire, eliminare e modificare il set di abilit&agrave; associato al tuo profilo
	</p>
	<form action="gestiscicompetenze" method="post">
 
		Which buildings do you want access to?<br />
		<input type="checkbox" name="ability" value="1" checked="true"/>Acorn Building<br />
		<input type="checkbox" name="ability" value="2" />Brown Hall<br />
		<input type="checkbox" name="ability" value="3" />Carnegie Complex<br />
		<input type="checkbox" name="ability" value="4" />Drake Commons<br />
		<input type="checkbox" name="ability" value="5" />Elliot House
		 
		<input type="submit" value="Invia" />
		 
	</form>
	
	<p>Se hai bisogno di ulteriore aiuto puoi contattarci all'email
		help@swim.net</p>
		</div>
    <!-- end .content --></div>

<jsp:include page="footer.jsp" />

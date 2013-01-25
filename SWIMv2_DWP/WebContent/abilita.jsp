<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<jsp:include page="header.jsp">
	<jsp:param name="ability" value="Gestione Abilit&agrave;"/>
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
 
		
		<input type="checkbox" name="ability" value="1" />Ingegnere Informatico<br />
		
		 
		<input type="submit" value="Invia" />
		 
	</form>
	
	<p>Se hai bisogno di ulteriore aiuto puoi contattarci all'email
		help@swim.net</p>
		</div>
    <!-- end .content --></div>

<jsp:include page="footer.jsp" />

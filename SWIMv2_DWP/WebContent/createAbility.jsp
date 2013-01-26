<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ page import="it.polimi.SWIMv2.EntityBeans.Ability" %>
<%@ page import="java.util.*" %>

<jsp:include page="header.jsp">
	<jsp:param name="page" value="ability"/>
</jsp:include>

  <div class="content">
  <div style="width:80%;">
	<% 
		String alert = (String)session.getAttribute("alert");
		if(alert!=null) {
			out.print("<p>" + alert + "</p>");
			session.removeAttribute("alert");
		} 
	%>
	<h1>Modifica delle abilit&agrave;</h1>
	<p>
		In questa pagina potrai inserire, eliminare e modificare il set di abilit&agrave; associato al tuo profilo
	</p>
	<h1>Crea Abilita'</h1>

	<p>Per creare un abilita' riempire i campi sottostanti. <br />
	Al termine, cliccare sul pulsante Crea.</p>
	
	<form action="createability" method="post">
		<table cellspacing="5" border="0">
		<tr>
		  <td align="right">Nome abilita':</td>
		  <td><input type="text" name="abilityName" /></td>
		</tr>
		<tr>
		  <td align="right">Descrizione:</td>
		  <td><input type="text" name="abilityDescription" /></td>
		</tr>
		<tr>
		  <td></td>
		  <td><input type="submit" value="Crea" /></td>
		</tr>
		</table>
	</form>
	
	<p>Se hai bisogno di ulteriore aiuto puoi contattarci all'email
		help@swim.net</p>
		</div>
    <!-- end .content --></div>

<jsp:include page="footer.jsp" />
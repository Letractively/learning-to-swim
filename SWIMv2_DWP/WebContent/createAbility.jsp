<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ page import="it.polimi.SWIMv2.EntityBeans.Ability" %>
<%@ page import="java.util.*" %>

<jsp:include page="header.jsp">
	<jsp:param name="page" value="ability"/>
</jsp:include>

	<% 
		String alert = (String)session.getAttribute("alert");
		if(alert!=null) {
			if(alert.equals("Abilita' creata con successo!")){
				%>
				<div align="center">
				<img alt="" src="images/goro.jpg">
				</div>
				<%
			}
			else if(alert.equals("Esiste gia' un' abilita' con questo nome !")){
				%>
				<div align="center">
				<img alt="" src="images/bulma.jpg">
				</div>
				<%
			}
			//out.print("<p>" + alert + "</p>");
			session.removeAttribute("alert");
		} 
	%>

  <div class="content">
  <div style="width:80%;">
	
	<h1>Modifica delle abilit&agrave;</h1>
	<p>
		In questa pagina potrai inserire, eliminare e modificare il set di abilit&agrave; associato al tuo profilo
	</p>
	<h1>Crea Abilit&agrave;</h1>

	<p>Per creare un abilit&agrave; riempire i campi sottostanti. <br />
	Al termine, cliccare sul pulsante Crea.</p>
	
	<form action="createability" method="post" style="padding-left:9px;">
		<table cellspacing="5" border="0">
		<tr>
		  <td>Nome abilit&agrave;:</td>
		  <td><input type="text" name="abilityName" /></td>
		</tr>
		<tr>
		  <td>Descrizione:</td>
		  <td><input type="text" name="abilityDescription" /></td>
		</tr>
		</table>
		<button class="likeConfirm" type="submit" style="float:right;">Crea</button>
	</form>
	
	<p style="clear:both;">Se hai bisogno di ulteriore aiuto puoi contattarci all'email
		help@swim.net</p>
		</div>
    <!-- end .content --></div>

<jsp:include page="footer.jsp" />
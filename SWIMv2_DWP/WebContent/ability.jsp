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
	<form action="manageabilities" method="post">
	<p><b>Le tue competenze</b><br />
		
		<%
		List<String> lstUserAbilities = (List<String>)request.getAttribute("userabilities");
		for (String ability : lstUserAbilities) {
			out.print("<input name='ability' type='checkbox' value='" + ability.split("\t")[0] + "' checked='true'/>" + ability.split("\t")[1]);
		}
		%>		
		
		<b>Le abilit&agrave; disponibili</b>
		
		<%
		List<String> lstAddAbilities = (List<String>)request.getAttribute("addabilities");
		for (String ability : lstAddAbilities) {
			out.print("<input name='ability' type='checkbox' value='" + ability.split("\t")[0] + "' checked='false'/>" + ability.split("\t")[1]);
		}
		%>

		<button id="abilityButton" type="submit">Conferma</button>
	</p></form>
	
	<p>Se hai bisogno di ulteriore aiuto puoi contattarci all'email
		help@swim.net</p>
		</div>
    <!-- end .content --></div>

<jsp:include page="footer.jsp" />

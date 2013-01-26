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
	<h1>Cerca</h1>
	<p>Per nome utente</p>
	<form action="searchbyname" method="post"><p>
		<b>Inserisci Nome</b> <input type="text" name="nome"><br/>
		<b>Inserisci Cognome</b> <input type="text" name="cognome"><br/>
		<input type="submit" value="Cerca">
		</p>
	</form>
	
	<p>Per abilit&agrave;</p>
	<form action="searchbyability" method="post"><p>
		<%
		List<String> lstAbilities = (List<String>)request.getSession().getAttribute("totalabilities");
		for (String ability : lstAbilities) {
			out.print("<input name='ability' type='radio' value='" + ability.split("\t")[0] + "'/>" + ability.split("\t")[1] + "<br/>");
		}
		%>
		<input type="submit" value="Cerca">
		</p>
	</form>
	
	<p>Se hai bisogno di ulteriore aiuto puoi contattarci all'email
		help@swim.net</p>
		</div>
    <!-- end .content --></div>

<jsp:include page="footer.jsp" />
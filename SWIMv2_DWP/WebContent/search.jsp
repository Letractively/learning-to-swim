<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ page import="it.polimi.SWIMv2.EntityBeans.Ability" %>
<%@ page import="java.util.*" %>

<jsp:include page="header.jsp">
	<jsp:param name="page" value="search"/>
</jsp:include>

	<% 
		String alert = (String)session.getAttribute("alert");
		if(alert!=null) {
			if(alert.equals("Nessun risultato trovato.")){
				%>
				<div align="center">
				<img alt="" src="images/conan.jpeg">
				</div>
				<%
			}
			//out.print("<p>" + alert + "</p>");
			session.removeAttribute("alert");
		} 
	%>

  <div class="content">
  <div style="width:80%;">
	
	<h1>Cerca</h1>
	<fieldset>
		<legend>Per nome utente</legend>
		<form action="searchbyname" method="post"><p>
			<table>
				<tr>
					<td><b>Nome:</b></td>
					<td><input type="text" name="nome"/></td>
				</tr>
				<tr>
					<td><b>Cognome:</b></td>
					<td><input type="text" name="cognome"/></td>
				</tr>
			</table>
			<br/>
			<p>
				<input style="float:right;" class="likeConfirm" type="submit" value="Cerca">
			</p>
		</form>	
	</fieldset>
		
	<fieldset>
		<legend>Per abilit&agrave;</legend>
		<form action="searchbyability" method="post"><p>
			<%
			List<String> lstAbilities = (List<String>)request.getSession().getAttribute("totalabilities");
			for (String ability : lstAbilities) {
				out.print("<input name='ability' type='radio' value='" + ability.split("\t")[0] + "'/>" + ability.split("\t")[1] + "<br/>");
			}
			%>
			<br/><input style="float:right;" class="likeConfirm" type="submit" value="Cerca">
			</p>
		</form>
	</fieldset>

	
	<p>Se hai bisogno di ulteriore aiuto puoi contattarci all'email
		help@swim.net</p>
		</div>
    <!-- end .content --></div>

<jsp:include page="footer.jsp" />
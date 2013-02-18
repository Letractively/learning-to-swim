<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ page import="it.polimi.SWIMv2.EntityBeans.Ability" %>
<%@ page import="java.util.*" %>

<jsp:include page="header.jsp">
	<jsp:param name="page" value="search"/>
</jsp:include>

<<<<<<< .mine
  <div class="content">
  
	<div style="float:right;width:40%;min-width:250px;margin-top:50px">
		<img src="images/Help.jpg" style="width:100%;height:100%;box-shadow: 0px 0px 3px 2px rgb(204, 204, 204);border-radius: 5px 5px 5px 5px;"/>
	</div>
  
  <div style="width:60%;min-width:200px;max-width:550px;margin:0px">
=======
>>>>>>> .r291
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
		<form action="searchbyname" method="post">	
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
			<p>
				<input style="float:right;" class="likeConfirm" type="submit" value="Cerca">
			</p>
		</form>	
	</fieldset>
	<br/>
	<fieldset>
		<legend>Per abilit&agrave;</legend>
		<form action="searchbyability" method="post">
			<p>
				<%
				List<String> lstAbilities = (List<String>)request.getSession().getAttribute("totalabilities");
				for (String ability : lstAbilities) {
					out.print("<input name='ability' type='radio' value='" + ability.split("\t")[0] + "'/>" + ability.split("\t")[1] + "<br/>");
				}
				%>
				<input style="float:right;" class="likeConfirm" type="submit" value="Cerca">
			</p>
		</form>
	</fieldset>

	
	<p>Se hai bisogno di ulteriore aiuto puoi contattarci all'email
		help@swim.net</p>
		</div>
    <!-- end .content --></div>

<jsp:include page="footer.jsp" />
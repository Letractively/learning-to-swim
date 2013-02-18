<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ page import="it.polimi.SWIMv2.EntityBeans.Ability" %>
<%@ page import="java.util.*" %>

<jsp:include page="header.jsp">
	<jsp:param name="page" value="ability"/>
</jsp:include>

<<<<<<< .mine
  <div class="content">
<div style="float:right;width:38%;min-width:250px;margin-top:10px">
	<img src="images/abilita.jpg" style="width:100%;height:100%;box-shadow: 0px 0px 3px 2px rgb(204, 204, 204);border-radius: 5px 5px 5px 5px;"/>
</div>
	  
<div style="width:57%;min-width:200px;max-width:550px;margin:0px">
=======
>>>>>>> .r291
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
			else if(alert.equals("Abilita' aggiunta!")){
				%>
				<div align="center">
				<img alt="" src="images/goku.jpg">
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
	

		
	<form action="manageabilities" method="post"><p>
		<table cellspacing="5">
			<tr>
				<td style="padding:10px;">
					<fieldset style="min-width:200px">
					<legend>Le tue competenze</legend>
						<%
							List<String> lstUserAbilities = (List<String>)request.getSession().getAttribute("userabilities");
							if (lstUserAbilities.size() == 0) {
								out.print("Sei un incompetente :D");
							}
							else {
								for (String ability : lstUserAbilities) {
									out.print("<input type='checkbox' value='" + ability.split("\t")[0] + "' checked='checked'/>" + ability.split("\t")[1] + "<br/>");
								}
							}
						%>	
					</fieldset>
				</td>
				<td style="padding:10px;">
					<fieldset style="min-width:200px">
					<legend>Le abilit&agrave; disponibili</legend>
					<%
						List<String> lstAddAbilities = (List<String>)request.getSession().getAttribute("addabilities");
						if (lstAddAbilities.size() == 0) {
							out.print("Sei onniscente!?!? :O");
						}
						else {
							for (String ability : lstAddAbilities) {
								out.print("<input name='ability' type='checkbox' value='" + ability.split("\t")[0] + "'/>" + ability.split("\t")[1] + "<br/>");
							}
						}
					%>
					</fieldset>
				</td>
			</tr>
		</table>
		<br/>
		<p>
			<button class="likeConfirm" id="abilityButton" type="submit" style="float:right;">Conferma</button>
		</p>
	</form>

	<%
		if (request.getSession().getAttribute("type").equals("amministratore")) {
			out.print("<p><a href='createAbility.jsp'>Clicca qui</a> per creare una nuova abilit&agrave;</p>");
		}
	%>
	
	<p>Se hai bisogno di ulteriore aiuto puoi contattarci all'email
		help@swim.net</p>
		</div>
    <!-- end .content --></div>

<jsp:include page="footer.jsp" />

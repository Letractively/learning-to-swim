<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<jsp:include page="header.jsp">
	<jsp:param name="title" value="Pagina principale"/>
</jsp:include>

<div class="content">
	<p>
		Benvenuti nella piattaforma SWIMv2!<br /> Qui potete cercare aiuto per
		le più svariate tematiche.
	</p>
	<h1>Get Start!</h1>
	<p>
		Sei nuovo e hai bisogno di aiuto? Sei nel posto giusto!<br /> Nel
		menù a destra trovi il tuo men&acute; personale dal quale puoi
		accedere a:<br />
		
	</p>
	
		<ul>
			<li>Le tue abilit&agrave; (aggiungerle, rimuoverle e
				modificarle)</li>
			<li>I tuoi feedback sull'aiuto ricevuto</li>
			<li>La gestione dei tuoi amici</li>
			<li>Le richieste di aiuto inviate e ricevute</li>
		</ul>
	
	<p>Se hai bisogno di ulteriore aiuto puoi contattarci all'email
		help@swim.net</p>
		
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
	
	<% 
		String confirmed = (String)session.getAttribute("alertconfirmed");
		if(confirmed!=null){
	%>
		<p>
		<%=confirmed %>
		</p>
		
	<%
		session.removeAttribute("alertconfirmed");} 
	%>


</div>

<jsp:include page="footer.jsp" />

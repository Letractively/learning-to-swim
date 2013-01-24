<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<jsp:include page="header.jsp">
	<jsp:param name="title" value="Pagina principale"/>
</jsp:include>



  <div class="content">
<div style="width:40%;float:right;text-align:center;"><h1>Registrazione</h1>
    <form name ="registration" id="regform" action="registration" method="post" onsubmit="return checkCoerence()">
      <div><b>Nome e Cognome</b></div>
      <div><input type="text" name="nome"  id="nome"><input type="text" name="cognome" id="cognome"></div>
      <div><b>Email</b></div>
      <div><input type="text" name="email"  id= "email" onblur="getxmlHttpRequest('DuplicateEmailServlet', 'registration', 'message', 'please wait...'); return false;"></div>
      <div><b>Password</b></div>
      <div><input type="password" name="password"  id="password"></div>
      <div><b>Conferma Password</b></div>
      <div><input type="password" name="cpassword"  id="cpassword"></div>
      <div><b>Citt&agrave;</b></div>
      <div><input type="text" name="city" id="city"></div>
      <div id="message"></div>
      <div><button type=”submit”>Registrati!</button></div>
      <% 
		  String loginError = (String)session.getAttribute("alertLogin");
		  if(loginError!=null){
	  %> 
		  <p>
		  <%=loginError %>
		  </p>
		
	  <%
		 session.removeAttribute("alertLogin");} 
	  %>
    </form>
</div>
  <div style="width:60%;">	<p>
		Benvenuti nella piattaforma SWIMv2!<br /> Qui potete cercare aiuto per
		le più svariate tematiche.
	</p>
	<h1>Get Started!</h1>
	<p>
		Sei nuovo e hai bisogno di aiuto? Sei nel posto giusto!<br /> Nel tuo
		men&uacute; personale potrai accedere a:<br />
		<ul>
			<li>Le tue abilit&agrave; (aggiungerle, rimuoverle e
				modificarle)</li>
			<li>I tuoi feedback sull'aiuto ricevuto</li>
			<li>La gestione dei tuoi amici</li>
			<li>Le richieste di aiuto inviate e ricevute</li>
		</ul>
	</p>
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
    <!-- end .content --></div>


<jsp:include page="footer.jsp" />

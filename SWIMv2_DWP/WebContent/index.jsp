<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<jsp:include page="header.jsp">
	<jsp:param name="title" value="Pagina principale"/>
</jsp:include>

  <div class="content">
<div style="width:40%;float:right;text-align:center;"><h1>Registrazione</h1>
    <form id="regform" action="registration" method="post">
      <div><input type="text" name="nome" value="Nome" onclick="this.value='';" onfocus="javascript: if (formfield.defaultValue==formfield.value)formfield.value = ''" onblur="this.value=!this.value?'Nome':this.value;"><input type="text" name="cognome" value="Cognome" onclick="this.value='';" onfocus="javascript: if (formfield.defaultValue==formfield.value)formfield.value = ''" onblur="this.value=!this.value?'Cognome':this.value;"></div>
      <div><input type="text" name="email" value="La tua Email" onclick="this.value='';" onfocus="javascript: if (formfield.defaultValue==formfield.value)formfield.value = ''" onblur="this.value=!this.value?'La tua Email':this.value;"></div>
      <div><input type="text" name="password" value="Password" onclick="this.value='';this.type='password'" onfocus="javascript: if (formfield.defaultValue==formfield.value)formfield.value = ''" onblur="if (!this.value) { this.value='Password'; this.type='text'; } else this.type='password'"></div>
      <div><input type="text" name="citta'" value="La tua Citt&agrave;" onclick="this.value='';" onfocus="javascript: if (formfield.defaultValue==formfield.value)formfield.value = ''" onblur="this.value=!this.value?'La tua Citt&agrave;':this.value;"></div>
      <div><button type=”submit”>Registrati!</button></div>
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
</div>

<jsp:include page="footer.jsp" />

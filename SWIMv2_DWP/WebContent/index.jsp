<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<jsp:include page="header.jsp">
	<jsp:param name="page" value="index"/>
</jsp:include>

  <div class="content">
<div style="width:40%;float:right;text-align:center;"><h1>Registrazione</h1>
    <form id="regform" name="registration" action="registration" method="post" onsubmit="return checkCoerence()">
      <div><input id="nome" type="text" name="nome" value="Nome" onclick="this.value='';" onfocus="javascript: if (formfield.defaultValue==formfield.value)formfield.value = ''" onblur="this.value=!this.value?'Nome':this.value;"><input id="cognome" type="text" name="cognome" value="Cognome" onclick="this.value='';" onfocus="javascript: if (formfield.defaultValue==formfield.value)formfield.value = ''" onblur="this.value=!this.value?'Cognome':this.value;"></div>
      <div><input id="email" type="text" name="email" value="La tua Email" onclick="this.value='';" onfocus="javascript: if (formfield.defaultValue==formfield.value)formfield.value = ''" onblur="this.value=!this.value?'La tua Email':this.value;getxmlHttpRequest('DuplicateEmailServlet','registration','message','please wait...');return false;"></div>
      <div><input id="password" type="text" name="password" value="Password" onclick="this.value='';this.type='password'" onfocus="javascript: if (formfield.defaultValue==formfield.value)formfield.value = ''" onblur="if (!this.value) { this.value='Password'; this.type='text'; } else this.type='password'"></div>
      <div><input id="cpassword" type="text" name="cpassword" value="Reinserisci la Password" onclick="this.value='';this.type='password'" onfocus="javascript: if (formfield.defaultValue==formfield.value)formfield.value = ''" onblur="if (!this.value) { this.value='Reinserisci la Password'; this.type='text'; } else this.type='password'"></div>
      <div><input id="city" type="text" name="citta'" value="La tua Citt&agrave;" onclick="this.value='';" onfocus="javascript: if (formfield.defaultValue==formfield.value)formfield.value = ''" onblur="this.value=!this.value?'La tua Citt&agrave;':this.value;"></div>
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
	String loginError = (String)session.getAttribute("alert");
	if(loginError!=null) {
		if(loginError.equals("Grazie per esserti registrato; riceverai presto una mail di attivazione account.")){
			%>
			<div align="center">
			<img alt="" src="images/junior.jpg">
			</div>
			<%
		}
		 //out.print("<p>" + loginError + "</p>");
		 session.removeAttribute("alert");
	} 
	
	loginError = (String)session.getAttribute("alertconfirmed");
	if(loginError!=null) {
		if(loginError.equals("Non puoi fare il login prima di aver confermato il tuo account")){
			%>
			<div align="center">
			<a href="images/drogba.jpg">
			<img alt="" src="images/ovrebo.jpg">
			</a>
			</div>
			<%
		}
		 //out.print("<p>" + loginError + "</p>");
		 session.removeAttribute("alertconfirmed");
	} 
	
	loginError = (String)session.getAttribute("alertLogin");
	if(loginError!=null) {
		if(loginError.equals("Login fallito, controlla meglio i dati che inserisci e riprova")){
			%>
			<div align="center">
			<img alt="" src="images/karatekid.jpg">
			</div>
			<%
		}
		 //out.print("<p>" + loginError + "</p>");
		 session.removeAttribute("alertLogin");
	} 
	%>
	
		</div>
    <!-- end .content --></div>

<jsp:include page="footer.jsp" />

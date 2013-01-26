<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ page import="it.polimi.SWIMv2.EntityBeans.GenericUser" %>
<%@ page import="java.util.*" %>

<jsp:include page="header.jsp">
	<jsp:param name="page" value="friendprofile"/>
</jsp:include>

  <div class="content">
  <div style="width:80%;">
	<% 
		String alert = (String)session.getAttribute("alert");
		if(alert!=null) {
			out.print("<p>" + alert + "</p>");
			session.removeAttribute("alert");
		}
		
		GenericUser u = (GenericUser)request.getSession().getAttribute("userData");
	%>
	<h1>La tua Messagebox!</h1>
	<p>
		Qui di seguito puoi visualizzare i messaggi inviati e ricevuti da e verso i tuoi amici 
	</p>
	<p id="outbox">
	<%
		out.print("<b>FROM:</b><br/>");
		out.print(nome + " " + cognome + " (" + email + ")<p class='dataMessage'>" + data + "</p><br/>");
		out.print(corpo);
	%>
	</p>
	<p id="inbox">
	<%
		out.print("<b>TO:</b><br/>");
		out.print("Nome: " + u.getFirstName() + " Cognome: " + u.getLastName() + "<br/>");
		out.print("Cognome: " + u.getLastName() + "<br/>");	
		out.print("Email: " + u.getEmail() + "<br/>");	
		out.print("Citt&agrave;: " + u.getCity() + "<br/>");
		out.print("Feedback: " + u.getFeedback() + "<br/>");
	%>
	</p>
	<p>Se hai bisogno di ulteriore aiuto puoi contattarci all'email
		help@swim.net</p>
		</div>
    <!-- end .content --></div>

<jsp:include page="footer.jsp" />


<!--!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Invio Email</title>
</head>
<body>
<h1>Invio Email</h1>

<p>Per inviare una email riempire i campi sottostanti. <br />
Al termine,cliccare sul pulsante Invia.</p>

<form action="MessageServlet" method="post">

<table cellspacing="5" border="0">

<tr>
  <td align="right">Indirizzo Destinatario:</td>
  <td><input type="text" name="receiverEmail" /></td>
</tr>
<tr>
  <td align="right">Oggetto:</td>
  <td><input type="text" name="oggetto" /></td>
</tr>
<tr>
  <td align="right">Testo:</td>
  <td><textarea name="body"></textarea></td>
</tr>
<tr>
  <td></td>
  <td><input type="submit" value="Invia" /></td>
</tr>
</table>
</form>
</body>
</html-->
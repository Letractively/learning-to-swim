<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ page import="it.polimi.SWIMv2.EntityBeans.GenericUser" %>
<%@ page import="java.util.*" %>

<jsp:include page="header.jsp">
	<jsp:param name="page" value="friendprofile"/>
</jsp:include>

<!-- 
  <div class="content">
<div style="float:right;width:38%;min-width:250px;margin-top:10px">
	<img src="images/mess.jpg" style="width:100%;height:100%;box-shadow: 0px 0px 3px 2px rgb(204, 204, 204);border-radius: 5px 5px 5px 5px;"/>
</div>
	  
<div style="width:57%;min-width:200px;max-width:550px;margin:0px">
-->
	<% 
		String alert = (String)session.getAttribute("alert");
		if(alert!=null) {
			if(alert.equals("Invio fallito: better luck next time!")){
				%>
				<div align="center">
				<img alt="" src="images/youcannotsend.jpg">
				</div>
				<%
			}
			else if(alert.equals("Invio riuscito!")){
				%>
				<div align="center">
				<img alt="" src="images/pegasus.jpeg">
				</div>
				<%
			}
			//out.print("<p>" + alert + "</p>");
			session.removeAttribute("alert");
		}
		else{
			%>
			  <div class="content">
			  <div style="float:right;width:38%;min-width:250px;margin-top:10px">
				<img src="images/mess.jpg" style="width:100%;height:100%;box-shadow: 0px 0px 3px 2px rgb(204, 204, 204);border-radius: 5px 5px 5px 5px;"/>
		   	  </div>
				  
			  <div style="width:57%;min-width:200px;max-width:550px;margin:0px">
			<%
		}
	%>
	<h1>La tua Messagebox!</h1>
	<p>
		Qui di seguito puoi visualizzare i messaggi inviati e ricevuti da e verso i tuoi amici
		<a href="sendmessage.jsp">Clicca qui</a> per inviare un nuovo messaggio
		<br/>
		<fieldset id="outbox">
			<legend>Outbox</legend>
			<%
				List<String> outbox = (List<String>)request.getSession().getAttribute("outbox");
				if (outbox != null) {
					if (outbox.size() == 0) {
						out.print("<p>Nessun messaggio inviato.</p>");
					}
					else {
						for (String message : outbox) {
							String[] details = message.split("\t");
							String nome = details[3];
							String cognome = details[4];
							String email = details[5];
							String data = details[6];
							String corpo = details[7];
							out.print("<p class='message' style='text-align:right;margin:0;'>" + data + "</p>");
							out.print("<p class='message'>A: " + nome + " " + cognome + " (" + email + ")");
							out.print("<br/>TESTO: " + corpo + "</p>");
						}
					}
				}
			%>
		</fieldset>
		<br/>
		<fieldset id="inbox">
			<form action='feedback' name='feedbackForm' method='post' style='display:inline;'>
			<legend>Inbox</legend>
			<%
				List<String> inbox = (List<String>)request.getSession().getAttribute("inbox");
				if (inbox != null) {
					if (outbox.size() == 0) {
						out.print("<p>Nessun messaggio inviato.</p>");
					}
					else {
						for (String message : inbox) {
							String[] details = message.split("\t");
							String nome = details[0];
							String cognome = details[1];
							String email = details[2];
							String data = details[6];
							String corpo = details[7];
							out.print("<p class='message' style='text-align:right;margin:0;'>" + data + "</p>");
							out.print("<p class='message'>DA: " + nome + " " + cognome + " (" + email + ")");
							if (!request.getSession().getAttribute("email").toString().equals(email))
								out.print("<input type='hidden' value='" + email + "' name='feedbackEmail'/><select name='feedbackValue' size='1' default='5' onchange='document.feedbackForm.submit();' style='float:right;'><option value='0'>1</option><option value='1'>2</option><option value='2'>3</option><option value='3'>4</option><option value='4'>5</option></select>");
							out.print("<br/>TESTO: " + corpo + "</p>");
						}
					}
				}
			%>
			</form>
		</fieldset>
		</form>
		<br/>
		<p>Se hai bisogno di ulteriore aiuto puoi contattarci all'email
			help@swim.net
		</p>
	</p>
</div>
    <!-- end .content --></div>

<jsp:include page="footer.jsp" />
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
	%>
	<h1>La tua Messagebox!</h1>
	<p>
		Qui di seguito puoi visualizzare i messaggi inviati e ricevuti da e verso i tuoi amici<br/>
		<a href="sendmessage.jsp">Clicca qui</a> per inviare un nuovo messaggio
	</p>
	<p id="outbox">
	<%
		out.print("<b>FROM:</b><br/>");
		List<String> outbox = (List<String>)request.getSession().getAttribute("outbox");
		if (outbox != null) {
			for (String message : outbox) {
				String[] details = message.split("\t");
				String nome = details[3];
				String cognome = details[4];
				String email = details[5];
				String data = details[6];
				String corpo = details[7];
				out.print("<p class='message'>TO: " + nome + " " + cognome + " (" + email + ")<p class='dataMessage'>" + data + "</p><br/>");
				out.print(corpo + "</p>");
			}
		}
	%>
	</p>
	<p id="inbox">
	<%
		out.print("<b>TO:</b><br/>");
		List<String> inbox = (List<String>)request.getSession().getAttribute("inbox");
		if (inbox != null) {
			for (String message : inbox) {
				String[] details = message.split("\t");
				String nome = details[0];
				String cognome = details[1];
				String email = details[2];
				String data = details[6];
				String corpo = details[7];
				out.print("<p class='message'>FROM: " + nome + " " + cognome + " (" + email + ")<p class='dataMessage'>" + data + "</p><br/>");
				out.print(corpo + "</p>");
			}
		}
	%>
	</p>
	<p>Se hai bisogno di ulteriore aiuto puoi contattarci all'email
		help@swim.net</p>
		</div>
    <!-- end .content --></div>

<jsp:include page="footer.jsp" />
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
		
		String friend = request.getSession().getAttribute("userData").toString();
		
		String nome = friend.split("\t")[0];
		String cognome = friend.split("\t")[1];
		String email = friend.split("\t")[2];
		String città = friend.split("\t")[3];
		String tipo = friend.split("\t")[4];
		String feedback = friend.split("\t")[5];
	%>
	<h1>Profilo di <%=nome %> <%=cognome %> </h1>
	<p>
		In questa pagina puoi vedere il profilo di ogni tuo singolo amico.
	</p>
	<%
		out.print("Nome: " + nome + "<br/>");
		out.print("Cognome: " + cognome + "<br/>");	
		out.print("Email: " + email + "<br/>");	
		out.print("Citt&agrave;: " + città + "<br/>");
		out.print("Tipo utente: " + tipo + "<br/>");
		out.print("Feedback: " + feedback + "<br/>");
	%>
	
	<p>Se hai bisogno di ulteriore aiuto puoi contattarci all'email
		help@swim.net</p>
		</div>
    <!-- end .content --></div>

<jsp:include page="footer.jsp" />
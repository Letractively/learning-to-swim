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
	<h1>Profilo di <%=u.getFirstName() %> <%=u.getLastName() %> </h1>
	<p>
		In questa pagina puoi vedere il profilo di ogni tuo singolo amico.
	</p>
	<%
		out.print("Nome: " + u.getFirstName() + "<br/>");
		out.print("Cognome: " + u.getLastName() + "<br/>");	
		out.print("Email: " + u.getEmail() + "<br/>");	
		out.print("Citt&agrave;: " + u.getCity() + "<br/>");
		out.print("Feedback: " + u.getFeedback() + "<br/>");
	%>
	
	<p>Se hai bisogno di ulteriore aiuto puoi contattarci all'email
		help@swim.net</p>
		</div>
    <!-- end .content --></div>

<jsp:include page="footer.jsp" />
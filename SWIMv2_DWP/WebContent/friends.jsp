<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ page import="it.polimi.SWIMv2.EntityBeans.Ability" %>
<%@ page import="java.util.*" %>

<jsp:include page="header.jsp">
	<jsp:param name="page" value="ability"/>
</jsp:include>

  <div class="content">
  <div style="width:80%;">
	<% 
		String alert = (String)session.getAttribute("alert");
		if(alert!=null) {
	%>
			<p>
				<%=alert %>
			</p>
	<%
			session.removeAttribute("alert");
		} 
	%>
	<h1>Elenco degli amici</h1>
	<p>
		In questa pagina puoi vedere tutti gli amici associati al tuo profilo
	</p>
	<%
		List<String> lstFriends = (List<String>)request.getSession().getAttribute("friends");
	    for(String friend: lstFriends){
	    	out.print("<p>" + friend + "</p>");
	    }
		
	%>
	
	<p>Se hai bisogno di ulteriore aiuto puoi contattarci all'email
		help@swim.net</p>
		</div>
    <!-- end .content --></div>

<jsp:include page="footer.jsp" />
<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ page import="it.polimi.SWIMv2.EntityBeans.Ability" %>
<%@ page import="java.util.*" %>

<jsp:include page="header.jsp">
	<jsp:param name="page" value="friends"/>
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
	<h1>Elenco degli amici</h1>
	<p>
		In questa pagina puoi vedere tutti gli amici associati al tuo profilo<br/>
		Clicca sul link associato al nome di ogni amico per accedere al suo profilo!
	</p>
	<form action="confirmfriendship" method="post"><p>
	<%
		List<String> lstFriends = (List<String>)request.getSession().getAttribute("friends");
	    for(String friend: lstFriends){
	    	out.print(friend + "<br/>");
	    }
	%>
	</p></form>
	<p>Numero totale di amici: <%=lstFriends.size() %></p>
	<p>Se hai bisogno di ulteriore aiuto puoi contattarci all'email
		help@swim.net</p>
		</div>
    <!-- end .content --></div>

<jsp:include page="footer.jsp" />
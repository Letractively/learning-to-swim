<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ page import="it.polimi.SWIMv2.EntityBeans.Ability" %>
<%@ page import="java.util.*" %>

<jsp:include page="header.jsp">
	<jsp:param name="page" value="friends"/>
</jsp:include>

  <div class="content">
  
<div style="float:right;width:38%;min-width:250px;margin-top:10px">
	<img src="images/amicizia1.jpeg" style="width:100%;height:100%;box-shadow: 0px 0px 3px 2px rgb(204, 204, 204);border-radius: 5px 5px 5px 5px;"/>
</div>
	  
<div style="width:57%;min-width:200px;max-width:550px;margin:0px">
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
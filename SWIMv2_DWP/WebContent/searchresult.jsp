<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ page import="it.polimi.SWIMv2.EntityBeans.Ability" %>
<%@ page import="java.util.*" %>

<jsp:include page="header.jsp">
	<jsp:param name="page" value="searchresult"/>
</jsp:include>

  <div class="content">
	<div style="float:right;width:38%;min-width:250px;margin-top:10px">
		<img src="images/lente.jpg" style="width:100%;height:100%;box-shadow: 0px 0px 3px 2px rgb(204, 204, 204);border-radius: 5px 5px 5px 5px;"/>
	</div>
		  
	<div style="width:57%;min-width:200px;max-width:550px;margin:0px">
	<% 
		String alert = (String)session.getAttribute("alert");
		if(alert!=null) {
			out.print("<p>" + alert + "</p>");
			session.removeAttribute("alert");
		} 
	%>
	<h1>Risultati della ricerca</h1>
	<form action="FriendshipRequestServlet" method="post">
		<p>
		<%
		List<String> lstResults = (List<String>)request.getSession().getAttribute("resultslist");
		for (String ability : lstResults) {
			if (ability.split("\t")[3].equals("0"))
				out.print(ability.split("\t")[0] + " " + ability.split("\t")[1] + " <button class='likeConfirm' type='submit'  name='directFriendEmail' value='" + ability.split("\t")[2] + "'>Aggiungi</button><br/>");
			else if (ability.split("\t")[3].equals("1"))
				out.print(ability.split("\t")[0] + " " + ability.split("\t")[1] + " - Amicizia in attesa di conferma<br/>");
			else
				out.print("<a href='friendprofile?email=" + ability.split("\t")[2] + "'>" + ability.split("\t")[0] + " " + ability.split("\t")[1] + "</a><br/>");
		}
		%>
		</p>
	</form>
	<p>Se hai bisogno di ulteriore aiuto puoi contattarci all'email
		help@swim.net</p>
		</div>
    <!-- end .content --></div>

<jsp:include page="footer.jsp" />
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
			out.print("<p>" + alert + "</p>");
			session.removeAttribute("alert");
		} 
	%>
	<h1>Risultati della ricerca</h1><br/>
	<p>
	<%
	List<String> lstResults = (List<String>)request.getSession().getAttribute("resultslist");
	for (String ability : lstResults) {
		out.print("<a href='friendprofile?email=" + ability.split("\t")[2] + "'>" + ability.split("\t")[0] + " " + ability.split("\t")[1] + "</a><br/>");
	}
	%>
	</p>
	
	<p>Se hai bisogno di ulteriore aiuto puoi contattarci all'email
		help@swim.net</p>
		</div>
    <!-- end .content --></div>

<jsp:include page="footer.jsp" />
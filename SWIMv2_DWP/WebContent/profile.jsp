<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<jsp:include page="header.jsp">
	<jsp:param name="page" value="profile"/>
</jsp:include>

	<p>
		<h1>Profilo di <%=session.getAttribute("nome") %> <%=session.getAttribute("cognome") %></h1>
		<p id="profile">
		<b>Benvenuto ecco i tuoi dati personali:</b>
		<div style="background-color:white;margin:30px;padding:10px;width:500px;height:200px;border-radius:7px;">
		<div style="width:150px;float:left;">
		<%
		if (session.getAttribute("type").toString().equals("amministratore")) {
			out.print("<img src='images/admin.jpg' width='150'/>");
		} else {
			out.print("<img src='images/user.jpg'  width='150'/>");
		}
		%>
		</div>
		<div style="text-align:left;">
		<b>Nome: <%=session.getAttribute("nome") %></b><br/>
    	<b>Cognome: <%=session.getAttribute("cognome") %></b><br/>
    	<b>Ruolo: <%=session.getAttribute("type") %></b><br/>
    	<b>Email: <%=session.getAttribute("email") %></b><br/>
    	<b>Citt&agrave;: <%=session.getAttribute("city") %></b><br/>
    	<b>Media Feedback: <%=session.getAttribute("avg") %></b></div>
    	</div>
    	</p>
    </p>

<jsp:include page="footer.jsp" />



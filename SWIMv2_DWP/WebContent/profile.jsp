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
		Nome: <%=session.getAttribute("nome") %><br/>
    	Cognome: <%=session.getAttribute("cognome") %><br/>
    	Ruolo: <%=session.getAttribute("type") %><br/>
    	Email: <%=session.getAttribute("email") %><br/>
    	Citt&agrave;: <%=session.getAttribute("city") %><br/>
    	Id: <%=session.getAttribute("id") %></div>
    	</div>
    	</p>
    </p>

<jsp:include page="footer.jsp" />



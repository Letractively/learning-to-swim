<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<jsp:include page="header.jsp">
	<jsp:param name="page" value="profile"/>
</jsp:include>

	<p>
		<h1>Profilo di <%=session.getAttribute("nome") %> <%=session.getAttribute("cognome") %></h1>
		<p id="profile">
		<b>Benvenuto ecco i tuoi dati personali:</b>
		<div class="profileDiv">
			<div style="width:150px;float:left;">
			<%
			String style = "";
			if (session.getAttribute("type").toString().equals("amministratore")) {
				style = "font-weight:bold;";
				out.print("<img src='images/admin.jpg' width='150'/>");
			} else {
				style = "color:#274DA2;font-weight:bold;";
				out.print("<img src='images/user.jpg' width='200'/>");
			}
			%>
			</div>
			<div style="text-align:left;">
				<span style="<%=style%>">Nome:</span> <%=session.getAttribute("nome") %><br/>
		    	<span style="<%=style%>">Cognome:</span> <%=session.getAttribute("cognome") %><br/>
		    	<span style="<%=style%>">Ruolo:</span> <%=session.getAttribute("type") %><br/>
		    	<span style="<%=style%>">Email:</span> <%=session.getAttribute("email") %><br/>
		    	<span style="<%=style%>">Citt&agrave;:</span> <%=session.getAttribute("city") %><br/>
		    	<span style="<%=style%>">Feedback:</span> <%=session.getAttribute("avg") %>
    		</div>
    	</div>
    	</p>
	</p>

<jsp:include page="footer.jsp" />



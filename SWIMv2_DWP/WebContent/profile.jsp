<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<jsp:include page="header.jsp">
	<jsp:param name="page" value="profile"/>
</jsp:include>

<div>

	<div style="float:right;width:38%;min-width:250px;margin-top:50px">
		<img src="images/agenda.jpg" style="width:100%;height:100%;box-shadow: 0px 0px 3px 2px rgb(204, 204, 204);border-radius: 5px 5px 5px 5px;"/>
	</div>
	  
	<div style="width:57%;min-width:200px;max-width:550px;margin:0px">
		<h1 style="white-space:nowrap;padding:0px">Profilo di <%=session.getAttribute("nome") %> <%=session.getAttribute("cognome") %></h1>
		<fieldset id="profile">
		<legend>Benvenuto ecco i tuoi dati personali:</legend>
		<div>
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
    	</fieldset>
	</div>

</div>

<jsp:include page="footer.jsp" />



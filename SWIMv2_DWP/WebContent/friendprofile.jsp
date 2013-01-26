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
		
		String friend = request.getSession().getAttribute("userData").toString();
		
		String nome = friend.split("\t")[0];
		String cognome = friend.split("\t")[1];
		String email = friend.split("\t")[2];
		String città = friend.split("\t")[3];
		String tipo = friend.split("\t")[4];
		String feedback = friend.split("\t")[5];
	%>
	<h1>Profilo di <%=nome %> <%=cognome %> </h1>
	<p>
		In questa pagina puoi vedere il profilo di ogni tuo singolo amico.
	</p>
	
	<div class="profileDiv">
		<div style="width:150px;float:left;">
		<%
		String style = "";
		if (tipo.equals("amministratore")) {
			style = "font-weight:bold;";
			out.print("<img src='images/admin.jpg' width='150'/>");
		} else {
			style = "color:#274DA2;font-weight:bold;";
			out.print("<img src='images/user.jpg' width='200'/>");
		}
		%>
		</div>
		<div style="text-align:left;">
			<span style="<%=style%>">Nome:</span> <%=nome%><br/>
	    	<span style="<%=style%>">Cognome:</span> <%=cognome%><br/>
	    	<span style="<%=style%>">Ruolo:</span> <%=tipo%><br/>
	    	<span style="<%=style%>">Email:</span> <%=email%><br/>
	    	<span style="<%=style%>">Citt&agrave;:</span> <%=città%><br/>
	    	<span style="<%=style%>">Feedback:</span> <%=feedback%>
   		</div>
   	</div>
	
	<div class="profileDiv">
	<form action="FriendshipRequestServlet" method="post"><p>
	<%
		out.print("Amici suggeriti<br/>");
		List<String> friends = (List<String>)request.getSession().getAttribute("hypoFriends");
		if (friends != null) {
			out.print("Non ci sono amici da suggerire.");
		}
		for(String f : friends) {
			String nomeH = f.split("\t")[0];
			String cognomeH = f.split("\t")[1];
			String emailH = f.split("\t")[2];
			out.print("<button class='likeConfirm' type='submit' name='friendEmail' value='" + emailH + "'>" + nomeH + " " + cognomeH + "</button><br/>");
		}		
	%>
	</p></form>
	</div>
	
	<p>Se hai bisogno di ulteriore aiuto puoi contattarci all'email
		help@swim.net</p>
		</div>
    <!-- end .content --></div>

<jsp:include page="footer.jsp" />
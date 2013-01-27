<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ page import="it.polimi.SWIMv2.EntityBeans.GenericUser" %>
<%@ page import="java.util.*" %>

<jsp:include page="header.jsp">
	<jsp:param name="page" value="sendmessage"/>
</jsp:include>

  <div class="content">
  <div style="width:600px;">
	<% 
		String alert = (String)session.getAttribute("alert");
		if(alert!=null) {
			out.print("<p>" + alert + "</p>");
			session.removeAttribute("alert");
		}
	%>
	<h1>Invio Email</h1>
	<p>Per inviare una email riempire i campi sottostanti. <br />
	Al termine,cliccare sul pulsante Invia.</p>
	
	<form action="MessageServlet" method="post">
			<table cellspacing="5" border="0" style="padding: 0px 10px;">
				<tr>
				  <td align="right">Email:</td>
				  <td><input type="text" name="receiverEmail" /></td>
				</tr>
				<tr>
				  <td align="right">Testo:</td>
				  <td><textarea name="body"></textarea></td>
				</tr>
			</table>
			<button class="likeConfirm" type="submit" style="float:right">Invia!</button>
	</form>
	<br/>
	<p>Se hai bisogno di ulteriore aiuto puoi contattarci all'email
		help@swim.net</p>
	</div>
    <!-- end .content --></div>

<jsp:include page="footer.jsp" />
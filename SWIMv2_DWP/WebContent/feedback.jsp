<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<jsp:include page="header.jsp">
	<jsp:param name="title" value="Gestione Abilit&agrave;"/>
</jsp:include>

  <div class="content">
  <div style="width:80%;">
	<% 
		String alert = (String)session.getAttribute("alert");
		if(alert!=null){
	%>
		<p>
		<%=alert %>
		</p>
	<%
		session.removeAttribute("alert");} 
	%>
	<h1>Feedback</h1>
	<p>
		In questa pagina potrai dare un feedback al tuo amico
	</p>
	<form action="daifeedback" method="post">
 
		
		<input type="checkbox" name="feedback" value="0" />zeroFeedback<br />
		<input type="checkbox" name="feedback" value="1" />oneFeedback<br />
		<input type="checkbox" name="feedback" value="2" />twoFeedback<br />
		<input type="checkbox" name="feedback" value="3" />threeFeedback<br />
		<input type="checkbox" name="feedback" value="4" />fourFeedback<br />
		 
		<input type="submit" value="Dai!" />
		 
	</form>
	
	<p>Se hai bisogno di ulteriore aiuto puoi contattarci all'email
		help@swim.net</p>
		</div>
    <!-- end .content --></div>

<jsp:include page="footer.jsp" />
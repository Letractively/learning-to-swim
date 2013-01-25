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
	%>
			<p>
				<%=alert %>
			</p>
	<%
			session.removeAttribute("alert");
		} 
	%>
	<h1>Modifica delle abilit&agrave;</h1>
	<p>
		In questa pagina potrai inserire, eliminare e modificare il set di abilit&agrave; associato al tuo profilo
	</p>
	<form action="gestiscicompetenze" method="post">
		Quali sono le tue competenze?<br />
		<%
		
		Map<Ability,Boolean> lstAbility = (Map<Ability,Boolean>)request.getAttribute("abilities");
		//out.print(lstAbility.isEmpty());
		/*Set<Ability> list  = lstAbility.keySet();
		Iterator<Ability> iter = list.iterator();
					
		while(iter.hasNext()) {
		     Ability a = iter.next();
		     Boolean b = lstAbility.get(a);
		     out.print("<input type='checkbox' name='ability' value='" + a.getId() + "' checked='" + b + "'/>" + a.getName() + "<br />");
		}*/
		
		%>

		<input type="submit" value="Invia" />
	</form>
	
	<p>Se hai bisogno di ulteriore aiuto puoi contattarci all'email
		help@swim.net</p>
		</div>
    <!-- end .content --></div>

<jsp:include page="footer.jsp" />

<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<jsp:include page="header.jsp">
	<jsp:param name="title" value="Profilo di <%= request.getSession().getAttribute("nome").toString() %> <%= request.getSession().getAttribute("cognome").toString() %>"/>
</jsp:include>

	<b>Benvenuto 
   		<%out.print(session.getAttribute("nome"));%>
   		<%out.print(session.getAttribute("cognome"));%>
    </b>
    <br/>
    <b>Io sono un 
   		<%out.print(session.getAttribute("type"));%>
    </b>
    
    <p>Sono loggato?
    <%=session.getAttribute("logged") %>
    </p>
    
    <div>
    <form name="logout" id="logout" method="post" action="logout">
    <button type="submit">Logout</button>
    </form>
    </div>

<jsp:include page="footer.jsp" />



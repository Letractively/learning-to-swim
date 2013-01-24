<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HomePage di <%out.print(session.getAttribute("nome"));%> <%out.print(session.getAttribute("cognome"));%></title>
</head>
<body>
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
</body>
</html>
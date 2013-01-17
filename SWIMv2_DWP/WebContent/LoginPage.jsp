<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Inserisci i tuoi dati</title>
</head>
<body>

<!-- INIZIO FORM PER IL LOGIN -->

<form action="login" method="post">
<b>E-mail</b>
<input type="text" name="email">
<b>Password</b>
<input type="text" name="password">
<input type="submit" value="Invia">
</form>

<!-- FINE FORM PER IL LOGIN -->

 <p>Sono loggato?
    <%=session.getAttribute("logged") %>
    </p>

</body>
</html>
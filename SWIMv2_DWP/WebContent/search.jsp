<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Pagina di Ricerca Utenti</title>
</head>
<body>

<p>Cerca per nome</p>
<form action="searchbyname" method="post">
<b>Inserisci Nome</b>
<input type="text" name="nome">
<br/>
<b>Inserisci Cognome</b>
<input type="text" name="cognome">
<br/>
<input type="submit" value="Invia">
</form>

<p>Cerca per abilit&agrave;</p>
<form action="searchbyability" method="post">
<b>Inserisci Abilit&agrave;</b>
<input type="text" name="nome">
<br/>
<input type="submit" value="Invia">
</form>

</body>
</html>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Invio Email</title>
</head>
<body>
<h1>Invio Email</h1>

<p>Per inviare una email riempire i campi sottostanti. <br />
Al termine,cliccare sul pulsante Invia.</p>

<form action="MailServlet" method="post">

<table cellspacing="5" border="0">
<tr>
  <td align="right">Indirizzo Mittente:</td>
  <td><input type="text" name="mittente" /></td>
</tr>
<tr>
  <td align="right">Indirizzo Destinatario:</td>
  <td><input type="text" name="destinatario" /></td>
</tr>
<tr>
  <td align="right">Oggetto:</td>
  <td><input type="text" name="oggetto" /></td>
</tr>
<tr>
  <td align="right">Testo:</td>
  <td><textarea name="contenuto"></textarea></td>
</tr>
<tr>
  <td></td>
  <td><input type="submit" value="Invia" /></td>
</tr>
</table>
</form>
</body>
</html>
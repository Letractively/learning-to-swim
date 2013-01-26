<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- TemplateBeginEditable name="doctitle" -->

<%
String pageType = request.getParameter("page").toString();
String title = "";
if (pageType.equals("index")) {
	title = "Pagina principale";
} else if (pageType.equals("profile")) {
	title = "Profilo di " + request.getSession().getAttribute("nome") + " " + request.getSession().getAttribute("cognome");;
} else if (pageType.equals("messages")) {
	title = "Messaggi";
} else if (pageType.equals("feedback")) {
	title = "Feedback";
} else if (pageType.equals("ability")) {
	title = "Gestione abilit&agrave;";
} else if (pageType.equals("friends")) {
	title = "Le mie amicizie";
} else if (pageType.equals("friendprofile")) {
	title = "Le mie amicizie - Dettaglio profilo";
} else if (pageType.equals("search")) {
	title = "Ricerca";
}
%>

<title>SWIMv2 - <%=title %></title>
<!-- TemplateEndEditable -->
<!-- TemplateBeginEditable name="head" -->
<!-- TemplateEndEditable -->
<link href="css/main.css" rel="stylesheet" type="text/css" />
<link  href="css/dropdown.css" rel="stylesheet" type="text/css" media="screen, projection"/>
	<!--[if lte IE 7]>
        <link rel="stylesheet" type="text/css" href="css/ie.css" media="screen" />
    <![endif]-->

<script type="text/javascript" src="js/jquery-1.3.1.min.js"></script>
<script type="text/javascript" src="js/jquery.dropdownPlain.js"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>

<!-- INIZIO JAVASCRIPT DI CONTROLLO SUI DATI IN INPUT -->

<script type="text/javascript" src="js/duplicateemailcheck.js"></script>
<script type="text/javascript" src="js/registrationformcoerence.js"></script>
<script type="text/javascript" src="js/loginformcoerence.js"></script>

<!-- FINE JAVASCRIPT DI CONTROLLO SUI DATI IN INPUT -->

</head>

<body>

<div class="container">
  <div id="header">
  <span class="headtitle">Small World hypothesIs Machine<span id="headap">2</span></span>
    <ul class="dropdown">
    	<li>
    	<%
    	boolean isLogged = false;
    	
    	try {
        	isLogged = request.isRequestedSessionIdValid() && request.getSession().getAttribute("logged").toString().equals("true");
    	}
    	catch (Exception e) { }
    	
    	if (isLogged)
    		out.write("<a href='#'><img src='images/ingranaggio.png'/></a><ul class='sub_menu'><li><a href='index.jsp'>Profilo</a></li><li><a href='friends'>Amici</a></li><li><a href='search'>Cerca</a></li><li><a href='ability'>Abilit&agrave;</a></li><li><a href='messagebox'>Messaggi</a></li><li><a href='logout'>Logout</a></li></ul>");
    	else
    		out.print("<img src='images/ingranaggio.png'/>");
    	%>
      </li>
    </ul>
    <!-- end .header --></div>
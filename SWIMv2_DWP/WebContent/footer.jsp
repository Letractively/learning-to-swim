  <div id="footer" style="clear:both;">
    <span id="foottile"><a href="index.jsp">SWIMv2</a></span><!-- end .footer --><img src="images/logo.png" alt="SWIM2" name="Insert_logo" width="50" height="50" id="logo" />
    <%
	boolean isLogged = false;
	
	try {
    	isLogged = request.isRequestedSessionIdValid() && request.getSession().getAttribute("logged").toString().equals("true");
	}
	catch (Exception e) { }
	
	if (!isLogged)
	{
		out.write("<span><form name='login' id='loginform' action='login' method='post' onsubmit='return allLoginFieldsAreFilled()'><b>E-mail</b> <input id='emailLogin' type='text' name='email' size='26'> <b>Password</b> <input id='passwordLogin' type='password' name='password'> <button type='submit'>Go!</button> </form></span>");
	}
	else
	{
		out.print("<form action='search'><button type='submit'>Cerca</button></form>");
	}
    %>
  </div>
  <!-- end .container --></div>
</body>
</html>
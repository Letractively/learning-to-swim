function getxmlHttpRequest(servletName,formname,responsediv,responsemsg) {
var xmlhttp = false;
var x = this;
// For the browsers Mozilla/Safari/Ie7
if (window.XMLHttpRequest) {
x.xmlhttp = new XMLHttpRequest();
}
// for the lower version of IE
else if (window.ActiveXObject) {
x.xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
}
x.xmlhttp.open("POST", servletName, true);
x.xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
x.xmlhttp.onreadystatechange = function() {
if (x.xmlhttp.readyState == 4)
{
updatepage(x.xmlhttp.responseText,responsediv);
}
else
{ 
updatepage(responsemsg,responsediv);

}
}
x.xmlhttp.send("email=" + getEmail(formname));
}


function getEmail(formname) {
//var form = document.forms[formname];
//var email = form.elements["email"].value;

//var email = document.forms[formname].elements['email'].value;

var email = document.getElementById("email");

return email.value;
}


function updatepage(str,responsediv){
document.getElementById(responsediv).innerHTML = str;
}
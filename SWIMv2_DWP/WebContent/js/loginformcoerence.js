function allLoginFieldsAreFilled(){
	with(document.login){
		if(emailLogin.value == "" || passwordLogin.value == ""){
			alert("Per loggarti devi riempire entrambi i campi");
			return false;
		}
		return true;
	}
}
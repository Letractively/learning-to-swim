function allFieldsAreFilled(){
	with(document.login){
		if(email.value == "" || password.value == ""){
			alert("Per loggarti devi riempire entrambi i campi");
			return false;
		}
		return true;
	}
}
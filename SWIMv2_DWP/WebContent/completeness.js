function check() {
			if(document.getElementById("nome").value == "" || document.getElementById("cognome").value == "" || document.getElementById("email").value == "" || document.getElementById("password").value == "" || document.getElementById("city").value == "") {
		  		alert("Prima di inviare i dati occorre riempire tutti i campi");
		 		return false;
			}
			return true;
		} 

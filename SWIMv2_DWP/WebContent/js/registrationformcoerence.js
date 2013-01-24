/**
 * Chiama due funzioni che controllano alcune condizioni sui dati della form di registrazione
 * 
 * @returns {Boolean} true se tutte le condizioni sono rispettate, false altrimenti
 */
function checkCoerence(){
	if(!allFieldsAreFilled()){
		return false;
	}
	if(!passwordEquality()){
		return false;
	}
	return true;
}

/**
 * Controlla se tutti i campi della registrazione sono stati riempiti
 * 
 * @returns {Boolean} true se non trova campi rimasti vuoti, false altrimenti
 */
function allFieldsAreFilled(){
	with(document.registration){
		if(nome.value == "" || cognome.value == "" || email.value == "" || password.value == "" || cpassword.value == "" || city.value == ""){
			alert("Prima di inviare i dati assicurati di aver riempito tutti i campi");
			return false;
		}
		return true;
	}
}

/**
 * Controlla l'equivalenza dei campi password e cpassword
 * 
 * @returns {Boolean} true se i campi coincidono, false altrimenti
 */
function passwordEquality(){
	with(document.registration){
		if(password.value != cpassword.value){
			alert("Le due password non coincidono");
			return false;
		}
		return true;
	}
}
package it.polimi.SWIMv2.Utilities;

public class EmailParser {
	
	private String email;
	private char[] emailArray;
	
	
	public EmailParser(String email){
		this.email = email;
		this.emailArray = email.toCharArray();
	}
	
	
	public boolean parse(){
		if(email == null){
			return false;
		}
		if(email.length() < 6){
			return false;
		}
		
		int i,j,k;
    	
    	i = email.indexOf('@');
    	j = email.lastIndexOf('@');
    	k = email.lastIndexOf('.');
    	
    	/**
    	 * i == -1 se e solo se la chiocciola non esiste
    	 */
    	if(i == -1){
    		return false;
    	}
    	
    	/**
    	 * i != j se e solo se esistono più chiocciole
    	 */
    	if(i != j){
    		return false;
    	}
    	
    	/**
    	 * l'ultimo carattere '.' di una mail deve trovarsi dopo la chiocciola e tra di loro ci deve essere almeno un altro carattere
    	 */
    	if(k >= j+1){
    		return false;
    	}
    	
    	/**
    	 * la chiocciola non può essere il primo carattere
    	 */
    	if(i == 1){
    		return false;
    	}
    	
    	int extension = email.length() - k;
    	
    	/**
    	 * l'ultimo punto deve essere seguito da almeno due caratteri che non siano un punto
    	 */
    	if(extension < 2){
    		return false;
    	}
    	
    	return startParsing();
	}


	private boolean startParsing() {
		int i;
		
		if(!controlFirstCharacter(emailArray[0])){
			return false;
		}
		
		for(i=0; emailArray[i]!='@'; i++){
			if(!controlNameAndProvider()){
				return false;
			}
		}
		
		
		return true;
	}


	private boolean controlNameAndProvider() {
		// TODO Auto-generated method stub
		return false;
	}


	private boolean controlFirstCharacter(char c) {
		// TODO Auto-generated method stub
		return false;
	}

}

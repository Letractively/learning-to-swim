package it.polimi.SWIMv2.Utilities;

import java.util.ArrayList;
import java.util.List;

/**
 * Questa classe è deputata ad effettuare il parsing dell'indirizzo email inserito dell'utente in fase di
 * registrazione per verificare che sia conforme al modello di un'indirizzo ben formato; in caso contrario
 * il session bean di registrazione solleverà un'eccezione (per maggiori dettagli vedere il  RegistrationBean.
 * 
 * Si assume che l'espressione regolare di un'indirizzo email ben formato sia:
 * 
 * detti x, X, n rispettivamente caratteri corrispondenti a una lettera minuscola, una maiuscola, un numero,
 * detti * e + rispettivamente l'operatore stella di Kleene e l'operatore croce,
 * detti . - _ @ gli unici altri caratteri ammessi in alcune parti di un indirizzo email ben formato,
 * detto | l'operatore or,
 * 
 * (x|X|n)+ ((.|-|_)(x|X|n)+)* @ (x|X|n)+ ((.|-|_)(x|X|n)+)* . (x|X) (x|X)+
 * 
 * Si noti che la scelta di far terminare un indirizzo ben formato con almeno due lettere è voluta
 * 
 * L'intera procedura di parsing simula il funzionamento di un automa a stati finiti (a qualcosa serve LFC!)
 * 
 * @author Emanuele Uliana
 *
 */
public class EmailParser {
	
	private char[] emailArray;
	private List<Character> emailList = new ArrayList<Character>();
	private List<Character> lowercaseLetters = new ArrayList<Character>();;
	private List<Character> uppercaseLetters = new ArrayList<Character>();;
	private List<Character> numbers = new ArrayList<Character>();;
	private List<Character> specialCharacters = new ArrayList<Character>();;
	
	/**
	 * Di fatto il costruttore agisce da scanner, ovvero tokenizza la stringa e "delimita" i possibili caratteri
	 * che può contenere.
	 * 
	 * @param email : la stringa da analizzare
	 */
	public EmailParser(String email){
		this.emailArray = email.toCharArray();
		createList();
		fillLowercaseLettersList();
		fillUppercaseLettersList();
		fillNumbersList();
		fillSpecialCharactersList();
	}
	
	
	private void createList() {
		for(int i=0; i<this.emailArray.length; i++){
			this.emailList.add(this.emailArray[i]);
		}
	}


	private void fillNumbersList() {
		for(Character i='0'; i<='9'; i++){
			this.numbers.add(i);
		}
	}


	private void fillSpecialCharactersList() {
		this.specialCharacters.add('.');
		this.specialCharacters.add('-');
		this.specialCharacters.add('_');
	}


	private void fillUppercaseLettersList() {
		for(Character i='A'; i<='Z'; i++){
			this.uppercaseLetters.add(i);
		}
		
	}


	private void fillLowercaseLettersList() {
		for(Character i='a'; i<='z'; i++){
			this.lowercaseLetters.add(i);
		}
		
	}

	
	public boolean parseEmail(){
		return parse0();
	}
	

	private boolean parse0(){
		if(!isFirstCharacterValid()){
			return false;
		}
		this.emailList.remove(0);
		return parse1();
		
	}


	private boolean isFirstCharacterValid() {
		if(this.emailList.isEmpty()){
			return false;
		}
		if(isLowercaseLetter(this.emailList.get(0)) || isUppercaseLetter(this.emailList.get(0)) || isNumber(this.emailList.get(0))){
			return true;
		}
		return false;
	}


	private boolean isNumber(Character c) {
		for(int i=0; i<this.numbers.size(); i++){
			if(c.equals(this.numbers.get(i))){
				return true;
			}
		}
		return false;
	}


	private boolean isUppercaseLetter(Character c) {
		for(int i=0; i<this.uppercaseLetters.size(); i++){
			if(c.equals(this.uppercaseLetters.get(i))){
				return true;
			}
		}
		return false;
	}


	private boolean isLowercaseLetter(Character c) {
		for(int i=0; i<this.lowercaseLetters.size(); i++){
			if(c.equals(this.lowercaseLetters.get(i))){
				return true;
			}
		}
		return false;
	}
	
	
	private boolean isSpecialCharacter(Character c) {
		for(int i=0; i<this.specialCharacters.size(); i++){
			if(c.equals(this.specialCharacters.get(i))){
				return true;
			}
		}
		return false;
	}


	private boolean parse1() {
		if(this.emailList.isEmpty()){
			return false;
		}
		if(isLowercaseLetter(this.emailList.get(0)) || isUppercaseLetter(this.emailList.get(0)) || isNumber(this.emailList.get(0))){
			this.emailList.remove(0);
			return parse1();
		}
		else if(isSpecialCharacter(this.emailList.get(0))){
			this.emailList.remove(0);
			return parse0();
		}
		else if(this.emailList.get(0).equals(new Character('@'))){
			this.emailList.remove(0);
			return parse2();
		}
		return false;
	}


	private boolean parse2() {
		if(this.emailList.isEmpty()){
			return false;
		}
		if(isLowercaseLetter(this.emailList.get(0)) || isUppercaseLetter(this.emailList.get(0)) || isNumber(this.emailList.get(0))){
			this.emailList.remove(0);
			return parse3();
		}
		return false;
	}


	private boolean parse3() {
		if(this.emailList.isEmpty()){
			return false;
		}
		if(isLowercaseLetter(this.emailList.get(0)) || isUppercaseLetter(this.emailList.get(0)) || isNumber(this.emailList.get(0))){
			this.emailList.remove(0);
			return parse3();
		}
		else if(isSpecialCharacter(this.emailList.get(0))){
			if(this.emailList.get(0).equals(new Character('-')) || this.emailList.get(0).equals(new Character('_'))){
				this.emailList.remove(0);
				return parse2();
			}
			else{
				this.emailList.remove(0);
				if(searchForAnotherDot()){
					return parse2();
				}
				return parse4();
			}
		}
		return false;
	}


	private boolean searchForAnotherDot() {
		if(this.emailList.isEmpty()){
			return false;
		}
		
		for(int i=0; i<this.emailList.size(); i++){
			if(this.emailList.get(i).equals(new Character('.'))){
				return true;
			}
		}
		return false;
	}


	private boolean parse4() {
		if(this.emailList.isEmpty()){
			return false;
		}
		if(isLowercaseLetter(this.emailList.get(0)) || isUppercaseLetter(this.emailList.get(0))){
			this.emailList.remove(0);
			return parse5();
		}
		return false;
	}


	private boolean parse5() {
		if(this.emailList.isEmpty()){
			return false;
		}
		if(isLowercaseLetter(this.emailList.get(0)) || isUppercaseLetter(this.emailList.get(0))){
			this.emailList.remove(0);
			return parse6();
		}
		return false;
	}


	private boolean parse6() {
		if(this.emailList.isEmpty()){
			return true;
		}
		if(isLowercaseLetter(this.emailList.get(0)) || isUppercaseLetter(this.emailList.get(0))){
			this.emailList.remove(0);
			return parse6();
		}
		return false;
	}
}

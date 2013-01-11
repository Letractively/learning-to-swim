package it.polimi.SWIMv2.Utilities;

import java.util.ArrayList;
import java.util.List;

public class EmailParser {
	
	private char[] emailArray;
	private List<Character> emailList = new ArrayList<Character>();
	private List<Character> lowercaseLetters = new ArrayList<Character>();;
	private List<Character> uppercaseLetters = new ArrayList<Character>();;
	private List<Character> numbers = new ArrayList<Character>();;
	private List<Character> specialCharacters = new ArrayList<Character>();;
	
	
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

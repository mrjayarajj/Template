package org.thoughtworks.sales.stage.people;

public class Passport {
	
	private PassportType passportType = null;
	
	private String passportNumber = null;
	
	public static void main(String[] args) {
		System.out.println(new Passport("123456ABCDOFTW").isDomestic());
	}

	public Passport(String passportNumber){
		
		this.passportNumber = passportNumber;
		
		if( isDomesticCase1(passportNumber) || isDomesticCase2(passportNumber) ){
			passportType = PassportType.DOMESTIC;
		}else{
			passportType = PassportType.INTERNATIONAL;
		}
	}
	
	public String getPassportNumber() {
		return passportNumber;
	}

	private boolean isDomesticCase1(String passportNumber){
		try{
			return passportNumber.startsWith("TW") && isNumeric(passportNumber.substring(2,14));
		}catch(StringIndexOutOfBoundsException e){
			return false;
		}
	}
	
	private boolean isDomesticCase2(String passportNumber ){
		String last4Digit = passportNumber.substring(10,passportNumber.length());
		return isNumeric(passportNumber.substring(0,6)) && last4Digit.endsWith("OFTW") ;
	}
	
	private boolean isNumeric(String number){
		try{
			Double.parseDouble(number);
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	
	public boolean isDomestic(){
		return passportType==PassportType.DOMESTIC;
	}
	
}

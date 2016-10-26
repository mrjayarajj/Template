package dto;

public class CreditCard extends PaymentService {

	private String creditCardNumber;

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public String toString() {
		return super.toString()+" "+"CC:>" + this.creditCardNumber;
	}

}

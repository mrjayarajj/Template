package dto;

public class OrderPayment {

	private Payment payment = null;

	private String transationType = null;

	private Integer transationId = null;

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setTransationId(Integer transationId) {
		this.transationId = transationId;
	}

	public Integer getTransationId() {
		return transationId;
	}

	public void setTransationType(String transationType) {
		this.transationType = transationType;
	}

	public String getTransationType() {
		return transationType;
	}
	
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

}

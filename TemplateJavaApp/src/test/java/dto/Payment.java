package dto;

import java.math.BigDecimal;

public class Payment {

	private long id;

	private BigDecimal amount;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String toString() {
		return "P:>" + getId() + "-" + getAmount();
	}

}

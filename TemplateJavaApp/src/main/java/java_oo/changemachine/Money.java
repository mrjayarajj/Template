package java_oo.changemachine;

import java.math.BigDecimal;

public class Money {

	private BigDecimal value;

	public Money(String value) {
		this.value = new BigDecimal(value);
	}

	public BigDecimal getValue() {
		return value;
	}

	public String toString() {
		return this.getValue().toString();
	}
}

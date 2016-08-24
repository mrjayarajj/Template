package java_oo.atm.learn;

import java.math.BigDecimal;

public class FundIO extends IO {

	private BigDecimal fundValue;

	public FundIO(){
		execute();
	}
	
	public BigDecimal getFundValue() {
		return fundValue;
	}

	public String getDisplayMessage() {
		StringBuffer sbf = new StringBuffer();
		sbf.append("------------------------");
		sbf.append("\n");
		sbf.append("Enter your fund value");
		sbf.append("\n");
		sbf.append("------------------------");
		return sbf.toString();
	}

	public String getErrorMessage() {
		StringBuffer sbf = new StringBuffer();
		sbf.append("------------------------");
		sbf.append("\n");
		sbf.append("Enter only Numberic");
		sbf.append("\n");
		sbf.append("------------------------");
		return sbf.toString();
	}

	public boolean validate(String command) {
		try {
			this.fundValue = new BigDecimal(command);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
